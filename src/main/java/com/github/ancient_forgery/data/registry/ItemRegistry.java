package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.item.custom.CarvingKnifeItem;
import com.github.ancient_forgery.data.item.custom.LongBowItem;
import com.github.ancient_forgery.data.item.custom.ShortBowItem;
import com.github.ancient_forgery.data.item.custom.SoulBottleItem;
import com.github.ancient_forgery.data.item.custom.TestBrush;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static com.github.ancient_forgery.data.registry.BlockRegistry.*;
import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class ItemRegistry implements AFRegistry {

    public static final Item SUSPICIOUS_SOUL_SAND_ITEM = new BlockItem(
            SUSPICIOUS_SOUL_SAND,
            new Item.Settings());
    public static final Item SOUL_BOTTLE = new SoulBottleItem(new FabricItemSettings().rarity(Rarity.RARE));
    public static final Item BONE_PILE_ITEM = new BlockItem(
            BONE_PILE,
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
    public static final Item COPPER_NUGGET = new Item(
            new FabricItemSettings());
    public static final Item CREEPER_SKULL_SHARD = new Item(
            new FabricItemSettings());
    public static final Item SKELETON_SKULL_SHARD = new Item(
            new FabricItemSettings());
    public static final Item ZOMBIE_SKULL_SHARD = new Item(
            new FabricItemSettings());
    public static final Item CREEPER_SKULL_ITEM = new BlockItem(
            CREEPER_SKULL,
            new Item.Settings());
    public static final Item ZOMBIE_SKULL_ITEM = new BlockItem(
            ZOMBIE_SKULL,
            new Item.Settings());
    public static final Item LONGBOW_ITEM = new LongBowItem(
            new FabricItemSettings().maxDamage(384));
    public static final Item SHORTBOW_ITEM = new ShortBowItem(
            new FabricItemSettings().maxDamage(384));
    public static final CarvingKnifeItem CARVING_KNIFE_ITEM = new CarvingKnifeItem(
            ToolMaterials.IRON, new FabricItemSettings()
    );
    public static final Item FLINT_ARROW_TIP = new Item(
            new FabricItemSettings());
    public static final Item IRON_ARROW_TIP = new Item(
            new FabricItemSettings());
    public static final Item DIAMOND_ARROW_TIP = new Item(
            new FabricItemSettings());
    public static final Item BONE_ARROW_TIP = new Item(
            new FabricItemSettings());

    public static final Item BONE_PILLAR_ITEM = new BlockItem(
            BONE_PILLAR, new FabricItemSettings());
    public static final Item BONE_SLAB_ITEM = new BlockItem(
            BONE_SLAB, new FabricItemSettings());
    public static final Item BONE_BRICKS_ITEM = new BlockItem(
            BONE_BRICKS, new FabricItemSettings());
    public static final Item BONE_BRICK_SLAB_ITEM = new BlockItem(
            BONE_BRICK_SLAB, new FabricItemSettings());

    @Override
    public void register() {
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "suspicious_soul_sand"),
                SUSPICIOUS_SOUL_SAND_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "soul_bottle"),
                SOUL_BOTTLE);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "test_brush"),
                TEST_BRUSH);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "parrot_feather"),
                PARROT_FEATHER);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "bone_pile_1"),
                BONE_PILE_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "candelabra"),
                CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "white_candelabra"),
                WHITE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "light_gray_candelabra"),
                LIGHT_GRAY_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "gray_candelabra"),
                GRAY_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "black_candelabra"),
                BLACK_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "brown_candelabra"),
                BROWN_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "red_candelabra"),
                RED_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "orange_candelabra"),
                ORANGE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "yellow_candelabra"),
                YELLOW_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "lime_candelabra"),
                LIME_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "green_candelabra"),
                GREEN_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "cyan_candelabra"),
                CYAN_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "light_blue_candelabra"),
                LIGHT_BLUE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "blue_candelabra"),
                BLUE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "purple_candelabra"),
                PURPLE_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "magenta_candelabra"),
                MAGENTA_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "pink_candelabra"),
                PINK_CANDELABRA_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "longbow"),
                LONGBOW_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "copper_nugget"),
                COPPER_NUGGET);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "creeper_skull_shard"),
                CREEPER_SKULL_SHARD);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "skeleton_skull_shard"),
                SKELETON_SKULL_SHARD);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "zombie_skull_shard"),
                ZOMBIE_SKULL_SHARD);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "creeper_skull"),
                CREEPER_SKULL_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "zombie_skull"),
                ZOMBIE_SKULL_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "shortbow"),
                SHORTBOW_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "carving_knife"),
                CARVING_KNIFE_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "flint_arrow_tip"),
                FLINT_ARROW_TIP);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "iron_arrow_tip"),
                IRON_ARROW_TIP);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "diamond_arrow_tip"),
                DIAMOND_ARROW_TIP);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "bone_arrow_tip"),
                BONE_ARROW_TIP);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "bone_pillar"),
                BONE_PILLAR_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "bone_slab"),
                BONE_SLAB_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "bone_bricks"),
                BONE_BRICKS_ITEM);
        Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "bone_brick_slab"),
                BONE_BRICK_SLAB_ITEM);
    }
}
