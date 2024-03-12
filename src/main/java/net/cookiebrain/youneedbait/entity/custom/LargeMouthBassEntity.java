package net.cookiebrain.youneedbait.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class LargeMouthBassEntity extends FishEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public LargeMouthBassEntity(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates(){
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }
    public static DefaultAttributeContainer.Builder createLargemouthbassAttributes() {
        return MobEntity.createMobAttributes()
        .add(EntityAttributes.GENERIC_MAX_HEALTH,15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 2f);

    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()){
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        super.initGoals();
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_SALMON_FLOP;
    }

    @Override
    public ItemStack getBucketItem() {
        return Items.COD_BUCKET.getDefaultStack();
    }
}
