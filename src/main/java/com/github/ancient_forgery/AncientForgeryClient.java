package com.github.ancient_forgery;

import com.github.ancient_forgery.data.registry.*;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AncientForgeryClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("ancient_frogery");
    public static final Registry[] REGISTRIES = new Registry[]{
            new BlockEntityRegistry(),
            new BlockEntityRendererRegistry()
    };

    @Override
    public void onInitializeClient() {
        for (Registry r : REGISTRIES) {
            r.registerClient();
        }
    }
}
