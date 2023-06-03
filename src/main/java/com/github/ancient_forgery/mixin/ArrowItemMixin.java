package com.github.ancient_forgery.mixin;

import com.github.ancient_forgery.data.item.ArrowFletching;
import com.github.ancient_forgery.data.item.ArrowItemInterface;
import com.github.ancient_forgery.data.item.ArrowShaft;
import com.github.ancient_forgery.data.item.ArrowTip;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(ArrowItem.class)
public abstract class ArrowItemMixin implements ArrowItemInterface {
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.getNbt() == null) return;
        tooltip.add(Text.translatable(ArrowTip.fromId(Integer.parseInt(stack.getNbt().get("tip").toString())).asString()).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(ArrowShaft.fromId(Integer.parseInt(stack.getNbt().get("shaft").toString())).asString()).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(ArrowFletching.fromId(Integer.parseInt(stack.getNbt().get("fletching").toString())).asString()).formatted(Formatting.GRAY));
    }
}
