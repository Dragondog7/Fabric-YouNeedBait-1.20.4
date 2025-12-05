package net.cookiebrain.youneedbait.util;

import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.villager.ModVillagers;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class ModCustomTrades {

    public static void registerCustomTrades() {

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD,2),
                            new ItemStack(ModItems.MINNOW_ITEM,6),
                            3,8,0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD,8),
                            new ItemStack(ModItems.SUCKERMINNOW_ITEM,4),
                            3,8,0.02f));
                });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD,4),
                            new ItemStack(ModItems.ONION_BULBS,8),
                            3,8,0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD,4),
                            new ItemStack(ModItems.ONION,4),
                            3,8,0.02f));
                });

        //Master Fisherman Trades
// MASTER FISHERMAN - Level 1 (Novice): Basic bait and tools
        TradeOfferHelper.registerVillagerOffers(ModVillagers.MASTER_FISHERMAN, 1,
                factories -> {
                    // Buy common bait from player
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.WORM, 16),
                            new ItemStack(Items.EMERALD, 1),
                            12, 2, 0.05f));

                    // Sell basic bait
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(ModItems.CATERPILLAR, 8),
                            8, 5, 0.05f));

                    // Sell fillet knife (your existing trade, slightly adjusted)
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.FILETKNIFE_ITEM, 1),
                            3, 5, 0.05f));
                });

// MASTER FISHERMAN - Level 2 (Apprentice): Better bait and hooks
        TradeOfferHelper.registerVillagerOffers(ModVillagers.MASTER_FISHERMAN, 2,
                factories -> {
                    // Buy minnows from player
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.MINNOW_ITEM, 8),
                            new ItemStack(Items.EMERALD, 1),
                            12, 5, 0.05f));

                    // Sell leeches
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.LEECH, 6),
                            8, 10, 0.05f));

                    // Sell hooks
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 5),
                            new ItemStack(ModItems.HOOK, 1),
                            5, 10, 0.05f));
                });

// MASTER FISHERMAN - Level 3 (Journeyman): Premium bait and food
        TradeOfferHelper.registerVillagerOffers(ModVillagers.MASTER_FISHERMAN, 3,
                factories -> {
                    // Buy raw fish from player
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.COD, 10),
                            new ItemStack(Items.EMERALD, 1),
                            12, 10, 0.05f));

                    // Sell nightcrawlers (premium bait)
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 5),
                            new ItemStack(ModItems.NIGHTCRAWLER, 4),
                            8, 15, 0.05f));

                    // Sell can of worms
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 8),
                            new ItemStack(ModItems.CANOFWORMS, 1),
                            5, 15, 0.05f));

                    // Sell dried fish filet (prepared food)
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 4),
                            new ItemStack(ModItems.DRIEDFISHFILET, 6),
                            8, 15, 0.05f));
                });

// MASTER FISHERMAN - Level 4 (Expert): Rare items and materials
        TradeOfferHelper.registerVillagerOffers(ModVillagers.MASTER_FISHERMAN, 4,
                factories -> {
                    // Buy sucker minnows from player
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.SUCKERMINNOW_ITEM, 6),
                            new ItemStack(Items.EMERALD, 2),
                            12, 20, 0.05f));

                    // Buy salmon from player
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.SALMON, 8),
                            new ItemStack(Items.EMERALD, 1),
                            12, 20, 0.05f));

                    // Sell salt (for preservation)
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.SALT, 12),
                            10, 20, 0.05f));

                    // Sell enchanted fishing rod
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 12),
                            new ItemStack(Items.FISHING_ROD, 1),
                            3, 20, 0.05f));
                });

// MASTER FISHERMAN - Level 5 (Master): THE FANCY FISHING ROD!
        TradeOfferHelper.registerVillagerOffers(ModVillagers.MASTER_FISHERMAN, 5,
                factories -> {
                    // Buy tropical fish from player (rare)
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.TROPICAL_FISH, 6),
                            new ItemStack(Items.EMERALD, 2),
                            12, 30, 0.05f));

                    // Buy pufferfish from player (rare)
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.PUFFERFISH, 4),
                            new ItemStack(Items.EMERALD, 3),
                            12, 30, 0.05f));

                    // Sell azuromite ingot (rare crafting material)
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 15),
                            new ItemStack(ModItems.AZUROMITE_INGOT, 1),
                            3, 30, 0.05f));

                    // THE MAIN EVENT: Fancy Fishing Rod
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 18),
                            new ItemStack(ModItems.FANCYFISHINGROD_ITEM, 1),
                            2, 30, 0.05f));

                    // Alternative: Fancy rod with materials trade
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 10),
                            new ItemStack(ModItems.AZUROMITE_INGOT, 2),
                            new ItemStack(ModItems.FANCYFISHINGROD_ITEM, 1),
                            2, 30, 0.05f));
                });
    }
}
