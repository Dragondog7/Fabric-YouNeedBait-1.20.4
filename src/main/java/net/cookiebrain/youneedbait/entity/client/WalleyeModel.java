package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.entity.animation.ModAnimations;
import net.cookiebrain.youneedbait.entity.custom.MuskellungeEntity;
import net.cookiebrain.youneedbait.entity.custom.WalleyeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class WalleyeModel<T extends WalleyeEntity> extends SinglePartEntityModel<T> {
	private final ModelPart walleye;
	public WalleyeModel(ModelPart root) {this.walleye = root.getChild("walleye"); }
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Walleye = modelPartData.addChild("walleye", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData TailFin = Walleye.addChild("TailFin", ModelPartBuilder.create().uv(0, 16).cuboid(-0.5F, -5.5F, 7.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(11, 12).cuboid(-0.5F, -6.5F, 8.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 8).cuboid(-0.5F, -6.7F, 9.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 5).cuboid(-0.5F, -3.0F, 9.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 0).cuboid(-0.5F, -3.3F, 8.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Head = Walleye.addChild("Head", ModelPartBuilder.create().uv(10, 0).cuboid(-0.6F, -5.0F, -5.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.4F, -5.0F, -5.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(5, 8).cuboid(-1.0F, -3.75F, -4.25F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(5, 8).cuboid(-1.0F, -4.25F, -5.75F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(-1, 3).cuboid(-0.75F, -4.5F, -6.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(-1, 3).cuboid(-1.25F, -4.5F, -6.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TopFinFront = Walleye.addChild("TopFinFront", ModelPartBuilder.create().uv(15, 11).cuboid(-0.5F, -6.0F, -0.3F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 13).cuboid(-0.5F, -5.3F, 0.2F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 15).cuboid(-0.5F, -6.5F, -1.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 15).cuboid(-0.5F, -6.0F, -1.3F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 13).cuboid(-0.5F, -5.3F, -1.7F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -1.0F));

		ModelPartData TopFinRear = Walleye.addChild("TopFinRear", ModelPartBuilder.create().uv(10, 3).cuboid(-0.5F, -6.3F, 4.7F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 16).cuboid(-0.5F, -7.0F, 4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 4).cuboid(-0.5F, -6.4F, 4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(15, 14).cuboid(-0.5F, -7.5F, 3.0F, 0.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(13, 15).cuboid(-0.5F, -7.0F, 2.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 13).cuboid(-0.5F, -6.25F, 1.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData AnalFin = Walleye.addChild("AnalFin", ModelPartBuilder.create().uv(13, 12).cuboid(-0.5F, -4.3F, 3.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 8).cuboid(-0.5F, -4.1F, 4.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 2).cuboid(-0.5F, -2.0F, 5.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 0).cuboid(-0.5F, -2.4F, 5.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData BottomFinLeft = Walleye.addChild("BottomFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData cube_r1 = BottomFinLeft.addChild("cube_r1", ModelPartBuilder.create().uv(16, 6).cuboid(0.4F, -2.4F, -1.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 4).cuboid(0.4F, -2.1F, -1.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 8).cuboid(0.4F, -2.5F, -2.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		ModelPartData SideFinLeft = Walleye.addChild("SideFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData cube_r2 = SideFinLeft.addChild("cube_r2", ModelPartBuilder.create().uv(14, 3).cuboid(1.1F, -4.7F, -1.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 4).cuboid(1.1F, -3.7F, -1.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 9).cuboid(1.1F, -4.2F, -2.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 0).cuboid(1.1F, -4.5F, -2.2F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		ModelPartData BottomFinRight = Walleye.addChild("BottomFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData cube_r3 = BottomFinRight.addChild("cube_r3", ModelPartBuilder.create().uv(12, 3).cuboid(-1.4F, -2.5F, -2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.4F, -2.4F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 4).cuboid(-1.4F, -2.1F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		ModelPartData SideFinRight = Walleye.addChild("SideFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData cube_r4 = SideFinRight.addChild("cube_r4", ModelPartBuilder.create().uv(2, 16).cuboid(-2.1F, -3.7F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 1).cuboid(-2.1F, -4.7F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 0).cuboid(-2.1F, -4.5F, -2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 3).cuboid(-2.1F, -4.2F, -2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		ModelPartData RearTailBody = Walleye.addChild("RearTailBody", ModelPartBuilder.create().uv(0, 9).cuboid(-1.5F, -5.5F, 3.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 9).cuboid(-1.0F, -5.0F, 5.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(7, 13).cuboid(-1.0F, -5.0F, 7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Body = Walleye.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -5.5F, -3.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		walleye.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	@Override
	public ModelPart getPart() {
		return walleye;
	}

	@Override
	public void setAngles(WalleyeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.animateMovement(ModAnimations.WALLEYESWIM,limbSwing,limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState,ModAnimations.IDLE,ageInTicks,1f);
	}
}