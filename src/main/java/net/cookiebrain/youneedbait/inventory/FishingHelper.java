package net.cookiebrain.youneedbait.inventory;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.config.ModConfig;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
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

    public static void removeBait(PlayerEntity player){
        //Remove a bait
        //Only if bait is being used
        if(cfg.features.baitRequired) {
            YouNeedBait.LOGGER.info("Removing bait");
            for (int i = 0; i < player.getInventory().size(); i++) {
                ItemStack stack = player.getInventory().getStack(i);

                // Check if the ItemStack is the item we want to remove
                if (stack.isIn(ModTags.Items.FISH_BAIT_ITEMS)) {
                    // Decrement the ItemStack size by 1
                    stack.decrement(1);

                    // If the stack is empty after shrinking, remove it from the inventory
                    if (stack.isEmpty()) {
                        player.getInventory().removeStack(i);
                    }
                    YouNeedBait.LOGGER.info("Removing one bait from player inventory");
                    // Break after removing one item
                    break;
                } else if (!stack.isEmpty() && !stack.isOf(Items.AIR) && stack.getNbt() != null) {
                    if (stack.getNbt().contains("tacklebox_inv")) {
                        //They have a tackle box in their inventory
                        YouNeedBait.LOGGER.info("Found a tacklebox in inventory for bait removal");
                        DefaultedList<ItemStack> tbItems = ItemStackHelper.nbtToItemStack(stack, "tacklebox_inv");
                        for (ItemStack tbItemStack : tbItems) {
                            YouNeedBait.LOGGER.info("Searching through the tacklebox");
                            if (tbItemStack.isIn(ModTags.Items.FISH_BAIT_ITEMS)) {
                                YouNeedBait.LOGGER.info("Found valid bait in the tacklebox");
                                tbItemStack.decrement(1);
                                YouNeedBait.LOGGER.info("Removed one {} from the tacklebox", tbItemStack.getName());
                                // Break after removing one item
                                break;
                            }
                        }
                    }
                } else {
                    // After tacklebox logic, if we still haven't removed bait:
                    for (int j = 0; j < player.getInventory().size(); j++) {
                        ItemStack rstack = player.getInventory().getStack(j);

                        if (!stack.isEmpty() && stack.isOf(ModItems.FANCYFISHINGROD_ITEM) && stack.getNbt() != null) {
                            if (stack.getNbt().contains("fishingrod_inventory")) {
                                DefaultedList<ItemStack> rodInv =
                                        ItemStackHelper.nbtToItemStack(stack, "fishingrod_inventory");

                                for (int z = 0; z < rodInv.size(); z++) {
                                    ItemStack baitStack = rodInv.get(z);
                                    if (baitStack.isIn(ModTags.Items.FISH_BAIT_ITEMS)) {
                                        YouNeedBait.LOGGER.info("Removing one bait from fishingrod_inventory");
                                        baitStack.decrement(1);

                                        // write back and save
                                        ItemStackHelper.itemStackToNBT(stack, "fishingrod_inventory",rodInv);
                                        return; // done
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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
