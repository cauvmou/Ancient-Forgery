package com.github.ancient_forgery.data.item;

import net.minecraft.item.Item;
import net.minecraft.util.StringIdentifiable;

import static net.minecraft.item.Items.*;

public enum ArrowShaft implements StringIdentifiable {
    WOOD(1, "Wood", STICK),
    FLAME(2, "Flaming", BLAZE_ROD);

    private final int id;
    private final String name;
    private final Item item;

    ArrowShaft(int id, String name, Item item) {
        this.id = id;
        this.name = name;
        this.item = item;
    }

    @Override
    public String asString() {
        return this.name + " Shaft";
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
    public static ArrowShaft fromItem(Item item) {
        for (ArrowShaft shaft : ArrowShaft.values()) {
            if (shaft.getItem().equals(item)) return shaft;
        }

        return null;
    }

    public static ArrowShaft fromId(int id) {
        for (ArrowShaft shaft : ArrowShaft.values()) {
            if (shaft.getId() == id) return shaft;
        }

        return null;
    }
}
