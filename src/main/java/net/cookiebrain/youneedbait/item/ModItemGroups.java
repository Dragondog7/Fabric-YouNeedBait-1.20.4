package net.cookiebrain.youneedbait.item;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup YOUNEEDBAIT_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(YouNeedBait.MOD_ID, "youneedbait"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.youneedbait"))
                    .icon(() -> new ItemStack(ModItems.MUSKELLUNGE)).entries((displayContext, entries) -> {
                        //Items
                        entries.add(ModItems.SALT);
                        entries.add(ModItems.AZUROMITE_INGOT);
                        entries.add(ModItems.HOOK);
                        entries.add(ModItems.FILETKNIFE_ITEM);
                        entries.add(ModItems.ONION_BULBS);

                        //Bait
                        entries.add(ModItems.WORM);
                        entries.add(ModItems.CATERPILLAR);
                        entries.add(ModItems.MINNOW_ITEM);
                        entries.add(ModItems.SUCKERMINNOW_ITEM);

                        //Food
                        entries.add(ModItems.RAWFISHFILET);
                        entries.add(ModItems.SALTEDFISHFILET);
                        entries.add(ModItems.DRIEDFISHFILET);
                        entries.add(ModItems.BEEFJERKY);
                        entries.add(ModItems.ONION);
                        entries.add(ModItems.LESSSUSPICIOUSSTEW_ITEM);

                        //Fish
                        entries.add(ModItems.MUSKELLUNGE);
                        entries.add(ModItems.NORTHERNPIKE);
                        entries.add(ModItems.BLACKCRAPPIE);
                        entries.add(ModItems.LARGEMOUTHBASS);
                        entries.add(ModItems.WALLEYE);
                        entries.add(ModItems.SALTEDBEEF);
                        entries.add(ModItems.MUSKELLUNGESPAWNEGG);
                        entries.add(ModItems.NORTHERNPIKESPAWNEGG);
                        entries.add(ModItems.LARGEMOUTHBASSSPAWNEGG);
                        entries.add(ModItems.BLACKCRAPPIESPAWNEGG);

                        //Blocks
                        entries.add(ModBlocks.SALT_BLOCK);
                        entries.add(ModBlocks.MINNOWTRAP_BLOCK);
                        entries.add(ModBlocks.TACKLEBOX_BLOCK);


                    }).build());


    public static void registerItemGroups() {
        YouNeedBait.LOGGER.info("Registering Item Groups for " +YouNeedBait.MOD_ID);
    }
}