package net.cookiebrain.youneedbait.block.entity;

import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.item.custom.AbstractFishItem;
import net.cookiebrain.youneedbait.loot.BonusLoot;
import net.cookiebrain.youneedbait.loot.ModBonusLoot;
import net.cookiebrain.youneedbait.screen.FishCleaningStationScreenHandler;
import net.cookiebrain.youneedbait.util.ModTags;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FishCleaningStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory,ImplementedInventory, SidedInventory {
    private static final int FISH_SLOT = 0;
    private static final int FILETKNIFE_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int BONUS_SLOT = 3;
    private static final double KNIFE_DAMAGE_CHANCE = 0.25;

    private static final int[] TOP_SLOTS = new int[] { FISH_SLOT };           // Hoppers from top insert fish
    private static final int[] SIDE_SLOTS = new int[] { FISH_SLOT, FILETKNIFE_SLOT }; // Optional: allow knife/fish from sides
    private static final int[] BOTTOM_SLOTS = new int[] { OUTPUT_SLOT,BONUS_SLOT };      // Hoppers from bottom extract filets and bonus items

    private DefaultedList<ItemStack> items = DefaultedList.ofSize(4,ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 100;
    private final ModBonusLoot bonusLoot = new ModBonusLoot();

    public FishCleaningStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FISHCLEANINGSTATION_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            //Allows the variables to be synchronized via the screen handler
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> FishCleaningStationBlockEntity.this.progress;
                    case 1 -> FishCleaningStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> FishCleaningStationBlockEntity.this.progress = value;
                    case 1 -> FishCleaningStationBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                //Size of the variables, not the inventory
                return 2;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FishCleaningStationScreenHandler(syncId,playerInventory,this, propertyDelegate);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Fish Cleaning Station");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public ItemStack getStack(int i) {
        return i >= 0 && i < this.items.size() ? this.items.get(i) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStack(int slot, int count) {
        return ImplementedInventory.super.removeStack(slot, count);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ImplementedInventory.super.setStack(slot, stack);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,items);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt,items);
        super.readNbt(nbt);
    }

    public static void tick(World world, BlockPos pos, BlockState state, FishCleaningStationBlockEntity blockEntity) {
        if (world.isClient()) {
            return;
        }

        if (blockEntity.isOutputSlotEmptyOrReceivable() && blockEntity.hasRecipe()) {
            blockEntity.increaseCraftingProgress();
            blockEntity.markDirty();
            // setChanged(level, pPos, pState); // still not needed on Fabric

            if (blockEntity.hasProgressFinished()) {
                blockEntity.craftItem();
                // Bonus item CONFIG
                if (Math.random() < 0.1) { // 10% chance
                    blockEntity.getBonusItem();
                }
                blockEntity.reduceKnifeDurability();
                blockEntity.resetProgress();
            }
        } else {
            blockEntity.resetProgress();
        }
    }

    private void getBonusItem() {
        BonusLoot bl = bonusLoot.getLootTableByName("fishcleaning");
        ItemStack bonusItem = bl.selectRandomWeightedItem();
        if(this.getStack(BONUS_SLOT).isEmpty()){
            this.setStack(BONUS_SLOT,bonusItem);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        int filetsToProduce = getFiletCountForCurrentFish(); // Get calculated filet count

        this.removeStack(FISH_SLOT,1);
        if(this.getStack(OUTPUT_SLOT).isEmpty()){
            this.setStack(OUTPUT_SLOT, new ItemStack(ModItems.RAWFISHFILET, filetsToProduce));
        } else {
            this.getStack(OUTPUT_SLOT).increment(filetsToProduce);
        }
    }

    private void reduceKnifeDurability() {
        if (Math.random() >= KNIFE_DAMAGE_CHANCE) {
            return; // no damage this time
        }
        // existing logic:
        if (!this.getStack(FILETKNIFE_SLOT).isEmpty() && this.world instanceof ServerWorld serverWorld) {
            ItemStack knife = this.getStack(FILETKNIFE_SLOT);
            knife.damage(1, serverWorld.getRandom(), null);
        }
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        int requiredOutputSpace = getFiletCountForCurrentFish(); // Get calculated filet count
        return canInsertAmountIntoOutputSlot(requiredOutputSpace) // Check if enough space for all filets
                && canInsertItemIntoOutputSlot(ModItems.RAWFISHFILET)
                && hasRecipeItemInInputSlots();
    }

    private boolean hasRecipeItemInInputSlots() {
        return this.getStack(FISH_SLOT).isIn(ModTags.Items.CUSTOM_FISH) && this.getStack(FILETKNIFE_SLOT).isOf(ModItems.FILETKNIFE_ITEM);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).isOf(item) || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.getStack(OUTPUT_SLOT).getMaxCount() > this.getStack(OUTPUT_SLOT).getCount() + count;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    private int getFiletCountForCurrentFish() {
        ItemStack fishStack = this.getStack(FISH_SLOT);
        if (fishStack.isEmpty() || !(fishStack.getItem() instanceof AbstractFishItem fishItem)) {
            return 1; // Default to 1 if not a weighted fish or empty
        }

        double weight = fishItem.getWeightKg(fishStack);
        double minWeight = fishItem.getMinKg();
        double maxWeight = fishItem.getMaxKg();

        // Calculate normalized weight (0.0 to 1.0)
        double normalizedWeight = 0.0;
        if (maxWeight > minWeight) { // Avoid division by zero
            normalizedWeight = (weight - minWeight) / (maxWeight - minWeight);
        }
        normalizedWeight = Math.max(0.0, Math.min(1.0, normalizedWeight)); // Clamp between 0 and 1

        // Determine filets based on normalized tiers (max cap 6)
        int filets = 1;
        if (normalizedWeight >= 0.15) { // e.g., top 85% of weight range
            filets = 2;
        }
        if (normalizedWeight >= 0.35) { // e.g., top 65%
            filets = 3;
        }
        if (normalizedWeight >= 0.55) { // e.g., top 45%
            filets = 4;
        }
        if (normalizedWeight >= 0.75) { // e.g., top 25%
            filets = 5;
        }
        if (normalizedWeight >= 0.90) { // e.g., top 10%
            filets = 6;
        }

        return filets;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.UP) {
            return TOP_SLOTS;
        } else if (side == Direction.DOWN) {
            return BOTTOM_SLOTS;
        } else {
            return SIDE_SLOTS;
        }
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {

        if (slot == FISH_SLOT) {
            boolean result = stack.getItem() instanceof AbstractFishItem;
            return result;
        }

        if (slot == FILETKNIFE_SLOT) {
            boolean result = stack.getItem() == ModItems.FILETKNIFE_ITEM;
            return result;
        }

        return false;
    }
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        if (slot == OUTPUT_SLOT) {
            // Allow extracting filets
            return stack.getItem() == ModItems.RAWFISHFILET;
        }

        // Optional: allow extracting broken knives from bottom or sides if you want
        // if (slot == FILETKNIFE_SLOT && stack.getDamage() >= stack.getMaxDamage()) { ... }

        return false;
    }
}
