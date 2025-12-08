package net.cookiebrain.youneedbait.entity.custom;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.cookiebrain.youneedbait.YouNeedBait; // <-- added
import net.cookiebrain.youneedbait.entity.ModEntities;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.loot.BonusLoot;
import net.cookiebrain.youneedbait.loot.ModBonusLoot;
import net.cookiebrain.youneedbait.mixin.FishingBobberAccessor;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.Collections;

public class FancyFishingBobberEntity extends FishingBobberEntity {
    private int luckOfTheSeaLevel = 0;
    private int lureLevel = 0;
    private Item modifierItem;
    private ItemStack lootItemStack;
    private final ModBonusLoot bonusLoot = new ModBonusLoot();

    // Default colors (e.g., if not set explicitly)
    private int bobberRed = 255;
    private int bobberGreen = 255;
    private int bobberBlue = 255;
    private int bobberAlpha = 255;

    private int lineRed = 0;
    private int lineGreen = 0;
    private int lineBlue = 0;
    private int lineAlpha = 255;

    public FancyFishingBobberEntity(EntityType<? extends FishingBobberEntity> entityType, World world) {
        super(entityType, world);
        //YouNeedBait.LOGGER.info("[FancyFishingBobberEntity] ctor(EntityType, World) created. world={}, isClient={}",
                //world.getRegistryKey().getValue(), world.isClient());
    }

    public FancyFishingBobberEntity(PlayerEntity thrower, World world, int luckOfTheSeaLevel, int lureLevel) {
        //this(ModEntities.FANCY_FISHING_BOBBER, world, luckOfTheSeaLevel, lureLevel);
        super(ModEntities.FANCY_FISHING_BOBBER, world);
        YouNeedBait.LOGGER.info(
                "[FancyFishingBobberEntity] ctor(Player, World, luck={}, lure={}) created. world={}, isClient={}, thrower={}",
                luckOfTheSeaLevel, lureLevel, world.getRegistryKey().getValue(), world.isClient(),
                thrower != null ? thrower.getName().getString() : "null"
        );

        this.ignoreCameraFrustum = true;
        this.luckOfTheSeaLevel = Math.max(0, luckOfTheSeaLevel);
        this.lureLevel = Math.max(0, lureLevel);

        // Set up owner manually, because we didn't call FishingBobberEntity(Player, ..)
        this.setOwner(thrower);
        this.updatePosition(thrower.getX(), thrower.getEyeY() - 0.1, thrower.getZ());
        this.setVelocity(thrower, thrower.getPitch(), thrower.getYaw(), 0.0F, 1.5F, 1.0F);

        YouNeedBait.LOGGER.info(
                "[FancyFishingBobberEntity] post-setup: pos=({}, {}, {}), vel={}",
                this.getX(), this.getY(), this.getZ(), this.getVelocity()
        );

        //modifierItem = ((FancyFishingRodItem) thrower.getMainHandStack().getItem()).getModifierItemType();
    }

    // --- NEW: Setter methods for colors ---
    public void setBobberColor(int r, int g, int b, int a) {
        this.bobberRed = r;
        this.bobberGreen = g;
        this.bobberBlue = b;
        this.bobberAlpha = a;
        //YouNeedBait.LOGGER.info("[FancyFishingBobberEntity] setBobberColor to rgba({}, {}, {}, {})", r, g, b, a);
    }

    public void setLineColor(int r, int g, int b, int a) {
        this.lineRed = r;
        this.lineGreen = g;
        this.lineBlue = b;
        this.lineAlpha = a;
        //YouNeedBait.LOGGER.info("[FancyFishingBobberEntity] setLineColor to rgba({}, {}, {}, {})", r, g, b, a);
    }

    // --- NEW: Getter methods for colors ---
    public int getBobberRed() { return bobberRed; }
    public int getBobberGreen() { return bobberGreen; }
    public int getBobberBlue() { return bobberBlue; }
    public int getBobberAlpha() { return bobberAlpha; }

    public int getLineRed() { return lineRed; }
    public int getLineGreen() { return lineGreen; }
    public int getLineBlue() { return lineBlue; }
    public int getLineAlpha() { return lineAlpha; }

