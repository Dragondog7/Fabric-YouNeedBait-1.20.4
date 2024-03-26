package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.custom.MuskellungeEntity;
import net.cookiebrain.youneedbait.entity.custom.NorthernPikeEntity;
import net.cookiebrain.youneedbait.entity.layer.ModModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class NorthernPikeRenderer extends MobEntityRenderer<NorthernPikeEntity,NorthernPikeModel<NorthernPikeEntity>> {
    private static final Identifier NORTHERPIKE_TEXTURE = new Identifier(YouNeedBait.MOD_ID,"textures/entity/northernpike.png");
    public NorthernPikeRenderer(EntityRendererFactory.Context context) {
        //the 0.6 is the size of the shadow
        super(context, new NorthernPikeModel<>(context.getPart(ModModelLayers.NORTHERNPIKE)), 0.6f);
    }

    @Override
    public Identifier getTexture(NorthernPikeEntity entity) {
        return NORTHERPIKE_TEXTURE;
    }

    @Override
    public void render(NorthernPikeEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        //Sets the size of the entity scaling .5 would be half size. All three need to match
        matrixStack.scale(1f,1f,1f);

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
