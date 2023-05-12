package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.entity.custom.DecoyPuppetEntity;
import com.github.ancient_forgery.data.entity.renderer.DecoyPuppetRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GeoEntityRendererRegistry implements AFRegistry {

    @Override
    public void registerClient() {
        EntityRendererRegistry.register(EntityRegistry.DECOY_PUPPET_ENTITY, DecoyPuppetRenderer::new);
    }

}
