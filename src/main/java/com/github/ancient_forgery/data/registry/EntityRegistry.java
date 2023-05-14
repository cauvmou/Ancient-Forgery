package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.entity.custom.DecoyPuppetEntity;
import com.github.ancient_forgery.data.entity.custom.LostSoulEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.object.builder.FabricEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class EntityRegistry implements AFRegistry {

    public static final EntityType<DecoyPuppetEntity> DECOY_PUPPET_ENTITY = FabricEntityTypeBuilder.create(
            SpawnGroup.CREATURE, DecoyPuppetEntity::new)
            .dimensions(EntityDimensions.fixed(1.0f, 1.75f))
            .build();
    public static final EntityType<LostSoulEntity> LOST_SOUL_ENTITY = FabricEntityTypeBuilder.create(
                    SpawnGroup.CREATURE, LostSoulEntity::new)
            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
            .build();

    @Override
    public void register() {
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "decoy_puppet"), DECOY_PUPPET_ENTITY);
        FabricDefaultAttributeRegistry.register(DECOY_PUPPET_ENTITY, DecoyPuppetEntity.setAttributes());
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "lost_soul"), LOST_SOUL_ENTITY);
        FabricDefaultAttributeRegistry.register(LOST_SOUL_ENTITY, LostSoulEntity.setAttributes());
    }
}
