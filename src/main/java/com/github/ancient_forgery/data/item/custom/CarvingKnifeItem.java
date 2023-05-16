package com.github.ancient_forgery.data.item.custom;

import com.github.ancient_forgery.data.registry.ItemRegistry;
import com.github.ancient_forgery.main.AncientForgery;
import net.fabricmc.fabric.impl.item.RecipeRemainderHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.sound.SoundEngine;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.*;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CarvingKnifeItem extends ToolItem {
    public CarvingKnifeItem(ToolMaterial material, Settings settings) {
        super(material, settings.recipeRemainder(ItemRegistry.CARVING_KNIFE_ITEM));
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        AncientForgery.LOGGER.info(String.valueOf(stack));
        ItemStack remainder = ItemRegistry.CARVING_KNIFE_ITEM.getDefaultStack();
        remainder.setDamage(stack.getDamage()+1);

        if (remainder.getDamage() >= remainder.getMaxDamage()) {
            return Items.AIR.getDefaultStack();
        }
        return remainder;
    }
}
