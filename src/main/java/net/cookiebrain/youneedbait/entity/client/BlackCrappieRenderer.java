package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.custom.BlackCrappieEntity;
import net.cookiebrain.youneedbait.entity.custom.LargeMouthBassEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlackCrappieRenderer extends MobEntityRenderer<BlackCrappieEntity,BlackCrappieModel<BlackCrappieEntity>> {
    private static final Identifier BLACKCRAPPIE_TEXTURE = new Identifier(YouNeedBait.MOD_ID,"textures/entity/blackcrappie.png");
    public BlackCrappieRenderer(EntityRendererFactory.Context context) {
        //the 0.6 is the size of the shadow
        super(context, new BlackCrappieModel<>(context.getPart(ModModelLayers.BLACKCRAPPIE)), 0.6f);
    }

    @Override
    public Identifier getTexture(BlackCrappieEntity entity) {
        return BLACKCRAPPIE_TEXTURE;
    }

    @Override
    public void render(BlackCrappieEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        //Sets the size of the entity scaling .5 would be half size. All three need to match
        matrixStack.scale(1f,1f,1f);

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
