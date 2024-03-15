package net.cookiebrain.youneedbait.mixin;

import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.advancement.criterion.FishingRodHookedCriterion;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(FishingRodHookedCriterion.class)
public class MixinFishingRodHooked {
        @Inject(method = "trigger", at = @At("HEAD"))
        private void onTrigger(ServerPlayerEntity player, ItemStack rod, FishingBobberEntity bobber, Collection<ItemStack> fishingLootst, CallbackInfo ci) {

            if (Math.random() < 0.1) { // 10% chance
                //Remove a bait
                for (int i = 0; i < player.getInventory().size(); i++) {
                    ItemStack stack = player.getInventory().getStack(i);

                    // Check if the ItemStack is the item we want to remove
                    if (stack.isIn(ModTags.Items.FISH_BAIT_ITEMS)) {
                        // Decrement the ItemStack size by 1
                        stack.decrement(1);

                        // If the stack is empty after shrinking, remove it from the inventory
                        if (stack.isEmpty()) {
                            player.getInventory().removeStack(i);
                        }

                        // Break after removing one item
                        break;
                    }
                }
            }
        }
    }
