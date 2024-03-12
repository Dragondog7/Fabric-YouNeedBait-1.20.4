package net.cookiebrain.youneedbait;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.entity.ModEntities;
import net.cookiebrain.youneedbait.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

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
    }
}
