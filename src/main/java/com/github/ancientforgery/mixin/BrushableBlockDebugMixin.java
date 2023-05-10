package com.github.ancientforgery.mixin;

import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrushableBlockEntity.class)
public class BrushableBlockDebugMixin extends BlockEntity {

    @Shadow
    private Identifier lootTable;
    @Shadow
    private ItemStack item;
    @Shadow
    private long lootTableSeed;
    @Shadow
    private static final Logger field_42801 = LogUtils.getLogger();

    public BrushableBlockDebugMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(at = @At("HEAD"), method = "generateItem(Lnet/minecraft/entity/player/PlayerEntity;)V")
    private void generateItem(PlayerEntity player, CallbackInfo info) {
        player.sendMessage(Text.literal("Updating display item of brush block"));
        player.sendMessage(Text.literal(String.format("ITEM: %s", this.item.getName())));
        if (this.lootTable != null && world != null && !world.isClient() && world.getServer() != null) {
            LootTable lootTable = world.getServer().getLootManager().getLootTable(this.lootTable);
            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
                Criteria.PLAYER_GENERATES_CONTAINER_LOOT.trigger(serverPlayerEntity, this.lootTable);
            }

            LootContext.Builder builder = (new LootContext.Builder((ServerWorld)world)).parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos)).random(lootTableSeed).luck(player.getLuck()).parameter(LootContextParameters.THIS_ENTITY, player);
            player.sendMessage(Text.literal("Position: " + pos.toShortString()));
            ObjectArrayList<ItemStack> objectArrayList = lootTable.generateLoot(builder.build(LootContextTypes.CHEST));
            ItemStack var10001;
            switch (objectArrayList.size()) {
                case 0:
                    var10001 = ItemStack.EMPTY;
                    break;
                case 1:
                    var10001 = (ItemStack)objectArrayList.get(0);
                    break;
                default:
                    Identifier var10002 = this.lootTable;
                    field_42801.warn("Expected max 1 loot from loot table " + var10002 + " got " + objectArrayList.size());
                    var10001 = (ItemStack)objectArrayList.get(0);
            }

            item = var10001;
            lootTable = null;
            markDirty();
        }
    }
}
