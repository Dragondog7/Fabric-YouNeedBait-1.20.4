package net.cookiebrain.youneedbait;

import net.cookiebrain.youneedbait.entity.ModEntities;
import net.cookiebrain.youneedbait.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class YouNeedBaitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.MUSKELLUNGE, MuskellungeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MUSKELLUNGE, MuskellungeModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.NORTHERNPIKE, NorthernPikeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.NORTHERNPIKE, NorthernPikeModel::getTexturedModelData);
    }
}
