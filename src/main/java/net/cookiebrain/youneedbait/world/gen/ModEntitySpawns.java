package net.cookiebrain.youneedbait.world.gen;

import net.cookiebrain.youneedbait.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.RIVER),
                SpawnGroup.WATER_CREATURE, ModEntities.WALLEYE, 20, 1, 2);

        SpawnRestriction.register(ModEntities.WALLEYE, SpawnRestriction.Location.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.RIVER),
                SpawnGroup.WATER_CREATURE, ModEntities.NORTHERNPIKE, 20, 1, 1);

        SpawnRestriction.register(ModEntities.NORTHERNPIKE, SpawnRestriction.Location.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP),
                SpawnGroup.WATER_CREATURE, ModEntities.MUSKELLUNGE, 10, 1, 1);

        SpawnRestriction.register(ModEntities.MUSKELLUNGE, SpawnRestriction.Location.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
                SpawnGroup.WATER_CREATURE, ModEntities.LARGEMOUTHBASS, 40, 2, 3);

        SpawnRestriction.register(ModEntities.LARGEMOUTHBASS, SpawnRestriction.Location.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
                SpawnGroup.WATER_CREATURE, ModEntities.BLACKCRAPPIE, 30, 4, 8);

        SpawnRestriction.register(ModEntities.BLACKCRAPPIE, SpawnRestriction.Location.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canSpawn);

//        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN),
//                SpawnGroup.WATER_CREATURE, ModEntities.GIANTSQUID, 2, 1, 1);
//
//        SpawnRestriction.register(ModEntities.GIANTSQUID, SpawnRestriction.Location.IN_WATER,
//                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP),
                SpawnGroup.WATER_CREATURE, ModEntities.CATFISH, 30, 1, 2);

        SpawnRestriction.register(ModEntities.CATFISH, SpawnRestriction.Location.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.RIVER),
                SpawnGroup.WATER_CREATURE, ModEntities.PUMPKINSEED, 40, 4, 10);

        SpawnRestriction.register(ModEntities.PUMPKINSEED, SpawnRestriction.Location.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canSpawn);




    }
}
