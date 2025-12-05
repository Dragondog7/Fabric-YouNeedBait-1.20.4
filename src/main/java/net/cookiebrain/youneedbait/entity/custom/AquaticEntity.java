package net.cookiebrain.youneedbait.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;

import java.util.EnumSet;

public abstract class AquaticEntity extends WaterCreatureEntity {
    // Configurable constants (override in subclasses)
    protected float detectionRange = 100.0f;    // Base detection range
    protected int circlePassesMin = 2;
    protected int circlePassesMax = 6;
    protected float circleRadius = 20.0f;       // Circling start distance
    protected float lungeDistance = 5.0f;       // Fight trigger distance
    protected float baitSnagChance = 0.20f;     // Per pass
    protected int tugCount = 4;                 // Tugs to catch
    protected float baseTugSuccess = 0.30f;     // Base success per tug

    // Data trackers
    private static final TrackedData<Integer> CIRCLE_PASSES = DataTracker.registerData(
            AquaticEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> IS_FIGHTING = DataTracker.registerData(
            AquaticEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> CURRENT_TUG = DataTracker.registerData(
            AquaticEntity.class, TrackedDataHandlerRegistry.INTEGER);

    // Instance fields
    protected FishingBobberEntity targetBobber;
    protected PlayerEntity hookedPlayer;

    public AquaticEntity(EntityType<? extends AquaticEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        //this.setNoGravity(true);
        this.moveControl = new AquaticMoveControl(
                this,
                8,      // pitchChange: up/down turn max deg per tick
                12,     // yawChange: left/right turn max deg per tick
                0.6f,   // speedInWater: feels natural; raise for zippy swimmers
                0.02f,  // speedInAir: barely moves when out of water
                true    // buoyant: gentle bob & easier depth keeping
        );
        this.navigation = new SwimNavigation(this,world);
    }

    // Abstract methods for subclass customization
    public abstract EntityType<? extends AquaticEntity> getEntityType();
    public abstract int getSpawnWeight();       // Weight for rarity (e.g., 1 for rare)
    public abstract int getSpawnGroupSize();    // Max group size (e.g., 1)
    public abstract boolean matchesBiome(World world, BlockPos pos);  // Custom biome check
    public abstract boolean isActiveAtNight();  // Night-only spawn?

    // Default attributes (override if needed)
    public static DefaultAttributeContainer.Builder createAquaticAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)  // Base, override per entity
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(CIRCLE_PASSES, 0);
        this.dataTracker.startTracking(IS_FIGHTING, false);
        this.dataTracker.startTracking(CURRENT_TUG, 0);
    }

