package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.entity.model.LostSoulEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class EntityModelLayerRegistry implements AFRegistry {

    private static final String MODDED = "af";

    public static final EntityModelLayer LOST_SOUL = new EntityModelLayer(new Identifier(MOD_ID, "lost_soul"), MODDED);

    @Override
    public void registerClient() {
        net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer(LOST_SOUL, LostSoulEntityModel::getTexturedModelData);
    }

}
