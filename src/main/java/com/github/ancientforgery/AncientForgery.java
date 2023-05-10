package com.github.ancientforgery;

import com.github.ancientforgery.block.custom.BonePileBlock;
import com.github.ancientforgery.block.custom.FakeFletchingTableBlock;
import com.github.ancientforgery.block.custom.SuspiciousSoulSand;
import com.github.ancientforgery.block.entity.FakeFletchingTableBlockEntity;
import com.github.ancientforgery.block.entity.SuspiciousSoulSandEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
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

		Registry.register(Registries.ITEM, new Identifier("ancient-forgery", "bone_pile_1"), new BlockItem(BONE_PILE, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("ancient-forgery", "fake_fletching_table"), new BlockItem(FAKE_FLETCHING_TABLE, new FabricItemSettings()));

		FLETCHING_TABLE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
				new Identifier("ancient-forgery", "fletching_table"),
				FabricBlockEntityTypeBuilder.create(FakeFletchingTableBlockEntity::new, FAKE_FLETCHING_TABLE).build(null));


	}
}