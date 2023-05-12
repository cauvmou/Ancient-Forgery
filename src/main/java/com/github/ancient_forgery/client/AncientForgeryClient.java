package com.github.ancient_forgery.client;

import com.github.ancient_forgery.data.registry.*;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AncientForgeryClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("ancient_forgery");
    public static final AFRegistry[] REGISTRIES = new AFRegistry[]{
            new BlockEntityRegistry(),
            new BlockEntityRendererRegistry()
    };

    @Override
    public void onInitializeClient() {
        for (AFRegistry r : REGISTRIES) {
            r.registerClient();
        }
        ModModelPredicateProvider.registerModModels();
    }
}
