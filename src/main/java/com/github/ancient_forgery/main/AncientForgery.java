package com.github.ancient_forgery.main;

import com.github.ancient_forgery.data.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.CandleBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AncientForgery implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ancient_forgery");
    public static final Registry[] REGISTRIES = new Registry[]{
            new BlockRegistry(),
            new BlockEntityRegistry(),
            new ItemRegistry(),
            new ScreenRegistry()
    };

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        for (Registry r : REGISTRIES) {
            r.register();
        }
    }
}