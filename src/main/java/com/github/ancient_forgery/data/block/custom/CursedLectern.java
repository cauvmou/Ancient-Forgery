package com.github.ancient_forgery.data.block.custom;

import com.github.ancient_forgery.data.block.entity.CursedLecternEntity;
import com.github.ancient_forgery.data.registry.BlockEntityRegistry;
import com.github.ancient_forgery.data.registry.BlockRegistry;
import com.github.ancient_forgery.data.registry.ParticleRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CursedLectern extends LecternBlock {


    public CursedLectern(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CursedLecternEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? CursedLectern.checkType(type, BlockEntityRegistry.CURSED_LECTERN_ENTITY, CursedLecternEntity::tick) : null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(HAS_BOOK).booleanValue()) {
            /*if (!world.isClient) {
                this.openScreen(world, pos, player);
            }*/
            return ActionResult.success(world.isClient);
        }
        ItemStack itemStack = player.getStackInHand(hand);
        var isCursed = EnchantmentHelper.get(itemStack).keySet().stream().filter(Enchantment::isCursed).toList().size() > 0;
        if (itemStack.isEmpty() || (itemStack.isOf(Items.ENCHANTED_BOOK) && isCursed)) {
            return ActionResult.PASS;
        }
        return ActionResult.CONSUME;
    }
}
