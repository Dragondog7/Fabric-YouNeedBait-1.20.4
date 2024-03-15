package net.cookiebrain.youneedbait.mixin;

import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public class MixinFishingRodItem {
    //the method is the name of the method in the class you want to override
    //HEAD means your code runs before the target class method starts, TAIL would be after
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void injectUse(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {

        //Check for a hook
        //player.sendMessage(Text.literal("Checking for a hook"));
        if(hasHook(player)){
            //player.sendMessage(Text.literal("Hook found"));
            //player.sendMessage(Text.literal("Checking for bait"));
            if (!hasBait(player)) {
                // Cancel the event (prevent fishing rod use) if the condition is not met
                // To effectively return "nothing" or indicate cancellation, use ActionResult.FAIL
                // and ItemStack.EMPTY (or another appropriate ItemStack if needed).
                // Gets the fishing rod item
                ItemStack itemStack = player.getStackInHand(hand);
                //Without this the fishing rod disappears
                cir.setReturnValue(TypedActionResult.fail(itemStack));
                cir.cancel();
                //player.sendMessage(Text.literal("You need bait to use the fishing rod!"));
            }
            //player.sendMessage(Text.literal("Hook found"));
        } else {
            // Gets the fishing rod item
            ItemStack itemStack = player.getStackInHand(hand);
            //Without this the fishing rod disappears
            cir.setReturnValue(TypedActionResult.fail(itemStack));
            cir.cancel();
            //player.sendMessage(Text.literal("You need a hook to use the fishing rod!"));

        }
        // If the player does have the item, the method proceeds as normal
    }

    @Inject(method = "use", at = @At("TAIL"), cancellable = true)
    private void afterCatch(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if(cir != null && hand == Hand.MAIN_HAND) {

            //player.sendMessage(Text.literal(cir.getReturnValue().getValue().));
        }
    }

//    @Inject(method = "use", at = @At("TAIL"), cancellable = true)
//    private void afterCatch(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
//        if (hand == Hand.MAIN_HAND) {
//            player.sendMessage(Text.literal("test1 - main hand"));
//            Entity fh = player.fishHook;
//            if (fh != null) {
//                player.sendMessage(Text.literal("fishhook is not null"));
//                if(player.fishHook.getHookedEntity() != null) {
//                    //The fish would have been caught here
//                    player.sendMessage(Text.literal(String.valueOf(player.fishHook.getHookedEntity().getDisplayName())));
//                    player.sendMessage(Text.literal("fishhook is null"));
//
//                    //Remove the bait item
//                    ItemStack itemStackToRemove = new ItemStack(ModItems.WORM, 1);
//                    if (player.getInventory().contains(itemStackToRemove)) {
//                        player.sendMessage(Text.literal("Player has a worm"));
//                        player.getInventory().removeOne(itemStackToRemove);
//                    }
//                }
//
//            }
//        }
//    }

    private static boolean hasHook(PlayerEntity player){
        return player.getInventory().contains(new ItemStack(ModItems.HOOK));
    }
    private static boolean hasBait(PlayerEntity player) {
        //This checks that the player has valid bait from the FISHING_BAIT tag

        // Get the tag
        TagKey<Item> FISHING_BAIT_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier("youneedbait", "fishing_bait"));

        for (int i = 0; i < player.getInventory().size(); i++) {
            //player.sendMessage(Text.literal("Looping through inventory"));
            ItemStack itemStack = player.getInventory().getStack(i);

            // Check if the ItemStack is not empty and it matches the tag
            if (!itemStack.isEmpty() && itemStack.isIn(FISHING_BAIT_TAG)) {
                //player.sendMessage(Text.literal("Found bait"));
                //player.sendMessage(Text.literal(itemStack.toString()));
                return true; // Found an item with the tag
            }
        }
        return false;
    }
}

