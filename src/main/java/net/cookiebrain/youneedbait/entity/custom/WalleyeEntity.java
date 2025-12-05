package net.cookiebrain.youneedbait.entity.custom;

import net.cookiebrain.youneedbait.entity.ai.goal.SimpleSwimGoal;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WalleyeEntity extends WaterCreatureEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState swimAnimationState = new AnimationState();
    private int animationTick = 0;

    public WalleyeEntity(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AquaticMoveControl(this, 75, 8, 0.03F, 0.12F, true);
        this.navigation = new SwimNavigation(this, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 0.0F);
    }

    public static DefaultAttributeContainer.Builder createwalleyeAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));
        this.goalSelector.add(1, new SimpleSwimGoal(this, 1.0));
    }

    @Override
    public void tick() {
        super.tick();

        // Mild vertical damping to prevent helical swimming
        Vec3d vel = this.getVelocity();
        this.setVelocity(vel.x, vel.y * 0.92, vel.z);

        if (this.getWorld().isClient()) {
            animationTick++;
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        int t = Math.max(1, animationTick);

        // Swim if navigation has a path OR if moving horizontally
        boolean isSwimming = !this.getNavigation().isIdle()
                || this.getVelocity().horizontalLengthSquared() > 0.0001;

        if (isSwimming) {
            swimAnimationState.startIfNotRunning(t);
            idleAnimationState.stop();
        } else {
            idleAnimationState.startIfNotRunning(t);
            swimAnimationState.stop();
        }
    }


}