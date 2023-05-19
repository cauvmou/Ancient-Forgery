package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.block.custom.BonePileBlock;
import com.github.ancient_forgery.data.block.custom.CandelabraBlock;
import com.github.ancient_forgery.data.block.custom.CursedBookshelfBlock;
import com.github.ancient_forgery.data.block.custom.SuspiciousSoulSandBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockRegistry implements AFRegistry {

    public static final Block CURSED_BOOKSHELF = new CursedBookshelfBlock(
            AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE)
    );

    public static final Block SUSPICIOUS_SOUL_SAND = new SuspiciousSoulSandBlock(
            Blocks.SOUL_SAND,
            FabricBlockSettings.copyOf(Blocks.SOUL_SAND),
            SoundEvent.of(Identifier.of("minecraft", "item.brush.brushing.sand")),
            SoundEvent.of(Identifier.of("minecraft", "item.brush.brushing.sand.complete")));

    public static final Block BONE_PILE = new BonePileBlock(
            FabricBlockSettings.copyOf(Blocks.DEAD_TUBE_CORAL_BLOCK)
                    .strength(2f)
                    .requiresTool()
                    .nonOpaque());
    public static final Block CREEPER_SKULL = new SkullBlock(SkullBlock.Type.CREEPER,
            FabricBlockSettings.copyOf(Blocks.CREEPER_HEAD));
    public static final Block ZOMBIE_SKULL = new SkullBlock(SkullBlock.Type.ZOMBIE,
            FabricBlockSettings.copyOf(Blocks.ZOMBIE_HEAD));
    public static final List<Block> SKULL_LIST = new ArrayList<>(Arrays.asList(
            CREEPER_SKULL, ZOMBIE_SKULL
    ));

    public static final Block CANDELABRA = createCandelabraBlock(MapColor.PALE_YELLOW);
    public static final Block WHITE_CANDELABRA = createCandelabraBlock(MapColor.WHITE_GRAY);
    public static final Block LIGHT_GRAY_CANDELABRA = createCandelabraBlock(MapColor.LIGHT_GRAY);
    public static final Block GRAY_CANDELABRA = createCandelabraBlock(MapColor.GRAY);
    public static final Block BLACK_CANDELABRA = createCandelabraBlock(MapColor.BLACK);
    public static final Block BROWN_CANDELABRA = createCandelabraBlock(MapColor.BROWN);
    public static final Block RED_CANDELABRA = createCandelabraBlock(MapColor.RED);
    public static final Block ORANGE_CANDELABRA = createCandelabraBlock(MapColor.ORANGE);
    public static final Block YELLOW_CANDELABRA = createCandelabraBlock(MapColor.YELLOW);
    public static final Block LIME_CANDELABRA = createCandelabraBlock(MapColor.LIME);
    public static final Block GREEN_CANDELABRA = createCandelabraBlock(MapColor.GREEN);
    public static final Block CYAN_CANDELABRA = createCandelabraBlock(MapColor.CYAN);
    public static final Block LIGHT_BLUE_CANDELABRA = createCandelabraBlock(MapColor.LIGHT_BLUE);
    public static final Block BLUE_CANDELABRA = createCandelabraBlock(MapColor.BLUE);
    public static final Block PURPLE_CANDELABRA = createCandelabraBlock(MapColor.PURPLE);
    public static final Block MAGENTA_CANDELABRA = createCandelabraBlock(MapColor.MAGENTA);
    public static final Block PINK_CANDELABRA = createCandelabraBlock(MapColor.PINK);
    public static final List<Block> CANDELABRA_LIST = new ArrayList<>(Arrays.asList(
            CANDELABRA, WHITE_CANDELABRA, LIGHT_GRAY_CANDELABRA, GRAY_CANDELABRA, BLACK_CANDELABRA, BROWN_CANDELABRA,
            RED_CANDELABRA, ORANGE_CANDELABRA, YELLOW_CANDELABRA, LIME_CANDELABRA, GREEN_CANDELABRA, CYAN_CANDELABRA,
            LIGHT_BLUE_CANDELABRA, BLUE_CANDELABRA, PURPLE_CANDELABRA, MAGENTA_CANDELABRA, PINK_CANDELABRA
    ));

    private static CandelabraBlock createCandelabraBlock(MapColor color) {
        return new CandelabraBlock(
                FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)
                        .mapColor(color)
                        .strength(1f)
                        .sounds(BlockSoundGroup.METAL)
                        .luminance(CandelabraBlock.STATE_TO_LUMINANCE));
    }

    public static final Block BONE_PILLAR = new PillarBlock(
            FabricBlockSettings.copyOf(Blocks.BONE_BLOCK));
    public static final Block BONE_SLAB = new SlabBlock(
            FabricBlockSettings.copyOf(Blocks.BONE_BLOCK));
    public static final Block BONE_BRICKS = new Block(
            FabricBlockSettings.copyOf(Blocks.BONE_BLOCK));
    public static final Block BONE_BRICK_SLAB = new SlabBlock(
            FabricBlockSettings.copyOf(Blocks.BONE_BLOCK));

    @Override
    public void register() {
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "suspicious_soul_sand"),
                SUSPICIOUS_SOUL_SAND);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "cursed_bookshelf"),
                CURSED_BOOKSHELF);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "bone_pile_1"),
                BONE_PILE);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "candelabra"),
                CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "white_candelabra"),
                WHITE_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "light_gray_candelabra"),
                LIGHT_GRAY_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "gray_candelabra"),
                GRAY_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "black_candelabra"),
                BLACK_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "brown_candelabra"),
                BROWN_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "red_candelabra"),
                RED_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "orange_candelabra"),
                ORANGE_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "yellow_candelabra"),
                YELLOW_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "lime_candelabra"),
                LIME_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "green_candelabra"),
                GREEN_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "cyan_candelabra"),
                CYAN_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "light_blue_candelabra"),
                LIGHT_BLUE_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "blue_candelabra"),
                BLUE_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "purple_candelabra"),
                PURPLE_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "magenta_candelabra"),
                MAGENTA_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "pink_candelabra"),
                PINK_CANDELABRA);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "creeper_skull"),
                CREEPER_SKULL);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "zombie_skull"),
                ZOMBIE_SKULL);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "bone_pillar"),
                BONE_PILLAR);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "bone_slab"),
                BONE_SLAB);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "bone_bricks"),
                BONE_BRICKS);
        Registry.register(Registries.BLOCK,
                new Identifier(MOD_ID, "bone_brick_slab"),
                BONE_BRICK_SLAB);
    }
}