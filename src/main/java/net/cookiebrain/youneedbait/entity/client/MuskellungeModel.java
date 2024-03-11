package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.entity.animation.ModAnimations;
import net.cookiebrain.youneedbait.entity.custom.MuskellungeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class MuskellungeModel<T extends MuskellungeEntity> extends SinglePartEntityModel<T> {
    private final ModelPart muskellunge;
    public MuskellungeModel(ModelPart root) {
        this.muskellunge = root.getChild("muskellunge");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Musky = modelPartData.addChild("muskellunge", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData LowerJaw = Musky.addChild("LowerJaw", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r1 = LowerJaw.addChild("cube_r1", ModelPartBuilder.create().uv(12, 4).cuboid(-1.5F, -5.8F, -18.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 18).cuboid(-1.5F, -5.8F, -19.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 20).cuboid(-1.5F, -5.8F, -20.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 29).cuboid(-1.0F, -5.8F, -21.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 29).cuboid(-1.0F, -5.8F, -22.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(30, 0).cuboid(-0.5F, -5.8F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

        ModelPartData UpperJaw = Musky.addChild("UpperJaw", ModelPartBuilder.create().uv(18, 12).cuboid(1.5F, -8.5F, -19.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(18, 10).cuboid(-1.5F, -8.5F, -19.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r2 = UpperJaw.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -8.1F, -22.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(30, 2).cuboid(-0.5F, -8.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 29).cuboid(-1.0F, -8.0F, -22.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(18, 29).cuboid(-1.0F, -8.0F, -21.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 0).cuboid(-1.5F, -8.3F, -20.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 27).cuboid(-1.5F, -8.0F, -20.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 27).cuboid(-1.5F, -8.0F, -18.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 27).cuboid(-1.5F, -9.0F, -18.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 2).cuboid(-1.5F, -8.8F, -19.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 27).cuboid(-1.5F, -8.0F, -19.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0218F, 0.0F, 0.0F));

        ModelPartData TailFin = Musky.addChild("TailFin", ModelPartBuilder.create().uv(17, 17).cuboid(-0.5F, -9.5F, 15.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 30).cuboid(0.0F, -9.5F, 16.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(18, 5).cuboid(0.0F, -10.5F, 17.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 9).cuboid(0.0F, -11.5F, 18.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(14, 13).cuboid(0.0F, -11.5F, 19.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(28, 28).cuboid(0.0F, -12.0F, 20.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(20, 4).cuboid(0.0F, -12.5F, 21.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 28).cuboid(0.0F, -12.5F, 22.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 17).cuboid(0.0F, -7.0F, 20.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(20, 0).cuboid(0.0F, -6.5F, 21.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(26, 28).cuboid(0.0F, -6.5F, 22.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData TopFin = Musky.addChild("TopFin", ModelPartBuilder.create().uv(10, 7).cuboid(0.0F, -14.0F, 9.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(10, 9).cuboid(0.0F, -12.0F, 8.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(7, 26).cuboid(0.0F, -12.0F, 11.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData AnalFin = Musky.addChild("AnalFin", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -5.5F, 10.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 2).cuboid(0.0F, -3.5F, 11.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(20, 10).cuboid(0.0F, -4.5F, 13.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData BackFinRight = Musky.addChild("BackFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r3 = BackFinRight.addChild("cube_r3", ModelPartBuilder.create().uv(0, 1).cuboid(2.75F, -4.0F, -12.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(2.75F, -6.0F, -13.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

        ModelPartData BackFinLeft = Musky.addChild("BackFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r4 = BackFinLeft.addChild("cube_r4", ModelPartBuilder.create().uv(2, 1).cuboid(-2.75F, -4.0F, -12.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(-2.75F, -6.0F, -13.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

        ModelPartData FrontFinLeft = Musky.addChild("FrontFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r5 = FrontFinLeft.addChild("cube_r5", ModelPartBuilder.create().uv(4, 1).cuboid(-3.25F, -3.75F, 2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(20, 8).cuboid(-3.25F, -4.75F, 3.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 16).cuboid(-3.25F, -5.75F, 1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

        ModelPartData FrontFinRight = Musky.addChild("FrontFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r6 = FrontFinRight.addChild("cube_r6", ModelPartBuilder.create().uv(20, 15).cuboid(3.25F, -4.75F, 3.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(18, 15).cuboid(3.25F, -3.75F, 2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 18).cuboid(3.25F, -5.75F, 1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

        ModelPartData FrontTailBody = Musky.addChild("FrontTailBody", ModelPartBuilder.create().uv(0, 10).cuboid(-1.5F, -9.5F, 11.0F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Neck = Musky.addChild("Neck", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -9.5F, -17.0F, 3.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Body = Musky.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -10.0F, -11.0F, 4.0F, 5.0F, 22.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(MuskellungeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //This HAS to be called first
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(ModAnimations.MUSKELLUNGE_SWIM,limbSwing,limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState,ModAnimations.MUSKELLUNGE_SWIM,ageInTicks,1f);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        muskellunge.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return muskellunge;
    }
}
