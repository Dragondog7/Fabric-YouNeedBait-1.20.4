package net.cookiebrain.youneedbait.loot;

import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.loot.BonusLoot;

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

    public ModBonusLoot() {
        lootList.add(fishCleaningBonusLoot);
        lootList.add(swampBaitTrap);
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
