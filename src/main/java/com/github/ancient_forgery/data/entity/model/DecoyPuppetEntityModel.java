package com.github.ancient_forgery.data.entity.model;

import com.github.ancient_forgery.data.entity.custom.DecoyPuppetEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class DecoyPuppetEntityModel extends GeoModel<DecoyPuppetEntity> {

    @Override
    public Identifier getModelResource(DecoyPuppetEntity animatable) {
        return new Identifier(MOD_ID, "geo/decoy_puppet.geo.json");
    }

    @Override
    public Identifier getTextureResource(DecoyPuppetEntity animatable) {
        return new Identifier(MOD_ID, "textures/entity/decoy_puppet/decoy_puppet.png");
    }

    @Override
    public Identifier getAnimationResource(DecoyPuppetEntity animatable) {
        return new Identifier(MOD_ID, "animations/decoy_puppet.animation.json");
    }
}
