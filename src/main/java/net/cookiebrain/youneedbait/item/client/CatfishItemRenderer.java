package net.cookiebrain.youneedbait.item.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class CatfishItemRenderer extends BuiltinModelItemRenderer {

    public CatfishItemRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelLoader entityModelLoader) {
        super(blockEntityRenderDispatcher, entityModelLoader);
    }

    @Override
    public void render(
            ItemStack stack,
            ModelTransformationMode mode,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay
    ) {
        matrices.push();

        // Only override transforms when in "fixed" mode (item frames etc.)
        if (mode == ModelTransformationMode.FIXED) {
            // Example: make it larger + rotate slightly
            // Reset-ish and apply your own transform
            matrices.translate(0.5F, 0.5F, 0.5F);     // center
            matrices.scale(1.6F, 1.6F, 1.6F);          // <-- your custom "fixed scale"
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(0.0F));
            matrices.translate(-0.5F, -0.5F, -0.5F);
        }

        // Render with the normal item renderer, which will use your modified matrix stack
        MinecraftClient.getInstance().getItemRenderer().renderItem(
                stack,
                mode,
                false,
                matrices,
                vertexConsumers,
                light,
                overlay,
                MinecraftClient.getInstance().getItemRenderer().getModel(stack, null, null, 0)
        );

        matrices.pop();
    }
}