package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.block.custom.BonePileBlock;
import com.github.ancient_forgery.data.block.custom.FakeFletchingTableBlock;
import com.github.ancient_forgery.data.block.custom.SuspiciousSoulSand;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class BlockRegistry implements com.github.ancient_forgery.data.registry.Registry {

    public static final SuspiciousSoulSand SUSPICIOUS_SOUL_SAND = new SuspiciousSoulSand(Blocks.SOUL_SAND, FabricBlockSettings.copyOf(Blocks.SOUL_SAND), SoundEvent.of(Identifier.of("minecraft", "item.brush.brushing.sand")), SoundEvent.of(Identifier.of("minecraft", "item.brush.brushing.sand.complete")));
    public static final BonePileBlock BONE_PILE = new BonePileBlock(FabricBlockSettings.copyOf(Blocks.DEAD_TUBE_CORAL_BLOCK).strength(2f).requiresTool().nonOpaque());
    public static final FakeFletchingTableBlock FAKE_FLETCHING_TABLE = new FakeFletchingTableBlock(FabricBlockSettings.copyOf(Blocks.FLETCHING_TABLE));


    @Override
    public void register() {
        Registry.register(Registries.BLOCK, new Identifier("ancient-forgery", "suspicious_soul_sand"), SUSPICIOUS_SOUL_SAND);
        Registry.register(Registries.BLOCK, new Identifier("ancient-forgery", "bone_pile_1"), BONE_PILE);
        Registry.register(Registries.BLOCK, new Identifier("ancient-forgery", "fake_fletching_table"), FAKE_FLETCHING_TABLE);
    }
}