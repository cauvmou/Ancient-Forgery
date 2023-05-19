package com.github.ancient_forgery.data.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class FletchingRecipe implements Recipe<SimpleInventory> {
    private final Ingredient inputTip;
    private final Ingredient inputShaft;
    private final Ingredient inputFletching;
    private final ItemStack outputStack;
    private final Identifier id;

    public FletchingRecipe(Ingredient inputTip, Ingredient inputShaft, Ingredient inputFletching, ItemStack outputStack, Identifier id) {
        this.inputTip = inputTip;
        this.inputShaft = inputShaft;
        this.inputFletching = inputFletching;
        this.outputStack = outputStack;
        this.id = id;
    }

    public static class Type implements RecipeType<FletchingRecipe> {
        // Define ExampleRecipe.Type as a singleton by making its constructor private and exposing an instance.
        private Type() {}
        public static final Type INSTANCE = new Type();

        // This will be needed in step 4
        public static final String ID = "fletching_recipe";
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public Ingredient getInputTip() {
        return inputTip;
    }

    public Ingredient getInputShaft() {
        return inputShaft;
    }

    public Ingredient getInputFletching() {
        return inputFletching;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (inventory.size() < 3) return false;
        if(world.isClient()) return false;

        return inputTip.test(inventory.getStack(0)) &&
                inputShaft.test(inventory.getStack(1)) &&
                inputFletching.test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return outputStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return outputStack.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return FletchingRecipeSerializer.INSTANCE;
    }
}
