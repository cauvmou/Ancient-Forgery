package com.github.ancient_forgery.mixin;

import com.github.ancient_forgery.data.screen.FletchingScreenHandler;
import com.github.ancient_forgery.data.screen.FletchingScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.FletchingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArrowItem.class)
public abstract class ArrowItemMixin implements ArrowItemInterface {
    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {

    }
}
