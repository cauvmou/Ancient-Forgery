package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.render.block.entity.AFBrushableEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class BlockEntityRendererRegistry implements Registry {

    @Override
    public void registerClient() {
        BlockEntityRendererFactories.register(BlockEntityRegistry.SUSPICIOUS_SOUL_SAND_ENTITY, AFBrushableEntityRenderer::new);
    }
}
