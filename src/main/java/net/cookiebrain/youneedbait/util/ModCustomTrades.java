package net.cookiebrain.youneedbait.util;

import net.cookiebrain.youneedbait.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Item;
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

    }
}
