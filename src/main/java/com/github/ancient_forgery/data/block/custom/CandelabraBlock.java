package com.github.ancient_forgery.data.block.custom;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.*;
import net.minecraft.block.enums.Attachment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.function.ToIntFunction;

public class CandelabraBlock extends AbstractCandleBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public CandelabraBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected Iterable<Vec3d> getParticleOffsets(BlockState state) {
        Direction direction = state.get(FACING);

        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            return ImmutableList.of(new Vec3d(0.8125, 1.0625, 0.46875), new Vec3d(0.5, 1.125, 0.46875), new Vec3d(0.21875, 1.0625, 0.46875));
        } else {
            return ImmutableList.of(new Vec3d(0.46875, 1.0625, 0.8125), new Vec3d(0.5, 1.125, 0.46875), new Vec3d(0.46875, 1.0625, 0.21875));
        }
    }

    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(1, 0, 6, 15, 16, 10);
    private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(6, 0, 1, 10, 16, 15);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);

        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            return NORTH_SHAPE;
        } else {
            return EAST_SHAPE;
        }
    }

    public static boolean canBeLit(BlockState state) {
        /*
        return state.isIn(BlockTags.CANDLES, (statex)
                -> statex.contains(LIT))
                && !(Boolean)state.get(LIT);

         */
        return state.isIn(BlockTags.CANDLES);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return hasTopRim(world, blockPos) || sideCoversSmallSquare(world, blockPos, Direction.UP);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        world.setBlockState(pos, state.with(Properties.LIT, false), 11);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }
}
