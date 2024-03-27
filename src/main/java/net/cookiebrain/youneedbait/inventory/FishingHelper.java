package net.cookiebrain.youneedbait.inventory;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class FishingHelper {

    public static boolean hasHook(PlayerEntity player){
        return player.getInventory().contains(new ItemStack(ModItems.HOOK));
    }
    public static boolean hasBait(PlayerEntity player) {
        //This checks that the player has valid bait from the FISHING_BAIT tag

        // Get the tag
        TagKey<Item> FISHING_BAIT_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier("youneedbait", "fishing_bait"));

        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack itemStack = player.getInventory().getStack(i);
            // Check if the ItemStack is not empty and it matches the tag
            if (!itemStack.isEmpty() && itemStack.isIn(ModTags.Items.FISH_BAIT_ITEMS)) {
                return true; // Found an item with the tag
            }
        }
        //Still here, check the tacklebox
        return baitInTacklebox(player);
    }
    public static boolean baitInTacklebox(PlayerEntity player){

        for (int i = 0; i < player.getInventory().size(); i++) {
            //player.sendMessage(Text.literal("Looping through inventory"));
            ItemStack itemStack = player.getInventory().getStack(i);
            if(itemStack.isOf(ModBlocks.TACKLEBOX_BLOCK.asItem())){
                // Check if the ItemStack is not empty and it matches the tag
                if (!itemStack.isEmpty() && !itemStack.isOf(Items.AIR) && itemStack.getNbt() != null) {
                    if(itemStack.getNbt().contains("tacklebox_inv")){
                        //System.out.println("Found a tacklebox in inventory");
                        DefaultedList<ItemStack> tbItems = ItemStackHelper.nbtToItemStack(itemStack,"tacklebox_inv");
                        for(ItemStack tbItemStack: tbItems){
                            //System.out.println("Searching through the tacklebox");
                            if(tbItemStack.isIn(ModTags.Items.FISH_BAIT_ITEMS)){
                                //System.out.println("Found valid bait in the tacklebox");
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
