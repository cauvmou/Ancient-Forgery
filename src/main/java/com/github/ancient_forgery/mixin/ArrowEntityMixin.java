package com.github.ancient_forgery.mixin;

import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Set;

import static net.minecraft.block.BubbleColumnBlock.DRAG;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin {
    NbtCompound itemCompound;

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/projectile/ArrowEntity;initFromStack(Lnet/minecraft/item/ItemStack;)V")
    public void initFromStack(ItemStack stack, CallbackInfo info) {
        this.itemCompound = stack.getNbt();
    }

    // TODO: DONT MAKE THIS BREAK TIPPED ARROWS!!!!
    @Inject(at = @At("RETURN"), method = "Lnet/minecraft/entity/projectile/ArrowEntity;asItemStack()Lnet/minecraft/item/ItemStack;", cancellable = true)
    protected void asItemStack(CallbackInfoReturnable<ItemStack> cir) {
        ItemStack ret = new ItemStack(Items.ARROW);
        ret.setNbt(itemCompound);
        cir.setReturnValue(ret);
    }
}
