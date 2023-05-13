package com.github.ancient_forgery.mixin;

import com.github.ancient_forgery.data.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BubbleColumnBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.BubbleColumnBlock.DRAG;

@Mixin(BubbleColumnBlock.class)
public abstract class BubbleColumnMixin {

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/block/BubbleColumnBlock;getBubbleState(Lnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;", cancellable = true)
    private static void getBubbleState(BlockState state, CallbackInfoReturnable<BlockState> cir) {
        if (state.isOf(BlockRegistry.SUSPICIOUS_SOUL_SAND)) {
            cir.setReturnValue((BlockState) Blocks.BUBBLE_COLUMN.getDefaultState().with(DRAG, false));
        }
    }
}
