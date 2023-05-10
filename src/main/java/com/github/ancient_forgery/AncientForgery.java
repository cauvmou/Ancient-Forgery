package com.github.ancient_forgery;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.github.ancient_forgery.data.registry.BlockEntityRegistry.SUSPICIOUS_SOUL_SAND_ENTITY;
import com.github.ancient_forgery.render.block.entity.SuspiciousSoulSandEntityRenderer;

public class AncientForgery implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ancient_frogery");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        BlockEntityRendererRegistry.register(SUSPICIOUS_SOUL_SAND_ENTITY, SuspiciousSoulSandEntityRenderer::new);
    }
}