package net.cookiebrain.youneedbait.block.entity;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<MinnowTrapBlockEntity> MINNOWTRAP_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(YouNeedBait.MOD_ID, "minnowtrap_be"),
                    FabricBlockEntityTypeBuilder.create(MinnowTrapBlockEntity::new,
                            ModBlocks.MINNOWTRAP_BLOCK).build());

    public static void registerBlockEntities() {
        YouNeedBait.LOGGER.info("Registering Block Entities for " + YouNeedBait.MOD_ID);
    }
}