    @Override
    protected void initGoals() {
//        this.goalSelector.add(1, new DetectBobberGoal(this));
//        this.goalSelector.add(2, new ArcPathfindGoal(this));
//        this.goalSelector.add(3, new CircleBobberGoal(this));
//        this.goalSelector.add(4, new LungeGoal(this));
//        this.goalSelector.add(5, new FightGoal(this));
        this.goalSelector.add(2, new SwimTowardBobberGoal(this, 0.40, /*acquireRange*/24.0, /*stopDist*/1.8));
        this.goalSelector.add(3, new SwimNearBottomGoal(this, 0.40)); // speed matches your navigator speed
        //this.goalSelector.add(2, new LungeGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient) {
            if (targetBobber != null && targetBobber.isRemoved()) {
                targetBobber = null;
                this.setCirclePasses(0);
                this.setFighting(false);
                this.setCurrentTug(0);
            }

            float adjustedRange = (float) (detectionRange + (this.getY() / -10.0f) * 10.0f);  // Depth boost
            if (targetBobber == null) {
                detectBobber(adjustedRange);
            }
        }
    }

    private void detectBobber(float range) {
        var bobbers = this.getWorld().getEntitiesByClass(FishingBobberEntity.class,
                this.getBoundingBox().expand(range), bobber -> bobber.getPlayerOwner() != null);
        if (!bobbers.isEmpty()) {
            targetBobber = bobbers.get(0);
            hookedPlayer = targetBobber.getPlayerOwner();
            this.getNavigation().stop();
        }
    }

    private void setCirclePasses(int passes) { this.dataTracker.set(CIRCLE_PASSES, passes); }
    private int getCirclePasses() { return this.dataTracker.get(CIRCLE_PASSES); }
    void setFighting(boolean fighting) { this.dataTracker.set(IS_FIGHTING, fighting); }
    private boolean isFighting() { return this.dataTracker.get(IS_FIGHTING); }
    private void setCurrentTug(int tug) { this.dataTracker.set(CURRENT_TUG, tug); }
    private int getCurrentTug() { return this.dataTracker.get(CURRENT_TUG); }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("CirclePasses", getCirclePasses());
        nbt.putBoolean("IsFighting", isFighting());
        nbt.putInt("CurrentTug", getCurrentTug());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        setCirclePasses(nbt.getInt("CirclePasses"));
        setFighting(nbt.getBoolean("IsFighting"));
        setCurrentTug(nbt.getInt("CurrentTug"));
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        return true;
        //BlockPos pos = new BlockPos(this.getX(), this.getY(), this.getZ());
        //return matchesBiome((World) world, pos) && (!isActiveAtNight() || world.isAir) && super.canSpawn(world, spawnReason);
    }

    // Goal classes (generic, adaptable to subclasses)
    static class DetectBobberGoal extends Goal {
        private final AquaticEntity entity;
        private int detectCooldown = 20;

        DetectBobberGoal(AquaticEntity entity) {
            this.entity = entity;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return entity.targetBobber == null && --detectCooldown <= 0;
        }

        @Override
        public void start() {
            entity.detectBobber(entity.detectionRange);
            detectCooldown = 20;
        }
    }

    static class ArcPathfindGoal extends Goal {
        private final AquaticEntity entity;
        private int pathCooldown = 10;

        ArcPathfindGoal(AquaticEntity entity) {
            this.entity = entity;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return entity.targetBobber != null && entity.distanceTo(entity.targetBobber) > entity.circleRadius
                    && --pathCooldown <= 0;
        }

        @Override
        public void tick() {
            float angleOffset = entity.random.nextFloat() * 60.0f - 30.0f;
            entity.getNavigation().startMovingTo(
                    entity.targetBobber.getX() + Math.sin(Math.toRadians(angleOffset)) * 10.0,
                    entity.targetBobber.getY(),
                    entity.targetBobber.getZ() + Math.cos(Math.toRadians(angleOffset)) * 10.0,
                    1.0f);
            pathCooldown = 10;
        }
    }

    static class CircleBobberGoal extends Goal {
        private final AquaticEntity entity;
        private int surfaceCooldown = 40;

        CircleBobberGoal(AquaticEntity entity) {
            this.entity = entity;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return entity.targetBobber != null && entity.distanceTo(entity.targetBobber) <= entity.circleRadius
                    && !entity.isFighting() && entity.getCirclePasses() == 0;
        }

        @Override
        public void start() {
            entity.setCirclePasses(entity.random.nextInt());
        }

        @Override
        public void tick() {
            if (entity.getCirclePasses() > 0) {
                float angle = (entity.getWorld().getTime() % 200) * 1.8f;
                double x = entity.targetBobber.getX() + entity.circleRadius * Math.cos(Math.toRadians(angle));
                double z = entity.targetBobber.getZ() + entity.circleRadius * Math.sin(Math.toRadians(angle));
                entity.setPosition(x, entity.targetBobber.getY(), z);

                if (--surfaceCooldown <= 0) {
                    entity.setPosition(entity.getX(), entity.getY() + 1.0, entity.getZ());
                    entity.getWorld().addParticle(ParticleTypes.SPLASH, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
                    entity.playSound(SoundEvents.ENTITY_DOLPHIN_SPLASH, 1.0f, 1.0f);
                    surfaceCooldown = 40;
                }

                if (entity.random.nextFloat() < entity.baitSnagChance) {
                    //entity.targetBobber.removeBait();  // Pseudo
                    entity.setCirclePasses(entity.getCirclePasses() - 1);
                }
                entity.setCirclePasses(entity.getCirclePasses() - 1);
            }
        }

        @Override
        public boolean shouldContinue() {
            return entity.getCirclePasses() > 0 && entity.targetBobber != null;
        }
    }
    public static class SwimNearBottomGoal extends Goal {
        private final AquaticEntity mob;
        private final double speed;
        private final Random rand;

        // Tuning knobs
        private static final int H_RADIUS = 8;             // horizontal wander radius (blocks)
        private static final int TRY_POS_ATTEMPTS = 12;    // attempts to find a valid spot
        private static final int RECALC_TICKS = 60;        // retarget every ~3s (20 tps)
        private static final double BOTTOM_CLEARANCE = 1.8; // blocks above the floor
        public static final int MAX_DEPTH_BELOW_SURFACE = 18; // clamp: don’t go deeper than this

        private Vec3d target;
        private int recalcTimer;
        private int stuckTimer;

        public SwimNearBottomGoal(AquaticEntity mob, double speed) {
            this.mob = mob;
            this.speed = speed;
            this.rand = mob.getRandom();
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            return mob.isTouchingWater();
        }

        @Override
        public boolean shouldContinue() {
            return mob.isTouchingWater();
        }

        @Override
        public void start() {
            recalcTimer = 0;
            stuckTimer = 0;
            pickTargetAndGo();
        }

        @Override
        public void stop() {
            target = null;
            mob.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (!mob.isTouchingWater()) return;

            recalcTimer++;
            stuckTimer++;

            // If arrived, path finished, or it's time to wander again — pick a new target
            if (target == null
                    || mob.squaredDistanceTo(target) < 1.0
                    || mob.getNavigation().isIdle()
                    || recalcTimer >= RECALC_TICKS) {
                pickTargetAndGo();
                recalcTimer = 0;
            }

            // Simple stuck detection: if navigator sits idle for a while, replan
            if (mob.getNavigation().isIdle() && stuckTimer > 40) {
                pickTargetAndGo();
                stuckTimer = 0;
            }

            if (target != null) {
                mob.getLookControl().lookAt(target.x, target.y, target.z);
            }
        }

        private void pickTargetAndGo() {
            Vec3d next = chooseNearBottomTarget();
            if (next != null) {
                target = next;
                mob.getNavigation().startMovingTo(target.x, target.y, target.z, this.speed);
            } else {
                // Fallback: hover roughly where you are (prevents thrashing)
                target = mob.getPos();
            }
        }

        private Vec3d chooseNearBottomTarget() {
            BlockPos start = mob.getBlockPos();

            Integer floorY = findSeafloorY(start);
            if (floorY == null) return null;

            Integer surfaceY = findWaterSurfaceY(start);
            if (surfaceY == null) surfaceY = start.getY() + 1; // conservative fallback

            // Preferred swimming height: a little above the bottom
            double preferredY = floorY + BOTTOM_CLEARANCE;

            // Clamp so we never go deeper than MAX_DEPTH below the surface
            double maxDepthY = (surfaceY - MAX_DEPTH_BELOW_SURFACE);
            double targetY = Math.max(preferredY, maxDepthY);

            // Try a few random lateral offsets at that Y
            for (int i = 0; i < TRY_POS_ATTEMPTS; i++) {
                int dx = MathHelper.nextInt(rand, -H_RADIUS, H_RADIUS);
                int dz = MathHelper.nextInt(rand, -H_RADIUS, H_RADIUS);
                BlockPos p = new BlockPos(start.getX() + dx, MathHelper.floor(targetY), start.getZ() + dz);
                if (isGoodWater(p)) {
                    return Vec3d.ofCenter(p);
                }
            }

            // Fallback: same XZ, corrected Y
            BlockPos fallback = new BlockPos(start.getX(), MathHelper.floor(targetY), start.getZ());
            return isGoodWater(fallback) ? Vec3d.ofCenter(fallback) : null;
        }

        private boolean isGoodWater(BlockPos pos) {
            // Ensure at least 2 blocks of water for the entity’s bounding box
            return mob.getWorld().getFluidState(pos).isIn(FluidTags.WATER)
                    && mob.getWorld().getFluidState(pos.up()).isIn(FluidTags.WATER);
        }

        /** Finds the Y of the first water block directly above the non-water block (the seafloor). */
        private Integer findSeafloorY(BlockPos from) {
            var world = mob.getWorld();
            BlockPos.Mutable m = from.mutableCopy();
            for (int y = from.getY(); y > world.getBottomY() + 1; y--) {
                m.setY(y);
                if (!world.getFluidState(m).isIn(FluidTags.WATER)) {
                    // The block above this y is the first water block; that’s the floor water cell
                    return y + 1;
                }
            }
            return null;
        }

        /** Finds the Y of the first non-water block above the water column (the “surface” cap). */
        private Integer findWaterSurfaceY(BlockPos from) {
            var world = mob.getWorld();
            BlockPos.Mutable m = from.mutableCopy();
            int top = world.getTopY() - 1;
            for (int y = from.getY(); y <= top; y++) {
                m.setY(y);
                if (!world.getFluidState(m).isIn(FluidTags.WATER)) {
                    return y; // first non-water Y; water surface is at y - 1
                }
            }
            return null;
        }
    }
    // SwimTowardBobberGoal: Moves toward the bobber, stares a bit and then resumes swimming
    public class SwimTowardBobberGoal extends Goal {
        private final AquaticEntity mob;
        private final double speed;
        private final double acquireRangeSq;
        private final double stopDistanceSq;

        private FishingBobberEntity target;
        private int repathTicks;
        private int stareTicks;
        private int losGraceTicks;

        private static final int STARE_TICKS = 200;   // 10s
        private static final int REPATH_INTERVAL = 10;
        private static final int LOS_GRACE_MAX = 40;  // 2s LOS grace

        public SwimTowardBobberGoal(AquaticEntity mob, double speed, double acquireRange, double stopDistance) {
            this.mob = mob;
            this.speed = speed;
            this.acquireRangeSq = acquireRange * acquireRange;
            this.stopDistanceSq = stopDistance * stopDistance;
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            FishingBobberEntity bob = mob.targetBobber;
            if (bob == null || !bob.isAlive()) return false;
            if (!mob.isTouchingWater()) return false;                  // only care about the fish, not the bobber
            if (mob.squaredDistanceTo(bob) > acquireRangeSq) return false;

            target = bob;
            losGraceTicks = LOS_GRACE_MAX; // start sticky
            return true;                   // DO NOT require bob.isTouchingWater() or LOS here
        }

        @Override
        public boolean shouldContinue() {
            if (target == null || !target.isAlive()) return false;
            if (!mob.isTouchingWater()) return false;

            if (stareTicks > 0) return true;

            // Range with a little hysteresis
            if (mob.squaredDistanceTo(target) > acquireRangeSq * 1.5) return false;

            // LOS can flicker—use grace
            boolean canSee = mob.getVisibilityCache().canSee(target);
            if (canSee) {
                losGraceTicks = LOS_GRACE_MAX;
            } else if (--losGraceTicks <= 0) {
                return false;
            }
            return true;
        }

        @Override
        public void start() {
            repathTicks = 0;
            stareTicks = 0;
            moveToTarget();
        }

        @Override
        public void stop() {
            mob.getNavigation().stop();
            target = null;               // no cooldown; allow quick reacquire
            stareTicks = 0;
        }

        @Override
        public void tick() {
            if (target == null) return;

            if (stareTicks > 0) {
                mob.getNavigation().stop();
                mob.setVelocity(mob.getVelocity().multiply(0.6));
                mob.getLookControl().lookAt(target, 30.0F, 30.0F);
                --stareTicks;
                return;
            }

            // Chase
            if (++repathTicks >= REPATH_INTERVAL || mob.getNavigation().isIdle()) {
                moveToTarget();
                repathTicks = 0;
            }
            mob.getLookControl().lookAt(target, 30.0F, 30.0F);

            // Close enough? Begin staring
            if (mob.squaredDistanceTo(target) <= stopDistanceSq) {
                stareTicks = STARE_TICKS;
                mob.getNavigation().stop();
            }
        }

        private void moveToTarget() {
            if (target == null) return;

            double tx = target.getX();
            double tz = target.getZ();

            // Keep current depth (don’t pop to surface). Optional: clamp under surface by ~2 blocks.
            double ty = mob.getY();
            int surfaceY = findWaterSurfaceY(BlockPos.ofFloored(tx, ty, tz));
            ty = Math.min(ty, surfaceY - 2.0);

            BlockPos tp = BlockPos.ofFloored(tx, ty, tz);
            if (!mob.getWorld().getFluidState(tp).isIn(net.minecraft.registry.tag.FluidTags.WATER)) {
                // Fallback: a bit below the bobber if our depth isn't water (rare)
                ty = Math.min(target.getY() - 1.0, surfaceY - 1.0);
            }

            mob.getNavigation().startMovingTo(tx, ty, tz, speed);
        }

        // Simple upward scan to find where water ends
        private int findWaterSurfaceY(BlockPos from) {
            var world = mob.getWorld();
            BlockPos.Mutable m = from.mutableCopy();
            int top = world.getTopY() - 1;
            for (int y = from.getY(); y <= top; y++) {
                m.setY(y);
                if (!world.getFluidState(m).isIn(net.minecraft.registry.tag.FluidTags.WATER)) return y;
            }
            return from.getY() + 2; // conservative
        }
    }

    // LungeGoal: Controlled lunge toward bobber in water
    static class LungeGoal extends Goal {
        private final AquaticEntity entity;
        private int lungeTicks = 10; // Duration of lunge

        LungeGoal(AquaticEntity entity) {
            this.entity = entity;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return entity.targetBobber != null && entity.distanceTo(entity.targetBobber) <= entity.lungeDistance
                    && entity.isTouchingWater() && !entity.isFighting();
        }

        @Override
        public void start() {
            entity.setFighting(true);
            if (entity.hookedPlayer != null) {
                entity.hookedPlayer.playSound(SoundEvents.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0.8f);
            }
            lungeTicks = 10; // Set lunge duration
        }

        @Override
        public void tick() {
            if (entity.targetBobber != null && entity.isTouchingWater() && lungeTicks > 0) {
                Vec3d target = entity.targetBobber.getPos();
                Vec3d direction = new Vec3d(target.x - entity.getX(), target.y - entity.getY(), target.z - entity.getZ()).normalize();
                // Limit vertical velocity to prevent jumping out
                double yVel = Math.min(0.2, direction.y) * 0.5; // Cap upward movement
                entity.setVelocity(direction.x * 0.5, yVel, direction.z * 0.5);
                lungeTicks--;
            }
        }

        @Override
        public boolean shouldContinue() {
            return entity.isFighting() && lungeTicks > 0 && entity.isTouchingWater();
        }
    }


    static class FightGoal extends Goal {
        private final AquaticEntity entity;
        private int tugCooldown = 20;

        FightGoal(AquaticEntity entity) {
            this.entity = entity;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return entity.isFighting() && entity.targetBobber != null;
        }

        @Override
        public void tick() {
            if (--tugCooldown <= 0) {
                if (entity.getCurrentTug() < entity.tugCount) {
                    float successChance = entity.baseTugSuccess;
                            //+ (entity.hookedPlayer != null ? entity.hookedPlayer.getRodTier() * 0.10f : 0.0f);
                    if (entity.random.nextFloat() < successChance) {
                        entity.setCurrentTug(entity.getCurrentTug() + 1);
                        if (entity.hookedPlayer != null) {
                            entity.hookedPlayer.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.5f);
                        }
                        if (entity.getCurrentTug() == entity.tugCount) {
                            entity.catchFish();
                        }
                    } else {
                        entity.failFight();
                    }
                }
                tugCooldown = 20;
            }
        }

        @Override
        public boolean shouldContinue() {
            return entity.isFighting() && entity.targetBobber != null && entity.getCurrentTug() < entity.tugCount;
        }
    }

    // Abstract fight outcomes (override for specific loot/behavior)
    protected abstract void catchFish();
    protected abstract void failFight();

    // Pseudo methods (implement per entity)
    private void removeBait() { /* Implement bait removal */ }
    private int getRodTier() { return 0; /* Implement tier logic */ }
}