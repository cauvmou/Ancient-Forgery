package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.block.entity.SuspiciousSoulSandEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntityRegistry implements AFRegistry {

    public static final BlockEntityType<SuspiciousSoulSandEntity> SUSPICIOUS_SOUL_SAND_ENTITY = FabricBlockEntityTypeBuilder.create(SuspiciousSoulSandEntity::new, BlockRegistry.SUSPICIOUS_SOUL_SAND).build();

    @Override
    public void register() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier("ancient_forgery", "suspicious_soul_sand"),
                SUSPICIOUS_SOUL_SAND_ENTITY);
    }


}
