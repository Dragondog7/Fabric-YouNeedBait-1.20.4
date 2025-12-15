package net.cookiebrain.youneedbait.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;

public class SaltBlockFactory {

    public static Block createSaltBlock() {
        // 1.20.1 signature: ExperienceDroppingBlock(Settings settings)
        return new ExperienceDroppingBlock(
                FabricBlockSettings.copyOf(Blocks.DEEPSLATE_LAPIS_ORE).strength(2.0f)
        );
    }
}