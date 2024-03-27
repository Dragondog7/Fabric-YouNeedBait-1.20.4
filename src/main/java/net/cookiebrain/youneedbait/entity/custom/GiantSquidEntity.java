//package net.cookiebrain.youneedbait.entity.custom;
//
//import net.minecraft.entity.AnimationState;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.ai.goal.ActiveTargetGoal;
//import net.minecraft.entity.ai.goal.AttackGoal;
//import net.minecraft.entity.ai.goal.Goal;
//import net.minecraft.entity.ai.goal.ZombieAttackGoal;
//import net.minecraft.entity.attribute.DefaultAttributeContainer;
//import net.minecraft.entity.attribute.EntityAttribute;
//import net.minecraft.entity.attribute.EntityAttributes;
//import net.minecraft.entity.boss.BossBar;
//import net.minecraft.entity.boss.ServerBossBar;
//import net.minecraft.entity.mob.MobEntity;
//import net.minecraft.entity.passive.SquidEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.text.Text;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.World;
//
//import static java.awt.AWTEventMulticaster.add;
//
//public class GiantSquidEntity extends SquidEntity {
//    public final AnimationState idleAnimationState = new AnimationState();
//    private int idleAnimationTimeout = 0;
//    public GiantSquidEntity(EntityType<? extends SquidEntity> entityType, World world) {
//        super(entityType, world);
//        bossBar.shouldThickenFog();
//    }
//
//    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("Bob The Squid"),
//            BossBar.Color.RED, BossBar.Style.NOTCHED_10);
//
//
//    private void setupAnimationStates(){
//        if (this.idleAnimationTimeout <= 0) {
//            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
//            this.idleAnimationState.start(this.age);
//        } else {
//            --this.idleAnimationTimeout;
//        }
//
//    }
//    public static DefaultAttributeContainer.Builder creategiantsquidAttributes() {
//        return MobEntity.createMobAttributes()
//        .add(EntityAttributes.GENERIC_MAX_HEALTH,100)
//                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1f)
//                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5f);
//
//    }
//
//
//    private static void add(EntityAttribute genericAttackDamage, float v) {
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//        if(this.getWorld().isClient()){
//            setupAnimationStates();
//        }
//    }
//
//    @Override
//    protected void initGoals() {
//        this.goalSelector.add(0, new SwimGoal(this));
//        this.goalSelector.add(0, new AttackGoal(this));
//        this.targetSelector.add(0, new ActiveTargetGoal<PlayerEntity>((MobEntity) this, PlayerEntity.class, true));
//    }
//    class SwimGoal
//            extends Goal {
//        private final SquidEntity squid;
//
//        public SwimGoal(SquidEntity squid) {
//            this.squid = squid;
//        }
//
//        @Override
//        public boolean canStart() {
//            return true;
//        }
//
//        @Override
//        public void tick() {
//            int i = this.squid.getDespawnCounter();
//            if (i > 100) {
//                this.squid.setSwimmingVector(0.0f, 0.0f, 0.0f);
//            } else if (this.squid.getRandom().nextInt(GiantSquidEntity.SwimGoal.toGoalTicks(50)) == 0  || !this.squid.hasSwimmingVector()) {
//                float f = this.squid.getRandom().nextFloat() * ((float)Math.PI * 2);
//                float g = MathHelper.cos(f) * 0.2f;
//                float h = -0.1f + this.squid.getRandom().nextFloat() * 0.2f;
//                float j = MathHelper.sin(f) * 0.2f;
//                this.squid.setSwimmingVector(g, h, j);
//
//            }
//        }
//    }
//
//    /* Boss Bar */
//
//    @Override
//    public void onStartedTrackingBy(ServerPlayerEntity player) {
//        super.onStartedTrackingBy(player);
//        this.bossBar.addPlayer(player);
//    }
//
//    @Override
//    public void onStoppedTrackingBy(ServerPlayerEntity player) {
//        super.onStoppedTrackingBy(player);
//        this.bossBar.removePlayer(player);
//    }
//
//    @Override
//    protected void mobTick() {
//        super.mobTick();
//        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
//    }
//}

