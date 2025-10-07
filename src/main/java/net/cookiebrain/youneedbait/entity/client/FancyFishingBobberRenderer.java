package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.entity.custom.FancyFishingBobberEntity;
import net.cookiebrain.youneedbait.item.ModItems;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class FancyFishingBobberRenderer extends FishingBobberEntityRenderer {

    public FancyFishingBobberRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public void render(FishingBobberEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        if (!(entity instanceof FancyFishingBobberEntity)) {
            super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
            return;
        }

        PlayerEntity player = entity.getPlayerOwner();
        if (player == null) return;

        ItemStack offhand = player.getStackInHand(Hand.OFF_HAND);
        if (offhand.getItem() == ModItems.FANCYFISHINGROD_ITEM) {
            renderFishingLineFromLeftHand((FancyFishingBobberEntity) entity, player, tickDelta, matrices, vertexConsumers, light);
        } else {
            super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        }
    }

    private void renderFishingLineFromLeftHand(FancyFishingBobberEntity entity, PlayerEntity player,
                                               float tickDelta, MatrixStack matrices,
                                               VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        // Get player's left hand position
        float yaw = player.getYaw(tickDelta);
        Vec3d leftHandPos = player.getLerpedPos(tickDelta)
                .add(0, player.getEyeHeight(player.getPose()) - 0.4, 0)
                .add(-0.35 * Math.sin(Math.toRadians(yaw)), 0, -0.35 * Math.cos(Math.toRadians(yaw)));

        // Get bobber position
        Vec3d bobberPos = entity.getPos();

        // Calculate line vector
        double dx = bobberPos.x - leftHandPos.x;
        double dy = bobberPos.y - leftHandPos.y;
        double dz = bobberPos.z - leftHandPos.z;

        // Render the line (adapted from vanilla)
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getLines());
        MatrixStack.Entry entry = matrices.peek();
        float length = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
        int segments = (int) (length * 4);

        for (int i = 0; i <= segments; i++) {
            float t = (float) i / segments;
            float x = (float) (leftHandPos.x + dx * t);
            float y = (float) (leftHandPos.y + dy * t - (t * t + t) * 0.5); // Add slight curve
            float z = (float) (leftHandPos.z + dz * t);
            vertexConsumer.vertex(entry.getPositionMatrix(), x, y, z)
                    .color(255, 255, 255, 255)
                    .normal(entry.getNormalMatrix(), 0, 1, 0)
                    .light(light)
                    .next();
        }

        matrices.pop();
    }
}
