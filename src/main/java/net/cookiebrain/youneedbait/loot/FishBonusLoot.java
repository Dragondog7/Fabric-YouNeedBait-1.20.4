package net.cookiebrain.youneedbait.loot;

import net.cookiebrain.youneedbait.config.ConfigManager;
import net.cookiebrain.youneedbait.config.ModConfig;
import net.cookiebrain.youneedbait.item.custom.AbstractFishItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FishBonusLoot extends BonusLoot {

    public FishBonusLoot(String name) {
        super(name);
    }

    @Override
    public ItemStack selectRandomWeightedItem() {
        ItemStack stack = super.selectRandomWeightedItem();
        if (stack == null || stack.isEmpty()) {
            return stack;
        }

        ModConfig.Features features = ConfigManager.get().features;
        if (!features.weightedFishEnabled) {
            // Feature disabled: do not touch NBT, keep vanilla stacking behavior
            return stack;
        }

        Item item = stack.getItem();
        if (item instanceof AbstractFishItem fishItem) {
            fishItem.ensureWeight(stack);
        }

        return stack;
    }
}