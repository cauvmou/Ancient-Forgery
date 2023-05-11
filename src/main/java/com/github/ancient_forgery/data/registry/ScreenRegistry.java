package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.screen.FletchingScreen;
import com.github.ancient_forgery.data.screen.FletchingScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ScreenRegistry implements com.github.ancient_forgery.data.registry.Registry {
    public static final ScreenHandlerType<FletchingScreenHandler> FLETCHING_SCREEN_HANDLER = new ScreenHandlerType<>(
            FletchingScreenHandler::new,
            FeatureFlags.VANILLA_FEATURES);

    @Override
    public void register() {
        HandledScreens.register(FLETCHING_SCREEN_HANDLER,
                FletchingScreen::new);
        net.minecraft.registry.Registry.register(Registries.SCREEN_HANDLER,
                new Identifier("ancient_forgery", "fake_fletching_table"),
                FLETCHING_SCREEN_HANDLER);
    }
}
