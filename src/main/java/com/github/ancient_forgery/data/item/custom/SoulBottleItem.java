package com.github.ancient_forgery.data.item.custom;

import com.github.ancient_forgery.data.entity.custom.LostSoulEntity;
import com.github.ancient_forgery.data.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SoulBottleItem extends Item {
    public SoulBottleItem(Settings settings) {
        super(settings);
    }

    // Check if glass bottle has been used on lost soul
    public static ActionResult OnEntityUseEvent(PlayerEntity playerEntity, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = playerEntity.getStackInHand(hand);
        if (stack.getItem() instanceof GlassBottleItem && entity instanceof LostSoulEntity) {
            stack.decrement(1);
            entity.remove(Entity.RemovalReason.UNLOADED_WITH_PLAYER);
            playerEntity.giveItemStack(new ItemStack(ItemRegistry.SOUL_BOTTLE));
            return ActionResult.success(playerEntity.getWorld().isClient);
        }
        return ActionResult.PASS;
    }
}
