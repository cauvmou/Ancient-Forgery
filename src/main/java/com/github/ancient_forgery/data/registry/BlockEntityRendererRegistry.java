package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.render.block.entity.AFBrushableEntityRenderer;
import com.github.ancient_forgery.data.render.block.entity.CursedLecternEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class BlockEntityRendererRegistry implements AFRegistry {

    @Override
    public void registerClient() {
        BlockEntityRendererFactories.register(BlockEntityRegistry.SUSPICIOUS_SOUL_SAND_ENTITY, AFBrushableEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockEntityRegistry.CURSED_LECTERN_ENTITY, CursedLecternEntityRenderer::new);
    }
}
