package net.cookiebrain.youneedbait.loot;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BonusLoot {
    static class BonusLootItem {
        Item lootItem;
        int weight;

        public BonusLootItem(Item lootItem, int weight) {
            this.lootItem = lootItem;
            this.weight = weight;
        }
    }

    private final String name;
    private final List<BonusLootItem> items = new ArrayList<>();
    private final Random rand = new Random();

    public BonusLoot(String name) {
        this.name = name;
    }

    public BonusLoot addItem(Item item, int weight) {
        items.add(new BonusLootItem(item, weight));
        return this;
    }
    public String getName(){
        return name;
    }
    public List<BonusLootItem> getItemList(){
        return items;
    }
    public ItemStack selectRandomWeightedItem() {
        int totalWeight = items.stream().mapToInt(item -> item.weight).sum();

        // Generate a random number between 0 (inclusive) and totalWeight (exclusive)
        int randomNum = rand.nextInt(totalWeight);

        // Determine which item has been selected
        int currentWeightSum = 0;
        for (BonusLootItem item : items) {
            currentWeightSum += item.weight;
            if (randomNum < currentWeightSum) {
                return new ItemStack(item.lootItem,1);
            }
        }

        // Fallback (should never hit this if items are correctly weighted)
        return null;
    }

}
