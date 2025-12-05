package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.custom.FancyFishingBobberEntity;
import net.cookiebrain.youneedbait.item.custom.FancyFishingRodItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import net.minecraft.client.render.OverlayTexture;

public class FancyFishingBobberRenderer extends EntityRenderer<FancyFishingBobberEntity> {

    // Texture is irrelevant for the solid-color box, but required by API
    private static final Identifier TEXTURE = new Identifier(YouNeedBait.MOD_ID, "textures/entity/fishing_hook.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);

    // === TUNING KNOBS ===
    // Adjust these to move where the line starts relative to the player
    private static final double EXTRA_FORWARD = 1.24; // Positive = further in front of player (along look direction)
    private static final double EXTRA_UP = -1.00;      // Negative = higher, Positive = lower
    private static final double EXTRA_SIDE = -0.755;    // Positive = further to the right side the rod is held on

    public FancyFishingBobberRenderer(EntityRendererFactory.Context context) {
        super(context);
        System.out.println("FancyFishingBobberRenderer created");
    }

    @Override
    public void render(FancyFishingBobberEntity fishingBobberEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        double s;
        float r;
        double q;
        double p;
        double o;

        // --- NEW: Get colors from the entity ---
        int bobberR = fishingBobberEntity.getBobberRed();
        int bobberG = fishingBobberEntity.getBobberGreen();
        int bobberB = fishingBobberEntity.getBobberBlue();
        int bobberA = fishingBobberEntity.getBobberAlpha();

        int lineR = fishingBobberEntity.getLineRed();
        int lineG = fishingBobberEntity.getLineGreen();
        int lineB = fishingBobberEntity.getLineBlue();
        int lineA = fishingBobberEntity.getLineAlpha();
        // ---------------------------------------

        PlayerEntity playerEntity = fishingBobberEntity.getPlayerOwner();
        if (playerEntity == null) {
            return;
        }
        matrixStack.push();
        matrixStack.push();
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        matrixStack.multiply(this.dispatcher.getRotation());
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);
        // Use the entity's colors for the bobber
        vertex(vertexConsumer, matrix4f, matrix3f, i, 0.0f, 0, 0, 1, bobberR, bobberG, bobberB, bobberA);
        vertex(vertexConsumer, matrix4f, matrix3f, i, 1.0f, 0, 1, 1, bobberR, bobberG, bobberB, bobberA);
        vertex(vertexConsumer, matrix4f, matrix3f, i, 1.0f, 1, 1, 0, bobberR, bobberG, bobberB, bobberA);
        vertex(vertexConsumer, matrix4f, matrix3f, i, 0.0f, 1, 0, 0, bobberR, bobberG, bobberB, bobberA);
        matrixStack.pop();
        int j = playerEntity.getMainArm() == Arm.RIGHT ? 1 : -1;
        ItemStack itemStack = playerEntity.getMainHandStack();
        // Modified to check for FancyFishingRodItem as well
        if (!itemStack.isOf(Items.FISHING_ROD) && !(itemStack.getItem() instanceof FancyFishingRodItem)) {
            j = -j;
        }
        float h = playerEntity.getHandSwingProgress(g);
        float k = MathHelper.sin(MathHelper.sqrt(h) * (float)Math.PI);
        float l = MathHelper.lerp(g, playerEntity.prevBodyYaw, playerEntity.bodyYaw) * ((float)Math.PI / 180);
        double d = MathHelper.sin(l);
        double e = MathHelper.cos(l);
        double m = (double)j * 0.35;
        double n = 0.8;
        if (this.dispatcher.gameOptions != null && !this.dispatcher.gameOptions.getPerspective().isFirstPerson() || playerEntity != MinecraftClient.getInstance().player) {
            o = MathHelper.lerp((double)g, playerEntity.prevX, playerEntity.getX()) - e * m - d * 0.8;
            p = playerEntity.prevY + (double)playerEntity.getStandingEyeHeight() + (playerEntity.getY() - playerEntity.prevY) * (double)g - 0.45;
            q = MathHelper.lerp((double)g, playerEntity.prevZ, playerEntity.getZ()) - d * m + e * 0.8;
            r = playerEntity.isInSneakingPose() ? -0.1875f : 0.0f;
        } else {
            s = 960.0 / (double)this.dispatcher.gameOptions.getFov().getValue().intValue();
            Vec3d vec3d = this.dispatcher.camera.getProjection().getPosition((float)j * 0.525f, -0.1f);
            vec3d = vec3d.multiply(s);
            vec3d = vec3d.rotateY(k * 0.5f);
            vec3d = vec3d.rotateX(-k * 0.7f);
            o = MathHelper.lerp((double)g, playerEntity.prevX, playerEntity.getX()) + vec3d.x;
            p = MathHelper.lerp((double)g, playerEntity.prevY, playerEntity.getY()) + vec3d.y;
            q = MathHelper.lerp((double)g, playerEntity.prevZ, playerEntity.getZ()) + vec3d.z;
            r = playerEntity.getStandingEyeHeight();
        }

        // === APPLY TUNING KNOBS ===
        // o, p, q are the rod tip position in world space
        // d = sin(bodyYaw), e = cos(bodyYaw)
        // Forward direction: -d (X), +e (Z)
        // Side direction: -e (X), -d (Z) for right side
        o = o - d * EXTRA_FORWARD - e * EXTRA_SIDE * j;
        p = p - EXTRA_UP;
        q = q + e * EXTRA_FORWARD - d * EXTRA_SIDE * j;

        s = MathHelper.lerp((double)g, fishingBobberEntity.prevX, fishingBobberEntity.getX());
        double t = MathHelper.lerp((double)g, fishingBobberEntity.prevY, fishingBobberEntity.getY()) + 0.25;
        double u = MathHelper.lerp((double)g, fishingBobberEntity.prevZ, fishingBobberEntity.getZ());
        float v = (float)(o - s);
        float w = (float)(p - t) + r;
        float x = (float)(q - u);
        VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getLineStrip());
        MatrixStack.Entry entry2 = matrixStack.peek();
        int y = 16;
        for (int z = 0; z <= 16; ++z) {
            renderFishingLine(v, w, x, vertexConsumer2, entry2, percentage(z, 16), percentage(z + 1, 16), lineR, lineG, lineB, lineA);
        }
        matrixStack.pop();
        super.render(fishingBobberEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private static float percentage(int value, int max) {
        return (float)value / (float)max;
    }

    // --- NEW: vertex method now accepts color parameters ---
    private static void vertex(VertexConsumer buffer, Matrix4f matrix, Matrix3f normalMatrix, int light, float x, int y, int u, int v, int r, int g, int b, int a) {
        buffer.vertex(matrix, x - 0.5f, (float)y - 0.5f, 0.0f)
                .color(r, g, b, a)
                .texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0.0f, 1.0f, 0.0f).next();
    }

    // --- NEW: renderFishingLine method now accepts color parameters ---
    private static void renderFishingLine(float x, float y, float z, VertexConsumer buffer, MatrixStack.Entry matrices, float segmentStart, float segmentEnd, int r, int g, int b, int a) {
        float f = x * segmentStart;
        float g_ = y * (segmentStart * segmentStart + segmentStart) * 0.5f + 0.25f; // Renamed g to g_ to avoid conflict
        float h = z * segmentStart;
        float i = x * segmentEnd - f;
        float j = y * (segmentEnd * segmentEnd + segmentEnd) * 0.5f + 0.25f - g_; // Use g_ here
        float k = z * segmentEnd - h;
        float l = MathHelper.sqrt(i * i + j * j + k * k);
        buffer.vertex(matrices.getPositionMatrix(), f, g_, h) // Use g_ here
                .color(r, g, b, a)
                .normal(matrices.getNormalMatrix(), i /= l, j /= l, k /= l).next();
    }

    @Override
    public Identifier getTexture(FancyFishingBobberEntity fishingBobberEntity) {
        return TEXTURE;
    }
}