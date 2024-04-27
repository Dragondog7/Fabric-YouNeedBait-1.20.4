package net.cookiebrain.youneedbait.item.custom;

import net.cookiebrain.youneedbait.inventory.FishingHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class FancyFishingRodItem extends Item
        implements Vanishable {
    public FancyFishingRodItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        int i;

//        //Check for requirements
//        //Check for a hook
//        if(FishingHelper.hasHook(user)) {
//            user.sendMessage(Text.literal("Hook found"));
//            user.sendMessage(Text.literal("Checking for bait"));
//            if (!FishingHelper.hasBait(user)) {
//                user.sendMessage(Text.literal("You need bait to use the fishing rod!"));
//                // Cancel the event (prevent fishing rod use) if the condition is not met
//                // To effectively return "nothing" or indicate cancellation, use ActionResult.FAIL
//                // and ItemStack.EMPTY (or another appropriate ItemStack if needed).
//                //Without this the fishing rod disappears
//                return TypedActionResult.fail(itemStack);
//
//            }
//        } else {
//            return TypedActionResult.fail(itemStack);
//        }
        user.sendMessage(Text.literal("Beginning of original logic"));
        if (user.fishHook != null) {
            user.sendMessage(Text.literal("Retrieve bobber"));
            if (!world.isClient) {
                i = user.fishHook.use(itemStack);
                itemStack.damage(i, user, (p) -> {
                    p.sendToolBreakStatus(hand);
                });
            }
            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            user.sendMessage(Text.literal("Throw bobber"));
            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!world.isClient) {
                i = EnchantmentHelper.getLure(itemStack);
                int j = EnchantmentHelper.getLuckOfTheSea(itemStack);
                user.sendMessage(Text.literal("Before spawn bobber"));
                world.spawnEntity(new FishingBobberEntity(user, world, j, i));
            }

            //user.incrementStat(Stats.USED.getOrCreateStat(this));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
