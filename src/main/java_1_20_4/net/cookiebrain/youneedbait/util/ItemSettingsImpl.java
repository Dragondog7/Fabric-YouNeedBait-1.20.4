package net.cookiebrain.youneedbait.util;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class ItemSettingsImpl {

    public static Item.Settings create() {
        return new FabricItemSettings();
    }
}