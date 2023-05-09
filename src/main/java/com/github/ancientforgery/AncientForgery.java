package com.github.ancientforgery;

import com.github.ancientforgery.blocks.SuspiciousSoulSand;
import com.github.ancientforgery.blocks.SuspiciousSoulSandEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AncientForgery implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("ancient-forgery");
	public static final TestBrush TEST_BRUSH =
			Registry.register(Registries.ITEM, new Identifier("ancient-forgery", "test_brush"),
					new TestBrush(new FabricItemSettings()
							.rarity(Rarity.EPIC)));

	public static final SuspiciousSoulSand SUSPICIOUS_SOUL_SAND = new SuspiciousSoulSand(
			Blocks.SOUL_SAND,
			FabricBlockSettings.copyOf(Blocks.SOUL_SAND),
			SoundEvent.of(Identifier.of("minecraft", "item.brush.brushing.sand")),
			SoundEvent.of(Identifier.of("minecraft", "item.brush.brushing.sand.complete")));
	public static final BlockEntityType<SuspiciousSoulSandEntity> SUSPICIOUS_SOUL_SAND_ENTITY = FabricBlockEntityTypeBuilder.create(
			SuspiciousSoulSandEntity::new,
			SUSPICIOUS_SOUL_SAND
	).build();

	@Override
	public void onInitialize() {
		LOGGER.info("AncientForgery Initialized!");
		Registry.register(
				Registries.BLOCK,
				new Identifier("ancient-forgery", "suspicious_soul_sand"),
				SUSPICIOUS_SOUL_SAND
		);
		Registry.register(
				Registries.ITEM,
				new Identifier("ancient-forgery", "suspicious_soul_sand"),
				new BlockItem(SUSPICIOUS_SOUL_SAND, new Item.Settings())
		);
		Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				new Identifier("ancient-forgery", "suspicious_soul_sand"),
				SUSPICIOUS_SOUL_SAND_ENTITY
		);
	}
}