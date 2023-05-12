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

	private static class SoulFireTagGenerator extends FabricTagProvider<Block> {
		public SoulFireTagGenerator(FabricDataOutput output, RegistryKey<? extends Registry<Block>> registryKey, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registryKey, registriesFuture);
		}

		private static final TagKey<Block> SOUL_FIRE_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "soul_fire_base_blocks"));

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(SOUL_FIRE_BASE_BLOCKS)
					.add(BlockRegistry.SUSPICIOUS_SOUL_SAND);
		}
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.createPack().addProvider((output, registriesFuture) -> new SoulFireTagGenerator(output, Registries.BLOCK.getKey(), registriesFuture));
	}
}
