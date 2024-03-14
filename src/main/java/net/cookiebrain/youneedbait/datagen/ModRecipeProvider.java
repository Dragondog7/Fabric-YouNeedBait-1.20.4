package net.cookiebrain.youneedbait.datagen;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

public class ModRecipeProvider extends FabricRecipeProvider {
    //private static List<ItemConvertible>
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        //Generates both the raw salt block and converting back to raw salt
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SALT,RecipeCategory.DECORATIONS, ModBlocks.SALT_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.HOOK,3)
                .pattern("I  ")
                .pattern("II ")
                .pattern("   ")
                .input('I', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_NUGGET),conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HOOK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModBlocks.AZUROMITE_BLOCK,3)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModItems.AZUROMITE_INGOT)
                .criterion(hasItem(ModItems.AZUROMITE_INGOT),conditionsFromItem(ModItems.AZUROMITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.AZUROMITE_BLOCK)));
    }
}
