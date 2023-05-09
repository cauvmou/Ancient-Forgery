package com.github.ancientforgery;

import com.github.ancientforgery.block.custom.BonePileBlock;
import com.github.ancientforgery.block.custom.FakeFletchingTableBlock;
import com.github.ancientforgery.block.entity.FakeFletchingTableBlockEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("ancient-forgery");
	public static final TestBrush TEST_BRUSH =
			Registry.register(Registries.ITEM,
					new Identifier("ancient-forgery", "test_brush"),
					new TestBrush(new FabricItemSettings()
							.rarity(Rarity.EPIC)));

	public static final Item PARROT_FEATHER =
			Registry.register(Registries.ITEM,
					new Identifier("ancient-forgery", "parrot_feather"),
					new Item(new FabricItemSettings()));

	public static final BonePileBlock BONE_PILE = Registry.register(Registries.BLOCK,
			new Identifier("ancient-forgery", "bone_pile_1"),
			new BonePileBlock(FabricBlockSettings
					.copyOf(Blocks.DEAD_TUBE_CORAL_BLOCK)
					.strength(2f)
					.requiresTool()
					.nonOpaque()));

	public static final FakeFletchingTableBlock FAKE_FLETCHING_TABLE = Registry.register(Registries.BLOCK,
			new Identifier("ancient-forgery", "fake_fletching_table"),
			new FakeFletchingTableBlock(FabricBlockSettings
					.copyOf(Blocks.FLETCHING_TABLE)));

	public static BlockEntityType<FakeFletchingTableBlockEntity> FLETCHING_TABLE;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		Registry.register(Registries.ITEM, new Identifier("ancient-forgery", "bone_pile_1"), new BlockItem(BONE_PILE, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("ancient-forgery", "fake_fletching_table"), new BlockItem(FAKE_FLETCHING_TABLE, new FabricItemSettings()));

		FLETCHING_TABLE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
				new Identifier("ancient-forgery", "fletching_table"),
				FabricBlockEntityTypeBuilder.create(FakeFletchingTableBlockEntity::new, FAKE_FLETCHING_TABLE).build(null));
	}
}