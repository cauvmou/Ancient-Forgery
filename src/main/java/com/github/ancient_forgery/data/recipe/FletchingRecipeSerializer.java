package com.github.ancient_forgery.data.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.rmi.registry.Registry;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class FletchingRecipeSerializer implements RecipeSerializer<FletchingRecipe> {
    private FletchingRecipeSerializer() {
    }

    public static final FletchingRecipeSerializer INSTANCE = new FletchingRecipeSerializer();

    // This will be the "type" field in the json
    public static final Identifier ID = new Identifier(MOD_ID, "fletching_recipe");

    @Override
    public FletchingRecipe read(Identifier id, JsonObject json) {
        FletchingRecipeJsonFormat recipeJson = new Gson().fromJson(json, FletchingRecipeJsonFormat.class);

        if (recipeJson.inputTip == null || recipeJson.inputShaft == null ||
                recipeJson.inputFletching == null ||recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;


        Ingredient inputTip = Ingredient.fromJson(recipeJson.inputTip);
        Ingredient inputShaft = Ingredient.fromJson(recipeJson.inputShaft);
        Ingredient inputFletching = Ingredient.fromJson(recipeJson.inputFletching);


        Item outputItem = Registries.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new FletchingRecipe(inputTip, inputShaft, inputFletching, output, id);
    }

    @Override
    public FletchingRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient inputTip = Ingredient.fromPacket(buf);
        Ingredient inputShaft = Ingredient.fromPacket(buf);
        Ingredient inputFletching = Ingredient.fromPacket(buf);

        ItemStack output = buf.readItemStack();
        return new FletchingRecipe(inputTip, inputShaft, inputFletching, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, FletchingRecipe recipe) {
        recipe.getInputTip().write(buf);
        recipe.getInputShaft().write(buf);
        recipe.getInputFletching().write(buf);

        // not sure if EMPTY is correct in this case
        buf.writeItemStack(recipe.getOutput(DynamicRegistryManager.EMPTY));
    }
}
