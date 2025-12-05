package net.cookiebrain.youneedbait.screen;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.inventory.ItemStackHelper;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.item.custom.FancyFishingRodItem;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;

public class FancyFishingRodScreenHandler extends ScreenHandler {
    public final ItemStack rodStack;
    private DefaultedList<ItemStack> menuInventory;
    private Inventory inventory;
    private final Hand hand;
    private final int slotCount;

    // Client-side constructor (reads from packet)
    protected FancyFishingRodScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, buf.readEnumConstant(Hand.class));
        YouNeedBait.LOGGER.info("CLIENT: FancyFishingRodScreenHandler constructor (PacketByteBuf) called");
        YouNeedBait.LOGGER.info("CLIENT: Read hand from packet: {}", this.hand);
    }

    // Common constructor used by both client and server
    private FancyFishingRodScreenHandler(int syncId, PlayerInventory playerInventory, Hand hand) {
        super(ModScreenHandlers.FANCYFISHINGROD_SCREEN_HANDLER, syncId);

        YouNeedBait.LOGGER.info("=== FancyFishingRodScreenHandler constructor (Hand) START ===");
        YouNeedBait.LOGGER.info("syncId: {}", syncId);
        YouNeedBait.LOGGER.info("Hand: {}", hand);

        this.hand = hand;
        this.rodStack = playerInventory.player.getStackInHand(hand);

        YouNeedBait.LOGGER.info("Rod stack: {}", this.rodStack);
        YouNeedBait.LOGGER.info("Rod item: {}", this.rodStack.getItem());

        // Verify we have the correct item
        if (!this.rodStack.isOf(ModItems.FANCYFISHINGROD_ITEM)) {
            YouNeedBait.LOGGER.error("ERROR: Player is not holding FancyFishingRodItem in hand {}!", hand);
            YouNeedBait.LOGGER.error("ERROR: Instead holding: {}", this.rodStack.getItem());
        }

        // Get the slot count based on the rod's tier
        this.slotCount = FancyFishingRodItem.getSlotCountForStack(this.rodStack);
        int tier = FancyFishingRodItem.getTier(this.rodStack);

        YouNeedBait.LOGGER.info("Rod tier: {}, Slot count: {}", tier, this.slotCount);

        // Load inventory from NBT
        this.menuInventory = ItemStackHelper.nbtToItemStack(this.rodStack, "fishingrod_inventory");
        YouNeedBait.LOGGER.info("Loaded inventory from NBT, size: {}", this.menuInventory.size());

        // Ensure the inventory list matches the rod's tier
        if (this.menuInventory.size() != this.slotCount) {
            YouNeedBait.LOGGER.info("Resizing inventory from {} to {}", this.menuInventory.size(), this.slotCount);
            DefaultedList<ItemStack> resized = DefaultedList.ofSize(this.slotCount, ItemStack.EMPTY);
            int copyCount = Math.min(this.menuInventory.size(), this.slotCount);
            for (int i = 0; i < copyCount; i++) {
                resized.set(i, this.menuInventory.get(i));
            }
            this.menuInventory = resized;
        }

        // Convert to Inventory
        try {
            this.inventory = ItemStackHelper.convertToInventory(this.menuInventory);
            YouNeedBait.LOGGER.info("Converted to Inventory successfully, size: {}", this.inventory.size());
        } catch (Exception e) {
            YouNeedBait.LOGGER.error("ERROR converting to Inventory", e);
            throw e;
        }

        // Create slots based on tier
        YouNeedBait.LOGGER.info("Creating slots...");

        // First slot is always modifier
        this.addSlot(new ModifierSlot(this.inventory, 0, 62, 16));
        YouNeedBait.LOGGER.info("Added ModifierSlot at index 0");

        // Second slot is always bait
        this.addSlot(new BaitSlot(this.inventory, 1, 62, 46));
        YouNeedBait.LOGGER.info("Added BaitSlot at index 1");

        // Third slot is always hook
        this.addSlot(new HookSlot(this.inventory, 2, 119, 46));
        YouNeedBait.LOGGER.info("Added HookSlot at index 2");

        // Add additional slots for higher tiers (if you want them visible)
        // Adjust positions as needed for your GUI
        for (int i = 3; i < this.slotCount; i++) {
            // Example positioning - adjust to fit your GUI design
            int xPos = 62 + ((i - 3) * 18);
            int yPos = 76;
            this.addSlot(new ModifierSlot(this.inventory, i, xPos, yPos));
            YouNeedBait.LOGGER.info("Added extra ModifierSlot at index {}", i);
        }

        YouNeedBait.LOGGER.info("Adding player inventory and hotbar...");
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        YouNeedBait.LOGGER.info("=== FancyFishingRodScreenHandler constructor END ===");
        YouNeedBait.LOGGER.info("Total slots created: {}", this.slots.size());
    }

    // Server-side constructor (called directly from item)
    public FancyFishingRodScreenHandler(int syncId, PlayerInventory playerInventory, ItemStack itemStack) {
        // We need to figure out which hand has this stack
        this(syncId, playerInventory, getHandForStack(playerInventory.player, itemStack));
        YouNeedBait.LOGGER.info("SERVER: FancyFishingRodScreenHandler constructor (ItemStack) called");
    }

    // Helper method to determine which hand is holding the stack
    private static Hand getHandForStack(PlayerEntity player, ItemStack stack) {
        if (player.getMainHandStack() == stack) {
            YouNeedBait.LOGGER.info("Stack is in MAIN_HAND");
            return Hand.MAIN_HAND;
        } else if (player.getOffHandStack() == stack) {
            YouNeedBait.LOGGER.info("Stack is in OFF_HAND");
            return Hand.OFF_HAND;
        }

        // Fallback: check by item type
        if (player.getMainHandStack().isOf(ModItems.FANCYFISHINGROD_ITEM)) {
            YouNeedBait.LOGGER.warn("Stack reference didn't match, but MAIN_HAND has FancyFishingRod");
            return Hand.MAIN_HAND;
        } else if (player.getOffHandStack().isOf(ModItems.FANCYFISHINGROD_ITEM)) {
            YouNeedBait.LOGGER.warn("Stack reference didn't match, but OFF_HAND has FancyFishingRod");
            return Hand.OFF_HAND;
        }

        YouNeedBait.LOGGER.error("ERROR: Could not determine which hand has the FancyFishingRod! Defaulting to MAIN_HAND");
        return Hand.MAIN_HAND;
    }

    // Define the different kinds of slots
    class HookSlot extends Slot {
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

    class BaitSlot extends Slot {
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

    class ModifierSlot extends Slot {
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
        YouNeedBait.LOGGER.info("=== FancyFishingRodScreenHandler.onClosed() START ===");
        super.onClosed(player);

        // Get the current rod stack (it might have changed hands)
        ItemStack currentRod = player.getStackInHand(this.hand);

        YouNeedBait.LOGGER.info("Hand: {}", this.hand);
        YouNeedBait.LOGGER.info("Current rod in hand: {}", currentRod);

        // Only save if the player still has the rod in the same hand
        if (currentRod.isOf(ModItems.FANCYFISHINGROD_ITEM)) {
            YouNeedBait.LOGGER.info("Saving inventory to NBT...");
            DefaultedList<ItemStack> rodItems = ItemStackHelper.convertToDefaultedList(this.inventory);
            YouNeedBait.LOGGER.info("Converted inventory to DefaultedList, size: {}", rodItems.size());

            try {
                ItemStackHelper.itemStackToNBT(currentRod, "fishingrod_inventory", rodItems);
                YouNeedBait.LOGGER.info("Successfully saved inventory to NBT");
            } catch (Exception e) {
                YouNeedBait.LOGGER.error("ERROR saving inventory to NBT", e);
            }
        } else {
            YouNeedBait.LOGGER.warn("Player no longer holding FancyFishingRod in hand {}, not saving", this.hand);
        }

        YouNeedBait.LOGGER.info("=== FancyFishingRodScreenHandler.onClosed() END ===");
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        ItemStack stack = player.getStackInHand(this.hand);
        boolean canUse = stack.isOf(ModItems.FANCYFISHINGROD_ITEM);

        if (!canUse) {
            YouNeedBait.LOGGER.warn("canUse() returning false - player not holding FancyFishingRod in hand {}", this.hand);
        }

        return canUse;
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