package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.block.entity.CursedLecternEntity;
import com.github.ancient_forgery.data.block.entity.SuspiciousSoulSandEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class BlockEntityRegistry implements AFRegistry {

    public static final BlockEntityType<SuspiciousSoulSandEntity> SUSPICIOUS_SOUL_SAND_ENTITY = FabricBlockEntityTypeBuilder.create(SuspiciousSoulSandEntity::new, BlockRegistry.SUSPICIOUS_SOUL_SAND).build();
    public static final BlockEntityType<CursedLecternEntity> CURSED_LECTERN_ENTITY = FabricBlockEntityTypeBuilder.create(CursedLecternEntity::new, BlockRegistry.CURSED_LECTERN).build();

    @Override
    public void register() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MOD_ID, "suspicious_soul_sand"),
                SUSPICIOUS_SOUL_SAND_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MOD_ID, "cursed_lectern"),
                CURSED_LECTERN_ENTITY);
    }


}
