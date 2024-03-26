package net.cookiebrain.youneedbait.block.entity;

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
import org.jetbrains.annotations.Nullable;

public class TackleBoxBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory,ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

//    private static final int INVENTORY_SLOT = 0;
//    private static final int INVENTORY_SLOT2 = 2;
//    private static final int INVENTORY_SLOT3 = 3;
//    private static final int INVENTORY_SLOT4 = 4;
//    private static final int INVENTORY_SLOT5 = 5;
//    private static final int INVENTORY_SLOT6 = 6;
//    private static final int INVENTORY_SLOT7 = 7;
//    private static final int INVENTORY_SLOT8 = 8;
//    private static final int INVENTORY_SLOT9 = 9;
//    private static final int INVENTORY_SLOT10 = 10;
//    private static final int INVENTORY_SLOT11 = 11;
//    private static final int INVENTORY_SLOT12 = 12;
//    private static final int INVENTORY_SLOT13 = 13;
//    private static final int INVENTORY_SLOT14 = 14;
//    private static final int INVENTORY_SLOT15 = 15;
//    private static final int INVENTORY_SLOT16 = 16;
//    private static final int INVENTORY_SLOT17 = 17;
//    private static final int INVENTORY_SLOT18 = 18;
//    private static final int INVENTORY_SLOT19 = 19;
//    private static final int INVENTORY_SLOT20 = 20;
//    private static final int INVENTORY_SLOT21 = 21;
//    private static final int INVENTORY_SLOT22 = 22;
//    private static final int INVENTORY_SLOT23 = 23;
//    private static final int INVENTORY_SLOT24 = 24;
//    private static final int INVENTORY_SLOT25 = 25;
//    private static final int INVENTORY_SLOT26 = 26;
//    private static final int INVENTORY_SLOT27 = 27;



    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public TackleBoxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TACKLEBOX_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> TackleBoxBlockEntity.this.progress;
                    case 1 -> TackleBoxBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TackleBoxBlockEntity.this.progress = value;
                    case 1 -> TackleBoxBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Tackle Box");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return null;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }
}
