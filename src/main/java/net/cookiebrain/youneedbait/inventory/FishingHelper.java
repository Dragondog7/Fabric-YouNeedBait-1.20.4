package net.cookiebrain.youneedbait.inventory;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class FishingHelper {

    public static boolean hasHook(PlayerEntity player){
        return player.getInventory().contains(new ItemStack(ModItems.HOOK))
                || itemInItemStack(player,"fishingrod_inventory",ModItems.FANCYFISHINGROD_ITEM,ModItems.HOOK);
    }
    public static boolean hasBait(PlayerEntity player) {
        //This checks that the player has valid bait from the FISHING_BAIT tag
        // Get the tag
        //TagKey<Item> FISHING_BAIT_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier("youneedbait", "fishing_bait"));

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
