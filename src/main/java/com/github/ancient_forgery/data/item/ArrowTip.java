package com.github.ancient_forgery.data.item;

import net.minecraft.block.MapColor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.StringIdentifiable;

import static com.github.ancient_forgery.data.registry.ItemRegistry.*;

public enum ArrowTip implements StringIdentifiable {
    FLINT(1, "Flint", FLINT_ARROW_TIP),
    IRON(2, "Iron", IRON_ARROW_TIP),
    DIAMOND(3, "Diamond", DIAMOND_ARROW_TIP),
    BONE(4, "Bone", BONE_ARROW_TIP);

    private final int id;
    private final String name;
    private final Item item;

    ArrowTip(int id, String name, Item item) {
        this.id = id;
        this.name = name;
        this.item = item;
    }

    @Override
    public String asString() {
        return this.name + " Tip";
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Item getItem() {
        return item;
    }


    // TODO: Improve this
    public static ArrowTip fromItem(Item item) {
        for (ArrowTip tip : ArrowTip.values()) {
            if (tip.getItem().equals(item)) return tip;
        }

        return null;
    }

    public static ArrowTip fromId(int id) {
        for (ArrowTip tip : ArrowTip.values()) {
            if (tip.getId() == id) return tip;
        }

        return null;
    }
}
