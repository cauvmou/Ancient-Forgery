package com.github.ancient_forgery.data.screen;

import com.github.ancient_forgery.data.screen.FletchingScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface FletchingScreenHandlerFactory {
    NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos);
}
