package net.cookiebrain.youneedbait.datagen;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    //private static List<ItemConvertible>
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        //Generates both the raw salt block and converting back to raw salt
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SALT,RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALT_BLOCK);
        //offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.AZUROMITE_INGOT,RecipeCategory.BUILDING_BLOCKS, ModBlocks.AZUROMITE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.HOOK,3)
                .pattern("I  ")
                .pattern("II ")
                .pattern("   ")
                .input('I', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_NUGGET),conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HOOK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FILETKNIFE_ITEM, 1)
                .pattern("I  ")
                .pattern(" S ")
                .pattern("   ")
                .input('I', Items.IRON_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FILETKNIFE_ITEM)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,ModBlocks.AZUROMITE_BLOCK,1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModItems.AZUROMITE_INGOT)
                .criterion(hasItem(ModItems.AZUROMITE_INGOT),conditionsFromItem(ModItems.AZUROMITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.AZUROMITE_BLOCK)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AZUROMITE_INGOT, 9)
                .input(ModBlocks.AZUROMITE_BLOCK)
                .criterion(hasItem(ModBlocks.AZUROMITE_BLOCK), conditionsFromItem(ModBlocks.AZUROMITE_BLOCK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AZUROMITE_INGOT) + "_from_block"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SALTEDBEEF, 1)
                .input(ModItems.SALT)
                .input(Items.BEEF)
                .criterion(hasItem(ModItems.SALT), conditionsFromItem(ModItems.SALT))
                .criterion(hasItem(Items.BEEF), conditionsFromItem(Items.BEEF))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SALTEDBEEF)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.AZUROMITE_INGOT,1)
                .pattern("QLQ")
                .pattern("LCL")
                .pattern("QLQ")
                .input('C', Items.COPPER_INGOT)
                .input('L', Items.LAPIS_LAZULI)
                .input('Q', Items.QUARTZ)
                .criterion(hasItem(ModItems.AZUROMITE_INGOT),conditionsFromItem(ModItems.AZUROMITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AZUROMITE_INGOT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.CANOFWORMS,1)
                .pattern("WWW")
                .pattern("WCW")
                .pattern("WWW")
                .input('C', Items.COPPER_INGOT)
                .input('W', ModItems.WORM)
                .criterion(hasItem(ModItems.CANOFWORMS),conditionsFromItem(ModItems.CANOFWORMS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CANOFWORMS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.FISHCLEANINGSTATION_BLOCK, 1)
                .pattern("III")
                .pattern("PBP")
                .pattern("P P")
                .input('I', Items.IRON_INGOT)
                .input('B', Items.BARREL)
                .input('P', ItemTags.PLANKS)
                .criterion(hasItem(Items.BARREL), conditionsFromItem(Items.BARREL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FISHCLEANINGSTATION_BLOCK)));
    }
}
