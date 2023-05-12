package com.github.ancient_forgery.data.entity.renderer;

import com.github.ancient_forgery.data.entity.custom.DecoyPuppetEntity;
import com.github.ancient_forgery.data.entity.model.DecoyPuppetEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DecoyPuppetRenderer extends GeoEntityRenderer<DecoyPuppetEntity> {
    public DecoyPuppetRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new DecoyPuppetEntityModel());
    }


}
