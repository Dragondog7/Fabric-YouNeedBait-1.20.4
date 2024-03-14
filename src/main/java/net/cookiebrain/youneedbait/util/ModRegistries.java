package net.cookiebrain.youneedbait.util;

import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.villager.ModVillagers;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class ModRegistries {
    public static void registerModStuffs() {
        registerCustomTrades();
    }






    private static void registerCustomTrades() {
            TradeOfferHelper.registerVillagerOffers(ModVillagers.MASTER_FISHERMAN, 1,
                    factories -> {
                        factories.add((entity, random) -> new TradeOffer(
                                new ItemStack(Items.EMERALD, 4),
                                new ItemStack(ModItems.FILETKNIFE_ITEM, 1), 1, 6, 0.08f
                        ));
                    });

    }
}
