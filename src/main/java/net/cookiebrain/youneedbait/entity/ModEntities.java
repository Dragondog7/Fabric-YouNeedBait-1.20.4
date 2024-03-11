package net.cookiebrain.youneedbait.entity;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.custom.MuskellungeEntity;
import net.cookiebrain.youneedbait.entity.custom.NorthernPikeEntity;
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
    FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,MuskellungeEntity::new)
            .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<NorthernPikeEntity> NORTHERNPIKE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(YouNeedBait.MOD_ID,"northernpike"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,NorthernPikeEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());
}


