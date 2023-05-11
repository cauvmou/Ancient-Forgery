package com.github.ancient_forgery.impl.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AncientForgery implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("ancient_forgery");

    @Override
    public void onInitializeClient() {

    }
}
