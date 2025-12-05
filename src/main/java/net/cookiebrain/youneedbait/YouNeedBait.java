package net.cookiebrain.youneedbait;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.block.entity.ModBlockEntities;
import net.cookiebrain.youneedbait.effect.ModEffects;
import net.cookiebrain.youneedbait.entity.ModEntities;
import net.cookiebrain.youneedbait.entity.custom.*;
import net.cookiebrain.youneedbait.item.ModItemGroups;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.screen.ModScreenHandlers;
import net.cookiebrain.youneedbait.util.ModCustomTrades;
import net.cookiebrain.youneedbait.util.ModLootTableModifiers;
import net.cookiebrain.youneedbait.world.gen.ModWorldGeneration;
import net.cookiebrain.youneedbait.util.ModRegistries;
import net.cookiebrain.youneedbait.villager.ModVillagers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YouNeedBait implements ModInitializer {
	public static String MOD_ID = "youneedbait";

	// Read from fabric.mod.json at runtime. Never hardcode here.
	public static final String VERSION = FabricLoader.getInstance()
			.getModContainer(MOD_ID)
			.map(mc -> mc.getMetadata().getVersion().getFriendlyString())
			.orElse("dev");
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing {} v{}", MOD_ID, VERSION);

		// Initialize config using centralized MOD_ID
		net.cookiebrain.youneedbait.config.ConfigManager.init();

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModLootTableModifiers.modifyLootTables();
		ModCustomTrades.registerCustomTrades();
		ModWorldGeneration.generateModWorldGen();

		ModVillagers.registerVillagers();
		ModRegistries.registerModStuffs();
		ModScreenHandlers.registerScreenHandler();
		ModEffects.registerEffects();

		FabricDefaultAttributeRegistry.register(ModEntities.MUSKELLUNGE, MuskellungeEntity.createMuskellungeAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.NORTHERNPIKE, NorthernPikeEntity.createNorthernPikeAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.LARGEMOUTHBASS, LargeMouthBassEntity.createLargemouthbassAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.BLACKCRAPPIE, BlackCrappieEntity.createblackcrappieAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.WALLEYE, WalleyeEntity.createwalleyeAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.CATFISH, CatFishEntity.createcatfishAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.PUMPKINSEED, PumpkinSeedEntity.createpumpkinseedAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.LARGEFISH, LargeFishEntity.createLargeFishAttributes());

//		FabricDefaultAttributeRegistry.register(ModEntities.GIANTSQUID, GiantSquidEntity.creategiantsquidAttributes());

	}
}