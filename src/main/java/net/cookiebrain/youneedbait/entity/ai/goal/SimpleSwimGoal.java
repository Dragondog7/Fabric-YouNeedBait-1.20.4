package net.cookiebrain.youneedbait.entity.ai.goal;

import net.cookiebrain.youneedbait.entity.custom.WalleyeEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

// Simple swim goal with forward-biased targeting
public class SimpleSwimGoal extends Goal {
    private final WaterCreatureEntity fish;
    private final double speed;
    private Vec3d target;
    private int cooldown = 0;

    public SimpleSwimGoal(WaterCreatureEntity fish, double speed) {
        this.fish = fish;
        this.speed = speed;
        this.setControls(java.util.EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        // Start if we don't have a path and cooldown expired
        if (cooldown > 0) {
            cooldown--;
            return false;
        }
        return fish.isTouchingWater() && fish.getNavigation().isIdle();
    }

    @Override
    public boolean shouldContinue() {
        return fish.isTouchingWater() && !fish.getNavigation().isIdle();
    }

    @Override
    public void start() {
        target = pickForwardBiasedTarget();
        if (target != null) {
            // Tiny kick forward to commit to new heading
            Vec3d dir = target.subtract(fish.getPos()).normalize();
            fish.setVelocity(fish.getVelocity().add(dir.multiply(0.02)));
            fish.getNavigation().startMovingTo(target.x, target.y, target.z, speed);
        }
        cooldown = 60; // 3 seconds before picking new target
    }

    @Override
    public void stop() {
        target = null;
    }

    @Override
    public void tick() {
        // Re-path every second to straighten course
        if (fish.age % 20 == 0 && target != null) {
            fish.getNavigation().startMovingTo(target.x, target.y, target.z, speed);
        }
    }

    private Vec3d pickForwardBiasedTarget() {
        var rnd = fish.getRandom();
        var pos = fish.getPos();

        // Forward vector from current yaw
        double yawRad = Math.toRadians(fish.getYaw());
        Vec3d forward = new Vec3d(-MathHelper.sin((float) yawRad), 0, MathHelper.cos((float) yawRad)).normalize();

        // Sample a cone ahead (50-90 degrees), not full circle
        double cone = Math.toRadians(50 + rnd.nextDouble() * 40);
        double angleOff = (rnd.nextDouble() - 0.5) * cone;
        double sin = Math.sin(angleOff);
        double cos = Math.cos(angleOff);
        Vec3d dir = new Vec3d(
                forward.x * cos - forward.z * sin,
                0,
                forward.x * sin + forward.z * cos
        ).normalize();

        double dist = 6.0 + rnd.nextDouble() * 8.0;  // 6-14 blocks
        double dy = (rnd.nextDouble() - 0.5) * 2.0;  // ±1 block vertical

        return pos.add(dir.multiply(dist)).add(0, dy, 0);
    }
}