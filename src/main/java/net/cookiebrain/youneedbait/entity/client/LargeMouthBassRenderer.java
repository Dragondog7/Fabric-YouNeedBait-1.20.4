package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.custom.LargeMouthBassEntity;
import net.cookiebrain.youneedbait.entity.layer.ModModelLayers;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LargeMouthBassRenderer extends MobEntityRenderer<LargeMouthBassEntity,LargeMouthBassModel<LargeMouthBassEntity>> {
    private static final Identifier LARGEMOUTHBASS_TEXTURE = new Identifier(YouNeedBait.MOD_ID,"textures/entity/largemouthbass.png");
    public LargeMouthBassRenderer(EntityRendererFactory.Context context) {
        //the 0.6 is the size of the shadow
        super(context, new LargeMouthBassModel<>(context.getPart(ModModelLayers.LARGEMOUTHBASS)), 0.6f);
    }

    @Override
    public Identifier getTexture(LargeMouthBassEntity entity) {
        return LARGEMOUTHBASS_TEXTURE;
    }

    @Override
    public void render(LargeMouthBassEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        //Sets the size of the entity scaling .5 would be half size. All three need to match
        matrixStack.scale(1f,1f,1f);

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
