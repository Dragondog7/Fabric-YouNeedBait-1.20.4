package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.entity.animation.ModAnimations;
import net.cookiebrain.youneedbait.entity.custom.MuskellungeEntity;
import net.cookiebrain.youneedbait.entity.custom.NorthernPikeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class NorthernPikeModel<T extends NorthernPikeEntity> extends SinglePartEntityModel<T> {
	private final ModelPart northernpike;

	public NorthernPikeModel(ModelPart root) {
		this.northernpike = root.getChild("northernpike");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData NorthernPike = modelPartData.addChild("northernpike", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData LowerJaw = NorthernPike.addChild("LowerJaw", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = LowerJaw.addChild("cube_r1", ModelPartBuilder.create().uv(0, 19).cuboid(-1.5F, -5.8F, -18.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(7, 20).cuboid(-1.5F, -5.8F, -19.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 27).cuboid(-1.5F, -5.8F, -20.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 29).cuboid(-1.0F, -5.8F, -21.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 29).cuboid(-1.0F, -5.8F, -22.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(30, 5).cuboid(-0.5F, -5.8F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(30, 5).cuboid(-0.5F, -5.8F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

		ModelPartData UpperJaw = NorthernPike.addChild("UpperJaw", ModelPartBuilder.create().uv(17, 7).cuboid(1.5F, -8.5F, -19.7F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(17, 5).cuboid(-1.5F, -8.5F, -19.7F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r2 = UpperJaw.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -7.9F, -22.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(30, 7).cuboid(-0.5F, -7.6F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(30, 7).cuboid(-0.5F, -7.6F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(20, 29).cuboid(-1.0F, -7.7F, -22.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(26, 29).cuboid(-1.0F, -7.8F, -21.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 0).cuboid(-1.5F, -8.3F, -20.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 27).cuboid(-1.5F, -8.0F, -20.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(24, 27).cuboid(-1.5F, -8.0F, -18.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 29).cuboid(-1.5F, -9.0F, -18.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 17).cuboid(-1.5F, -8.8F, -19.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 27).cuboid(-1.5F, -8.0F, -19.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0218F, 0.0F, 0.0F));

		ModelPartData TailFin = NorthernPike.addChild("TailFin", ModelPartBuilder.create().uv(17, 17).cuboid(-0.5F, -9.5F, 15.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 30).cuboid(0.0F, -9.5F, 16.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(18, 1).cuboid(0.0F, -10.5F, 17.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 9).cuboid(0.0F, -11.5F, 18.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 11).cuboid(0.0F, -11.5F, 19.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 30).cuboid(0.0F, -12.0F, 20.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(20, 11).cuboid(0.0F, -12.5F, 21.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 30).cuboid(0.0F, -12.5F, 22.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(18, 11).cuboid(0.0F, -7.0F, 20.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(20, 0).cuboid(0.0F, -6.5F, 21.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(30, 0).cuboid(0.0F, -6.5F, 22.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TopFin = NorthernPike.addChild("TopFin", ModelPartBuilder.create().uv(12, 1).cuboid(0.0F, -13.0F, 9.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(10, 7).cuboid(0.0F, -12.0F, 8.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(30, 8).cuboid(0.0F, -12.0F, 11.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(30, 8).cuboid(0.0F, -13.0F, 12.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData AnalFin = NorthernPike.addChild("AnalFin", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -6.0F, 10.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(12, 0).cuboid(0.0F, -4.0F, 11.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(16, 4).cuboid(0.0F, -5.0F, 13.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData BackFinRight = NorthernPike.addChild("BackFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r3 = BackFinRight.addChild("cube_r3", ModelPartBuilder.create().uv(12, 1).cuboid(2.75F, -5.0F, -13.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 4).cuboid(2.75F, -4.0F, -14.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 8).cuboid(2.75F, -6.0F, -15.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		ModelPartData BackFinLeft = NorthernPike.addChild("BackFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

		ModelPartData cube_r4 = BackFinLeft.addChild("cube_r4", ModelPartBuilder.create().uv(4, 1).cuboid(-2.75F, -5.0F, -11.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 1).cuboid(-2.75F, -4.0F, -12.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 10).cuboid(-2.75F, -6.0F, -13.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		ModelPartData FrontFinLeft = NorthernPike.addChild("FrontFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r5 = FrontFinLeft.addChild("cube_r5", ModelPartBuilder.create().uv(14, 4).cuboid(-3.25F, -3.75F, 2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(20, 4).cuboid(-3.25F, -4.75F, 4.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 30).cuboid(-3.25F, -4.75F, 3.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 10).cuboid(-3.25F, -5.75F, 1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		ModelPartData FrontFinRight = NorthernPike.addChild("FrontFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r6 = FrontFinRight.addChild("cube_r6", ModelPartBuilder.create().uv(20, 15).cuboid(3.25F, -4.75F, 4.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 30).cuboid(3.25F, -4.75F, 3.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 1).cuboid(3.25F, -3.75F, 2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 15).cuboid(3.25F, -5.75F, 1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		ModelPartData FrontTailBody = NorthernPike.addChild("FrontTailBody", ModelPartBuilder.create().uv(0, 10).cuboid(-1.5F, -9.0F, 11.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Neck = NorthernPike.addChild("Neck", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -9.5F, -17.0F, 3.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Body = NorthernPike.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -10.0F, -11.0F, 4.0F, 5.0F, 22.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(NorthernPikeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//This HAS to be called first
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.animateMovement(ModAnimations.NORTHERNPIKE_SWIM,limbSwing,limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState,ModAnimations.IDLE,ageInTicks,1f);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		northernpike.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return northernpike;
	}

}