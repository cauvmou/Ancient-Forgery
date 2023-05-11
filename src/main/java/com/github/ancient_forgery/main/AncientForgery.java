package com.github.ancient_forgery.main;

import com.github.ancient_forgery.data.registry.BlockEntityRegistry;
import com.github.ancient_forgery.data.registry.BlockRegistry;
import com.github.ancient_forgery.data.registry.ItemRegistry;
import com.github.ancient_forgery.data.registry.Registry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.github.ancient_forgery.data.registry.BlockEntityRegistry.SUSPICIOUS_SOUL_SAND_ENTITY;

public class AncientForgery implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ancient_frogery");
    public static final Registry[] REGISTRIES = new Registry[]{
            new BlockRegistry(),
            new BlockEntityRegistry(),
            new ItemRegistry(),
    };

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        for (Registry r : REGISTRIES) {
            r.register();
        }
    }
}