package com.github.ancientforgery.mixin.client;

import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BrushableBlockEntityRenderer;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrushableBlockEntityRenderer.class)
public class BrushableBlockEntityRendererMixin {

    @Shadow
    private ItemRenderer itemRenderer;

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/render/block/entity/BrushableBlockEntityRenderer;render(Lnet/minecraft/block/entity/BrushableBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V")
    public void render(BrushableBlockEntity brushableBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci) {
        if (brushableBlockEntity.getWorld() != null) {
            int k = (Integer)brushableBlockEntity.getCachedState().get(Properties.DUSTED);
            if (k > 0) {
                brushableBlockEntity.getWorld().getPlayers().forEach(
                        playerEntity -> playerEntity.sendMessage(Text.literal("BRUSHING: " + k))
                );
                Direction direction = brushableBlockEntity.getHitDirection();
                if (direction != null) {
                    ItemStack itemStack = brushableBlockEntity.getItem();
                    if (!itemStack.isEmpty()) {
                        brushableBlockEntity.getWorld().getPlayers().forEach(
                                playerEntity -> playerEntity.sendMessage(Text.literal("WE ARE RENDERING!!!!!"))
                        );
                        matrixStack.push();
                        matrixStack.translate(0.0F, 0.5F, 0.0F);
                        float[] fs = this.getTranslation(direction, k);
                        matrixStack.translate(fs[0], fs[1], fs[2]);
                        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(75.0F));
                        boolean bl = direction == Direction.EAST || direction == Direction.WEST;
                        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)((bl ? 90 : 0) + 11)));
                        matrixStack.scale(0.5F, 0.5F, 0.5F);
                        int l = WorldRenderer.getLightmapCoordinates(brushableBlockEntity.getWorld(), brushableBlockEntity.getCachedState(), brushableBlockEntity.getPos().offset(direction));
                        this.itemRenderer.renderItem(itemStack, ModelTransformationMode.FIXED, l, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, brushableBlockEntity.getWorld(), 0);
                        matrixStack.pop();
                    }
                }
            }
        }
        return;
    }

    @Shadow
    private float[] getTranslation(Direction direction, int k) {
        return new float[0];
    }
}
