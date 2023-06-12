package com.github.ancient_forgery.data.item;

import net.minecraft.item.Item;
import net.minecraft.util.StringIdentifiable;

import static com.github.ancient_forgery.data.registry.ItemRegistry.*;
import static net.minecraft.item.Items.*;

public enum ArrowFletching implements StringIdentifiable {
    CHICKEN(1, "Chicken Feather", FEATHER),
    PARROT(2, "Parrot Feather", PARROT_FEATHER);

    private final int id;
    private final String name;
    private final Item item;

    ArrowFletching(int id, String name, Item item) {
        this.id = id;
        this.name = name;
        this.item = item;
    }

    @Override
    public String asString() {
        return this.name + " Fletching";
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
    public static ArrowFletching fromItem(Item item) {
        for (ArrowFletching fletching : ArrowFletching.values()) {
            if (fletching.getItem().equals(item)) return fletching;
        }

        return null;
    }

    public static ArrowFletching fromId(int id) {
        for (ArrowFletching fletching : ArrowFletching.values()) {
            if (fletching.getId() == id) return fletching;
        }

        return null;
    }
}
