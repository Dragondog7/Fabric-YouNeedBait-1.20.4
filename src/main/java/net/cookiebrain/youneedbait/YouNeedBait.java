package net.cookiebrain.youneedbait;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.entity.ModEntities;
import net.cookiebrain.youneedbait.entity.custom.MuskellungeEntity;
import net.cookiebrain.youneedbait.entity.custom.NorthernPikeEntity;
import net.cookiebrain.youneedbait.item.ModItemGroups;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.util.ModCustomTrades;
import net.cookiebrain.youneedbait.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YouNeedBait implements ModInitializer {
	public static String MOD_ID = "youneedbait";
    public static final Logger LOGGER = LoggerFactory.getLogger("Mod_ID");

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModLootTableModifiers.modifyLootTables();
		ModCustomTrades.registerCustomTrades();

		FabricDefaultAttributeRegistry.register(ModEntities.MUSKELLUNGE, MuskellungeEntity.createMuskellungeAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.NORTHERNPIKE, NorthernPikeEntity.createNorthernPikeAttributes());

	}
}