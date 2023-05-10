package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.block.entity.FakeFletchingTableBlockEntity;
import com.github.ancient_forgery.data.block.entity.SuspiciousSoulSandEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntityRegistry {

    public static final BlockEntityType<SuspiciousSoulSandEntity> SUSPICIOUS_SOUL_SAND_ENTITY;
    public static final BlockEntityType<FakeFletchingTableBlockEntity> FLETCHING_TABLE;

    static {
        SUSPICIOUS_SOUL_SAND_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("ancient-forgery", "suspicious_soul_sand"), FabricBlockEntityTypeBuilder.create(SuspiciousSoulSandEntity::new, BlockRegistry.SUSPICIOUS_SOUL_SAND).build());
        FLETCHING_TABLE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("ancient-forgery", "fletching_table"), FabricBlockEntityTypeBuilder.create(FakeFletchingTableBlockEntity::new, BlockRegistry.FAKE_FLETCHING_TABLE).build(null));
    }

}
