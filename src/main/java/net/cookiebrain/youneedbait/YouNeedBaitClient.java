package net.cookiebrain.youneedbait;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.entity.ModEntities;
import net.cookiebrain.youneedbait.entity.client.*;
import net.cookiebrain.youneedbait.entity.layer.ModModelLayers;
import net.cookiebrain.youneedbait.screen.ModScreenHandlers;
import net.cookiebrain.youneedbait.screen.TackleBoxScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

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

        EntityRendererRegistry.register(ModEntities.GIANTSQUID, GiantSquidRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GIANTSQUID, GiantSquidModel
                ::getTexturedModelData);

        HandledScreens.register(ModScreenHandlers.TACKLEBOX_SCREEN_HANDLER, TackleBoxScreen::new);
    }
}
