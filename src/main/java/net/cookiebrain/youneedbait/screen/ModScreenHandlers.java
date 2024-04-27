package net.cookiebrain.youneedbait.screen;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static final ScreenHandlerType<TackleBoxScreenHandler> TACKLEBOX_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(YouNeedBait.MOD_ID, "tacklebox_screen_handler"),
                    new ExtendedScreenHandlerType<>(TackleBoxScreenHandler::new));


    public static final ScreenHandlerType<FishCleaningStationScreenHandler> FISH_CLEANING_STATION_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(YouNeedBait.MOD_ID, "fish_cleaning_station_screen_handler"),
                    new ExtendedScreenHandlerType<>(FishCleaningStationScreenHandler::new));

    public static void registerScreenHandler() {
        YouNeedBait.LOGGER.info("Registering Screen Handlers for " + YouNeedBait.MOD_ID);
    }
}
