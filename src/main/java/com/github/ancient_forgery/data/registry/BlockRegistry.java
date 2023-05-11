package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.block.custom.BonePileBlock;
import com.github.ancient_forgery.data.block.custom.CandelabraBlock;
import com.github.ancient_forgery.data.block.custom.FakeFletchingTableBlock;
import com.github.ancient_forgery.data.block.custom.SuspiciousSoulSandBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagBuilder;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class BlockRegistry implements AFRegistry {

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
    public static final Block FAKE_FLETCHING_TABLE = new FakeFletchingTableBlock(
            FabricBlockSettings.copyOf(Blocks.FLETCHING_TABLE));

    public static final Block CANDELABRA = new CandelabraBlock(
            FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)
                    .strength(1f)
                    .sounds(BlockSoundGroup.METAL));


    @Override
    public void register() {
        Registry.register(Registries.BLOCK,
                new Identifier("ancient_forgery", "suspicious_soul_sand"),
                SUSPICIOUS_SOUL_SAND);
        Registry.register(Registries.BLOCK,
                new Identifier("ancient_forgery", "bone_pile_1"),
                BONE_PILE);
        Registry.register(Registries.BLOCK,
                new Identifier("ancient_forgery", "fake_fletching_table"),
                FAKE_FLETCHING_TABLE);
        Registry.register(Registries.BLOCK,
                new Identifier("ancient_forgery", "candelabra"),
                CANDELABRA);
    }
}