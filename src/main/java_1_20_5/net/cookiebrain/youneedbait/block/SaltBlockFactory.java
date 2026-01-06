package net.cookiebrain.youneedbait.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class SaltBlockFactory {

    public static Block createSaltBlock() {
        return new ExperienceDroppingBlock(
                UniformIntProvider.create(2, 5),
                FabricBlockSettings.copyOf(Blocks.DEEPSLATE_LAPIS_ORE).strength(2.0f)
        );
    }
}