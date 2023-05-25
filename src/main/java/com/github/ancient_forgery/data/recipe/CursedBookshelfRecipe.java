package com.github.ancient_forgery.data.recipe;

import com.github.ancient_forgery.data.registry.BlockRegistry;
import com.github.ancient_forgery.data.registry.RecipeSerializerRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CursedBookshelfRecipe extends SpecialCraftingRecipe {
    public CursedBookshelfRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    // Every slot is checked for a match, I think this is used for the recipe book, so it is best to check
    // from top-left to bottom-right
    @Override
    public boolean matches(CraftingInventory inventory, World world) {

        // This is not to gain negative performance.
        for (int i = 0; i < 3; i++)
            if (inventory.getStack(i).isOf(Items.POLISHED_BLACKSTONE)) return true;

        for (int i = 3; i < 6; i++) {
            var itemStack = inventory.getStack(i);
            var isCursed = EnchantmentHelper.get(itemStack).keySet().stream().filter(Enchantment::isCursed).toList().size() > 0;
            if (itemStack.isOf(Items.ENCHANTED_BOOK) && isCursed) return true;
        }

        for (int i = 6; i < 9; i++)
            if (inventory.getStack(i).isOf(Items.POLISHED_BLACKSTONE)) return true;

        return false;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory, DynamicRegistryManager registryManager) {
        for (int i = 0; i < 3; i++) {
            if (!inventory.getStack(i).isOf(Items.POLISHED_BLACKSTONE) || !inventory.getStack(i+6).isOf(Items.POLISHED_BLACKSTONE)) return ItemStack.EMPTY;
        }

        for (int i = 3; i < 6; i++) {
            var itemStack = inventory.getStack(i);
            var isCursed = EnchantmentHelper.get(itemStack).keySet().stream().filter(Enchantment::isCursed).toList().size() > 0;
            if (!itemStack.isOf(Items.ENCHANTED_BOOK) || !isCursed) return ItemStack.EMPTY;
        }

        return new ItemStack(BlockRegistry.CURSED_BOOKSHELF);
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializerRegistry.CURSED_BOOKSHELF;
    }
}
