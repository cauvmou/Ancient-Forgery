package com.github.ancient_forgery.data.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ArrowItemInterface {
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context);
}