    private boolean removeIfInvalid(PlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack2 = player.getOffHandStack();
        boolean bl = itemStack.isOf(ModItems.FANCYFISHINGROD_ITEM);
        boolean bl2 = itemStack2.isOf(ModItems.FANCYFISHINGROD_ITEM);

        boolean invalid = player.isRemoved()
                || !player.isAlive()
                || (!bl && !bl2)
                || this.squaredDistanceTo(player) > 1024.0;

        if (invalid) {
//            YouNeedBait.LOGGER.info(
//                    "[FancyFishingBobberEntity] removeIfInvalid -> true. playerRemoved={}, alive={}, mainIsFancyRod={}, offIsFancyRod={}, distSq={}",
//                    player.isRemoved(), player.isAlive(), bl, bl2, this.squaredDistanceTo(player)
//            );
            this.discard();
            return true;
        }

        return false;
    }

    @Override
    public int use(ItemStack usedItem) {
        PlayerEntity playerEntity = this.getPlayerOwner();
//        YouNeedBait.LOGGER.info(
//                "[FancyFishingBobberEntity] use() called. worldIsClient={}, owner={}, usedItem={}",
//                this.getWorld().isClient(),
//                playerEntity != null ? playerEntity.getName().getString() : "null",
//                usedItem
//        );

        if (this.getWorld().isClient || playerEntity == null || this.removeIfInvalid(playerEntity)) {
//            YouNeedBait.LOGGER.info(
//                    "[FancyFishingBobberEntity] use() early return with 0. isClient={}, playerNull={}, invalid={}",
//                    this.getWorld().isClient(),
//                    playerEntity == null,
//                    playerEntity != null && this.removeIfInvalid(playerEntity)
//            );
            return 0;
        }

        int i = 0;
        int hookCountdown = ((FishingBobberAccessor) this).getHookCountdown();
//        YouNeedBait.LOGGER.info(
//                "[FancyFishingBobberEntity] use() server-side. hookCountdown={}, hookedEntity={}",
//                hookCountdown,
//                this.getHookedEntity()
//        );

        if (this.getHookedEntity() != null) {
//            YouNeedBait.LOGGER.info(
//                    "[FancyFishingBobberEntity] use() pulling hooked entity: {}",
//                    this.getHookedEntity().getType().toString()
//            );
            this.pullHookedEntity(this.getHookedEntity());
            Criteria.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity) playerEntity, usedItem, this, Collections.emptyList());
            this.getWorld().sendEntityStatus(this, EntityStatuses.PULL_HOOKED_ENTITY);
            i = this.getHookedEntity() instanceof ItemEntity ? 3 : 5;
        } else if (hookCountdown > 0) {
            YouNeedBait.LOGGER.info("[FancyFishingBobberEntity] use() generating fishing loot.");

            LootContextParameterSet lootContextParameterSet =
                    new LootContextParameterSet.Builder((ServerWorld) this.getWorld())
                            .add(LootContextParameters.ORIGIN, this.getPos())
                            .add(LootContextParameters.TOOL, usedItem)
                            .add(LootContextParameters.THIS_ENTITY, this)
                            .luck((float) this.luckOfTheSeaLevel + playerEntity.getLuck())
                            .build(LootContextTypes.FISHING);

            LootTable lootTable = this.getWorld().getServer().getLootManager().getLootTable(LootTables.FISHING_GAMEPLAY);
            ObjectArrayList<ItemStack> list = lootTable.generateLoot(lootContextParameterSet);

            YouNeedBait.LOGGER.info(
                    "[FancyFishingBobberEntity] base loot generated: size={}, first={}",
                    list.size(),
                    list.isEmpty() ? "EMPTY" : list.get(0)
            );

            if (!list.isEmpty()) {
                // Capture the original first stack – this is “what was caught”
                ItemStack originalFirst = list.get(0);

                // 1) Junk → Garden conversion (your existing behavior)
                if (originalFirst.isIn(ModTags.Items.JUNK_ITEMS_MC)) {
                    YouNeedBait.LOGGER.info(
                            "[FancyFishingBobberEntity] first loot is in JUNK_ITEMS_MC: {}",
                            originalFirst
                    );

                    if (modifierItem != null && modifierItem.asItem().equals(Items.IRON_HOE)) {
                        BonusLoot bl = bonusLoot.getLootTableByName("junktogarden");
                        if (bl != null) {
                            lootItemStack = bl.selectRandomWeightedItem();
                            YouNeedBait.LOGGER.info(
                                    "[FancyFishingBobberEntity] junk->garden conversion candidate: {}",
                                    lootItemStack
                            );
                        }
                    }
                    // Junk conversion chance CONFIG
                    if (lootItemStack != null && Math.random() < 0.15) { // 15% chance
                        list.set(0, lootItemStack);
                        YouNeedBait.LOGGER.info(
                                "[FancyFishingBobberEntity] junk->garden conversion APPLIED. newFirst={}",
                                lootItemStack
                        );
                    }
                }
                // 2) Vanilla fish → roll from BonusLoot "fishing_fish"
                else if (isVanillaFish(originalFirst.getItem())) {
                    YouNeedBait.LOGGER.info(
                            "[FancyFishingBobberEntity] first loot is vanilla fish: {}",
                            originalFirst
                    );
                    BonusLoot fishLoot = bonusLoot.getLootTableByName("fishing_fish");
                    if (fishLoot != null) {
                        ItemStack replacement = fishLoot.selectRandomWeightedItem();
                        if (replacement != null) {
                            list.set(0, replacement);
                            YouNeedBait.LOGGER.info(
                                    "[FancyFishingBobberEntity] vanilla fish replaced with custom fish loot: {}",
                                    replacement
                            );
                        }
                    }
                } else {
                    YouNeedBait.LOGGER.info(
                            "[FancyFishingBobberEntity] first loot is neither junk nor vanilla fish: {}",
                            originalFirst
                    );
                }
            }

            Criteria.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity) playerEntity, usedItem, this, list);

            for (ItemStack itemStack : list) {
                YouNeedBait.LOGGER.info(
                        "[FancyFishingBobberEntity] spawning loot item entity: {}",
                        itemStack
                );
                ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), itemStack);
                double d = playerEntity.getX() - this.getX();
                double e = playerEntity.getY() - this.getY();
                double f = playerEntity.getZ() - this.getZ();
                double g = 0.1;
                itemEntity.setVelocity(
                        d * 0.1,
                        e * 0.1 + Math.sqrt(Math.sqrt(d * d + e * e + f * f)) * 0.08,
                        f * 0.1
                );
                this.getWorld().spawnEntity(itemEntity);

                playerEntity.getWorld().spawnEntity(
                        new ExperienceOrbEntity(
                                playerEntity.getWorld(),
                                playerEntity.getX(),
                                playerEntity.getY() + 0.5,
                                playerEntity.getZ() + 0.5,
                                this.random.nextInt(6) + 1
                        )
                );

                if (!itemStack.isIn(ItemTags.FISHES)) continue;
                playerEntity.increaseStat(Stats.FISH_CAUGHT, 1);
            }
            i = 1;
        }

        if (this.isOnGround()) {
            //YouNeedBait.LOGGER.info("[FancyFishingBobberEntity] use(): bobber isOnGround -> setting result to 2");
            i = 2;
        }

