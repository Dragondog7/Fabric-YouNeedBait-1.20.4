package net.cookiebrain.youneedbait.screen;

import net.cookiebrain.youneedbait.block.entity.TackleBoxBlockEntity;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class TackleBoxScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final int slotCount = 4;
    private final PropertyDelegate propertyDelegate;
    public final TackleBoxBlockEntity blockEntity;
    protected TackleBoxScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos())
                ,new ArrayPropertyDelegate(4));
    }

    public TackleBoxScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.TACKLEBOX_SCREEN_HANDLER,syncId);
        checkSize(((Inventory) blockEntity),slotCount);
        this.inventory = ((Inventory) blockEntity);
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = ((TackleBoxBlockEntity) blockEntity);

        //This defines the slots for the tacklebox screen
//        for (int i = 0; i < 2; ++i) {
//            for (int l = 0; l < 9; ++l) {
//                this.addSlot(new Slot(inventory, l + i * 9 + 9, 8 + l * 18, 18 + i * 18));
//            }
//        }

        this.addSlot(new TackleBoxSlot(inventory,0,50,55));
        this.addSlot(new TackleBoxSlot(inventory,1,70,55));
        this.addSlot(new TackleBoxSlot(inventory,2,90,55));
        this.addSlot(new TackleBoxSlot(inventory,3,110,55));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
    }

    class TackleBoxSlot extends Slot{

        public TackleBoxSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.isIn(ModTags.Items.BONUS_FISH_THINGS);
        }

        @Override
        public int getMaxItemCount() {
            return 64;
        }
    }

    //The next two methods are for updating some sort of crafting progress
    public boolean isCrafting() {
        return  propertyDelegate.get(0) > 0;
    }
    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    //These methods add the player inventory and hotbar items to the gui
    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
