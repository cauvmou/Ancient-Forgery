package com.github.ancient_forgery.data.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.BodyControl;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class LostSoulEntity extends PassiveEntity {

    Vec3d targetPosition = Vec3d.ZERO;
    BlockPos circlingCenter = BlockPos.ORIGIN;
    LostSoulEntity.LostSoulMovementType movementType = LostSoulEntity.LostSoulMovementType.WANDER;

    static enum LostSoulMovementType {
        CIRCLE,
        WANDER,
    }

    public LostSoulEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, true);
        this.lookControl = new LostSoulLookControl(this);
    }

    public static DefaultAttributeContainer setAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0d)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 1.0d)
                .build();
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.getWorld().isClient) {
            this.getWorld().addParticle(
                    new DustParticleEffect(new Vector3f(0.37f, 0.96f, 0.98f), 1.0f),
                    this.getPos().x, this.getPos().y + this.getDimensions(this.getPose()).height / 2, this.getPos().z,
                    0.0, 0.0, 0.0
            );
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new CircleMovementGoal());
        this.goalSelector.add(2, new FlyGoal(this, 1.0f));
        this.targetSelector.add(1, new FindTargetGoal());
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height / 2.0f;
    }

    public static boolean canSpawn(EntityType<BatEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBiome(pos).matchesId(Identifier.of("minecraft", "soul_sand_valley"));
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    class LostSoulMoveControl
            extends MoveControl {
        private float targetSpeed;

        public LostSoulMoveControl(MobEntity owner) {
            super(owner);
            this.targetSpeed = 0.1f;
        }

        @Override
        public void tick() {
            if (LostSoulEntity.this.horizontalCollision) {
                LostSoulEntity.this.setYaw(LostSoulEntity.this.getYaw() + 180.0f);
                this.targetSpeed = 0.1f;
            }
            double d = LostSoulEntity.this.targetPosition.x - LostSoulEntity.this.getX();
            double e = LostSoulEntity.this.targetPosition.y - LostSoulEntity.this.getY();
            double f = LostSoulEntity.this.targetPosition.z - LostSoulEntity.this.getZ();
            double g = Math.sqrt(d * d + f * f);
            if (Math.abs(g) > (double)1.0E-5f) {
                double h = 1.0 - Math.abs(e * (double)0.7f) / g;
                g = Math.sqrt((d *= h) * d + (f *= h) * f);
                double i = Math.sqrt(d * d + f * f + e * e);
                float j = LostSoulEntity.this.getYaw();
                float k = (float) MathHelper.atan2(f, d);
                float l = MathHelper.wrapDegrees(LostSoulEntity.this.getYaw() + 90.0f);
                float m = MathHelper.wrapDegrees(k * 57.295776f);
                LostSoulEntity.this.setYaw(MathHelper.stepUnwrappedAngleTowards(l, m, 4.0f) - 90.0f);
                LostSoulEntity.this.bodyYaw = LostSoulEntity.this.getYaw();
                this.targetSpeed = MathHelper.angleBetween(j, LostSoulEntity.this.getYaw()) < 3.0f ? MathHelper.stepTowards(this.targetSpeed, 1.8f, 0.005f * (1.8f / this.targetSpeed)) : MathHelper.stepTowards(this.targetSpeed, 0.2f, 0.025f);
                float n = (float)(-(MathHelper.atan2(-e, g) * 57.2957763671875));
                LostSoulEntity.this.setPitch(n);
                float o = LostSoulEntity.this.getYaw() + 90.0f;
                double p = (double)(this.targetSpeed * MathHelper.cos(o * ((float)Math.PI / 180))) * Math.abs(d / i);
                double q = (double)(this.targetSpeed * MathHelper.sin(o * ((float)Math.PI / 180))) * Math.abs(f / i);
                double r = (double)(this.targetSpeed * MathHelper.sin(n * ((float)Math.PI / 180))) * Math.abs(e / i);
                Vec3d vec3d = LostSoulEntity.this.getVelocity();
                LostSoulEntity.this.setVelocity(vec3d.add(new Vec3d(p, r, q).subtract(vec3d).multiply(0.2)));
            }
        }
    }

    class LostSoulLookControl
            extends LookControl {
        public LostSoulLookControl(MobEntity entity) {
            super(entity);
        }

        @Override
        public void tick() {
        }
    }

    class CircleMovementGoal
            extends LostSoulEntity.MovementGoal {
        private float angle;
        private float radius;
        private float yOffset;
        private float circlingDirection;

        CircleMovementGoal() {
        }

        @Override
        public boolean canStart() {
            return LostSoulEntity.this.getTarget() == null || LostSoulEntity.this.movementType == LostSoulEntity.LostSoulMovementType.CIRCLE;
        }

        @Override
        public void start() {
            this.radius = 5.0f + LostSoulEntity.this.random.nextFloat() * 10.0f;
            this.yOffset = -4.0f + LostSoulEntity.this.random.nextFloat() * 9.0f;
            this.circlingDirection = LostSoulEntity.this.random.nextBoolean() ? 1.0f : -1.0f;
            this.adjustDirection();
        }

        @Override
        public void tick() {
            if (LostSoulEntity.this.random.nextInt(this.getTickCount(350)) == 0) {
                this.yOffset = -4.0f + LostSoulEntity.this.random.nextFloat() * 9.0f;
            }
            if (LostSoulEntity.this.random.nextInt(this.getTickCount(250)) == 0) {
                this.radius += 1.0f;
                if (this.radius > 15.0f) {
                    this.radius = 5.0f;
                    this.circlingDirection = -this.circlingDirection;
                }
            }
            if (LostSoulEntity.this.random.nextInt(this.getTickCount(450)) == 0) {
                this.angle = LostSoulEntity.this.random.nextFloat() * 2.0f * (float)Math.PI;
                this.adjustDirection();
            }
            if (this.isNearTarget()) {
                this.adjustDirection();
            }
            if (LostSoulEntity.this.targetPosition.y < LostSoulEntity.this.getY() && !LostSoulEntity.this.getWorld().isAir(LostSoulEntity.this.getBlockPos().down(1))) {
                this.yOffset = Math.max(1.0f, this.yOffset);
                this.adjustDirection();
            }
            if (LostSoulEntity.this.targetPosition.y > LostSoulEntity.this.getY() && !LostSoulEntity.this.getWorld().isAir(LostSoulEntity.this.getBlockPos().up(1))) {
                this.yOffset = Math.min(-1.0f, this.yOffset);
                this.adjustDirection();
            }
        }

        private void adjustDirection() {
            if (BlockPos.ORIGIN.equals(LostSoulEntity.this.circlingCenter)) {
                LostSoulEntity.this.circlingCenter = LostSoulEntity.this.getBlockPos();
            }
            this.angle += this.circlingDirection * 15.0f * ((float)Math.PI / 180);
            LostSoulEntity.this.targetPosition = Vec3d.of(LostSoulEntity.this.circlingCenter).add(this.radius * MathHelper.cos(this.angle), -4.0f + this.yOffset, this.radius * MathHelper.sin(this.angle));
        }
    }

    class FindTargetGoal
            extends Goal {
        private final TargetPredicate PLAYERS_IN_RANGE_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(64.0);
        private int delay = LostSoulEntity.FindTargetGoal.toGoalTicks(20);

        FindTargetGoal() {
        }

        @Override
        public boolean canStart() {
            if (this.delay > 0) {
                --this.delay;
                return false;
            }
            this.delay = LostSoulEntity.FindTargetGoal.toGoalTicks(60);
            List<PlayerEntity> list = LostSoulEntity.this.getWorld().getPlayers(this.PLAYERS_IN_RANGE_PREDICATE, LostSoulEntity.this, LostSoulEntity.this.getBoundingBox().expand(16.0, 64.0, 16.0));
            if (!list.isEmpty()) {
                list.sort(Comparator.comparing(Entity::getY).reversed());
                for (PlayerEntity playerEntity : list) {
                    if (!LostSoulEntity.this.isTarget(playerEntity, TargetPredicate.DEFAULT)) continue;
                    LostSoulEntity.this.setTarget(playerEntity);
                    playerEntity.sendMessage(Text.literal("TARGETED"));
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean shouldContinue() {
            LivingEntity livingEntity = LostSoulEntity.this.getTarget();
            if (livingEntity != null) {
                return LostSoulEntity.this.isTarget(livingEntity, TargetPredicate.DEFAULT);
            }
            return false;
        }
    }

    abstract class MovementGoal
            extends Goal {
        public MovementGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        protected boolean isNearTarget() {
            return LostSoulEntity.this.targetPosition.squaredDistanceTo(LostSoulEntity.this.getX(), LostSoulEntity.this.getY(), LostSoulEntity.this.getZ()) < 4.0;
        }
    }
}
