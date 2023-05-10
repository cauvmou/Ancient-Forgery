package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.item.custom.TestBrush;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import static com.github.ancient_forgery.data.registry.BlockRegistry.SUSPICIOUS_SOUL_SAND;

public class ItemRegistry {

    public static final BlockItem SUSPICIOUS_SOUL_SAND_ITEM;
    public static final TestBrush TEST_BRUSH;
    public static final Item PARROT_FEATHER;

    static {
        SUSPICIOUS_SOUL_SAND_ITEM = Registry.register(Registries.ITEM, new Identifier("ancient-forgery", "suspicious_soul_sand"), new BlockItem(SUSPICIOUS_SOUL_SAND, new Item.Settings()));
        TEST_BRUSH = Registry.register(Registries.ITEM, new Identifier("ancient-forgery", "test_brush"), new TestBrush(new FabricItemSettings().rarity(Rarity.EPIC)));
        PARROT_FEATHER = Registry.register(Registries.ITEM, new Identifier("ancient-forgery", "parrot_feather"), new Item(new FabricItemSettings()));
    }
}
