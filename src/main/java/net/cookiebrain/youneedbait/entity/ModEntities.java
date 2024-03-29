package net.cookiebrain.youneedbait.entity;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.custom.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MuskellungeEntity> MUSKELLUNGE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(YouNeedBait.MOD_ID,"muskellunge"),
    FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE,MuskellungeEntity::new)
            .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<NorthernPikeEntity> NORTHERNPIKE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(YouNeedBait.MOD_ID,"northernpike"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE,NorthernPikeEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<LargeMouthBassEntity> LARGEMOUTHBASS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(YouNeedBait.MOD_ID,"largemouthbass"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE,LargeMouthBassEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<BlackCrappieEntity> BLACKCRAPPIE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(YouNeedBait.MOD_ID,"blackcrappie"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE,BlackCrappieEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<WalleyeEntity> WALLEYE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(YouNeedBait.MOD_ID,"walleye"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, WalleyeEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<CatFishEntity> CATFISH = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(YouNeedBait.MOD_ID,"catfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, CatFishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<PumpkinSeedEntity> PUMPKINSEED = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(YouNeedBait.MOD_ID,"pumpkinseed"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, PumpkinSeedEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

//    public static final EntityType<GiantSquidEntity> GIANTSQUID = Registry.register(Registries.ENTITY_TYPE,
//            new Identifier(YouNeedBait.MOD_ID,"giantsquid"),
//            FabricEntityTypeBuilder.create(SpawnGroup.UNDERGROUND_WATER_CREATURE, GiantSquidEntity::new)
//                    .dimensions(EntityDimensions.fixed(1.5f,12f)).build());
}


