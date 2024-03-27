package net.cookiebrain.youneedbait.datagen;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.block.custom.OnionCropBlock;
import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;

import net.minecraft.util.Identifier;

import java.util.Optional;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(ModBlocks.MINNOWTRAP_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.TACKLEBOX_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.MINNOWBUCKET_BLOCK);

        blockStateModelGenerator.registerSimpleState(ModBlocks.AZUROMITE_BLOCK);

        blockStateModelGenerator.registerCrop(ModBlocks.ONION_CROP, OnionCropBlock.AGE, 0, 1, 2);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.MUSKELLUNGESPAWNEGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.NORTHERNPIKESPAWNEGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.PUMPKINSEEDSPAWNEGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.CATFISHSPAWNEGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.LARGEMOUTHBASSSPAWNEGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.WALLEYESPAWNEGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.BLACKCRAPPIESPAWNEGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

//        itemModelGenerator.register(ModItems.GIANTSQUIDSPAWNEGG,
//                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    }
}
