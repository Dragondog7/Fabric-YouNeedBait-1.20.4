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
import net.minecraft.item.Items;
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
        AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(Items.FISHING_ROD),
                        Text.literal("You Need Bait!"), Text.literal("Obtain A Fishing Rod To Get On You're Fishing Journey"),
                        Optional.of(new Identifier(YouNeedBait.MOD_ID, "textures/block/water.png")), AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_fishing_rod", InventoryChangedCriterion.Conditions.items(Items.FISHING_ROD))
                .build(consumer, YouNeedBait.MOD_ID + ":youneedbait");

        AdvancementEntry MutilatedFlesh = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.MUTILATED_FLESH),
                        Text.literal("Learn To Fish..."), Text.literal("Just Fish It Up... Don't Be Lazy"),
                        Optional.of(new Identifier(YouNeedBait.MOD_ID, "textures/block/water.png")), AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_mutilated_flesh", InventoryChangedCriterion.Conditions.items(ModItems.MUTILATED_FLESH))
                .parent(rootAdvancement)
                .build(consumer, YouNeedBait.MOD_ID + ":mutilatedflesh");

            AdvancementEntry Muskellunge = Advancement.Builder.create()
                    .display(new AdvancementDisplay(new ItemStack(ModItems.MUSKELLUNGE),
                            Text.literal("That's Not a Sausage!"), Text.literal("Please Clean The Fish First Don't Just Eat It Straight"),
                            Optional.of(new Identifier(YouNeedBait.MOD_ID, "textures/block/water.png")), AdvancementFrame.TASK,
                            true, true, false))
                    .criterion("has_muskellunge", InventoryChangedCriterion.Conditions.items(ModItems.MUSKELLUNGE))
                    .parent(MutilatedFlesh)
                    .build(consumer, YouNeedBait.MOD_ID + ":muskellunge");

        AdvancementEntry Northernpike = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.NORTHERNPIKE),
                        Text.literal("Northern!"), Text.literal("You Caught A Northern"),
                        Optional.of(new Identifier(YouNeedBait.MOD_ID, "textures/block/water.png")), AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_northernpike", InventoryChangedCriterion.Conditions.items(ModItems.NORTHERNPIKE))
                .parent(MutilatedFlesh)
                .build(consumer, YouNeedBait.MOD_ID + ":northernpike");

        AdvancementEntry Largemouthbass = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.LARGEMOUTHBASS),
                        Text.literal("Largemouthbass!"), Text.literal("You Caught A Largemouthbass"),
                        Optional.of(new Identifier(YouNeedBait.MOD_ID, "textures/block/water.png")), AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_largemouthbass", InventoryChangedCriterion.Conditions.items(ModItems.LARGEMOUTHBASS))
                .parent(MutilatedFlesh)
                .build(consumer, YouNeedBait.MOD_ID + ":largemouthbass");

        AdvancementEntry Blackcrappie = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.BLACKCRAPPIE),
                        Text.literal("Blackcrappie"), Text.literal("You Caught A Blackcrappie"),
                        Optional.of(new Identifier(YouNeedBait.MOD_ID, "textures/block/water.png")), AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_blackcrappie", InventoryChangedCriterion.Conditions.items(ModItems.BLACKCRAPPIE))
                .parent(MutilatedFlesh)
                .build(consumer, YouNeedBait.MOD_ID + ":blackcrappie");

        AdvancementEntry Walleye = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.WALLEYE),
                        Text.literal("Walleye!"), Text.literal("You Caught A Walleye"),
                        Optional.of(new Identifier(YouNeedBait.MOD_ID, "textures/block/water.png")), AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_walleye", InventoryChangedCriterion.Conditions.items(ModItems.WALLEYE))
                .parent(MutilatedFlesh)
                .build(consumer, YouNeedBait.MOD_ID + ":walleye");

//        AdvancementEntry Catfish = Advancement.Builder.create()
//                .display(new AdvancementDisplay(new ItemStack(ModItems.CAT),
//                        Text.literal("UGHHHH IT'S MUDDY!"), Text.literal("You Caught A Catfish"),
//                        Optional.of(new Identifier(YouNeedBait.MOD_ID, "textures/block/water.png")), AdvancementFrame.TASK,
//                        true, true, false))
//                .criterion("has_catfish", InventoryChangedCriterion.Conditions.items(ModItems.CAT))
//                .parent(MutilatedFlesh)
//                .build(consumer, YouNeedBait.MOD_ID + ":catfish");
    }
}
