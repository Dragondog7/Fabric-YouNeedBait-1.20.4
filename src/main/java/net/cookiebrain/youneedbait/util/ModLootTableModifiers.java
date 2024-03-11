package net.cookiebrain.youneedbait.util;

import net.cookiebrain.youneedbait.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {

    private static final Identifier OAK_LEAVES_ID =
            new Identifier("minecraft","blocks/oak_leaves");

    private static final Identifier DIRT_ID =
            new Identifier("minecraft","blocks/dirt");
    public static void modifyLootTables() {
    LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
        if(OAK_LEAVES_ID.equals(id)){
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.05f)) // Drops 5% of the time
                    .with(ItemEntry.builder(ModItems.CATERPILLAR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

            tableBuilder.pool(poolBuilder.build());
        }

        if(DIRT_ID.equals(id)){
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.05f)) // Drops 5% of the time
                    .with(ItemEntry.builder(ModItems.WORM))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

            tableBuilder.pool(poolBuilder.build());
        }

    });
    }
}
