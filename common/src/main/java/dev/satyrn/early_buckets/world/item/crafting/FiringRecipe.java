package dev.satyrn.early_buckets.world.item.crafting;

import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

public class FiringRecipe extends AbstractCookingRecipe {
    public FiringRecipe(ResourceLocation resourceLocation, String string, Ingredient ingredient, ItemStack itemStack, float f, int i) {
        super(BucketRecipeTypes.FIRING.get(), resourceLocation, string, ingredient, itemStack, f, i);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return BucketRecipeSerializers.FIRING_SERIALIZER.get();
    }

    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(BucketBlocks.KILN.get());
    }
}
