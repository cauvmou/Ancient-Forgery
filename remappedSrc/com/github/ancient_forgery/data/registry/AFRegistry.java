package com.github.ancient_forgery.data.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public interface AFRegistry {

    default void register() {}

    @Environment(EnvType.CLIENT)
    default void registerClient() {}
}
