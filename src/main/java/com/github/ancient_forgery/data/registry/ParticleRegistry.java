package com.github.ancient_forgery.data.registry;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EnchantGlyphParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class ParticleRegistry implements AFRegistry {

    public static DefaultParticleType CURSE = FabricParticleTypes.simple(true);

    @Override
    public void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "curse"), CURSE);
    }

    @Override
    public void registerClient() {
        ParticleFactoryRegistry.getInstance().register(CURSE, EnchantGlyphParticle.EnchantFactory::new);
    }
}
