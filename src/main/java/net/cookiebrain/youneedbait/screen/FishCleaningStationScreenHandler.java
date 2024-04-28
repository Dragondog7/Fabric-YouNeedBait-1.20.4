package net.cookiebrain.youneedbait.screen;

import net.cookiebrain.youneedbait.block.entity.FishCleaningStationBlockEntity;
import net.cookiebrain.youneedbait.item.ModItems;
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

public class FishCleaningStationScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final int slotCount = 4;
    private final PropertyDelegate propertyDelegate;
    public final FishCleaningStationBlockEntity blockEntity;
    private static final int RAWFISH_SLOT = 0;
    private static final int FILETKNIFE_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int BONUS_SLOT = 3;

    protected FishCleaningStationScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos())
                ,new ArrayPropertyDelegate(4));
    }

    public FishCleaningStationScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.FISH_CLEANING_STATION_SCREEN_HANDLER,syncId);
        checkSize(((Inventory) blockEntity),slotCount);
        this.inventory = ((Inventory) blockEntity);
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = ((FishCleaningStationBlockEntity) blockEntity);

        //Define the different kinds of slots
        class FiletKnifeSlot extends Slot{

            public FiletKnifeSlot(Inventory inventory, int index, int x, int y) {
                super(inventory, index, x, y);
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.FILETKNIFE_ITEM);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        }

        class RawFishSlot extends Slot{

            public RawFishSlot(Inventory inventory, int index, int x, int y) {
                super(inventory, index, x, y);
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.Items.CUSTOM_FISH);
            }

            @Override
            public int getMaxItemCount() {
                return 64;
            }
        }

        class FiletSlot extends Slot{

            public FiletSlot(Inventory inventory, int index, int x, int y) {
                super(inventory, index, x, y);
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.RAWFISHFILET);
            }

            @Override
            public int getMaxItemCount() {
                return 64;
            }
        }

        class BonusSlot extends Slot{

            public BonusSlot(Inventory inventory, int index, int x, int y) {
                super(inventory, index, x, y);
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
                //stack.isIn(ModTags.Items.CUSTOM_FISH);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        }

        //Creates the slots
        this.addSlot(new RawFishSlot(inventory,RAWFISH_SLOT,58,7));
        this.addSlot(new FiletKnifeSlot(inventory,FILETKNIFE_SLOT,15,55));
        this.addSlot(new FiletSlot(inventory,OUTPUT_SLOT,115,55));
        this.addSlot(new BonusSlot(inventory,BONUS_SLOT,138,55));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
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
