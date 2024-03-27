package net.cookiebrain.youneedbait.block.entity;

import net.cookiebrain.youneedbait.screen.TackleBoxScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TackleBoxBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory,ImplementedInventory {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27,ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public TackleBoxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TACKLEBOX_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            //Allows the variables to be synchronized via the screen handler
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> TackleBoxBlockEntity.this.progress;
                    case 1 -> TackleBoxBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: TackleBoxBlockEntity.this.progress = value;
                    case 1: TackleBoxBlockEntity.this.maxProgress = value;
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
        return new TackleBoxScreenHandler(syncId,playerInventory,this, propertyDelegate);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Tackle Box");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    public void setItems(DefaultedList<ItemStack> itemStacks) {
        this.inventory = itemStacks;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt,inventory);
        super.readNbt(nbt);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
//        if(canInsertIntoOutputSlot() && hasRecipe()) {
//            increaseCraftingProgress();
//            markDirty(world, pos, state);
//
//            if(hasCraftingFinished()) {
//                craftItem();
//                resetProgress();
//            }
//        } else {
//            resetProgress();
//        }
    }
}
