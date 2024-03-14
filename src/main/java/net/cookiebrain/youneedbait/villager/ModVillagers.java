package net.cookiebrain.youneedbait.villager;

import com.google.common.collect.ImmutableSet;
import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.client.sound.Sound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;


public class ModVillagers {
    public static final RegistryKey<PointOfInterestType> SOUND_POI_KEY = registerPoiKey("soundpoi");



    public static final PointOfInterestType SOUND_POI = registerPoi("soundpoi",ModBlocks.SALT_BLOCK);

    public static final VillagerProfession MASTER_FISHERMAN = registerProfession("masterfisherman", SOUND_POI_KEY);





    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(YouNeedBait.MOD_ID, name),
                new VillagerProfession(name, entry -> true, entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_FISHERMAN));
    }


    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(YouNeedBait.MOD_ID, name),
                1, 1, block);
    }


    private static RegistryKey<PointOfInterestType> registerPoiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(YouNeedBait.MOD_ID, name));
    }






    public static void registerVillagers() {
        YouNeedBait.LOGGER.info("Registering Villagers for " + YouNeedBait.MOD_ID);
    }
}
