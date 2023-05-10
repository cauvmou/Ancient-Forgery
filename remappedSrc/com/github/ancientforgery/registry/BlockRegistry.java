package com.github.ancientforgery.registry;

import com.github.ancient_forgery.data.block.custom.SuspiciousSoulSand;
import com.github.ancient_forgery.data.block.entity.SuspiciousSoulSandEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class BlockRegistry {

    public static final SuspiciousSoulSand SUSPICIOUS_SOUL_SAND;
    public static final BlockEntityType<SuspiciousSoulSandEntity> SUSPICIOUS_SOUL_SAND_ENTITY;
    public static final BlockItem SUSPICIOUS_SOUL_SAND_ITEM;

    static {
        SUSPICIOUS_SOUL_SAND = net.minecraft.registry.Registry.register(Registries.BLOCK, new Identifier("ancient-forgery", "suspicious_soul_sand"), new SuspiciousSoulSand(Blocks.SOUL_SAND, FabricBlockSettings.copyOf(Blocks.SOUL_SAND), SoundEvent.of(Identifier.of("minecraft", "item.brush.brushing.sand")), SoundEvent.of(Identifier.of("minecraft", "item.brush.brushing.sand.complete"))));
        SUSPICIOUS_SOUL_SAND_ENTITY = net.minecraft.registry.Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("ancient-forgery", "suspicious_soul_sand"), FabricBlockEntityTypeBuilder.create(SuspiciousSoulSandEntity::new, SUSPICIOUS_SOUL_SAND).build());
        SUSPICIOUS_SOUL_SAND_ITEM = Registry.register(Registries.ITEM, new Identifier("ancient-forgery", "suspicious_soul_sand"), new BlockItem(SUSPICIOUS_SOUL_SAND, new Item.Settings()));
    }
}