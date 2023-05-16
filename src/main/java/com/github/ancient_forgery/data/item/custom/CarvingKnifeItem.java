package com.github.ancient_forgery.data.item.custom;

import net.fabricmc.fabric.impl.item.RecipeRemainderHandler;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.world.World;

public class CarvingKnifeItem extends ToolItem {
    public CarvingKnifeItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
}
