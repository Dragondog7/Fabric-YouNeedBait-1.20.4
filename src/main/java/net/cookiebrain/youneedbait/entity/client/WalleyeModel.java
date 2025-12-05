package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.entity.animation.ModAnimations;
import net.cookiebrain.youneedbait.entity.custom.WalleyeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class WalleyeModel<W extends WalleyeEntity> extends SinglePartEntityModel<WalleyeEntity> {
	private final ModelPart root;
	private final ModelPart Head;
	private final ModelPart LowerJaw;
	private final ModelPart Teeth;
	private final ModelPart UpperJaw;
	private final ModelPart Teeth2;
	private final ModelPart MouthRear;
	private final ModelPart Body;
	private final ModelPart LeftPectoralFin;
	private final ModelPart RightPectoralFin;
	private final ModelPart FrontDorsalFin;
	private final ModelPart RightThoracicPelvicFin;
	private final ModelPart LeftThoracicPelvicFin;
	private final ModelPart FrontTail;
	private final ModelPart RearDorsalFin;
	private final ModelPart AnalFin;
	private final ModelPart MidTail;
	private final ModelPart CaudalFin;
	public WalleyeModel(ModelPart root) {
		this.root = root;
		this.Head = root.getChild("Head");
		this.LowerJaw = this.Head.getChild("LowerJaw");
		this.Teeth = this.LowerJaw.getChild("Teeth");
		this.UpperJaw = this.Head.getChild("UpperJaw");
		this.Teeth2 = this.UpperJaw.getChild("Teeth2");
		this.MouthRear = this.Head.getChild("MouthRear");
		this.Body = root.getChild("Body");
		this.LeftPectoralFin = this.Body.getChild("LeftPectoralFin");
		this.RightPectoralFin = this.Body.getChild("RightPectoralFin");
		this.FrontDorsalFin = this.Body.getChild("FrontDorsalFin");
		this.RightThoracicPelvicFin = this.Body.getChild("RightThoracicPelvicFin");
		this.LeftThoracicPelvicFin = this.Body.getChild("LeftThoracicPelvicFin");
		this.FrontTail = root.getChild("FrontTail");
		this.RearDorsalFin = this.FrontTail.getChild("RearDorsalFin");
		this.AnalFin = this.FrontTail.getChild("AnalFin");
		this.MidTail = root.getChild("MidTail");
		this.CaudalFin = root.getChild("CaudalFin");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Head = modelPartData.addChild("Head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 18.0F, -3.0F));

		ModelPartData cube_r1 = Head.addChild("cube_r1", ModelPartBuilder.create().uv(44, 0).cuboid(-3.0F, -3.0F, -5.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.15F, 1.75F, 4.05F, 0.0F, -0.3927F, 0.0F));

		ModelPartData cube_r2 = Head.addChild("cube_r2", ModelPartBuilder.create().uv(28, 43).cuboid(2.0F, -3.0F, -5.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.15F, 1.75F, 4.05F, 0.0F, 0.3927F, 0.0F));

		ModelPartData LowerJaw = Head.addChild("LowerJaw", ModelPartBuilder.create(), ModelTransform.pivot(0.1F, 2.1F, 3.0F));

		ModelPartData cube_r3 = LowerJaw.addChild("cube_r3", ModelPartBuilder.create().uv(30, 32).cuboid(-0.99F, -1.0F, -6.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(20, 32).cuboid(-1.49F, -1.0F, -6.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.65F, 0.1F, 0.0F, -0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r4 = LowerJaw.addChild("cube_r4", ModelPartBuilder.create().uv(10, 32).cuboid(-0.99F, -1.0F, -6.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.4F, 0.15F, -0.5F, -0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r5 = LowerJaw.addChild("cube_r5", ModelPartBuilder.create().uv(34, 0).cuboid(-0.9899F, -1.0F, -6.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.4F, 0.0F, -1.4F, -0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r6 = LowerJaw.addChild("cube_r6", ModelPartBuilder.create().uv(0, 18).cuboid(-1.02F, -1.0F, -8.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
				.uv(16, 11).cuboid(-1.78F, -1.0F, -8.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.8F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

		ModelPartData Teeth = LowerJaw.addChild("Teeth", ModelPartBuilder.create(), ModelTransform.pivot(0.65F, -0.25F, -6.0F));

		ModelPartData cube_r7 = Teeth.addChild("cube_r7", ModelPartBuilder.create().uv(31, 54).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -0.1F, 0.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r8 = Teeth.addChild("cube_r8", ModelPartBuilder.create().uv(31, 53).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(31, 52).cuboid(1.5F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -0.1F, -1.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r9 = Teeth.addChild("cube_r9", ModelPartBuilder.create().uv(29, 52).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData UpperJaw = Head.addChild("UpperJaw", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.15F, -0.15F));

		ModelPartData cube_r10 = UpperJaw.addChild("cube_r10", ModelPartBuilder.create().uv(48, 49).cuboid(-1.01F, -1.0F, -5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(38, 23).cuboid(-0.9F, -2.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(50, 0).cuboid(-1.79F, -1.0F, -5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(34, 30).cuboid(-1.9F, -2.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.9F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r11 = UpperJaw.addChild("cube_r11", ModelPartBuilder.create().uv(20, 0).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(14, 18).cuboid(-0.5F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, -0.5F, 0.0F, 0.2618F, 0.0F, 0.0F));

		ModelPartData cube_r12 = UpperJaw.addChild("cube_r12", ModelPartBuilder.create().uv(20, 7).cuboid(-1.0F, -2.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(34, 37).cuboid(-0.7F, -2.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.15F, -0.75F, 0.0F, 0.2618F, 0.0F, 0.0F));

		ModelPartData cube_r13 = UpperJaw.addChild("cube_r13", ModelPartBuilder.create().uv(40, 15).cuboid(-1.09F, -3.0F, -5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(48, 28).cuboid(-0.11F, -3.0F, -5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.1F, -1.1F, 1.5F, 0.48F, 0.0F, 0.0F));

		ModelPartData cube_r14 = UpperJaw.addChild("cube_r14", ModelPartBuilder.create().uv(0, 38).cuboid(-1.0F, -2.0F, -4.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(40, 30).cuboid(-1.8F, -2.0F, -4.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.9F, 0.0F, -0.25F, 0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r15 = UpperJaw.addChild("cube_r15", ModelPartBuilder.create().uv(34, 25).cuboid(-1.0F, -2.0F, -5.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(30, 13).cuboid(-0.5F, -2.0001F, -5.0001F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, -0.15F, -0.3F, 0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r16 = UpperJaw.addChild("cube_r16", ModelPartBuilder.create().uv(34, 41).cuboid(-1.05F, -1.0F, -7.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(42, 41).cuboid(-1.75F, -1.0001F, -7.0001F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.9F, -0.25F, 1.25F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r17 = UpperJaw.addChild("cube_r17", ModelPartBuilder.create().uv(8, 43).cuboid(0.06F, -1.0F, -7.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 43).cuboid(0.74F, -1.0001F, -7.0001F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.9F, 0.1F, 1.5F, 0.0436F, 0.0F, 0.0F));

		ModelPartData Teeth2 = UpperJaw.addChild("Teeth2", ModelPartBuilder.create(), ModelTransform.pivot(0.75F, 0.45F, -2.85F));

		ModelPartData cube_r18 = Teeth2.addChild("cube_r18", ModelPartBuilder.create().uv(24, 52).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(23, 55).cuboid(-1.5F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -1.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r19 = Teeth2.addChild("cube_r19", ModelPartBuilder.create().uv(24, 53).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(26, 55).cuboid(-1.5F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r20 = Teeth2.addChild("cube_r20", ModelPartBuilder.create().uv(20, 55).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(20, 52).cuboid(1.5F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData MouthRear = Head.addChild("MouthRear", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.15F, -0.15F));

		ModelPartData cube_r21 = MouthRear.addChild("cube_r21", ModelPartBuilder.create().uv(0, 25).cuboid(-1.0F, -3.0F, -2.05F, 2.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 7.0F, new Dilation(0.0F))
				.uv(34, 5).cuboid(-1.0F, -2.25F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 11).cuboid(-1.0F, 1.35F, 0.001F, 2.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, -3.0F));

		ModelPartData cube_r22 = Body.addChild("cube_r22", ModelPartBuilder.create().uv(44, 35).cuboid(-2.0F, -3.0F, 2.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.55F, 1.5F, -1.3F, 0.0F, -0.0436F, 0.0F));

		ModelPartData cube_r23 = Body.addChild("cube_r23", ModelPartBuilder.create().uv(28, 18).cuboid(-2.0F, -3.0F, -5.0F, 1.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.55F, 1.5F, 7.5F, 0.0F, 0.0436F, 0.0F));

		ModelPartData cube_r24 = Body.addChild("cube_r24", ModelPartBuilder.create().uv(12, 25).cuboid(-1.0F, -3.0F, -1.95F, 2.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.15F, 3.15F, -0.2618F, 0.0F, 0.0F));

		ModelPartData cube_r25 = Body.addChild("cube_r25", ModelPartBuilder.create().uv(44, 23).cuboid(1.0F, -3.0F, 2.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.55F, 1.5F, -1.3F, 0.0F, 0.0436F, 0.0F));

		ModelPartData cube_r26 = Body.addChild("cube_r26", ModelPartBuilder.create().uv(24, 25).cuboid(1.0F, -3.0F, -5.0F, 1.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.55F, 1.5F, 7.5F, 0.0F, -0.0436F, 0.0F));

		ModelPartData LeftPectoralFin = Body.addChild("LeftPectoralFin", ModelPartBuilder.create(), ModelTransform.pivot(1.5F, 1.0F, 0.5F));

		ModelPartData cube_r27 = LeftPectoralFin.addChild("cube_r27", ModelPartBuilder.create().uv(48, 13).cuboid(2.0F, -2.0F, -4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.2F, 0.9F, 4.7F, 0.0873F, 0.1745F, 0.0F));

		ModelPartData cube_r28 = LeftPectoralFin.addChild("cube_r28", ModelPartBuilder.create().uv(50, 3).cuboid(2.0001F, -2.0F, -3.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.2F, 1.1F, 4.7F, 0.0873F, 0.1745F, 0.0F));

		ModelPartData cube_r29 = LeftPectoralFin.addChild("cube_r29", ModelPartBuilder.create().uv(12, 52).cuboid(2.0F, -3.0F, -4.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.2F, 1.75F, 4.7F, 0.0873F, 0.1745F, 0.0F));

		ModelPartData cube_r30 = LeftPectoralFin.addChild("cube_r30", ModelPartBuilder.create().uv(8, 51).cuboid(2.0F, -3.0F, -5.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.1F, 1.8F, 5.2F, 0.0873F, 0.1745F, 0.0F));

		ModelPartData RightPectoralFin = Body.addChild("RightPectoralFin", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, 1.0F, 0.5F));

		ModelPartData cube_r31 = RightPectoralFin.addChild("cube_r31", ModelPartBuilder.create().uv(50, 43).cuboid(-2.0F, -2.0F, -4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.2F, 0.9F, 4.7F, 0.0873F, -0.1745F, 0.0F));

		ModelPartData cube_r32 = RightPectoralFin.addChild("cube_r32", ModelPartBuilder.create().uv(50, 13).cuboid(-2.0001F, -2.0F, -3.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.2F, 1.1F, 4.7F, 0.0873F, -0.1745F, 0.0F));

		ModelPartData cube_r33 = RightPectoralFin.addChild("cube_r33", ModelPartBuilder.create().uv(16, 52).cuboid(-2.0F, -3.0F, -5.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.1F, 1.8F, 5.2F, 0.0873F, -0.1745F, 0.0F));

		ModelPartData cube_r34 = RightPectoralFin.addChild("cube_r34", ModelPartBuilder.create().uv(52, 13).cuboid(-2.0F, -3.0F, -4.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.2F, 1.75F, 4.7F, 0.0873F, -0.1745F, 0.0F));

		ModelPartData FrontDorsalFin = Body.addChild("FrontDorsalFin", ModelPartBuilder.create().uv(42, 23).cuboid(-0.0001F, -1.25F, -0.45F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(50, 40).cuboid(0.0001F, -0.5F, -0.7F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 3.0F));

		ModelPartData cube_r35 = FrontDorsalFin.addChild("cube_r35", ModelPartBuilder.create().uv(42, 35).cuboid(1.9499F, -1.85F, -6.35F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, 4.8F, 7.45F, -0.6109F, 0.0F, 0.0F));

		ModelPartData cube_r36 = FrontDorsalFin.addChild("cube_r36", ModelPartBuilder.create().uv(48, 5).cuboid(1.95F, -1.85F, -9.35F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, 3.3F, 8.85F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r37 = FrontDorsalFin.addChild("cube_r37", ModelPartBuilder.create().uv(6, 47).cuboid(1.95F, -1.85F, 6.35F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, 5.05F, -7.6F, 0.5236F, 0.0F, 0.0F));

		ModelPartData RightThoracicPelvicFin = Body.addChild("RightThoracicPelvicFin", ModelPartBuilder.create(), ModelTransform.pivot(-0.15F, 2.0F, 2.0F));

		ModelPartData cube_r38 = RightThoracicPelvicFin.addChild("cube_r38", ModelPartBuilder.create().uv(18, 48).cuboid(1.95F, 0.85F, 5.35F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.8F, -6.4F, -1.75F, -1.0908F, 0.0F, 0.1745F));

		ModelPartData cube_r39 = RightThoracicPelvicFin.addChild("cube_r39", ModelPartBuilder.create().uv(48, 9).cuboid(1.9501F, -0.15F, 5.35F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.991F, -5.3167F, -3.65F, -0.829F, 0.0F, 0.1745F));

		ModelPartData LeftThoracicPelvicFin = Body.addChild("LeftThoracicPelvicFin", ModelPartBuilder.create(), ModelTransform.of(0.1F, 2.25F, 2.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData cube_r40 = LeftThoracicPelvicFin.addChild("cube_r40", ModelPartBuilder.create().uv(24, 48).cuboid(1.95F, 0.85F, 5.35F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, -6.4F, -1.75F, -1.0908F, 0.0F, 0.0F));

		ModelPartData cube_r41 = LeftThoracicPelvicFin.addChild("cube_r41", ModelPartBuilder.create().uv(12, 48).cuboid(1.9499F, -0.15F, 5.35F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, -5.3F, -3.65F, -0.829F, 0.0F, 0.0F));

		ModelPartData FrontTail = modelPartData.addChild("FrontTail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 18.0F, 4.0F));

		ModelPartData cube_r42 = FrontTail.addChild("cube_r42", ModelPartBuilder.create().uv(26, 37).cuboid(1.0F, -3.0F, 2.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, 2.1F, -2.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r43 = FrontTail.addChild("cube_r43", ModelPartBuilder.create().uv(18, 37).cuboid(1.0F, -3.0F, 2.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.6F, 2.1F, -2.0F, 0.0436F, 0.0873F, 0.0F));

		ModelPartData cube_r44 = FrontTail.addChild("cube_r44", ModelPartBuilder.create().uv(40, 5).cuboid(1.0F, -4.0F, -5.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.1F, 2.8F, 4.35F, -0.1745F, 0.0873F, 0.0F));

		ModelPartData cube_r45 = FrontTail.addChild("cube_r45", ModelPartBuilder.create().uv(40, 10).cuboid(1.0F, -4.0F, -5.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, 2.8F, 4.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r46 = FrontTail.addChild("cube_r46", ModelPartBuilder.create().uv(30, 7).cuboid(0.0F, -3.0F, 2.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.85F, -0.5F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r47 = FrontTail.addChild("cube_r47", ModelPartBuilder.create().uv(10, 37).cuboid(1.0F, -3.0F, 2.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.4F, 2.1F, -2.25F, 0.0438F, -0.0873F, -0.0038F));

		ModelPartData cube_r48 = FrontTail.addChild("cube_r48", ModelPartBuilder.create().uv(38, 18).cuboid(1.3F, -4.0F, -4.925F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.2F, 2.8F, 4.0F, -0.1745F, -0.0873F, 0.0F));

		ModelPartData RearDorsalFin = FrontTail.addChild("RearDorsalFin", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.5F, 2.0F));

		ModelPartData cube_r49 = RearDorsalFin.addChild("cube_r49", ModelPartBuilder.create().uv(38, 30).cuboid(1.9499F, -0.85F, 8.35F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, 2.8F, -7.3F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r50 = RearDorsalFin.addChild("cube_r50", ModelPartBuilder.create().uv(8, 38).cuboid(1.9501F, -1.85F, 9.35F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, 5.55F, -6.05F, 0.7418F, 0.0F, 0.0F));

		ModelPartData cube_r51 = RearDorsalFin.addChild("cube_r51", ModelPartBuilder.create().uv(16, 43).cuboid(1.95F, -1.85F, 6.35F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, 6.55F, -4.2F, 0.9599F, 0.0F, 0.0F));

		ModelPartData AnalFin = FrontTail.addChild("AnalFin", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.0F, 0.5F));

		ModelPartData cube_r52 = AnalFin.addChild("cube_r52", ModelPartBuilder.create().uv(0, 51).cuboid(1.9493F, 0.85F, 6.35F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, -6.0F, -3.2F, -0.829F, 0.0F, 0.0F));

		ModelPartData cube_r53 = AnalFin.addChild("cube_r53", ModelPartBuilder.create().uv(46, 28).cuboid(1.9507F, -0.15F, 8.35F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, -3.4F, -6.25F, -0.4363F, 0.0F, 0.0F));

		ModelPartData cube_r54 = AnalFin.addChild("cube_r54", ModelPartBuilder.create().uv(44, 28).cuboid(1.9503F, 0.85F, 8.35F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, -3.9F, -6.0F, -0.4363F, 0.0F, 0.0F));

		ModelPartData cube_r55 = AnalFin.addChild("cube_r55", ModelPartBuilder.create().uv(4, 51).cuboid(1.9496F, 0.85F, 6.35F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, -6.25F, -2.95F, -0.829F, 0.0F, 0.0F));

		ModelPartData cube_r56 = AnalFin.addChild("cube_r56", ModelPartBuilder.create().uv(22, 43).cuboid(1.95F, -0.15F, 5.35F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, -5.65F, -2.25F, -1.0908F, 0.0F, 0.0F));

		ModelPartData MidTail = modelPartData.addChild("MidTail", ModelPartBuilder.create().uv(50, 23).cuboid(-0.5F, -1.25F, 1.9F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, 8.0F));

		ModelPartData cube_r57 = MidTail.addChild("cube_r57", ModelPartBuilder.create().uv(48, 31).cuboid(1.0F, -2.0F, 5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(36, 49).cuboid(1.0F, -0.5F, 5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.05F, 0.85F, -5.35F, 0.0F, -0.0436F, 0.0F));

		ModelPartData cube_r58 = MidTail.addChild("cube_r58", ModelPartBuilder.create().uv(30, 49).cuboid(1.0F, -2.0001F, 5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(42, 49).cuboid(1.0F, -0.5001F, 5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, 0.85F, -5.25F, 0.0F, 0.0436F, 0.0F));

		ModelPartData cube_r59 = MidTail.addChild("cube_r59", ModelPartBuilder.create().uv(40, 45).cuboid(1.0F, -2.0F, 5.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.2F, 1.1F, -4.75F, 0.0F, 0.0436F, 0.0F));

		ModelPartData cube_r60 = MidTail.addChild("cube_r60", ModelPartBuilder.create().uv(34, 45).cuboid(1.0F, -2.0F, 5.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.8F, 1.1F, -4.85F, 0.0F, -0.0436F, 0.0F));

		ModelPartData CaudalFin = modelPartData.addChild("CaudalFin", ModelPartBuilder.create().uv(46, 19).cuboid(0.0F, 0.75F, 1.15F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(40, 35).cuboid(-0.0001F, 1.0F, 3.15F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 47).cuboid(0.0F, -0.25F, 0.9F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(46, 45).cuboid(0.0F, -1.25F, 1.3F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(50, 34).cuboid(0.0F, -2.25F, 2.3F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, 10.0F));

		ModelPartData cube_r61 = CaudalFin.addChild("cube_r61", ModelPartBuilder.create().uv(50, 37).cuboid(1.9501F, -1.85F, 8.35F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, 2.2F, -5.1F, 0.3491F, 0.0F, 0.0F));

		ModelPartData cube_r62 = CaudalFin.addChild("cube_r62", ModelPartBuilder.create().uv(46, 15).cuboid(1.9499F, -1.85F, 5.35F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, 4.8F, -2.45F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r63 = CaudalFin.addChild("cube_r63", ModelPartBuilder.create().uv(0, 32).cuboid(1.9501F, 0.85F, 4.35F, 0.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-1.95F, -2.7F, -4.05F, -0.4363F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		Body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		FrontTail.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		MidTail.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		CaudalFin.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	@Override
	public ModelPart getPart() {
		return this.root;
	}

	@Override
	public void setAngles(WalleyeEntity entity, float limbSwing, float limbSwingAmount,
						  float ageInTicks, float netHeadYaw, float headPitch) {
		// Reset to model-space pose first
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		// If your fish rarely produces limbSwing, gate it:
		if (limbSwingAmount > 0.01f) {
			// Speed & degree are tuning knobs
			this.animateMovement(ModAnimations.WALLEYESWIM, limbSwing, limbSwingAmount, 2.0f, 2.5f);
		}

		// Drive state-based animations (make sure these names/bones match your animation file)
		this.updateAnimation(entity.swimAnimationState, ModAnimations.WALLEYESWIM, ageInTicks, 1.0f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.WALLEYEIDLE, ageInTicks, 1.0f);

		// Optional: apply head aim if your animation expects it
		// (convert degrees -> radians)
		this.Head.yaw = netHeadYaw * ((float)Math.PI / 180f);
		this.Head.pitch = headPitch * ((float)Math.PI / 180f);
	}

}