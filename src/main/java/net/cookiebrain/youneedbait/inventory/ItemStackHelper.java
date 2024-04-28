package net.cookiebrain.youneedbait.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.collection.DefaultedList;

public class ItemStackHelper {

    public static void itemStackToNBT(ItemStack stack, String key,  DefaultedList<ItemStack> items){
        NbtCompound nbt;

        if(stack.hasNbt()){
            //System.out.println("Stack already has nbt data");
            nbt = stack.getNbt();
        } else {
            //System.out.println("Adding nbt data");
            nbt = new NbtCompound();
            stack.setNbt(nbt);
        }

        //Create the NBT list to store the ItemStacks
        NbtList listNBT = new NbtList();

        //Convert each itemstack in the list to a compound nbt and add it to the NBT List
        for (ItemStack item : items) {
            NbtCompound itemTag = new NbtCompound();
            item.writeNbt(itemTag);
            listNBT.add(itemTag);
        }
        if (!listNBT.isEmpty()) {
            nbt.put(key, listNBT);
        }
    }

    public static DefaultedList<ItemStack> nbtToItemStack(ItemStack stack, String key){
        // Retrieves a list of ItemStacks from the specified ItemStack's NBT data
        DefaultedList<ItemStack> items;
        items = DefaultedList.ofSize(27,ItemStack.EMPTY);
        // Check if the ItemStack has a tag and the tag contains the specified key

        if (stack.hasNbt() && stack.getNbt().contains(key)) {
            //System.out.println("I found stuff to put in the block");
            NbtList listTag = stack.getNbt().getList(key,NbtCompound.COMPOUND_TYPE);

            // Convert each CompoundNBT in the ListNBT back to an ItemStack
            for (int i = 0; i < items.size(); i++) {
                //System.out.println("Item being placed in the block");
                NbtCompound itemTag = listTag.getCompound(i);
                ItemStack item = ItemStack.fromNbt(itemTag); // This line creates an ItemStack from the CompoundNBT
                items.set(i,item);
            }
        }

        return items;
    }

    //Converts an itemstack to a SimpleInventory (Inventory interface)

    public static Inventory convertToInventory(DefaultedList<ItemStack> itemStacks){
        SimpleInventory inventory = new SimpleInventory(itemStacks.size());

        for (int i = 0; i < itemStacks.size(); i++) {
            inventory.setStack(i,itemStacks.get(i));
        }
        return inventory;
    }

    public static DefaultedList<ItemStack> convertToDefaultedList(Inventory inventory){
        DefaultedList<ItemStack> items = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        // Transfer the items from the Inventory to the DefaultedList
        for (int i = 0; i < inventory.size(); i++) {
            items.set(i, inventory.getStack(i));
        }

        return items;
    }
    public static void giveItemToPlayer(PlayerEntity player, ItemStack stack){
        if(!player.giveItemStack(stack)){
            player.dropItem(stack,false);
            //} else if (player instanceof ServerPlayer){
            //    player.inventoryMenu.sendAllDataToRemote();
        }
    }
}
