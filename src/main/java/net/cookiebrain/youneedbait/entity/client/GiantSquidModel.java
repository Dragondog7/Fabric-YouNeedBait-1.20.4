//package net.cookiebrain.youneedbait.entity.client;
//
//import net.cookiebrain.youneedbait.entity.animation.ModAnimations;
//import net.cookiebrain.youneedbait.entity.custom.CatFishEntity;
//import net.cookiebrain.youneedbait.entity.custom.GiantSquidEntity;
//import net.minecraft.client.model.*;
//import net.minecraft.client.render.VertexConsumer;
//import net.minecraft.client.render.entity.model.EntityModel;
//import net.minecraft.client.render.entity.model.SinglePartEntityModel;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.entity.Entity;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
//public class GiantSquidModel<T extends GiantSquidEntity> extends SinglePartEntityModel<T> {
//	private final ModelPart giantsquid;
//	public GiantSquidModel(ModelPart root) {
//		this.giantsquid = root.getChild("GiantSquid");
//	}
//	public static TexturedModelData getTexturedModelData() {
//		ModelData modelData = new ModelData();
//		ModelPartData modelPartData = modelData.getRoot();
//		ModelPartData GiantSquid = modelPartData.addChild("GiantSquid", ModelPartBuilder.create().uv(0, 0).cuboid(-11.0F, -163.0F, -10.0F, 20.0F, 46.0F, 20.0F, new Dilation(0.0F))
//		.uv(0, 66).cuboid(-9.0F, -181.0F, -8.0F, 16.0F, 18.0F, 16.0F, new Dilation(0.0F))
//		.uv(80, 0).cuboid(-10.0F, -117.0F, -9.0F, 18.0F, 6.0F, 18.0F, new Dilation(0.0F))
//		.uv(48, 105).cuboid(-7.0F, -111.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F))
//		.uv(64, 80).cuboid(-9.0F, -109.0F, -8.0F, 16.0F, 9.0F, 16.0F, new Dilation(0.0F))
//		.uv(80, 24).cuboid(-7.0F, -86.0F, -8.0F, 12.0F, 7.0F, 16.0F, new Dilation(0.0F))
//		.uv(48, 161).cuboid(-7.0F, -79.0F, 3.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(158, 70).cuboid(-3.5F, -79.0F, 5.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(158, 56).cuboid(-3.5F, -79.0F, -8.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(24, 158).cuboid(-8.0F, -79.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(98, 105).cuboid(-7.75F, -80.0F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
//		.uv(158, 0).cuboid(2.0F, -79.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(36, 161).cuboid(-7.0F, -79.0F, -6.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(84, 160).cuboid(1.0F, -79.0F, 4.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(156, 160).cuboid(1.0F, -79.0F, -7.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(154, 96).cuboid(-8.0F, -68.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(114, 154).cuboid(-7.0F, -68.0F, -6.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(154, 118).cuboid(-3.5F, -68.0F, -8.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(132, 154).cuboid(1.0F, -68.0F, -7.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(154, 146).cuboid(2.0F, -68.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(157, 28).cuboid(1.0F, -68.0F, 4.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(157, 42).cuboid(-3.5F, -68.0F, 5.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(144, 157).cuboid(-7.0F, -68.0F, 3.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(48, 147).cuboid(-8.0F, -57.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(60, 147).cuboid(-7.0F, -57.0F, -6.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(102, 149).cuboid(-3.5F, -57.0F, -8.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(0, 150).cuboid(1.0F, -57.0F, -7.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(12, 150).cuboid(2.0F, -57.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(72, 151).cuboid(1.0F, -57.0F, 4.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(151, 132).cuboid(-3.5F, -57.0F, 5.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(152, 14).cuboid(-7.0F, -57.0F, 3.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(145, 34).cuboid(-8.0F, -46.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(145, 48).cuboid(-7.0F, -46.0F, -6.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(145, 107).cuboid(-3.5F, -46.0F, -8.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(146, 0).cuboid(1.0F, -46.0F, -7.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(146, 62).cuboid(2.0F, -46.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(146, 82).cuboid(1.0F, -46.0F, 4.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(90, 146).cuboid(-3.5F, -46.0F, 5.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(36, 147).cuboid(-7.0F, -46.0F, 3.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(111, 135).cuboid(-8.0F, -35.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(0, 136).cuboid(-7.0F, -35.0F, -6.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(12, 136).cuboid(-3.5F, -35.0F, -8.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(78, 137).cuboid(1.0F, -35.0F, -7.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(142, 121).cuboid(2.0F, -35.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(123, 143).cuboid(1.0F, -35.0F, 4.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(142, 143).cuboid(-3.5F, -35.0F, 5.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(24, 144).cuboid(-7.0F, -35.0F, 3.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(54, 133).cuboid(-8.0F, -24.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(66, 133).cuboid(-7.0F, -24.0F, -6.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(133, 110).cuboid(-3.5F, -24.0F, -8.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(133, 132).cuboid(1.0F, -24.0F, -7.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(134, 0).cuboid(2.0F, -24.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(134, 61).cuboid(1.0F, -24.0F, 4.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(134, 82).cuboid(-3.5F, -24.0F, 5.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(99, 135).cuboid(-7.0F, -24.0F, 3.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(60, 0).cuboid(-8.0F, -13.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(0, 66).cuboid(-7.0F, -13.0F, -6.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(48, 66).cuboid(-3.5F, -13.0F, -8.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(80, 24).cuboid(1.0F, -13.0F, -7.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(64, 82).cuboid(2.0F, -13.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(112, 82).cuboid(1.0F, -13.0F, 4.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(42, 133).cuboid(-3.5F, -13.0F, 5.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(133, 44).cuboid(-7.0F, -13.0F, 3.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
//		.uv(62, 48).cuboid(-10.0F, -100.0F, -9.0F, 18.0F, 14.0F, 18.0F, new Dilation(0.0F))
//		.uv(112, 80).cuboid(-10.0F, -110.0F, 8.0F, 18.0F, 1.0F, 1.0F, new Dilation(0.0F))
//		.uv(98, 110).cuboid(-10.0F, -110.0F, -9.0F, 18.0F, 1.0F, 1.0F, new Dilation(0.0F))
//		.uv(78, 105).cuboid(7.0F, -110.0F, -9.0F, 1.0F, 1.0F, 18.0F, new Dilation(0.0F))
//		.uv(60, 0).cuboid(-10.0F, -110.0F, -8.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F))
//		.uv(0, 100).cuboid(-7.0F, -192.0F, -6.0F, 12.0F, 11.0F, 12.0F, new Dilation(0.0F))
//		.uv(50, 119).cuboid(5.0F, -183.0F, -4.0F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F))
//		.uv(98, 112).cuboid(-8.0F, -183.0F, -4.0F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F))
//		.uv(50, 107).cuboid(-8.0F, -186.0F, -2.0F, 1.0F, 3.0F, 4.0F, new Dilation(0.0F))
//		.uv(84, 105).cuboid(5.0F, -186.0F, -2.0F, 1.0F, 3.0F, 4.0F, new Dilation(0.0F))
//		.uv(62, 119).cuboid(-10.0F, -165.0F, -6.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F))
//		.uv(36, 119).cuboid(7.0F, -165.0F, -6.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F))
//		.uv(160, 104).cuboid(7.0F, -168.0F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
//		.uv(158, 84).cuboid(-10.0F, -168.0F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
//		.uv(104, 112).cuboid(-3.0F, -203.0F, -6.0F, 4.0F, 11.0F, 12.0F, new Dilation(0.0F))
//		.uv(78, 124).cuboid(1.0F, -195.0F, -5.0F, 2.0F, 3.0F, 10.0F, new Dilation(0.0F))
//		.uv(136, 24).cuboid(1.0F, -198.0F, -3.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F))
//		.uv(56, 100).cuboid(1.0F, -201.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
//		.uv(0, 123).cuboid(-5.0F, -195.0F, -5.0F, 2.0F, 3.0F, 10.0F, new Dilation(0.0F))
//		.uv(134, 96).cuboid(-5.0F, -198.0F, -3.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F))
//		.uv(36, 100).cuboid(-5.0F, -201.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
//		.uv(24, 127).cuboid(-2.5F, -203.0F, -12.0F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F))
//		.uv(0, 0).cuboid(-2.5F, -203.0F, 6.0F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F))
//		.uv(0, 100).cuboid(-2.0F, -192.0F, -10.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F))
//		.uv(78, 10).cuboid(-2.0F, -192.0F, 5.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F))
//		.uv(114, 91).cuboid(-2.5F, -208.0F, -7.0F, 3.0F, 5.0F, 14.0F, new Dilation(0.0F))
//		.uv(120, 24).cuboid(-2.5F, -211.0F, -5.0F, 3.0F, 3.0F, 10.0F, new Dilation(0.0F))
//		.uv(90, 10).cuboid(-2.0F, -206.0F, 7.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
//		.uv(78, 0).cuboid(-2.0F, -206.0F, -9.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
//		.uv(14, 123).cuboid(-2.0F, -214.0F, -3.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F))
//		.uv(12, 0).cuboid(-2.0F, -217.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
//
//		ModelPartData cube_r1 = GiantSquid.addChild("cube_r1", ModelPartBuilder.create().uv(78, 0).cuboid(6.0F, -183.0F, -3.0F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F))
//		.uv(88, 0).cuboid(6.0F, -186.0F, -1.0F, 1.0F, 3.0F, 4.0F, new Dilation(0.0F))
//		.uv(46, 100).cuboid(-7.0F, -186.0F, -1.0F, 1.0F, 3.0F, 4.0F, new Dilation(0.0F))
//		.uv(36, 100).cuboid(-7.0F, -183.0F, -3.0F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F))
//		.uv(160, 126).cuboid(8.0F, -168.0F, -2.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
//		.uv(60, 161).cuboid(-9.0F, -168.0F, -2.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
//		.uv(48, 68).cuboid(-9.0F, -165.0F, -5.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F))
//		.uv(116, 47).cuboid(8.0F, -165.0F, -5.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
//		return TexturedModelData.of(modelData, 256, 256);
//	}
//	@Override
//	public void setAngles(GiantSquidEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//		//This HAS to be called first
//		this.getPart().traverse().forEach(ModelPart::resetTransform);
//		this.getPart().traverse().forEach(ModelPart::resetTransform);
//
//		this.animateMovement(ModAnimations.GIANTSQUIDSWIM, limbSwing, limbSwingAmount, 2f, 2.5f);
//		this.updateAnimation(entity.idleAnimationState, ModAnimations.GIANTSQUIDIDLE, ageInTicks, 1f);
//	}
//
//	@Override
//	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
//		giantsquid.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
//	}
//
//	@Override
//	public ModelPart getPart() { return giantsquid; }
//
//}