package net.cookiebrain.youneedbait.datagen;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<AdvancementEntry> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.MUTILATED_FLESH),
                        Text.literal("Learn To Fish..."), Text.literal("Just Fish It Up... Don't Be Lazy"),
                        Optional.of(new Identifier(YouNeedBait.MOD_ID, "textures/block/azuromite_block.png")), AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_mutilated_flesh", InventoryChangedCriterion.Conditions.items(ModItems.MUTILATED_FLESH))
                .build(consumer, YouNeedBait.MOD_ID + ":youneedbait").value();
    }
}
