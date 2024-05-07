package net.cookiebrain.youneedbait.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.item.ModItems;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntityRenderer.class)
public class FishingBobberRendererMixin {
    private static boolean isCustomModItem = false;

    @ModifyExpressionValue(method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean addCustomFishingRodToFishingRodCheck(boolean original, @Local ItemStack itemStack) {

        if (itemStack.getItem() == ModItems.FANCYFISHINGROD_ITEM) {
            isCustomModItem = true; // Set the flag to true if it's a custom mod item
        } else {
            isCustomModItem = false;
        }
        return original || itemStack.isOf(ModItems.FANCYFISHINGROD_ITEM);
    }


    @ModifyVariable(
            method = "renderFishingLine",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true)

    private static float modifyXCoordinate(float originalX) {
        if (isCustomModItem) {
            //Adding moves the line away from the player in the direction the player is facing
            return originalX + 1.2f;
        } else {
            return originalX;
        }
    }

    @ModifyVariable(
            method = "renderFishingLine",
            at = @At("HEAD"),
            ordinal = 1,
            argsOnly = true)
    private static float modifyYCoordinate(float originalY) {
        if (isCustomModItem) {
            return originalY + 0.9f;
        } else {
            return originalY;
        }
    }

    @ModifyVariable(
            method = "renderFishingLine",
            at = @At("HEAD"),
            ordinal = 2,
            argsOnly = true)
    private static float modifyZCoordinate(float originalZ) {
        if (isCustomModItem) {
            return originalZ - 1.0f;
        } else {
            return originalZ;
        }
    }

}
