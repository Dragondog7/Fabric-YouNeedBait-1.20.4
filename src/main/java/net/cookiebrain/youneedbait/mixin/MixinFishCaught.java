package net.cookiebrain.youneedbait.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FishingBobberEntity.class) // Replace TargetClass with the actual class you're targeting
public abstract class MixinFishCaught {

//    @Mixin(FishingHook.class)
//    public abstract class FishingHookMixin {
//        @Shadow @Nullable public abstract Player getPlayerOwner();
//        @ModifyArg(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z", ordinal = 0))
//        public Entity replaceHookedItems(Entity original) {
//            return FishingReal.convertItemEntity(original, getPlayerOwner());
//        }
//    }
    //The @Shadow directive allow access to methods within the class
    @Shadow
//    public abstract PlayerEntity getPlayerOwner();
//    @Shadow
//    public abstract Entity getHookedEntity();
//    @Shadow
//    private void updateHookedEntityId(){};
//    @Inject(method = "updateHookedEntityId", @At(value = "LINE", line = 123), locals = LocalCapture.CAPTURE_FAILSOFT)
//    private void onFishCaught(ItemStack usedItem, CallbackInfoReturnable<TypedActionResult<Integer>> cir) {
    @Inject(method = "use", at = @At("HEAD"),locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onFishCaught(ItemStack usedItem, CallbackInfoReturnable<TypedActionResult<Integer>> cir) {
//        System.out.println("things are here");
//        Entity result = getHookedEntity();
//        if(result != null){
//            System.out.println("got hooked entity");
//        }
//        System.out.println(getHookedEntity().getDisplayName());
//        if(hookedEntity != null){
//            System.out.println("hooked something");
//            System.out.println(getHookedEntity().getDisplayName());
//        }
//        System.out.println(getPlayerOwner().getDisplayName());
//        System.out.println(usedItem.getName());
        //thrower.sendMessage(Text.literal("This is where I think the fishing caught stuff should happen"));
        // Your code goes here
        // This is where you can modify what happens when a fish is caught
    }
}
