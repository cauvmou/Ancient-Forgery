package com.github.ancient_forgery.data.recipe;

import net.minecraft.block.CraftingTableBlock;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import static com.github.ancient_forgery.main.AncientForgery.LOGGER;

public class FletchingRecipe implements Recipe<SimpleInventory> {
    private final DefaultedList<Ingredient> recipeItems;
    public final ItemStack output;
    private final Identifier id;

    public FletchingRecipe(DefaultedList<Ingredient> recipeItems, ItemStack output, Identifier id) {
        this.recipeItems = recipeItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public RecipeType<?> getType() {
        return FletchingRecipeType.INSTANCE;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        return recipeItems.get(0).test(inventory.getStack(0)) &&
                recipeItems.get(1).test(inventory.getStack(1)) &&
                recipeItems.get(2).test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
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
