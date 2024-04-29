package net.cookiebrain.youneedbait.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.cookiebrain.youneedbait.item.ModItems;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FishingBobberEntityRenderer.class)
public class FishingBobberRendererMixin {
    @ModifyExpressionValue(method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean addCustomFishingRodToFishingRodCheck(boolean original, @Local ItemStack itemStack) {
        return original || itemStack.isOf(ModItems.FANCYFISHINGROD_ITEM);
    }

    @ModifyVariable(
            method = "renderFishingLine",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true)

    private static float modifyXCoordinate(float originalX) {
        return originalX + 2.5f;
    }

    @ModifyVariable(
            method = "renderFishingLine",
            at = @At("HEAD"),
            ordinal = 1,
            argsOnly = true)
    private static float modifyYCoordinate(float originalY) {
        return originalY + 0.8f;
    }

    @ModifyVariable(
            method = "renderFishingLine",
            at = @At("HEAD"),
            ordinal = 2,
            argsOnly = true)
    private static float modifyZCoordinate(float originalZ) {
        return originalZ + 0.3f;
    }
}
