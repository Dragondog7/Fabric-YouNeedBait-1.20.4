package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.entity.animation.ModAnimations;
import net.cookiebrain.youneedbait.entity.custom.BlackCrappieEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class BlackCrappieModel<T extends BlackCrappieEntity> extends SinglePartEntityModel<T> {
	private final ModelPart blackcrappie;
	public BlackCrappieModel(ModelPart root) {
		this.blackcrappie = root.getChild("BlackCrappie");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData BlackCrappie = modelPartData.addChild("BlackCrappie", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData TailFin = BlackCrappie.addChild("TailFin", ModelPartBuilder.create().uv(0, 16).cuboid(0.5F, -11.0F, 6.75F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 11).cuboid(0.5F, -11.5F, 7.75F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 16).cuboid(0.5F, -11.75F, 8.75F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 17).cuboid(0.5F, -8.25F, 8.75F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData BottomFinLeft = BlackCrappie.addChild("BottomFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = BottomFinLeft.addChild("cube_r1", ModelPartBuilder.create().uv(4, 4).cuboid(2.0F, -7.0F, -3.25F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 11).cuboid(2.0F, -6.25F, -2.25F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		ModelPartData SideFinLeft = BlackCrappie.addChild("SideFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r2 = SideFinLeft.addChild("cube_r2", ModelPartBuilder.create().uv(10, 19).cuboid(2.3F, -8.3F, -3.25F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(2.3F, -8.525F, -2.25F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 16).cuboid(2.3F, -9.3F, -1.25F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		ModelPartData SideFinRight = BlackCrappie.addChild("SideFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r3 = SideFinRight.addChild("cube_r3", ModelPartBuilder.create().uv(19, 11).cuboid(-1.3F, -8.3F, -3.25F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 4).cuboid(-1.3F, -8.525F, -2.25F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 16).cuboid(-1.3F, -9.3F, -1.25F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

		ModelPartData BottomFinRight = BlackCrappie.addChild("BottomFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r4 = BottomFinRight.addChild("cube_r4", ModelPartBuilder.create().uv(6, 11).cuboid(-1.0F, -7.0F, -3.25F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(18, 16).cuboid(-1.0F, -6.25F, -2.25F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		ModelPartData BottomFin = BlackCrappie.addChild("BottomFin", ModelPartBuilder.create().uv(19, 2).cuboid(0.5F, -6.0F, 3.75F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 16).cuboid(0.5F, -6.5F, 2.75F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 16).cuboid(0.5F, -6.75F, 1.75F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 19).cuboid(0.5F, -6.25F, 0.75F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(18, 18).cuboid(0.5F, -7.0F, -0.25F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(19, 0).cuboid(0.5F, -7.6F, -0.5F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TopFin = BlackCrappie.addChild("TopFin", ModelPartBuilder.create().uv(4, 19).cuboid(0.5F, -13.25F, 3.75F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 16).cuboid(0.5F, -13.5F, 2.75F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 16).cuboid(0.5F, -13.0F, 1.75F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 19).cuboid(0.5F, -12.5F, 0.75F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 19).cuboid(0.5F, -11.75F, -0.25F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(19, 4).cuboid(0.5F, -11.4F, -0.5F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TailBody = BlackCrappie.addChild("TailBody", ModelPartBuilder.create().uv(16, 12).cuboid(0.0F, -10.5F, 5.75F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 12).cuboid(-0.5F, -10.0F, 3.75F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.5F, -10.5F, 2.75F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Body = BlackCrappie.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -11.0F, -4.0F, 3.0F, 5.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Head = BlackCrappie.addChild("Head", ModelPartBuilder.create().uv(8, 12).cuboid(-0.5F, -4.5F, -5.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(13, 0).cuboid(-0.5F, -3.75F, -6.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(13, 4).cuboid(-0.5F, -3.5F, -7.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(BlackCrappieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//This HAS to be called first
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.animateMovement(ModAnimations.BLACKCRAPPIESWIMMING, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.BLACKCRAPPIEIDLE, ageInTicks, 1f);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		blackcrappie.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() { return blackcrappie; }

}