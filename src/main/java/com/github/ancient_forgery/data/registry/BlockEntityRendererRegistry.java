package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.render.block.entity.SuspiciousSoulSandEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import com.github.ancient_forgery.data.registry.BlockEntityRegistry;

public class BlockEntityRendererRegistry implements Registry {

    @Override
    public void registerClient() {
        BlockEntityRendererFactories.register(BlockEntityRegistry.SUSPICIOUS_SOUL_SAND_ENTITY, SuspiciousSoulSandEntityRenderer::new);
    }
}
