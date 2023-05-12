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
    public static final Item WHITE_CANDELABRA_ITEM = new BlockItem(
            WHITE_CANDELABRA,
            new Item.Settings());
    public static final Item LIGHT_GRAY_CANDELABRA_ITEM = new BlockItem(
            LIGHT_GRAY_CANDELABRA,
            new Item.Settings());
    public static final Item GRAY_CANDELABRA_ITEM = new BlockItem(
            GRAY_CANDELABRA,
            new Item.Settings());
    public static final Item BLACK_CANDELABRA_ITEM = new BlockItem(
            BLACK_CANDELABRA,
            new Item.Settings());
    public static final Item BROWN_CANDELABRA_ITEM = new BlockItem(
            BROWN_CANDELABRA,
            new Item.Settings());
    public static final Item RED_CANDELABRA_ITEM = new BlockItem(
            RED_CANDELABRA,
            new Item.Settings());
    public static final Item ORANGE_CANDELABRA_ITEM = new BlockItem(
            ORANGE_CANDELABRA,
            new Item.Settings());
    public static final Item YELLOW_CANDELABRA_ITEM = new BlockItem(
            YELLOW_CANDELABRA,
            new Item.Settings());
    public static final Item LIME_CANDELABRA_ITEM = new BlockItem(
            LIME_CANDELABRA,
            new Item.Settings());
    public static final Item GREEN_CANDELABRA_ITEM = new BlockItem(
            GREEN_CANDELABRA,
            new Item.Settings());
    public static final Item CYAN_CANDELABRA_ITEM = new BlockItem(
            CYAN_CANDELABRA,
            new Item.Settings());
    public static final Item LIGHT_BLUE_CANDELABRA_ITEM = new BlockItem(
            LIGHT_BLUE_CANDELABRA,
            new Item.Settings());
    public static final Item BLUE_CANDELABRA_ITEM = new BlockItem(
            BLUE_CANDELABRA,
            new Item.Settings());
    public static final Item PURPLE_CANDELABRA_ITEM = new BlockItem(
            PURPLE_CANDELABRA,
            new Item.Settings());
    public static final Item MAGENTA_CANDELABRA_ITEM = new BlockItem(
            MAGENTA_CANDELABRA,
            new Item.Settings());
    public static final Item PINK_CANDELABRA_ITEM = new BlockItem(
            PINK_CANDELABRA,
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
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "white_candelabra"),
                WHITE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "light_gray_candelabra"),
                LIGHT_GRAY_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "gray_candelabra"),
                GRAY_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "black_candelabra"),
                BLACK_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "brown_candelabra"),
                BROWN_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "red_candelabra"),
                RED_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "orange_candelabra"),
                ORANGE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "yellow_candelabra"),
                YELLOW_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "lime_candelabra"),
                LIME_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "green_candelabra"),
                GREEN_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "cyan_candelabra"),
                CYAN_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "light_blue_candelabra"),
                LIGHT_BLUE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "blue_candelabra"),
                BLUE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "purple_candelabra"),
                PURPLE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "magenta_candelabra"),
                MAGENTA_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier("ancient_forgery", "pink_candelabra"),
                PINK_CANDELABRA_ITEM);
    }
}
