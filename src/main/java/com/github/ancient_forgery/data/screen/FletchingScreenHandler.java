package com.github.ancient_forgery.data.screen;

import com.github.ancient_forgery.data.item.ArrowFletching;
import com.github.ancient_forgery.data.item.ArrowShaft;
import com.github.ancient_forgery.data.item.ArrowTip;
import com.github.ancient_forgery.data.item.custom.ArrowTipItem;
import com.github.ancient_forgery.data.recipe.FletchingRecipe;
import com.github.ancient_forgery.data.recipe.FletchingRecipeType;
import com.github.ancient_forgery.data.registry.ScreenRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.scanner.NbtCollector;
import net.minecraft.screen.*;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

import static com.github.ancient_forgery.data.registry.ItemRegistry.FLETCHING_ITEMS;
import static com.github.ancient_forgery.data.registry.ItemRegistry.SHAFT_ITEMS;
import static net.minecraft.item.Items.ARROW;

public class FletchingScreenHandler extends ScreenHandler {
    private final World world;
    private final Inventory inventory;
    protected final CraftingResultInventory output = new CraftingResultInventory();
    protected final ScreenHandlerContext context;
    private final Slot tipSlot;
    private final Slot shaftSlot;
    private final Slot fletchingSlot;
    private final Slot resultSlot;

    public FletchingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ScreenRegistry.FLETCHING_SCREEN_HANDLER, syncId);

        this.inventory = createInputInventory();
        this.inventory.onOpen(playerInventory.player);
        this.world = playerInventory.player.getWorld();
        this.context = context;

        //Our inventory
        this.tipSlot = this.addSlot(new Slot(this.inventory, 0, 10, 15) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof ArrowTipItem;
            }
        });
        this.shaftSlot = this.addSlot(new Slot(this.inventory, 1, 10, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return SHAFT_ITEMS.contains(stack.getItem());
            }
        });
        this.fletchingSlot = this.addSlot(new Slot(this.inventory, 2, 10, 55) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return FLETCHING_ITEMS.contains(stack.getItem());
            }
        });
        this.resultSlot = this.addSlot(new FletchingResultSlot(output, 3, 65, 35));

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
        ItemStack tipItem = this.tipSlot.getStack();
        ItemStack shaftItem = this.shaftSlot.getStack();
        ItemStack fletchingItem = this.fletchingSlot.getStack();
        ItemStack resultItem = ARROW.getDefaultStack();

        if (!tipItem.isEmpty() && !shaftItem.isEmpty() && !fletchingItem.isEmpty()) {
            ArrowTip arrowTip = ((ArrowTipItem)tipItem.getItem()).getArrowTip();
            ArrowShaft arrowShaft = ArrowShaft.fromItem(shaftItem.getItem());
            ArrowFletching arrowFletching = ArrowFletching.fromItem(fletchingItem.getItem());

            NbtCompound compound = new NbtCompound();
            compound.putInt("tip", arrowTip.getId());
            compound.putInt("shaft", arrowShaft.getId());
            compound.putInt("fletching", arrowFletching.getId());
            resultItem.setNbt(compound);
            resultItem.setCount(6);

            this.output.setStack(resultSlot.getIndex(), resultItem);
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
        this.decrementStack(tipSlot.getIndex());
        this.decrementStack(shaftSlot.getIndex());
        this.decrementStack(fletchingSlot.getIndex());
        // TODO: make fletching world event
        //this.context.run((world, pos) -> world.syncWorldEvent(WorldEvents.SMITHING_TABLE_USED, pos, 0));
    }

    private List<ItemStack> getInputStacks() {
        return List.of(tipSlot.getStack(), shaftSlot.getStack(), fletchingSlot.getStack());
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

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.inventory));
    }

    private SimpleInventory createInputInventory() {
        return new SimpleInventory(3){
            @Override
            public void markDirty() {
                super.markDirty();
                FletchingScreenHandler.this.onContentChanged(this);
            }
        };
    }
}
