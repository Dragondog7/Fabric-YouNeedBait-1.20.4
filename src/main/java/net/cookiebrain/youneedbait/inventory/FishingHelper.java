package net.cookiebrain.youneedbait.inventory;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.config.ModConfig;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.collection.DefaultedList;

import net.cookiebrain.youneedbait.config.ConfigManager;

public class FishingHelper {
    static ModConfig cfg = ConfigManager.get();

    public static boolean hasHook(PlayerEntity player){
        return player.getInventory().contains(new ItemStack(ModItems.HOOK))
                || itemInItemStack(player,"fishingrod_inventory",ModItems.FANCYFISHINGROD_ITEM,ModItems.HOOK);
    }
    public static boolean hasBait(PlayerEntity player) {
        //If require bait is turned off always return true
        if(!cfg.features.baitRequired){
            YouNeedBait.LOGGER.info("Bait requirement is turned off");
            return true;
        }
        //This checks that the player has valid bait
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack itemStack = player.getInventory().getStack(i);
            // Check if the ItemStack is not empty and it matches the tag
            if (!itemStack.isEmpty() && itemStack.isIn(ModTags.Items.FISH_BAIT_ITEMS)) {
                return true; // Found an item with the tag
            }
        }
        //Still here, check the tacklebox
        if(baitInTacklebox(player)){
            return true;
        };

