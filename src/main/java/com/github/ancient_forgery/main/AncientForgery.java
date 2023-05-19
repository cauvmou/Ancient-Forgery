package com.github.ancient_forgery.main;

import com.github.ancient_forgery.data.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.CandleBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AncientForgery implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ancient_forgery");
    public static final String MOD_ID = "ancient_forgery";
    public static final AFRegistry[] REGISTRIES = new AFRegistry[]{
            new BlockRegistry(),
            new BlockEntityRegistry(),
            new ItemRegistry(),
            new ScreenRegistry(),
            new EntityRegistry(),
            new ItemGroupRegistry(),
            new EventRegistry(),
            new RecipeSerializerRegistry(),
    };

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        for (AFRegistry r : REGISTRIES) {
            r.register();
        }

    }
}