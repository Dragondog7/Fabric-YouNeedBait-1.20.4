package net.cookiebrain.youneedbait.loot;

import net.cookiebrain.youneedbait.item.ModItems;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModBonusLoot {

    protected final List<BonusLoot> lootList = new ArrayList<>();
    private final BonusLoot fishCleaningBonusLoot = new BonusLoot("fishcleaning")
            .addItem(ModItems.MINNOW_ITEM,15)
            .addItem(ModItems.WORM,10)
            .addItem(ModItems.CATERPILLAR,5)
            .addItem(ModItems.SUCKERMINNOW_ITEM, 5)
            .addItem(ModItems.MUTILATED_FLESH, 15)
            .addItem(ModItems.HOOK, 3);

    private final BonusLoot swampBaitTrap = new BonusLoot("swampbaittrap")
            .addItem(ModItems.LEECH,50)
            .addItem(ModItems.SUCKERMINNOW_ITEM,50);

    private final BonusLoot junkToGardenLoot = new BonusLoot("junktogarden")
            .addItem(Items.MELON_SEEDS,20)
            .addItem(Items.PUMPKIN_SEEDS,20)
            .addItem(Items.WHEAT_SEEDS,40)
            .addItem(ModItems.ONION_BULBS,10);

    private final BonusLoot fishingFishLoot = new FishBonusLoot("fishing_fish")
//            .addItem(Items.COD, 60)
//            .addItem(Items.SALMON, 25)
//            .addItem(Items.TROPICAL_FISH, 2)
//            .addItem(Items.PUFFERFISH, 13)

            // YouNeedBait mod fish – replace with your actual item references
            .addItem(ModItems.MUSKELLUNGE, 2)
            .addItem(ModItems.LARGEMOUTHBASS, 5)
            .addItem(ModItems.BLACKCRAPPIE, 8)
            .addItem(ModItems.NORTHERNPIKE, 6)
            .addItem(ModItems.WALLEYE, 4)
            .addItem(ModItems.CATFISH, 6)
            .addItem(ModItems.PUMPKINSEED, 15);

            // Conditional entry: only in biome minecraft:bamboo_jungle
            // If BonusLoot supports conditions, swap this to your conditional API.
            //.addItem(YouNeedBaitItems.REDHERRING.get(), 1);

    public ModBonusLoot() {
        lootList.add(fishCleaningBonusLoot);
        lootList.add(swampBaitTrap);
        lootList.add(junkToGardenLoot);
        lootList.add(fishingFishLoot);
    }
    public List<BonusLoot> getLootTables()
    {
        return lootList;
    }

    public BonusLoot getLootTableByName(String name){
        for (int i = 0; i < lootList.size(); i++) {
            if(Objects.equals(lootList.get(i).getName(), name)){
                return lootList.get(i);
            }
        }
        return null;
    }
}
