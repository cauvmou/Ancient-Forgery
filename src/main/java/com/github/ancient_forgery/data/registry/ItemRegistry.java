package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.item.custom.TestBrush;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static com.github.ancient_forgery.data.registry.BlockRegistry.*;

public class ItemRegistry implements AFRegistry {

    public static final Item SUSPICIOUS_SOUL_SAND_ITEM = new BlockItem(
            SUSPICIOUS_SOUL_SAND,
            new Item.Settings());
    public static final Item BONE_PILE_ITEM = new BlockItem(
            BONE_PILE,
            new Item.Settings());
    public static final Item FAKE_FLETCHING_TABLE_ITEM = new BlockItem(
            FAKE_FLETCHING_TABLE,
            new Item.Settings());
    public static final Item CANDELABRA_ITEM = new BlockItem(
            CANDELABRA,
            new Item.Settings());
    public static final Item TEST_BRUSH = new TestBrush(
            new FabricItemSettings()
                    .rarity(Rarity.EPIC));
    public static final Item PARROT_FEATHER = new Item(
            new FabricItemSettings());

    @Override
    public void register() {
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "suspicious_soul_sand"),
                SUSPICIOUS_SOUL_SAND_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "test_brush"),
                TEST_BRUSH);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "parrot_feather"),
                PARROT_FEATHER);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "bone_pile_1"),
                BONE_PILE_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "fake_fletching_table"),
                FAKE_FLETCHING_TABLE_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "candelabra"),
                CANDELABRA_ITEM);
    }
}
