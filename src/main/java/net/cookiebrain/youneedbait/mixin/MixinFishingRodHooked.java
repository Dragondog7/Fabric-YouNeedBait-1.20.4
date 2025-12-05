package net.cookiebrain.youneedbait.mixin;

import net.cookiebrain.youneedbait.config.ConfigManager;
import net.cookiebrain.youneedbait.config.ModConfig;
import net.minecraft.advancement.criterion.FishingRodHookedCriterion;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.inventory.FishingHelper;

@Mixin(FishingRodHookedCriterion.class)
public class MixinFishingRodHooked {
    private static ModConfig cfg = ConfigManager.get();

        @Inject(method = "trigger", at = @At("HEAD"))
        private void onTrigger(ServerPlayerEntity player, ItemStack rod, FishingBobberEntity bobber, Collection<ItemStack> fishingLootst, CallbackInfo ci) {

// Only consume bait for non‑vanilla rods (e.g. your fancy rod)
            if (rod.isOf(Items.FISHING_ROD)) {
                YouNeedBait.LOGGER.info(
                        "[MixinFishingRodHooked] Skipping bait removal for vanilla fishing rod."
                );
                return;
            }

            // (Optional) If you only ever want YOUR rod to use bait, be stricter:
            // if (!rod.isOf(ModItems.FANCYFISHINGROD_ITEM)) {
            //     YouNeedBait.LOGGER.info("[MixinFishingRodHooked] Skipping bait removal for non‑fancy rod: {}", rod);
            //     return;
            // }

            float probability = cfg.features.baitUsagePercent / 100.0f;
            YouNeedBait.LOGGER.info(
                    "[MixinFishingRodHooked] Bait use probability is {} for rod {}",
                    probability, rod
            );

            if (Math.random() < probability) {
                FishingHelper.removeBait(player);
            }
        }

    }
