package com.github.ancient_forgery;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AncientForgery implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ancient_frogery");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");
    }
}