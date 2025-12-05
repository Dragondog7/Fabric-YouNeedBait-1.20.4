package net.cookiebrain.youneedbait.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import static net.cookiebrain.youneedbait.entity.ModEntities.LARGEFISH;

public class LargeFishEntity extends AquaticEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState swimAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public LargeFishEntity(EntityType<? extends AquaticEntity> entityType, World world) {
        super(entityType, world);
        this.detectionRange = 100.0f;  // Override as needed
        this.circlePassesMin = 2;
        this.circlePassesMax = 6;
        this.circleRadius = 20.0f;
        this.lungeDistance = 5.0f;
        this.baitSnagChance = 0.20f;
        this.tugCount = 4;
        this.baseTugSuccess = 0.30f;
    }

    @Override
    public EntityType<? extends AquaticEntity> getEntityType() {
        return LARGEFISH;
    }
    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()){
            setupAnimationStates();
        }
    }
    @Override
    public int getSpawnWeight() {
        return 1;  // Rare spawn
    }

    @Override
    public int getSpawnGroupSize() {
        return 1;
    }

    @Override
    public boolean matchesBiome(World world, BlockPos pos) {
        return true;
        //return world.getBiome(pos).value().getCategory() == net.minecraft.world.biome.Biome.Category.OCEAN;
    }

    @Override
    public boolean isActiveAtNight() {
        return true;  // Night-only
    }

//    @Override
//    public DefaultAttributeContainer.Builder createAquaticAttributes() {
//        return AquaticEntity.createAquaticAttributes()
//                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
//                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8);
//    }
    public static DefaultAttributeContainer.Builder createLargeFishAttributes() {
        return MobEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH,15)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 2.5f);

}
    @Override
    protected void catchFish() {
        this.setRemoved(Entity.RemovalReason.DISCARDED);
        if (targetBobber != null) {
            targetBobber.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    protected void failFight() {
        this.setFighting(false);
        if (hookedPlayer != null) {
            ItemStack rod = hookedPlayer.getMainHandStack();
            rod.damage(1000, hookedPlayer, (entity) -> {
                if (rod.getDamage() >= rod.getMaxDamage()) {
                    Hand hand = hookedPlayer.getActiveHand();
                    EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                    entity.sendEquipmentBreakStatus(slot);
                    rod.decrement(1);
                }
            });
            hookedPlayer.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
        }
        if (targetBobber != null) {
            targetBobber.remove(Entity.RemovalReason.KILLED);
        }
        this.discard();
    }

    private void setupAnimationStates(){
        if (this.horizontalSpeed > 0.01f || this.isSwimming()) {
            swimAnimationState.startIfNotRunning(this.age);
            idleAnimationState.stop();
        } else {
            idleAnimationState.startIfNotRunning(this.age);
            swimAnimationState.stop();
        }
    }
}