        //Finally check the rod
        return baitInFishingRod(player);
    }
    public static boolean baitInTacklebox(PlayerEntity player){
        return tagInItemStack(player,"tacklebox_inv",ModBlocks.TACKLEBOX_BLOCK.asItem(),ModTags.Items.FISH_BAIT_ITEMS);
    }
    public static boolean baitInFishingRod(PlayerEntity player){
        return tagInItemStack(player,"fishingrod_inventory",ModItems.FANCYFISHINGROD_ITEM,ModTags.Items.FISH_BAIT_ITEMS);
    }

    public static void removeBait(PlayerEntity player) {
        // Remove a bait only if bait is required
        if (!cfg.features.baitRequired) {
            YouNeedBait.LOGGER.debug("Bait not required by config; skipping bait removal for {}",
                    player.getName().getString());
            return;
        }


        // 1) Fishing rod bait slot (held rod only)
        if (removeBaitFromHeldRod(player)) {
            //YouNeedBait.LOGGER.info("Successfully removed bait from held fishing rod for {}",player.getName().getString());
            return;
        }

        // 2) Tackle box
        if (removeBaitFromTackleBox(player)) {
            //YouNeedBait.LOGGER.info("Successfully removed bait from tackle box for {}",player.getName().getString());
            return;
        }

        // 3) Player inventory
        if (removeBaitFromInventory(player)) {
            //YouNeedBait.LOGGER.info("Successfully removed bait from player inventory for {}",player.getName().getString());
            return;
        }

        // Nothing found anywhere
        //YouNeedBait.LOGGER.warn("No bait found to remove for {}", player.getName().getString());
    }

    /**
     * Try to remove bait from the fishing rod's internal inventory (bait slot)
     * on the rod the player is currently holding (main hand, then offhand).
     *
     * @return true if bait was removed, false otherwise.
     */
    private static boolean removeBaitFromHeldRod(PlayerEntity player) {
        ItemStack rodStack = ItemStack.EMPTY;

        // Prefer rod in main hand
        ItemStack mainHand = player.getMainHandStack();
        if (!mainHand.isEmpty() && mainHand.isOf(ModItems.FANCYFISHINGROD_ITEM)) {
            rodStack = mainHand;
        } else {
            // Then rod in offhand
            ItemStack offHand = player.getOffHandStack();
            if (!offHand.isEmpty() && offHand.isOf(ModItems.FANCYFISHINGROD_ITEM)) {
                rodStack = offHand;
            }
        }

        if (rodStack.isEmpty()) {
            return false;
        }

        if (rodStack.getNbt() == null || !rodStack.getNbt().contains("fishingrod_inventory")) {
            return false;
        }

        DefaultedList<ItemStack> rodInv =
                ItemStackHelper.nbtToItemStack(rodStack, "fishingrod_inventory");

        for (int i = 0; i < rodInv.size(); i++) {
            ItemStack baitStack = rodInv.get(i);

            if (!baitStack.isEmpty() && baitStack.isIn(ModTags.Items.FISH_BAIT_ITEMS)) {
                baitStack.decrement(1);

                if (baitStack.isEmpty()) {
                    rodInv.set(i, ItemStack.EMPTY);
                }

                // Write back & save rod inventory
                ItemStackHelper.itemStackToNBT(rodStack, "fishingrod_inventory", rodInv);
                return true;
            }
        }

        return false;
    }

    /**
     * Try to remove bait from the tackle box (stored as NBT inventory on an item).
     *
     * @return true if bait was removed, false otherwise.
     */
    private static boolean removeBaitFromTackleBox(PlayerEntity player) {
        PlayerInventory inv = player.getInventory();

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);

            if (stack.isEmpty() || stack.getNbt() == null) {
                continue;
            }

            if (stack.getNbt().contains("tacklebox_inv")) {

                DefaultedList<ItemStack> tbItems =
                        ItemStackHelper.nbtToItemStack(stack, "tacklebox_inv");

                for (int j = 0; j < tbItems.size(); j++) {
                    ItemStack tbItemStack = tbItems.get(j);

                    if (!tbItemStack.isEmpty() && tbItemStack.isIn(ModTags.Items.FISH_BAIT_ITEMS)) {

                        tbItemStack.decrement(1);

                        if (tbItemStack.isEmpty()) {
                            tbItems.set(j, ItemStack.EMPTY);
                        }

                        // Write back to the tackle box NBT
                        ItemStackHelper.itemStackToNBT(stack, "tacklebox_inv", tbItems);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Fallback: remove bait directly from the player's main inventory.
     *
     * @return true if bait was removed, false otherwise.
     */
    private static boolean removeBaitFromInventory(PlayerEntity player) {

        PlayerInventory inv = player.getInventory();

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);

            if (!stack.isEmpty() && stack.isIn(ModTags.Items.FISH_BAIT_ITEMS)) {

                stack.decrement(1);

                if (stack.isEmpty()) {
                    inv.removeStack(i);
                }

                return true;
            }
        }

        return false;
    }

    public static boolean itemInItemStack(PlayerEntity player,String inventoryNbt,Item matchItem,Item validItem){
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack itemStack = player.getInventory().getStack(i);
            if(itemStack.isOf(matchItem)){
                // Check if the ItemStack is not empty and it matches the tag
                if (!itemStack.isEmpty() && !itemStack.isOf(Items.AIR) && itemStack.getNbt() != null) {
                    if(itemStack.getNbt().contains(inventoryNbt)){
                        DefaultedList<ItemStack> tbItems = ItemStackHelper.nbtToItemStack(itemStack,inventoryNbt);
                        for(ItemStack tbItemStack: tbItems){
                            if(tbItemStack.isOf(validItem)){
                                return true; // Found an item with the tag

                            }

                        }
                    }
                }

            }
        }
        return false;
    }

    public static boolean tagInItemStack(PlayerEntity player, String inventoryNbt, Item matchItem, TagKey<Item> validTag){
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack itemStack = player.getInventory().getStack(i);
            if(itemStack.isOf(matchItem)){
                // Check if the ItemStack is not empty and it matches the tag
                if (!itemStack.isEmpty() && !itemStack.isOf(Items.AIR) && itemStack.getNbt() != null) {
                    if(itemStack.getNbt().contains(inventoryNbt)){
                        DefaultedList<ItemStack> tbItems = ItemStackHelper.nbtToItemStack(itemStack,inventoryNbt);
                        for(ItemStack tbItemStack: tbItems){
                            if(tbItemStack.isIn(validTag)){
                                return true; // Found an item with the tag

                            }

                        }
                    }
                }

            }
        }
        return false;
    }

}
