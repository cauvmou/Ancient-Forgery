package com.github.ancient_forgery.data.entity.renderer;


import com.github.ancient_forgery.data.entity.custom.LostSoulEntity;
import com.github.ancient_forgery.data.entity.model.DecoyPuppetEntityModel;
import com.github.ancient_forgery.data.entity.model.LostSoulEntityModel;
import com.github.ancient_forgery.data.registry.EntityModelLayerRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.SpriteAtlasManager;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

@Environment(EnvType.CLIENT)
public class LostSoulEntityRenderer extends MobEntityRenderer<LostSoulEntity, LostSoulEntityModel> {

    private final float scale = 1.0f;

    public LostSoulEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new LostSoulEntityModel(ctx.getPart(EntityModelLayerRegistry.LOST_SOUL)), 0.0f);
    }
    
    @Override
    public Identifier getTexture(LostSoulEntity entity) {
        return new Identifier(MOD_ID, "textures/entity/lost_soul/lost_soul.png");
    }

    @Override
    protected int getBlockLight(LostSoulEntity entity, BlockPos blockPos) {
        return 15;
    }
}
