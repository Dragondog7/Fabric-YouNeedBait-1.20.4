package net.cookiebrain.youneedbait.mixin;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.minecraft.advancement.criterion.FishingRodHookedCriterion;
import net.minecraft.advancement.criterion.UsingItemCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(UsingItemCriterion.class)
public class MixinUsingItem {
    @Inject(method = "trigger", at = @At("HEAD"))
    private void onTrigger(ServerPlayerEntity player, ItemStack stack, CallbackInfo ci){
        System.out.println("You used an item of: ");
        System.out.println(stack.getName());
        //System.out.println("You placed a tacklebox");
    }
}
