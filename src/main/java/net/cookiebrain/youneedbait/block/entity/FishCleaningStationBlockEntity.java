package net.cookiebrain.youneedbait.block.entity;

import net.cookiebrain.youneedbait.item.ModItems;
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
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FishCleaningStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory,ImplementedInventory {
    private static final int FISH_SLOT = 0;
    private static final int FILETKNIFE_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int BONUS_SLOT = 3;
    private ServerPlayerEntity player;

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
                    case 0: FishCleaningStationBlockEntity.this.progress = value;
                    case 1: FishCleaningStationBlockEntity.this.maxProgress = value;
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

    public void setPlayer(ServerPlayerEntity player){
        this.player = player;
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

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        if(isOutputSlotEmptyOrReceivable() && hasRecipe()){
            increaseCraftingProgress();
            markDirty();
            //setChanged(level,pPos,pState);

            if(hasProgressFinished()){
                craftItem();
                if (Math.random() < 0.1) { // 10% chance
                    getBonusItem();
                }
                reduceKnifeDurability();
                resetProgress();
            }
        } else {
            resetProgress();
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
        this.removeStack(FISH_SLOT,1);
        if(this.getStack(OUTPUT_SLOT).isEmpty()){
            this.setStack(OUTPUT_SLOT,new ItemStack(ModItems.RAWFISHFILET));
        } else {
            this.getStack(OUTPUT_SLOT).increment(1);
        }
    }

    private void reduceKnifeDurability() {
        System.out.println("Attempting to reduce filet knife durability");
        if(!this.getStack(FILETKNIFE_SLOT).isEmpty()) {
            System.out.println("Filet knife exists");
            ItemStack itemstack = this.getStack(FILETKNIFE_SLOT);
            ItemStack original = itemstack.copy();
            itemstack.damage(1, player, (player) -> {
            });
            System.out.println("Filet knife damaged (attempted?)");
        }
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        return canInsertAmountIntoOutputSlot(1) && canInsertItemIntoOutputSlot(ModItems.RAWFISHFILET)
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
}
