package com.github.ancient_forgery.data.registry;


import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelPredicateProvider {
    public static void registerModModels(){
        registerBow(ItemRegistry.LONGBOW);
    }

    private static void registerBow(Item bow){
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                ((stack, world, entity, seed) -> {
                    if(entity == null){
                        return 0.0f;
                    }
                    if(entity.getActiveItem() != stack){
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)(stack.getMaxUseTime()/3600);
                }));

        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pulling"),
                ((stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ?
                        1.0f : 0.0f));
    }
}
