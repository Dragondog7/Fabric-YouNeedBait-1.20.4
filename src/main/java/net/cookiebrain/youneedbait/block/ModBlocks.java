package net.cookiebrain.youneedbait.block;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.block.custom.MinnowBucketBlock;
import net.cookiebrain.youneedbait.block.custom.OnionCropBlock;
import net.cookiebrain.youneedbait.block.custom.MinnowTrapBlock;
import net.cookiebrain.youneedbait.block.custom.TackleBoxBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    //Ores
    public static final Block SALT_BLOCK = registerBlock("salt_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), FabricBlockSettings.copyOf(Blocks.DEEPSLATE_LAPIS_ORE).strength(2f)));
    public static final Block AZUROMITE_BLOCK = registerBlock("azuromite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    //Fishing Related Blocks
    public static final Block MINNOWTRAP_BLOCK = registerBlock("minnowtrap_block",
            new MinnowTrapBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(1f).nonOpaque()));
    public static final Block TACKLEBOX_BLOCK = registerBlock("tacklebox_block",
            new TackleBoxBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(1f).nonOpaque()));
    public static final Block MINNOWBUCKET_BLOCK = registerBlock("minnowbucket_block",
            new MinnowBucketBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(1f).nonOpaque()));

    //Crops
    public static final Block ONION_CROP = Registry.register(Registries.BLOCK, new Identifier(YouNeedBait.MOD_ID, "onion_crop"),
            new OnionCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    private static Block registerBlock (String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(YouNeedBait.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(YouNeedBait.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        YouNeedBait.LOGGER.info("Registering ModBlocks for " + YouNeedBait.MOD_ID);
    }
}
