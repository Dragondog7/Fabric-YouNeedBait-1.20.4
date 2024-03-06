package net.cookiebrain.youneedbait;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.item.ModItemGroups;
import net.cookiebrain.youneedbait.item.ModItems;
import net.fabricmc.api.ModInitializer;

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
	}
}