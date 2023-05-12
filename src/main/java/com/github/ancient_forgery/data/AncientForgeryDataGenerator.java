package com.github.ancient_forgery.data;

import com.github.ancient_forgery.data.registry.BlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class AncientForgeryDataGenerator implements DataGeneratorEntrypoint {

	private static class BlockTagGenerator extends FabricTagProvider<Block> {
		public BlockTagGenerator(FabricDataOutput output, RegistryKey<? extends Registry<Block>> registryKey, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registryKey, registriesFuture);
		}

		private static final TagKey<Block> SOUL_FIRE_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "soul_fire_base_blocks"));
		private static final TagKey<Block> CANDLES = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "candles"));

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(SOUL_FIRE_BASE_BLOCKS)
					.add(BlockRegistry.SUSPICIOUS_SOUL_SAND);
			getOrCreateTagBuilder(CANDLES)
					.add(BlockRegistry.CANDELABRA)
					.add(BlockRegistry.WHITE_CANDELABRA)
					.add(BlockRegistry.LIGHT_GRAY_CANDELABRA)
					.add(BlockRegistry.GRAY_CANDELABRA)
					.add(BlockRegistry.BLACK_CANDELABRA)
					.add(BlockRegistry.BROWN_CANDELABRA)
					.add(BlockRegistry.RED_CANDELABRA)
					.add(BlockRegistry.ORANGE_CANDELABRA)
					.add(BlockRegistry.YELLOW_CANDELABRA)
					.add(BlockRegistry.LIME_CANDELABRA)
					.add(BlockRegistry.GREEN_CANDELABRA)
					.add(BlockRegistry.CYAN_CANDELABRA)
					.add(BlockRegistry.LIGHT_BLUE_CANDELABRA)
					.add(BlockRegistry.BLUE_CANDELABRA)
					.add(BlockRegistry.PURPLE_CANDELABRA)
					.add(BlockRegistry.MAGENTA_CANDELABRA)
					.add(BlockRegistry.PINK_CANDELABRA);
		}
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.createPack().addProvider((output, registriesFuture) -> new BlockTagGenerator(output, Registries.BLOCK.getKey(), registriesFuture));
	}
}
