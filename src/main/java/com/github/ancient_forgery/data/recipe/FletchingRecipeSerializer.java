package com.github.ancient_forgery.data.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;

import java.rmi.registry.Registry;

import static com.github.ancient_forgery.main.AncientForgery.LOGGER;
import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class FletchingRecipeSerializer implements RecipeSerializer<FletchingRecipe> {
    private FletchingRecipeSerializer() {
    }

    public static final FletchingRecipeSerializer INSTANCE = new FletchingRecipeSerializer();

    // This will be the "type" field in the json
    //public static final Identifier ID = new Identifier(MOD_ID, "fletching_recipe");
    public static final String ID = "fletching_recipe";

    @Override
    public FletchingRecipe read(Identifier id, JsonObject json) {
        ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));

        JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
        DefaultedList<Ingredient> inputs = DefaultedList.ofSize(3, Ingredient.EMPTY);

        for (int i = 0; i < inputs.size(); i++) {
            inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
        }

        return new FletchingRecipe(inputs, output, id);
    }

    @Override
    public FletchingRecipe read(Identifier id, PacketByteBuf buf) {
        DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

        for (int i = 0; i < inputs.size(); i++) {
            inputs.set(i, Ingredient.fromPacket(buf));
        }

        ItemStack output = buf.readItemStack();
        return new FletchingRecipe(inputs, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, FletchingRecipe recipe) {
        buf.writeInt(recipe.getIngredients().size());
        for (Ingredient ing : recipe.getIngredients()) {
            ing.write(buf);
        }
        //buf.writeItemStack(recipe.getOutput(null));
        buf.writeItemStack(recipe.output);
    }
}
