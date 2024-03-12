package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.entity.animation.ModAnimations;
import net.cookiebrain.youneedbait.entity.custom.LargeMouthBassEntity;
import net.cookiebrain.youneedbait.entity.custom.MuskellungeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;


public class LargeMouthBassModel<T extends LargeMouthBassEntity> extends SinglePartEntityModel<T> {

	private final ModelPart LargeMouthBass;

	public LargeMouthBassModel(ModelPart root) {
		this.LargeMouthBass = root.getChild("LargeMouthBass");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData LargeMouthBass = modelPartData.addChild("LargeMouthBass", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData TailFin = LargeMouthBass.addChild("TailFin", ModelPartBuilder.create().uv(10, 17).cuboid(-0.5F, -5.5F, 7.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-0.5F, -6.5F, 8.0F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 3).cuboid(-0.5F, -6.7F, 9.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(10, 9).cuboid(-0.5F, -3.3F, 9.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Head = LargeMouthBass.addChild("Head", ModelPartBuilder.create().uv(10, 10).cuboid(-2.0F, -5.7F, -5.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
				.uv(12, 0).cuboid(-1.5F, -5.4F, -6.7F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TopFinFront = LargeMouthBass.addChild("TopFinFront", ModelPartBuilder.create().uv(14, 14).cuboid(-0.5F, -6.3F, 1.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 14).cuboid(-0.5F, -6.4F, 0.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(10, 14).cuboid(-0.5F, -6.5F, -1.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 14).cuboid(-0.5F, -6.3F, -1.3F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TopFinRear = LargeMouthBass.addChild("TopFinRear", ModelPartBuilder.create().uv(4, 18).cuboid(-0.5F, -6.6F, 4.7F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(6, 18).cuboid(-0.5F, -7.1F, 3.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(18, 4).cuboid(-0.5F, -7.0F, 4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(18, 5).cuboid(-0.5F, -6.5F, 4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 17).cuboid(-0.5F, -6.5F, 3.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(16, 14).cuboid(-0.5F, -6.5F, 2.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData AnalFin = LargeMouthBass.addChild("AnalFin", ModelPartBuilder.create().uv(8, 9).cuboid(-0.5F, -3.5F, 2.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(2, 2).cuboid(-0.5F, -4.3F, 3.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(4, 2).cuboid(-0.5F, -4.4F, 4.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 9).cuboid(-0.5F, -4.1F, 5.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData BottomFinLeft = LargeMouthBass.addChild("BottomFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = BottomFinLeft.addChild("cube_r1", ModelPartBuilder.create().uv(14, 3).cuboid(0.6F, -2.2F, -1.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(14, 4).cuboid(0.6F, -2.4F, -2.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		ModelPartData SideFinLeft = LargeMouthBass.addChild("SideFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r2 = SideFinLeft.addChild("cube_r2", ModelPartBuilder.create().uv(0, 15).cuboid(1.6F, -4.2F, -0.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(6, 14).cuboid(1.6F, -4.5F, -0.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(14, 17).cuboid(1.6F, -4.7F, -1.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(16, 17).cuboid(1.6F, -3.7F, -1.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(16, 4).cuboid(1.6F, -4.2F, -2.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 17).cuboid(1.6F, -4.5F, -2.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		ModelPartData BottomFinRight = LargeMouthBass.addChild("BottomFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r3 = BottomFinRight.addChild("cube_r3", ModelPartBuilder.create().uv(0, 14).cuboid(-1.6F, -2.4F, -2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 0).cuboid(-1.6F, -2.2F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		ModelPartData SideFinRight = LargeMouthBass.addChild("SideFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r4 = SideFinRight.addChild("cube_r4", ModelPartBuilder.create().uv(6, 15).cuboid(-2.6F, -4.2F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(16, 3).cuboid(-2.6F, -4.5F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(2, 18).cuboid(-2.6F, -3.7F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(18, 0).cuboid(-2.6F, -4.7F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 18).cuboid(-2.6F, -4.5F, -2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(18, 3).cuboid(-2.6F, -4.2F, -2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		ModelPartData RearTailBody = LargeMouthBass.addChild("RearTailBody", ModelPartBuilder.create().uv(0, 10).cuboid(-2.0F, -5.5F, 3.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 15).cuboid(-1.5F, -5.0F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(2, 0).cuboid(-1.0F, -5.0F, 7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Body = LargeMouthBass.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -6.0F, -3.0F, 3.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(LargeMouthBassEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//This HAS to be called first
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.animateMovement(ModAnimations.BASSSWIM, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.BASSSWIM, ageInTicks, 1f);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		LargeMouthBass.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return LargeMouthBass;
	}

}