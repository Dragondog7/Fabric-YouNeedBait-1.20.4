package net.cookiebrain.youneedbait.world.gen;

import net.cookiebrain.youneedbait.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.RIVER),
                SpawnGroup.WATER_CREATURE, ModEntities.WALLEYE, 50, 1, 2);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.RIVER),
                SpawnGroup.WATER_CREATURE, ModEntities.NORTHERNPIKE, 50, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP),
                SpawnGroup.WATER_CREATURE, ModEntities.MUSKELLUNGE, 50, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
                SpawnGroup.WATER_CREATURE, ModEntities.LARGEMOUTHBASS, 50, 2, 3);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
                SpawnGroup.WATER_CREATURE, ModEntities.BLACKCRAPPIE, 50, 4, 8);




    }
}
