package net.cookiebrain.youneedbait.item;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.entity.ModEntities;
import net.cookiebrain.youneedbait.item.custom.*;
import net.cookiebrain.youneedbait.util.ItemSettingsImpl;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MUSKELLUNGE = registerItem("muskellunge", new MuskellungeItem(ItemSettingsImpl.create()));
    public static final Item NORTHERNPIKE = registerItem("northernpike", new NorthernpikeItem(ItemSettingsImpl.create()));
    public static final Item BLACKCRAPPIE = registerItem("blackcrappie", new BlackcrappieItem(ItemSettingsImpl.create()));
    public static final Item WALLEYE = registerItem("walleye", new WalleyeItem(ItemSettingsImpl.create()));
    public static final Item LARGEMOUTHBASS = registerItem("largemouthbass", new LargemouthbassItem(ItemSettingsImpl.create()));
    public static final Item REDHERRING = registerItem("redherring", new Item(ItemSettingsImpl.create()));
    public static final Item PUMPKINSEED = registerItem("pumpkinseed", new PumpkinSeedItem(ItemSettingsImpl.create()));
    public static final Item CATFISH = registerItem("catfish", new CatFishItem(ItemSettingsImpl.create()));
    //public static final Item LARGEFISH = registerItem("largefish", new LargeFishItem(ItemSettingsImpl.create()));

    public static final Item SALT = registerItem("salt", new Item(ItemSettingsImpl.create()));
    public static final Item AZUROMITE_INGOT = registerItem("azuromite_ingot", new Item(ItemSettingsImpl.create()));

    public static final Item HOOK = registerItem("hook", new Item(ItemSettingsImpl.create().maxCount(1)));
    public static final Item WORM = registerItem("worm", new Item(ItemSettingsImpl.create()));
    public static final Item CATERPILLAR = registerItem("caterpillar", new Item(ItemSettingsImpl.create()));
    public static final Item CANOFWORMS = registerItem("canofworms", new CanOfWormsItem(ItemSettingsImpl.create()));
    public static final Item LEECH = registerItem("leech", new Item(ItemSettingsImpl.create()));
    public static final Item NIGHTCRAWLER = registerItem("nightcrawler", new Item(ItemSettingsImpl.create()));
    public static final Item MINNOW_ITEM = registerItem("minnow", new Item(ItemSettingsImpl.create()));
    public static final Item SUCKERMINNOW_ITEM = registerItem("suckerminnow", new Item(ItemSettingsImpl.create()));

    public static final Item FANCYFISHINGROD_ITEM = registerItem("fancyfishingrod", new FancyFishingRodItem(new Item.Settings().maxCount(1).maxDamage(256)));

    public static final Item RAWFISHFILET = registerItem("rawfishfilet", new Item(ItemSettingsImpl.create().food(ModFoodComponents.RAWFISHFILET)));
    public static final Item SALTEDFISHFILET = registerItem("saltedfishfilet", new Item(ItemSettingsImpl.create().food(ModFoodComponents.SALTEDFISHFILET)));
    public static final Item SALTEDBEEF = registerItem("saltedbeef", new Item(ItemSettingsImpl.create().food(ModFoodComponents.SALTEDBEEF)));
    public static final Item DRIEDFISHFILET = registerItem("driedfishfilet", new Item(ItemSettingsImpl.create().food(ModFoodComponents.DRIEDFISHFILET)));
    public static final Item BEEFJERKY = registerItem("beefjerky", new Item(ItemSettingsImpl.create().food(ModFoodComponents.BEEFJERKY)));
    public static final Item ONION = registerItem("onion_crop", new Item(ItemSettingsImpl.create().food(ModFoodComponents.ONION)));
    public static final Item MUTILATED_FLESH = registerItem("mutilated_flesh", new Item(ItemSettingsImpl.create().food(ModFoodComponents.MUTILATED_FLESH)));
    public static final Item ONION_BULBS = registerItem("onion_bulbs", new AliasedBlockItem(ModBlocks.ONION_CROP, ItemSettingsImpl.create()));
    public static final Item LESSSUSPICIOUSSTEW_ITEM = registerItem("lesssuspiciousstew", new Item(ItemSettingsImpl.create().food(ModFoodComponents.LESSSUSPICIOUSSTEW_FOOD)));

    public static final Item FILETKNIFE_ITEM = registerItem("filetknife", new Item(ItemSettingsImpl.create().maxCount(1)));
    //Spawn Eggs
    public static final Item MUSKELLUNGESPAWNEGG = registerItem("muskellungespawnegg",
            new SpawnEggItem(ModEntities.MUSKELLUNGE, 0x669900, 0x734d26, ItemSettingsImpl.create()));
    public static final Item NORTHERNPIKESPAWNEGG = registerItem("northernpikespawnegg",
            new SpawnEggItem(ModEntities.NORTHERNPIKE, 0xace600, 0xdfdf20, ItemSettingsImpl.create()));
    public static final Item LARGEMOUTHBASSSPAWNEGG = registerItem("largemouthbassspawnegg",
            new SpawnEggItem(ModEntities.LARGEMOUTHBASS, 0x5a5609, 0x20180d, ItemSettingsImpl.create()));
    public static final Item BLACKCRAPPIESPAWNEGG = registerItem("blackcrappiespawnegg",
            new SpawnEggItem(ModEntities.BLACKCRAPPIE, 0xccedab, 0xeeee2b, ItemSettingsImpl.create()));
    public static final Item WALLEYESPAWNEGG = registerItem("walleyespawnegg",
            new SpawnEggItem(ModEntities.WALLEYE, 0xc4e242, 0x433b00, ItemSettingsImpl.create()));
    public static final Item PUMPKINSEEDSPAWNEGG = registerItem("pumpkinseedspawnegg",
            new SpawnEggItem(ModEntities.PUMPKINSEED, 0xc4e242, 0x433b00, ItemSettingsImpl.create()));
    public static final Item CATFISHSPAWNEGG = registerItem("catfishspawnegg",
            new SpawnEggItem(ModEntities.CATFISH, 0xc4e242, 0x433b00, ItemSettingsImpl.create()));
    //public static final Item LARGEFISHSPAWNEGG = registerItem("largefishspawnegg",
    //        new SpawnEggItem(ModEntities.LARGEFISH, 0xc4e242, 0x433b00, ItemSettingsImpl.create()));

    //    public static final Item GIANTSQUIDSPAWNEGG = registerItem("giantsquidspawnegg",
//            new SpawnEggItem(ModEntities.GIANTSQUID, 669900, 73426, ItemSettingsImpl.create()));

    public static void addItemsToToolsItemGroup(FabricItemGroupEntries entries){
        entries.add(FANCYFISHINGROD_ITEM);
    }
    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(YouNeedBait.MOD_ID, name), item);
    }

    public static void registerModItems() {
        YouNeedBait.LOGGER.info("Registering Mod Items For " + YouNeedBait.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
    }
}
