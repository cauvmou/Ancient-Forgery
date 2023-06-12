package com.github.ancient_forgery.data.block.custom;

import com.github.ancient_forgery.data.block.entity.CursedLecternEntity;
import com.github.ancient_forgery.data.registry.BlockEntityRegistry;
import com.github.ancient_forgery.data.registry.BlockRegistry;
import com.github.ancient_forgery.data.registry.ParticleRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CursedLectern extends LecternBlock {

    public CursedLectern(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CursedLecternEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? CursedLectern.checkType(type, BlockEntityRegistry.CURSED_LECTERN_ENTITY, CursedLecternEntity::tick) : null;
    }

}
