package net.cookiebrain.youneedbait.screen;

import net.cookiebrain.youneedbait.inventory.ItemStackHelper;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.collection.DefaultedList;

public class FancyFishingRodScreenHandler extends ScreenHandler {
    private final int slotCount = 3;
    public final ItemStack rodInstance;
    private DefaultedList<ItemStack> menuInventory;
    private Inventory inventory;
    protected FancyFishingRodScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getMainHandStack());
    }

    public FancyFishingRodScreenHandler(int syncId, PlayerInventory playerInventory, ItemStack itemStack) {
        super(ModScreenHandlers.FANCYFISHINGROD_SCREEN_HANDLER,syncId);
        Item item = itemStack.getItem();
        this.rodInstance = playerInventory.getMainHandStack();
        //this.rodInstance = itemStack;
        //Don't use .getitems() as it cannot be converted to an Inventory
        //checkSize(playerInventory,slotCount);
        //this.inventory = this.rodInstance;
        this.menuInventory = ItemStackHelper.nbtToItemStack(this.rodInstance,"fishingrod_inventory");
        this.inventory = ItemStackHelper.convertToInventory(this.menuInventory);
        //Creates the slots
        this.addSlot(new ModifierSlot(this.inventory,0,62,16));
        this.addSlot(new BaitSlot(this.inventory,1,62,46));
        this.addSlot(new HookSlot(this.inventory,2,119,46));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    //Define the different kinds of slots
    class HookSlot extends Slot{

        public HookSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.isOf(ModItems.HOOK);
        }

        @Override
        public int getMaxItemCount() {
            return 1;
        }
    }
    class BaitSlot extends Slot{

        public BaitSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.isIn(ModTags.Items.FISH_BAIT_ITEMS);
        }

        @Override
        public int getMaxItemCount() {
            return 64;
        }
    }
    class ModifierSlot extends Slot{

        public ModifierSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.isIn(ModTags.Items.FISHINGROD_MODIFIERS);
        }

        @Override
        public int getMaxItemCount() {
            return 1;
        }
    }
    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        DefaultedList<ItemStack> rodItems = ItemStackHelper.convertToDefaultedList(this.inventory);
        ItemStackHelper.itemStackToNBT(this.rodInstance,"fishingrod_inventory",rodItems);
        //System.out.println("The Fishing Rod Screen was closed");
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return player.getMainHandStack().isOf(ModItems.FANCYFISHINGROD_ITEM);
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
