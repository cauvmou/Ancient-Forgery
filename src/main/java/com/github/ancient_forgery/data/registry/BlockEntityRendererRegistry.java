package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.render.block.entity.SuspiciousSoulSandEntityRenderer;
import com.github.ancient_forgery.data.screen.FletchingScreen;
import com.github.ancient_forgery.data.screen.FletchingScreenHandler;
import com.github.ancient_forgery.main.AncientForgery;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import com.github.ancient_forgery.data.registry.BlockEntityRegistry;

public class BlockEntityRendererRegistry implements Registry {

    @Override
    public void registerClient() {
        BlockEntityRendererFactories.register(BlockEntityRegistry.SUSPICIOUS_SOUL_SAND_ENTITY,
                SuspiciousSoulSandEntityRenderer::new);
    }
}
