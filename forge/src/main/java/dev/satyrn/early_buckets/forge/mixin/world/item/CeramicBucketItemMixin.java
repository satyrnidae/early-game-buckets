package dev.satyrn.early_buckets.forge.mixin.world.item;

import dev.satyrn.early_buckets.world.item.CeramicBucketItem;
import dev.satyrn.early_buckets.world.item.CustomBucketItem;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.extensions.IForgeItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CeramicBucketItem.class)
public abstract class CeramicBucketItemMixin extends CustomBucketItem implements IForgeItem {

    /**
     * Initializes a new breakable bucket.
     *
     * @param fluid    The fluid which this bucket contains.
     * @param settings The item initialization settings.
     *
     * @since 1.0.0
     */
    protected CeramicBucketItemMixin(Fluid fluid, Properties settings) {
        super(fluid, settings);
        throw new AssertionError();
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.fluid.is(FluidTags.LAVA) ? 20000 : super.getBurnTime(itemStack, recipeType);
    }
}
