package net.cookiebrain.youneedbait;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.entity.ModEntities;
import net.cookiebrain.youneedbait.entity.client.*;
import net.cookiebrain.youneedbait.entity.layer.ModModelLayers;
import net.cookiebrain.youneedbait.screen.FancyFishingRodScreen;
import net.cookiebrain.youneedbait.screen.FishCleaningStationScreen;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.screen.ModScreenHandlers;
import net.cookiebrain.youneedbait.screen.TackleBoxScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.util.Identifier;

public class YouNeedBaitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ONION_CROP, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.MUSKELLUNGE, MuskellungeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MUSKELLUNGE, MuskellungeModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.NORTHERNPIKE, NorthernPikeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.NORTHERNPIKE, NorthernPikeModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.LARGEMOUTHBASS, LargeMouthBassRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LARGEMOUTHBASS, LargeMouthBassModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BLACKCRAPPIE, BlackCrappieRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BLACKCRAPPIE, BlackCrappieModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.WALLEYE, WalleyeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WALLEYE, WalleyeModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CATFISH, CatFishRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CATFISH, CatFishModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.PUMPKINSEED, PumpkinSeedRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PUMPKINSEED, PumpkinSeedModel::getTexturedModelData);

//        EntityRendererRegistry.register(ModEntities.GIANTSQUID, GiantSquidRenderer::new);
//        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GIANTSQUID, GiantSquidModel
//                ::getTexturedModelData);

        //Handles the 3d Rendering
        ModelLoadingRegistry.INSTANCE.registerModelProvider(((manager, out) -> new ModelIdentifier(YouNeedBait.MOD_ID,"fancyfishingrod_3d","inventory")));

        HandledScreens.register(ModScreenHandlers.TACKLEBOX_SCREEN_HANDLER, TackleBoxScreen::new);
        HandledScreens.register(ModScreenHandlers.FISH_CLEANING_STATION_SCREEN_HANDLER, FishCleaningStationScreen::new);
        HandledScreens.register(ModScreenHandlers.FANCYFISHINGROD_SCREEN_HANDLER, FancyFishingRodScreen::new);

//        //This is from FishingParadise
        ModelPredicateProviderRegistry.register(ModItems.FANCYFISHINGROD_ITEM, new Identifier("cast"), (stack, world, entity, seed) -> {
            boolean bl2;
            if (entity == null) {
                return 0.0f;
            }
            boolean bl = entity.getMainHandStack() == stack;
            boolean bl3 = bl2 = entity.getOffHandStack() == stack;
            if (entity.getMainHandStack().getItem() instanceof FishingRodItem) {
                bl2 = false;
            }
            return (bl || bl2) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishHook != null ? 1.0f : 0.0f;
        });
    }
}
