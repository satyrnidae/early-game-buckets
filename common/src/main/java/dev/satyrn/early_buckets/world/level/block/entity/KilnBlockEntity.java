package dev.satyrn.early_buckets.world.level.block.entity;

import dev.satyrn.early_buckets.world.inventory.KilnMenu;
import dev.satyrn.early_buckets.world.item.crafting.BucketRecipeTypes;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    public KilnBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BucketBlockEntityTypes.KILN.get(), blockPos, blockState, BucketRecipeTypes.FIRING.get());
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable(Util.makeDescriptionId("container", BucketBlocks.KILN.getId()));
    }

    @Override
    protected int getBurnDuration(ItemStack itemStack) {
        return super.getBurnDuration(itemStack) / 2;
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new KilnMenu(i, inventory, this, this.dataAccess);
    }
}
