package net.cookiebrain.youneedbait.item;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.entity.ModEntities;
import net.cookiebrain.youneedbait.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MUSKELLUNGE = registerItem("muskellunge", new MuskellungeItem(new FabricItemSettings()));
    public static final Item NORTHERNPIKE = registerItem("northernpike", new NorthernpikeItem(new FabricItemSettings()));
    public static final Item BLACKCRAPPIE = registerItem("blackcrappie", new BlackcrappieItem(new FabricItemSettings()));
    public static final Item WALLEYE = registerItem("walleye", new WalleyeItem(new FabricItemSettings()));
    public static final Item LARGEMOUTHBASS = registerItem("largemouthbass", new LargemouthbassItem(new FabricItemSettings()));
    public static final Item REDHERRING = registerItem("redherring", new Item(new FabricItemSettings()));
    public static final Item SALT = registerItem("salt", new Item(new FabricItemSettings()));
    public static final Item AZUROMITE_INGOT = registerItem("azuromite_ingot", new Item(new FabricItemSettings()));
    public static final Item HOOK = registerItem("hook", new Item(new FabricItemSettings()));
    public static final Item WORM = registerItem("worm", new Item(new FabricItemSettings()));
    public static final Item CATERPILLAR = registerItem("caterpillar", new Item(new FabricItemSettings()));

    public static final Item RAWFISHFILET = registerItem("rawfishfilet", new Item(new FabricItemSettings().food(ModFoodComponents.RAWFISHFILET)));
    public static final Item SALTEDFISHFILET = registerItem("saltedfishfilet", new Item(new FabricItemSettings().food(ModFoodComponents.SALTEDFISHFILET)));
    public static final Item SALTEDBEEF = registerItem("saltedbeef", new Item(new FabricItemSettings().food(ModFoodComponents.SALTEDBEEF)));
    public static final Item DRIEDFISHFILET = registerItem("driedfishfilet", new Item(new FabricItemSettings().food(ModFoodComponents.DRIEDFISHFILET)));
    public static final Item BEEFJERKY = registerItem("beefjerky", new Item(new FabricItemSettings().food(ModFoodComponents.BEEFJERKY)));
    public static final Item ONION = registerItem("onion_crop", new Item(new FabricItemSettings().food(ModFoodComponents.ONION)));
    public static final Item MUTILATED_FLESH = registerItem("mutilated_flesh", new Item(new FabricItemSettings().food(ModFoodComponents.MUTILATED_FLESH)));
    public static final Item ONION_BULBS = registerItem("onion_bulbs", new AliasedBlockItem(ModBlocks.ONION_CROP, new FabricItemSettings()));
    public static final Item LESSSUSPICIOUSSTEW_ITEM = registerItem("lesssuspiciousstew", new Item(new FabricItemSettings().food(ModFoodComponents.LESSSUSPICIOUSSTEW_FOOD)));

    public static final Item FILETKNIFE_ITEM = registerItem("filetknife", new Item(new FabricItemSettings()));
    public static final Item MINNOW_ITEM = registerItem("minnow", new Item(new FabricItemSettings()));
    public static final Item SUCKERMINNOW_ITEM = registerItem("suckerminnow", new Item(new FabricItemSettings()));

    //Spawn Eggs
    public static final Item MUSKELLUNGESPAWNEGG = registerItem("muskellungespawnegg",
            new SpawnEggItem(ModEntities.MUSKELLUNGE, 669900, 73426, new FabricItemSettings()));
    public static final Item NORTHERNPIKESPAWNEGG = registerItem("northernpikespawnegg",
            new SpawnEggItem(ModEntities.NORTHERNPIKE, 669900, 73426, new FabricItemSettings()));
    public static final Item LARGEMOUTHBASSSPAWNEGG = registerItem("largemouthbassspawnegg",
            new SpawnEggItem(ModEntities.LARGEMOUTHBASS, 669900, 73426, new FabricItemSettings()));
    public static final Item BLACKCRAPPIESPAWNEGG = registerItem("blackcrappiespawnegg",
            new SpawnEggItem(ModEntities.BLACKCRAPPIE, 669900, 73426, new FabricItemSettings()));
    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(YouNeedBait.MOD_ID, name), item);
    }


    public static void registerModItems() {
        YouNeedBait.LOGGER.info("Registering Mod Items For " + YouNeedBait.MOD_ID);

    }
}
