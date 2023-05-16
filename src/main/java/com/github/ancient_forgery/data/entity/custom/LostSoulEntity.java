package com.github.ancient_forgery.data.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
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
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
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
        this.setNoGravity(true);
        this.moveControl = new LostSoulMoveControl(this, 5);
    }

    public static DefaultAttributeContainer setAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0d)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 4.0d)
                .build();
    }

    @Override
    public void baseTick() {
        super.baseTick();
        /*if (this.getWorld().isClient) {
            this.getWorld().addParticle(
                    //new DustParticleEffect(new Vector3f(0.37f, 0.96f, 0.98f), 2.0f),
                    new DustParticleEffect(new Vector3f(1.0f, 1.0f, 0.0f), 2.0f),
                    this.getPos().x, this.getPos().y + this.getDimensions(this.getPose()).height / 2, this.getPos().z,
                    0.0, 1.0, 0.0
            );
        }*/
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new LostSoulFlyGoal(this, 1.0f));
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
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height / 2.0f;
    }

    public static boolean canSpawn(EntityType<LostSoulEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
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

    private static class LostSoulMoveControl
            extends MoveControl {
        private final int maxPitchChange;

        public LostSoulMoveControl(MobEntity entity, int maxPitchChange) {
            super(entity);
            this.maxPitchChange = maxPitchChange;
        }

        @Override
        public void tick() {
            if (this.state == MoveControl.State.MOVE_TO) {
                this.state = MoveControl.State.WAIT;
                this.entity.setNoGravity(true);
                double d = this.targetX - this.entity.getX();
                double e = this.targetY - this.entity.getY();
                double f = this.targetZ - this.entity.getZ();
                double g = d * d + e * e + f * f;
                if (g < 2.500000277905201E-7) {
                    this.entity.setUpwardSpeed(0.0f);
                    this.entity.setForwardSpeed(0.0f);
                    return;
                }
                float h = (float)(MathHelper.atan2(f, d) * 57.2957763671875) - 90.0f;
                this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), h, 90.0f));
                float i = this.entity.isOnGround() ? (float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)) : (float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED));
                this.entity.setMovementSpeed(i);
                double j = Math.sqrt(d * d + f * f);
                if (Math.abs(e) > (double)1.0E-5f || Math.abs(j) > (double)1.0E-5f) {
                    float k = (float)(-(MathHelper.atan2(e, j) * 57.2957763671875));
                    this.entity.setPitch(this.wrapDegrees(this.entity.getPitch(), k, this.maxPitchChange));
                    this.entity.setUpwardSpeed(e > 0.0 ? i : -i);
                }
            } else {
                this.entity.setUpwardSpeed(0.0f);
                this.entity.setForwardSpeed(0.0f);
            }
        }
    }

    public static class LostSoulFlyGoal
            extends WanderAroundFarGoal {
        public LostSoulFlyGoal(PathAwareEntity pathAwareEntity, double d) {
            super(pathAwareEntity, d);
        }

        @Override
        @Nullable
        protected Vec3d getWanderTarget() {
            Vec3d vec3d = this.mob.getRotationVec(0.0f);
            Vec3d vec3d2 = AboveGroundTargeting.find(this.mob, 8, 3, vec3d.x, vec3d.z, 1.5707964f, 8, 5);
            if (vec3d2 != null) {
                return vec3d2;
            }
            return NoPenaltySolidTargeting.find(this.mob, 8, 2, -2, vec3d.x, vec3d.z, 1.5707963705062866);
        }
    }
}
