package com.github.ancient_forgery.data.entity.renderer;


import com.github.ancient_forgery.data.entity.custom.LostSoulEntity;
import com.github.ancient_forgery.data.entity.model.DecoyPuppetEntityModel;
import com.github.ancient_forgery.data.entity.model.LostSoulEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class LostSoulEntityRenderer extends EntityRenderer<LostSoulEntity> {

    private final float scale = 1.0f;

    public LostSoulEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }
    
    @Override
    public void render(LostSoulEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if ((entity).age < 2 && this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(entity) < 12.25) {
            return;
        }
        matrices.push();
        matrices.scale(this.scale, this.scale, this.scale);
        matrices.translate(0.0f, 0.5f, 0.0f);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(this.getTexture(entity)));
        this.vertex(matrix4f, matrix3f, vertexConsumer, -.5f, -.5f, 0, 0.0f, 0.0f, 0, -1, 0, 15);
        this.vertex(matrix4f, matrix3f, vertexConsumer, .5f, -.5f, 0, 1.0f, 0.0f, 0, -1, 0, 15);
        this.vertex(matrix4f, matrix3f, vertexConsumer, .5f, .5f, 0, 1.0f, 1.0f, 0, -1, 0, 15);
        this.vertex(matrix4f, matrix3f, vertexConsumer, -.5f, .5f, 0, 0.0f, 1.0f, 0, -1, 0, 15);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);

    }
    
    @Override
    public Identifier getTexture(LostSoulEntity entity) {
        return new Identifier(MOD_ID, "textures/entity/lost_soul/lost_soul_normal.png");
    }

    public void vertex(Matrix4f positionMatrix, Matrix3f normalMatrix, VertexConsumer vertexConsumer, float x, float y, float z, float u, float v, float normalX, float normalZ, float normalY, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, z).color(255, 255, 255, 255).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, normalX, normalY, normalZ).next();
    }
}
