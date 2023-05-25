package com.github.ancient_forgery.data.screen;

import com.github.ancient_forgery.data.recipe.FletchingRecipe;
import com.github.ancient_forgery.data.recipe.FletchingRecipeType;
import com.github.ancient_forgery.data.registry.ScreenRegistry;
import com.github.ancient_forgery.data.screen.slot.ModResultSlot;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FletchingScreenHandler extends ScreenHandler {
    private final World world;
    private final Inventory inventory;
    protected final CraftingResultInventory output = new CraftingResultInventory();
    protected final ScreenHandlerContext context;

    public FletchingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ScreenRegistry.FLETCHING_SCREEN_HANDLER, syncId);

        this.inventory = createInputInventory();
        this.inventory.onOpen(playerInventory.player);
        this.world = playerInventory.player.getWorld();
        this.context = context;

        //Our inventory
        this.addSlot(new Slot(this.inventory, 0, 10, 15));
        this.addSlot(new Slot(this.inventory, 1, 10, 35));
        this.addSlot(new Slot(this.inventory, 2, 10, 55));
        this.addSlot(new FletchingResultSlot(output, 3, 65, 35));

        //The player inventory
        for (int m = 0; m < 3; m++) {
            for (int l = 0; l < 9; l++) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        //The player Hotbar
        for (int m = 0; m < 9; m++) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

        Optional<FletchingRecipe> match = world.getRecipeManager()
                .getFirstMatch(FletchingRecipeType.INSTANCE, (SimpleInventory) inventory, world);
        playerInventory.player.sendMessage(Text.of(match.toString()));
    }

    private class FletchingResultSlot extends Slot {
        public FletchingResultSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return false;
        }

        @Override
        public void onTakeItem(PlayerEntity player, ItemStack stack) {
            FletchingScreenHandler.this.onTakeOutput(player, stack);
        }

        @Override
        public void onQuickTransfer(ItemStack newItem, ItemStack original) {
            super.onQuickTransfer(newItem, original);
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public void updateResult() {
        List<FletchingRecipe> list = this.world.getRecipeManager().getAllMatches(FletchingRecipeType.INSTANCE, (SimpleInventory) this.inventory, this.world);
        if (list.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
        } else {
            FletchingRecipe fletchingRecipe = list.get(0);
            ItemStack itemStack = fletchingRecipe.craft((SimpleInventory) this.inventory, this.world.getRegistryManager());
            if (itemStack.isItemEnabled(this.world.getEnabledFeatures())) {
                this.output.setLastRecipe(fletchingRecipe);
                this.output.setStack(0, itemStack);
            }
        }
    }

    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.inventory) {
            this.updateResult();
        }
    }

    @Override
    protected void dropInventory(PlayerEntity player, Inventory inventory) {
        super.dropInventory(player, inventory);
    }

    private void decrementStack(int slot) {
        ItemStack itemStack = this.inventory.getStack(slot);
        if (!itemStack.isEmpty()) {
            itemStack.decrement(1);
            this.inventory.setStack(slot, itemStack);
        }
    }

    public void onTakeOutput(PlayerEntity player, ItemStack stack) {
        stack.onCraft(player.getWorld(), player, stack.getCount());
        this.output.unlockLastRecipe(player, this.getInputStacks());
        this.decrementStack(0);
        this.decrementStack(1);
        this.decrementStack(2);
        // TODO: make fletching world event
        //this.context.run((world, pos) -> world.syncWorldEvent(WorldEvents.SMITHING_TABLE_USED, pos, 0));
    }

    private List<ItemStack> getInputStacks() {
        return List.of(this.inventory.getStack(0), this.inventory.getStack(1), this.inventory.getStack(2));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    private SimpleInventory createInputInventory() {
        return new SimpleInventory(3){
            @Override
            public void markDirty() {
                super.markDirty();
                FletchingScreenHandler.this.onContentChanged(this);
            }

            // TODO: get this to work
            @Override
            public void onClose(PlayerEntity player) {
                super.onClose(player);
                FletchingScreenHandler.this.context.run((world, pos) ->
                        FletchingScreenHandler.this.dropInventory(player, FletchingScreenHandler.this.inventory));
            }
        };
    }
}
