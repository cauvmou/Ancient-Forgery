package com.github.ancientforgery;

import com.github.ancientforgery.render.block.entity.SuspiciousSoulSandEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BrushableBlockEntityRenderer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.ancientforgery.registry.BlockRegistry.SUSPICIOUS_SOUL_SAND_ENTITY;


public class AncientForgeryClient implements ClientModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("ancient-forgery");

	@Override
	public void onInitializeClient() {
		LOGGER.info("CLIENT ONLY!");
		BlockEntityRendererRegistry.register(SUSPICIOUS_SOUL_SAND_ENTITY, SuspiciousSoulSandEntityRenderer::new);
	}
}