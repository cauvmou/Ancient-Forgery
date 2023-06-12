package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.screen.FletchingScreen;
import com.github.ancient_forgery.data.screen.FletchingScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class ScreenRegistry implements AFRegistry {
    public static final ScreenHandlerType<FletchingScreenHandler> FLETCHING_SCREEN_HANDLER = new ScreenHandlerType<>(
            (syncId, playerInventory) -> new FletchingScreenHandler(syncId, playerInventory,
                    ScreenHandlerContext.create(playerInventory.player.getWorld(), playerInventory.player.getBlockPos())),
            FeatureFlags.DEFAULT_ENABLED_FEATURES);

    @Override
    public void register() {
        net.minecraft.registry.Registry.register(Registries.SCREEN_HANDLER,
                new Identifier(MOD_ID, "fletching_table"),
                FLETCHING_SCREEN_HANDLER);
    }

    @Override
    public void registerClient() {
        HandledScreens.register(FLETCHING_SCREEN_HANDLER,
                FletchingScreen::new);
    }
}
