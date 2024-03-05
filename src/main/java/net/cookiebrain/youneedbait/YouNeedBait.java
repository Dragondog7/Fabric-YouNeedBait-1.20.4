package net.cookiebrain.youneedbait;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YouNeedBait implements ModInitializer {
	public static String MOD_ID = "youneedbait";
    public static final Logger LOGGER = LoggerFactory.getLogger("Mod_ID");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}