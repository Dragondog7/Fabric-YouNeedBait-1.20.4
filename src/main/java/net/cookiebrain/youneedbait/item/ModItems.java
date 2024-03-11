package net.cookiebrain.youneedbait.item;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MUSKELLUNGE = registerItem("muskellunge", new Item(new FabricItemSettings()));
    public static final Item NORTHERNPIKE = registerItem("northernpike", new Item(new FabricItemSettings()));
    public static final Item BLACKCRAPPIE = registerItem("blackcrappie", new Item(new FabricItemSettings()));
    public static final Item WALLEYE = registerItem("walleye", new Item(new FabricItemSettings()));
    public static final Item LARGEMOUTHBASS = registerItem("largemouthbass", new Item(new FabricItemSettings()));
    public static final Item REDHERRING = registerItem("redherring", new Item(new FabricItemSettings()));
    public static final Item SALT = registerItem("salt", new Item(new FabricItemSettings()));
    public static final Item HOOK = registerItem("hook", new Item(new FabricItemSettings()));
    public static final Item WORM = registerItem("worm", new Item(new FabricItemSettings()));
    public static final Item CATERPILLAR = registerItem("caterpillar", new Item(new FabricItemSettings()));

    public static final Item RAWFISHFILET = registerItem("rawfishfilet", new Item(new FabricItemSettings().food(ModFoodComponents.RAWFISHFILET)));
    public static final Item SALTEDFISHFILET = registerItem("saltedfishfilet", new Item(new FabricItemSettings().food(ModFoodComponents.SALTEDFISHFILET)));
    public static final Item SALTEDBEEF = registerItem("saltedbeef", new Item(new FabricItemSettings().food(ModFoodComponents.SALTEDBEEF)));
    public static final Item DRIEDFISHFILET = registerItem("driedfishfilet", new Item(new FabricItemSettings().food(ModFoodComponents.DRIEDFISHFILET)));
    public static final Item BEEFJERKY = registerItem("beefjerky", new Item(new FabricItemSettings().food(ModFoodComponents.BEEFJERKY)));

    public static final Item FILETKNIFE_ITEM = registerItem("filetknife", new Item(new FabricItemSettings()));
    public static final Item MINNOW_ITEM = registerItem("minnow", new Item(new FabricItemSettings()));
    public static final Item SUCKERMINNOW_ITEM = registerItem("suckerminnow", new Item(new FabricItemSettings()));
    public static final Item MUSKELLUNGESPAWNEGG = registerItem("muskellungespawnegg",
            new SpawnEggItem(ModEntities.MUSKELLUNGE, 669900, 73426, new FabricItemSettings()));
    public static final Item NORTHERNPIKESPAWNEGG = registerItem("northernpikespawnegg",
            new SpawnEggItem(ModEntities.MUSKELLUNGE, 669900, 73426, new FabricItemSettings()));
    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(YouNeedBait.MOD_ID, name), item);
    }


    public static void registerModItems() {
        YouNeedBait.LOGGER.info("Registering Mod Items For " + YouNeedBait.MOD_ID);

    }
}
