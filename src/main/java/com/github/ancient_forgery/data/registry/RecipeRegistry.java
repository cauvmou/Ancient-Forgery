package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.recipe.FletchingRecipe;
import com.github.ancient_forgery.data.recipe.FletchingRecipeSerializer;
import net.minecraft.block.CandleBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.util.Identifier;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class RecipeRegistry implements AFRegistry {
    @Override
    public void register() {
        Registry.register(Registries.RECIPE_SERIALIZER, FletchingRecipeSerializer.ID,
                FletchingRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(MOD_ID, FletchingRecipe.Type.ID),
                FletchingRecipe.Type.INSTANCE);
    }
}
