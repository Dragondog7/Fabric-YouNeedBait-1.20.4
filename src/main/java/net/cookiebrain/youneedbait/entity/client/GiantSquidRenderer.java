package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.custom.CatFishEntity;
import net.cookiebrain.youneedbait.entity.custom.GiantSquidEntity;
import net.cookiebrain.youneedbait.entity.layer.ModModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GiantSquidRenderer extends MobEntityRenderer<GiantSquidEntity,GiantSquidModel<GiantSquidEntity>> {
    private static final Identifier GIANTSQUID_TEXTURE = new Identifier(YouNeedBait.MOD_ID,"textures/entity/giantsquid.png");
    public GiantSquidRenderer(EntityRendererFactory.Context context) {
        //the 0.6 is the size of the shadow
        super(context, new GiantSquidModel<>(context.getPart(ModModelLayers.GIANTSQUID)), 0.6f);
    }

    @Override
    public Identifier getTexture(GiantSquidEntity entity) {
        return GIANTSQUID_TEXTURE;
    }

    @Override
    public void render(GiantSquidEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        //Sets the size of the entity scaling .5 would be half size. All three need to match
        matrixStack.scale(1f,1f,1f);

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
