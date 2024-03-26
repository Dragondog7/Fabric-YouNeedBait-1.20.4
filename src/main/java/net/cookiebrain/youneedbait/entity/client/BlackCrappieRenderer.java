package net.cookiebrain.youneedbait.entity.client;

import com.google.common.collect.Maps;
import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.custom.BlackCrappieEntity;
import net.cookiebrain.youneedbait.entity.custom.LargeMouthBassEntity;
import net.cookiebrain.youneedbait.entity.layer.ModModelLayers;
import net.cookiebrain.youneedbait.entity.variant.CrappieVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class BlackCrappieRenderer extends MobEntityRenderer<BlackCrappieEntity,BlackCrappieModel<BlackCrappieEntity>> {

    private static final Map<CrappieVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(CrappieVariant.class), map -> {
                map.put(CrappieVariant.DEFAULT,
                        new Identifier(YouNeedBait.MOD_ID,"textures/entity/blackcrappie.png"));
                map.put(CrappieVariant.WHITE,
                        new Identifier(YouNeedBait.MOD_ID,"textures/entity/whitecrappie.png"));
            });

    public BlackCrappieRenderer(EntityRendererFactory.Context context) {
        //the 0.6 is the size of the shadow
        super(context, new BlackCrappieModel<>(context.getPart(ModModelLayers.BLACKCRAPPIE)), 0.6f);
    }

    @Override
    public Identifier getTexture(BlackCrappieEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(BlackCrappieEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        //Sets the size of the entity scaling .5 would be half size. All three need to match
        matrixStack.scale(1f,1f,1f);

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
