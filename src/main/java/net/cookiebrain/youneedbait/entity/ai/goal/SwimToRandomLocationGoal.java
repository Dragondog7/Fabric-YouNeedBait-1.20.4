package net.cookiebrain.youneedbait.entity.ai.goal;

import net.cookiebrain.youneedbait.entity.ai.goal.util.WaterNavigationUtil;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class SwimToRandomLocationGoal extends Goal {
    private final WaterCreatureEntity fish;
    private final double speed;
    private final int searchRange;
    private final int cooldownTime;
    private int cooldown;
    private Vec3d targetPosition;

    public SwimToRandomLocationGoal(WaterCreatureEntity fish, double speed, int searchRange, int cooldownTime) {
        this.fish = fish;
        this.speed = speed;
        this.searchRange = searchRange;
        this.cooldownTime = cooldownTime;
        this.cooldown = 0;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (cooldown > 0) {
            cooldown--;
            return false;
        }

        this.targetPosition = WaterNavigationUtil.findRandomWaterPosition(this.fish, this.searchRange);
        return this.targetPosition != null;
    }

    @Override
    public boolean shouldContinue() {
        return this.targetPosition != null &&
                !this.fish.getNavigation().isIdle() &&
                this.fish.squaredDistanceTo(this.targetPosition) > 1.0;
    }

    @Override
    public void start() {
        if (this.targetPosition != null) {
            this.fish.getNavigation().startMovingTo(
                    this.targetPosition.x,
                    this.targetPosition.y,
                    this.targetPosition.z,
                    this.speed
            );
        }
    }

    @Override
    public void stop() {
        this.targetPosition = null;
        this.fish.getNavigation().stop();
        this.cooldown = this.cooldownTime;
    }
}