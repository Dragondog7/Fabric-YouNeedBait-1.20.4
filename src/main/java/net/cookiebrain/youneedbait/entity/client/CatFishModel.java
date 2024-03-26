package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.entity.animation.ModAnimations;
import net.cookiebrain.youneedbait.entity.custom.BlackCrappieEntity;
import net.cookiebrain.youneedbait.entity.custom.CatFishEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class CatFishModel<T extends CatFishEntity> extends SinglePartEntityModel<T> {
	private final ModelPart catfish;
	public CatFishModel(ModelPart root) {
		this.catfish = root.getChild("CatFish");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData CatFish = modelPartData.addChild("CatFish", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 24.0F, 0.0F));

		ModelPartData Body = CatFish.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, -10.0F, -9.0F, 5.0F, 5.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TopFinFront = Body.addChild("TopFinFront", ModelPartBuilder.create().uv(28, 20).cuboid(-1.0F, -11.0F, -10.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 8).cuboid(-1.0F, -11.0F, -9.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(24, 8).cuboid(-1.0F, -12.0F, -9.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(12, 19).cuboid(-1.0F, -13.0F, -9.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(22, 28).cuboid(-1.0F, -14.0F, -6.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 8).cuboid(-1.0F, -14.0F, -8.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.0F, -15.0F, -7.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData MidFinLeft = Body.addChild("MidFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = MidFinLeft.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(2.2F, -5.5F, -5.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(10, 0).cuboid(2.2F, -5.5F, -6.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.1309F, 0.0F));

		ModelPartData MidFinRight = Body.addChild("MidFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r2 = MidFinRight.addChild("cube_r2", ModelPartBuilder.create().uv(10, 0).cuboid(-4.2F, -5.5F, -5.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(12, 0).cuboid(-4.2F, -5.5F, -6.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, -0.1309F, 0.0F));

		ModelPartData BottomFin = Body.addChild("BottomFin", ModelPartBuilder.create().uv(16, 28).cuboid(-1.0F, -5.5F, -2.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 15).cuboid(-1.0F, -5.0F, -1.0F, 0.0F, 3.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 5).cuboid(-1.0F, -5.5F, 3.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(10, 5).cuboid(-1.0F, -6.0F, 5.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(24, 1).cuboid(-1.0F, -6.5F, 7.0F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(30, 5).cuboid(-1.0F, -5.5F, -3.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Head = CatFish.addChild("Head", ModelPartBuilder.create().uv(24, 0).cuboid(-2.5F, -8.5F, -19.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 7).cuboid(-2.5F, -9.0F, -17.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r3 = Head.addChild("cube_r3", ModelPartBuilder.create().uv(18, 28).cuboid(-2.0F, -12.0F, -15.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(28, 11).cuboid(0.0F, -12.0F, -15.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r4 = Head.addChild("cube_r4", ModelPartBuilder.create().uv(28, 18).cuboid(-16.0F, -6.5F, 1.3F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.2F, 8.0F, -9.55F, -1.5708F, -0.9599F, 1.5708F));

		ModelPartData cube_r5 = Head.addChild("cube_r5", ModelPartBuilder.create().uv(10, 26).cuboid(-16.0F, -6.5F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.2F, 8.0F, -9.55F, -1.5708F, -0.9599F, 1.5708F));

		ModelPartData cube_r6 = Head.addChild("cube_r6", ModelPartBuilder.create().uv(24, 28).cuboid(-16.0F, -6.5F, -2.3F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.2F, 5.5F, -30.45F, 1.5708F, 1.1781F, 1.5708F));

		ModelPartData cube_r7 = Head.addChild("cube_r7", ModelPartBuilder.create().uv(26, 28).cuboid(-16.0F, -6.5F, -1.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.2F, 5.5F, -30.45F, 1.5708F, 1.1781F, 1.5708F));

		ModelPartData cube_r8 = Head.addChild("cube_r8", ModelPartBuilder.create().uv(6, 26).cuboid(1.8F, -9.0F, -14.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -3.0F, 0.1309F, 0.0F, -0.1745F));

		ModelPartData cube_r9 = Head.addChild("cube_r9", ModelPartBuilder.create().uv(20, 28).cuboid(1.8F, -8.8F, -13.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -3.0F, 0.2618F, 0.0F, -0.1745F));

		ModelPartData cube_r10 = Head.addChild("cube_r10", ModelPartBuilder.create().uv(30, 3).cuboid(1.8F, -11.2F, -9.5F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -3.0F, 0.6545F, 0.0F, -0.1745F));

		ModelPartData cube_r11 = Head.addChild("cube_r11", ModelPartBuilder.create().uv(14, 29).cuboid(1.8F, -11.8F, -5.8F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -3.0F, 1.0036F, 0.0F, -0.1745F));

		ModelPartData cube_r12 = Head.addChild("cube_r12", ModelPartBuilder.create().uv(24, 6).cuboid(-0.8F, -9.0F, -14.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.0F, -3.0F, 0.1309F, 0.0F, 0.1745F));

		ModelPartData cube_r13 = Head.addChild("cube_r13", ModelPartBuilder.create().uv(28, 26).cuboid(-0.8F, -8.8F, -13.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.0F, -3.0F, 0.2618F, 0.0F, 0.1745F));

		ModelPartData cube_r14 = Head.addChild("cube_r14", ModelPartBuilder.create().uv(12, 29).cuboid(-0.8F, -11.2F, -9.5F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.0F, -3.0F, 0.6545F, 0.0F, 0.1745F));

		ModelPartData cube_r15 = Head.addChild("cube_r15", ModelPartBuilder.create().uv(28, 28).cuboid(-0.8F, -11.8F, -5.8F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.0F, -3.0F, 1.0036F, 0.0F, 0.1745F));

		ModelPartData TailBody = CatFish.addChild("TailBody", ModelPartBuilder.create().uv(0, 19).cuboid(-3.0F, -9.5F, 5.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.5F, -9.0F, 9.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TopFinBack = TailBody.addChild("TopFinBack", ModelPartBuilder.create().uv(0, 20).cuboid(-1.0F, -10.7F, 5.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(2, 0).cuboid(-1.0F, -10.5F, 4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData TailFin = TailBody.addChild("TailFin", ModelPartBuilder.create().uv(24, 7).cuboid(-2.0F, -8.5F, 12.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(6, 27).cuboid(-1.5F, -9.5F, 13.0F, 1.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 24).cuboid(-1.0F, -10.5F, 15.0F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 17).cuboid(-1.0F, -11.0F, 18.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(24, 25).cuboid(-1.0F, -11.0F, 20.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(12, 25).cuboid(-1.0F, -7.0F, 18.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(20, 25).cuboid(-1.0F, -6.0F, 20.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Neck = CatFish.addChild("Neck", ModelPartBuilder.create().uv(16, 19).cuboid(-3.0F, -9.5F, -13.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData FrontFinRight = Neck.addChild("FrontFinRight", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r16 = FrontFinRight.addChild("cube_r16", ModelPartBuilder.create().uv(18, 21).cuboid(-4.6F, -5.5F, -13.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(24, 10).cuboid(-4.6F, -5.5F, -12.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, -0.1309F, 0.0F));

		ModelPartData FrontFinLeft = Neck.addChild("FrontFinLeft", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r17 = FrontFinLeft.addChild("cube_r17", ModelPartBuilder.create().uv(24, 0).cuboid(2.6F, -5.5F, -13.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 25).cuboid(2.6F, -5.5F, -12.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.1309F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(CatFishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//This HAS to be called first
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.animateMovement(ModAnimations.CATFISHSWIM, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.CATFISHIDLE, ageInTicks, 1f);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		catfish.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() { return catfish; }

	}

