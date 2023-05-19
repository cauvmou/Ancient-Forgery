package com.github.ancient_forgery.data.registry;

import com.github.ancient_forgery.data.recipe.CursedBookshelfRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.github.ancient_forgery.main.AncientForgery.MOD_ID;

public class RecipeSerializerRegistry implements AFRegistry {
    public static final RecipeSerializer<CursedBookshelfRecipe> CURSED_BOOKSHELF = new SpecialRecipeSerializer<CursedBookshelfRecipe>(CursedBookshelfRecipe::new);

    @Override
    public void register() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "crafting_special_cursedbookshelf"), CURSED_BOOKSHELF)
;    }
}
