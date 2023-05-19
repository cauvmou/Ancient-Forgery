package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.recipe.FletchingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class RecipeTypeRegistry implements AFRegistry {
    @Override
    public void register() {
        Registry.register(Registries.RECIPE_TYPE, new Identifier(MOD_ID, FletchingRecipe.Type.ID),
                FletchingRecipe.Type.INSTANCE);
    }
}
