package com.github.ancient_forgery.mixin;

import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherPortalBlock.class)
public abstract class SpreadNetherPortalMixin {

    public void spreadInOverworld(BlockPos portal, ServerWorld world, int radius){
        for (BlockPos nextPosition : BlockPos.iterateOutwards(portal, radius, radius, radius)) {
            if(!nextPosition.isWithinDistance(portal, radius*1.2)) return;
            if (world.getBlockState(nextPosition).isIn(BlockTags.BASE_STONE_OVERWORLD)) {
                world.setBlockState(nextPosition, Blocks.NETHERRACK.getDefaultState());
                return;
            }else if (world.getBlockState(nextPosition).isIn(BlockTags.DIRT)) {
                world.setBlockState(nextPosition, Blocks.CRIMSON_NYLIUM.getDefaultState());
                return;
            }
        }
    }
    public void spreadInNether(BlockPos portal, ServerWorld world, int radius){
        for (BlockPos nextPosition : BlockPos.iterateOutwards(portal, radius, radius, radius)) {
            if(!nextPosition.isWithinDistance(portal, radius*1.2)) return;
            if (world.getBlockState(nextPosition).isIn(BlockTags.BASE_STONE_NETHER)) {
                world.setBlockState(nextPosition, Blocks.STONE.getDefaultState());
                return;
            }else if(world.getBlockState(nextPosition).isIn(BlockTags.NYLIUM)){
                world.setBlockState(nextPosition, Blocks.GRASS_BLOCK.getDefaultState());
                return;
            }
        }
    }
    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/block/NetherPortalBlock;randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)V")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo cr){
        int count = 0;
        int radius = 3;
        for (BlockPos nextPosition : BlockPos.iterateOutwards(pos, radius, radius, radius)) {
            if (world.getBlockState(nextPosition).getBlock() == Blocks.SOUL_LANTERN) {
                count++;
                if(count >= 2){
                    return;
                }
            }
        }
        if(world.getDimension().natural() && Math.random() < 0.25){
            spreadInOverworld(pos, world, radius);
        }else if(!world.getDimension().natural() && Math.random() < 0.25){
            spreadInNether(pos, world, radius);
        }
    }
}
