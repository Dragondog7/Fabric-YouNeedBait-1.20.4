package net.cookiebrain.youneedbait.item.custom;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.entity.custom.FancyFishingBobberEntity;
import net.cookiebrain.youneedbait.inventory.FishingHelper;
import net.cookiebrain.youneedbait.inventory.ItemStackHelper;
import net.cookiebrain.youneedbait.screen.FancyFishingRodScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class FancyFishingRodItem extends FishingRodItem {
    private static final String ROD_TIER_KEY = "rod_tier";
    private static final int MIN_ROD_TIER = 0;
    private static final int MAX_ROD_TIER = 4;

    public FancyFishingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

//        YouNeedBait.LOGGER.info("=== FancyFishingRodItem.use() START ===");
//        YouNeedBait.LOGGER.info("World isClient: {}", world.isClient);
//        YouNeedBait.LOGGER.info("Player: {}", user.getName().getString());
//        YouNeedBait.LOGGER.info("Hand: {}", hand);
//        YouNeedBait.LOGGER.info("Player isSneaking: {}", user.isSneaking());

        // Determine how many slots this rod *should* have based on its tier
        int slotCount = getSlotCountForStack(stack);
        int currentTier = getTier(stack);

        //YouNeedBait.LOGGER.info("Rod tier: {}, Slot count: {}", currentTier, slotCount);

        // Load items from NBT
        DefaultedList<ItemStack> rodInventory = ItemStackHelper.nbtToItemStack(stack, "fishingrod_inventory");
        //YouNeedBait.LOGGER.info("Loaded inventory size: {}", rodInventory.size());

        // Ensure the list has the proper size for this tier
        rodInventory = resizeInventoryList(rodInventory, slotCount);
        //YouNeedBait.LOGGER.info("Resized inventory size: {}", rodInventory.size());

        // Check if the player is holding down shift
        if (user.isSneaking()) {
            //YouNeedBait.LOGGER.info("Player is sneaking - attempting to open GUI");

            if (!world.isClient) {
                //YouNeedBait.LOGGER.info("SERVER: Creating ExtendedScreenHandlerFactory");

                try {
                    user.openHandledScreen(new ExtendedScreenHandlerFactory() {
                        @Override
                        public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                            buf.writeEnumConstant(hand);
                        }

                        @Override
                        public Text getDisplayName() {
                            return Text.literal("Fancy Fishing Rod");
                        }

                        @Override
                        public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {

                            try {
                                FancyFishingRodScreenHandler handler = new FancyFishingRodScreenHandler(syncId, playerInventory, stack);
                                return handler;
                            } catch (Exception e) {
                                throw e;
                            }
                        }
                    });

                } catch (Exception e) {
                    YouNeedBait.LOGGER.error("SERVER: ERROR opening screen", e);
                }
            } else {
            }
            return TypedActionResult.success(stack, world.isClient());
        } else {
            // Check for requirements
            // Check for a hook
            if (FishingHelper.hasHook(user)) {
                if (!FishingHelper.hasBait(user)) {
                    YouNeedBait.LOGGER.info("Player has hook but no bait - failing");
                    return TypedActionResult.fail(stack);
                }
            } else {
                YouNeedBait.LOGGER.info("Player has no hook - failing");
                return TypedActionResult.fail(stack);
            }

            if (user.fishHook != null) {

                if (!world.isClient) {
                    int damage = user.fishHook.use(stack);
                    stack.damage(damage, user, (p) -> {
                        p.sendToolBreakStatus(hand);
                    });

                    // Custom modifier slot logic
                    if (rodInventory.stream().anyMatch(itemStack -> itemStack.getItem() == Items.GLOW_BERRIES)) {
                        user.addExperience(1);
                        YouNeedBait.LOGGER.info("Glow Berries bonus exp awarded (1)");
                    }
                }

                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL,
                        1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
            } else {
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL,
                        0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

                if (!world.isClient) {
                    int lure = EnchantmentHelper.getLure(stack);
                    int luck = EnchantmentHelper.getLuckOfTheSea(stack);

                    FancyFishingBobberEntity bobberEntity = new FancyFishingBobberEntity(user, world, luck, lure);
                    world.spawnEntity(bobberEntity);
                }

                user.incrementStat(Stats.USED.getOrCreateStat(this));
                user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
            }
            return TypedActionResult.success(stack, world.isClient());
        }
    }

    public static int getTier(ItemStack stack) {
        if (!stack.hasNbt()) {
            return MIN_ROD_TIER;
        }

        var nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(ROD_TIER_KEY)) {
            return MIN_ROD_TIER;
        }

        int tier = nbt.getInt(ROD_TIER_KEY);

        // Clamp just in case something wrote a bad value
        if (tier < MIN_ROD_TIER) {
            return MIN_ROD_TIER;
        }
        if (tier > MAX_ROD_TIER) {
            return MAX_ROD_TIER;
        }

        return tier;
    }

    public static void setTier(ItemStack stack, int tier) {
        // Clamp to our allowed range
        if (tier < MIN_ROD_TIER) {
            tier = MIN_ROD_TIER;
        }
        if (tier > MAX_ROD_TIER) {
            tier = MAX_ROD_TIER;
        }

        var nbt = stack.getOrCreateNbt();
        nbt.putInt(ROD_TIER_KEY, tier);
    }

    public static int getSlotCountForTier(int tier) {
        // Make sure the tier is clamped to our valid range
        if (tier < MIN_ROD_TIER) {
            tier = MIN_ROD_TIER;
        }
        if (tier > MAX_ROD_TIER) {
            tier = MAX_ROD_TIER;
        }

        // Simple mapping: base 3 slots + 1 per tier
        // Tier 0 -> 3, Tier 1 -> 4, Tier 2 -> 5, Tier 3 -> 6, Tier 4 -> 7
        return 3 + tier;
    }

    public static int getSlotCountForStack(ItemStack stack) {
        int tier = getTier(stack);
        return getSlotCountForTier(tier);
    }

    private static DefaultedList<ItemStack> resizeInventoryList(DefaultedList<ItemStack> original, int targetSize) {
        DefaultedList<ItemStack> resized = DefaultedList.ofSize(targetSize, ItemStack.EMPTY);

        // Copy as many items as will fit
        int copyCount = Math.min(original.size(), targetSize);
        for (int i = 0; i < copyCount; i++) {
            resized.set(i, original.get(i));
        }

        return resized;
    }
}