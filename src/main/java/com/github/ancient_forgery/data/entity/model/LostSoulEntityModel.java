package com.github.ancient_forgery.data.entity.model;

import com.github.ancient_forgery.data.entity.custom.DecoyPuppetEntity;
import com.github.ancient_forgery.data.entity.custom.LostSoulEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.ArrayList;
import java.util.HashMap;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class LostSoulEntityModel extends GeoModel<LostSoulEntity> {

    @Override
    public Identifier getModelResource(LostSoulEntity animatable) {
        return new Identifier(MOD_ID, "geo/lost_soul.geo.json");
    }

    @Override
    public Identifier getTextureResource(LostSoulEntity animatable) {
        return new Identifier(MOD_ID, "textures/entity/lost_soul/lost_soul.png");
    }

    @Override
    public Identifier getAnimationResource(LostSoulEntity animatable) {
        return new Identifier(MOD_ID, "animations/lost_soul.animation.json");
    }

    @Override
    public void setCustomAnimations(LostSoulEntity animatable, long instanceId, AnimationState<LostSoulEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
