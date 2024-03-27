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
                    FabricBlockEntityTypeBuilder.create((pos,state) -> new MinnowTrapBlockEntity(pos,state),
                            ModBlocks.MINNOWTRAP_BLOCK).build());
    public static final BlockEntityType<TackleBoxBlockEntity> TACKLEBOX_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(YouNeedBait.MOD_ID, "tacklebox_be"),
                    FabricBlockEntityTypeBuilder.create(TackleBoxBlockEntity::new,
                            ModBlocks.TACKLEBOX_BLOCK).build(null));
    public static void registerBlockEntities() {
        YouNeedBait.LOGGER.info("Registering Block Entities for " + YouNeedBait.MOD_ID);
    }
}
