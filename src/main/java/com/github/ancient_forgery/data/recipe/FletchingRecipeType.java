package com.github.ancient_forgery.data.recipe;

import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.TippedArrowRecipe;

public class FletchingRecipeType implements RecipeType<FletchingRecipe> {
    // Define ExampleRecipe.Type as a singleton by making its constructor private and exposing an instance.
    private FletchingRecipeType() {}
    public static final FletchingRecipeType INSTANCE = new FletchingRecipeType();

    // This will be needed in step 4
    public static final String ID = "fletching_recipe";
}
