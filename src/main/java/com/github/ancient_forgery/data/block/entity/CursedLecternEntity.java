package com.github.ancient_forgery.data.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.github.ancient_forgery.data.registry.BlockEntityRegistry.CURSED_LECTERN_ENTITY;

public class CursedLecternEntity extends BlockEntity implements Clearable {
    public int ticks;
    public float nextPageAngle;
    public float pageAngle;
    public float flipRandom;
    public float flipTurn;
    public float nextPageTurningSpeed;
    public float pageTurningSpeed;
    public Direction facing;
    ItemStack book = ItemStack.EMPTY;
    private static final Random RANDOM = Random.create();

    public CursedLecternEntity(BlockPos pos, BlockState state) {
        super(CURSED_LECTERN_ENTITY, pos, state);
        facing = state.get(LecternBlock.FACING);
    }

    public static void tick(World world, BlockPos pos, BlockState state, CursedLecternEntity blockEntity) {
        blockEntity.facing = state.get(LecternBlock.FACING);
        blockEntity.pageTurningSpeed = blockEntity.nextPageTurningSpeed;
        //PlayerEntity playerEntity = world.getClosestPlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 3.0, false);
        blockEntity.nextPageTurningSpeed += 0.1f;
        if (blockEntity.nextPageTurningSpeed < 0.5f || RANDOM.nextInt(40) == 0) {
            float f = blockEntity.flipRandom;
            do {
                blockEntity.flipRandom += (float)(RANDOM.nextInt(4) - RANDOM.nextInt(4));
            } while (f == blockEntity.flipRandom);
        }
        blockEntity.nextPageTurningSpeed = MathHelper.clamp(blockEntity.nextPageTurningSpeed, 0.0f, 1.0f);
        ++blockEntity.ticks;
        blockEntity.pageAngle = blockEntity.nextPageAngle;
        float h = (blockEntity.flipRandom - blockEntity.nextPageAngle) * 0.4f;
        h = MathHelper.clamp(h, -0.2f, 0.2f);
        blockEntity.flipTurn += (h - blockEntity.flipTurn) * 0.9f;
        blockEntity.nextPageAngle += blockEntity.flipTurn;
    }

    public ItemStack getBook() {
        return this.book;
    }

    public boolean hasBook() {
        return this.book.isOf(Items.ENCHANTED_BOOK) && EnchantmentHelper.get(book).keySet().stream().filter(Enchantment::isCursed).toList().size() > 0;
    }

    public void setBook(ItemStack book) {
        this.setBook(book, null);
    }

    void onBookRemoved() {
        LecternBlock.setHasBook(null, this.getWorld(), this.getPos(), this.getCachedState(), false);
    }

    public void setBook(ItemStack book, @Nullable PlayerEntity player) {
        this.book = this.resolveBook(book, player);
        this.markDirty();
    }

    private ItemStack resolveBook(ItemStack book, @Nullable PlayerEntity player) {
        var isCursed = EnchantmentHelper.get(book).keySet().stream().filter(Enchantment::isCursed).toList().size() > 0;
        if (this.world instanceof ServerWorld && book.isOf(Items.ENCHANTED_BOOK) && isCursed) {
            WrittenBookItem.resolve(book, this.getCommandSource(player), player);
        }
        return book;
    }

    private ServerCommandSource getCommandSource(@Nullable PlayerEntity player) {
        Text text;
        String string;
        if (player == null) {
            string = "Cursed Lectern";
            text = Text.literal("Cursed Lectern");
        } else {
            string = player.getName().getString();
            text = player.getDisplayName();
        }
        Vec3d vec3d = Vec3d.ofCenter(this.pos);
        return new ServerCommandSource(CommandOutput.DUMMY, vec3d, Vec2f.ZERO, (ServerWorld)this.world, 2, string, text, this.world.getServer(), player);
    }

    @Override
    public boolean copyItemDataRequiresOperator() {
        return true;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.book = nbt.contains("Book", NbtElement.COMPOUND_TYPE) ? this.resolveBook(ItemStack.fromNbt(nbt.getCompound("Book")), null) : ItemStack.EMPTY;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.getBook().isEmpty()) {
            nbt.put("Book", this.getBook().writeNbt(new NbtCompound()));
        }
    }

    @Override
    public void clear() {
        this.setBook(ItemStack.EMPTY);
    }
}