//        YouNeedBait.LOGGER.info(
//                "[FancyFishingBobberEntity] use() returning {} and discarding bobber. pos=({}, {}, {}), worldIsClient={}",
//                i, this.getX(), this.getY(), this.getZ(), this.getWorld().isClient()
//        );
        this.discard();
        return i;
    }

    //AI suggested these
    @Override
    public void writeCustomDataToNbt(net.minecraft.nbt.NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
//        YouNeedBait.LOGGER.info(
//                "[FancyFishingBobberEntity] writeCustomDataToNbt called. owner={}",
//                this.getPlayerOwner() != null ? this.getPlayerOwner().getUuid() : "null"
//        );

        // Ensure the owner's UUID is written to NBT for client sync
        if (this.getPlayerOwner() != null) {
            nbt.putUuid("Owner", this.getPlayerOwner().getUuid());
        }
        // If you have custom data like modifierItem, write it here too
        // if (this.modifierItem != null) {
        //     nbt.putString("ModifierItem", this.modifierItem.toString());
        // }
    }

    @Override
    public void readCustomDataFromNbt(net.minecraft.nbt.NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
//        YouNeedBait.LOGGER.info(
//                "[FancyFishingBobberEntity] readCustomDataFromNbt called. hasOwner={}, worldIsClient={}",
//                nbt.containsUuid("Owner"),
//                this.getWorld() != null && this.getWorld().isClient()
//        );
        // The super call should handle reading the "Owner" UUID and setting playerOwner
        // If you had custom data, read it here
        // if (nbt.contains("ModifierItem")) {
        //     this.modifierItem = new Identifier(nbt.getString("ModifierItem"));
        // }
    }

    private boolean isVanillaFish(Item item) {
        return item == Items.COD
                || item == Items.SALMON
                || item == Items.TROPICAL_FISH
                || item == Items.PUFFERFISH;
    }
}