package com.github.ancient_forgery.data.entity.goal;

import com.github.ancient_forgery.data.entity.custom.LostSoulEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class CirclePlayerGoal extends Goal {
    private final LostSoulEntity lostSoul;
    private final double speed;
    @Nullable
    private PlayerEntity closestPlayer;

    private final TargetPredicate CLOSE_PLAYER_PREDICATE;

    public CirclePlayerGoal(LostSoulEntity lostSoul, double speed, double distance) {
        this.lostSoul = lostSoul;
        this.speed = speed;
        this.CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(distance).ignoreVisibility();
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        this.closestPlayer = this.lostSoul.getWorld().getClosestPlayer(CLOSE_PLAYER_PREDICATE, this.lostSoul);
        if (this.closestPlayer == null) {
            return false;
        }
        return true;
    }

    @Override
    public void stop() {
        this.closestPlayer = null;
        this.lostSoul.getNavigation().stop();
    }

    @Override
    public boolean shouldContinue() {
        return this.closestPlayer != null && this.lostSoul.squaredDistanceTo(this.closestPlayer) < 256.0;
    }

    @Override
    public void tick() {
        if (this.lostSoul.squaredDistanceTo(this.closestPlayer) < 12) {
            this.lostSoul.getNavigation().stop();
        } else {
            this.lostSoul.getNavigation().startMovingTo(this.closestPlayer, this.speed);
        }
    }
}
