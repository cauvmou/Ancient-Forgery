package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.entity.renderer.DecoyPuppetRenderer;
import com.github.ancient_forgery.data.entity.renderer.LostSoulEntityRenderer;

public class EntityRendererRegistry implements AFRegistry {

    @Override
    public void registerClient() {
        net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(EntityRegistry.DECOY_PUPPET_ENTITY, DecoyPuppetRenderer::new);
        net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(EntityRegistry.LOST_SOUL_ENTITY, LostSoulEntityRenderer::new);
    }

}
