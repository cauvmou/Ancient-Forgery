package com.github.ancient_forgery.data.item.custom;

import com.github.ancient_forgery.data.item.ArrowTip;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;

public class ArrowTipItem extends Item {
    private final ArrowTip arrowTip;

    public ArrowTipItem(ArrowTip arrowTip, Settings settings) {
        super(settings);
        this.arrowTip = arrowTip;
    }

    public ArrowTip getArrowTip() {
        return arrowTip;
    }
}
