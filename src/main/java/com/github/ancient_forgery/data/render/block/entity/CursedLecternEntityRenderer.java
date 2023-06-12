package com.github.ancient_forgery.data.render.block.entity;

import com.github.ancient_forgery.data.block.entity.CursedLecternEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.BookModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class CursedLecternEntityRenderer implements BlockEntityRenderer<CursedLecternEntity> {
    public static final SpriteIdentifier BOOK_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("entity/enchanting_table_book"));
    private final BookModel book;

    public CursedLecternEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.book = new BookModel(ctx.getLayerModelPart(EntityModelLayers.BOOK));
    }

    @Override
    public void render(CursedLecternEntity blockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        BlockState blockState = blockEntity.getCachedState();
        if (!blockState.get(LecternBlock.HAS_BOOK).booleanValue()) {
            return;
        }
        matrixStack.push();
        matrixStack.translate(0.5f, 1.0f, 0.5f);
        float g = (float)blockEntity.ticks + f;
        matrixStack.translate(0.0f, 0.1f + MathHelper.sin(g * 0.1f) * 0.01f, 0.0f);
        float r = blockEntity.facing.rotateYClockwise().asRotation();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-r));
        matrixStack.translate(0.1f, 0.0f, 0.0f);
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(65f));
        float l = MathHelper.lerp(f, blockEntity.pageAngle, blockEntity.nextPageAngle);
        float m = MathHelper.fractionalPart(l + 0.25f) * 1.6f - 0.3f;
        float n = MathHelper.fractionalPart(l + 0.75f) * 1.6f - 0.3f;
        float o = MathHelper.lerp(f, blockEntity.pageTurningSpeed, blockEntity.nextPageTurningSpeed);
        this.book.setPageAngles(g, MathHelper.clamp(m, 0.0f, 1.0f), MathHelper.clamp(n, 0.0f, 1.0f), o);
        VertexConsumer vertexConsumer = BOOK_TEXTURE.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntitySolid);
        this.book.renderBook(matrixStack, vertexConsumer, i, j, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.pop();
    }
}
