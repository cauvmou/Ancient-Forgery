package com.github.ancient_forgery.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ArrowItemInterface {
    public void onCraft(ItemStack stack, World world, PlayerEntity player);
}
