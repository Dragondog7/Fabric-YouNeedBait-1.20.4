package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.entity.animation.ModAnimations;
import net.cookiebrain.youneedbait.entity.custom.PumpkinSeedEntity;
import net.cookiebrain.youneedbait.entity.custom.WalleyeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class PumpkinSeedModel <T extends PumpkinSeedEntity> extends SinglePartEntityModel<T> {
	private final ModelPart PumpkinSeed;
	public PumpkinSeedModel(ModelPart root) {
		this.PumpkinSeed = root.getChild("PumpkinSeed");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData PumpkinSeed = modelPartData.addChild("PumpkinSeed", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData Head = PumpkinSeed.addChild("Head", ModelPartBuilder.create().uv(8, 9).cuboid(-1.0F, -10.7F, -5.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 4).cuboid(-1.0F, -10.0F, -6.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 9).cuboid(-0.5F, -9.4F, -7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Body = PumpkinSeed.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -11.0F, -4.0F, 2.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TailBody = Body.addChild("TailBody", ModelPartBuilder.create().uv(0, 9).cuboid(-1.0F, -10.7F, 0.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 0).cuboid(-1.0F, -10.0F, 2.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData BottomFinRear = TailBody.addChild("BottomFinRear", ModelPartBuilder.create().uv(0, 8).cuboid(0.0F, -8.3F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 14).cuboid(0.0F, -7.7F, 1.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 8).cuboid(0.0F, -7.5F, 2.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(3, 14).cuboid(0.0F, -6.7F, 3.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TopFinBack = TailBody.addChild("TopFinBack", ModelPartBuilder.create().uv(6, 16).cuboid(0.0F, -10.8F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 12).cuboid(0.0F, -11.8F, 0.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(12, 16).cuboid(0.0F, -12.6F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Tail = Body.addChild("Tail", ModelPartBuilder.create().uv(14, 0).cuboid(-0.5F, -9.5F, 3.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.5F, -10.0F, 4.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 15).cuboid(-0.5F, -10.3F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 16).cuboid(0.0F, -9.5F, 5.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-0.5F, -7.7F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 12).cuboid(-0.5F, -7.2F, 6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 14).cuboid(0.0F, -8.3F, 6.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 16).cuboid(0.0F, -10.7F, 6.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 15).cuboid(-0.5F, -10.8F, 6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TopFinFront = Body.addChild("TopFinFront", ModelPartBuilder.create().uv(16, 7).cuboid(0.0F, -12.0F, -4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 16).cuboid(0.0F, -12.3F, -3.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 16).cuboid(0.0F, -11.4F, -2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 16).cuboid(0.0F, -11.8F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData SideFinLeft = Body.addChild("SideFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = SideFinLeft.addChild("cube_r1", ModelPartBuilder.create().uv(7, 14).cuboid(1.4F, -9.0F, -3.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 6).cuboid(1.4F, -8.0F, -3.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		ModelPartData SideFinRight = Body.addChild("SideFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r2 = SideFinRight.addChild("cube_r2", ModelPartBuilder.create().uv(16, 2).cuboid(-1.4F, -9.0F, -3.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 12).cuboid(-1.4F, -8.0F, -3.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		ModelPartData BottomFinFront = Body.addChild("BottomFinFront", ModelPartBuilder.create().uv(13, 8).cuboid(0.0F, -6.0F, -2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 2).cuboid(0.0F, -6.3F, -3.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		PumpkinSeed.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	@Override
	public ModelPart getPart() {
		return PumpkinSeed;
	}

	@Override
	public void setAngles(PumpkinSeedEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.animateMovement(ModAnimations.PUMPKINSEEDSWIM,limbSwing,limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState,ModAnimations.PUMPKINSEEDSWIM,ageInTicks,1f);
	}
}