package dev.satyrn.early_buckets.world.item;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nonnull;

import static dev.satyrn.early_buckets.world.item.BucketItems.createItemStack;

/**
 * Implementation for a wooden bucket item.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class WoodenBucketItem extends CustomBucketItem {
    /**
     * Represents a wooden bucket
     *
     * @param fluid    The fluid which this bucket contains.
     * @param settings The item initialization settings.
     *
     * @since 1.0.0
     */
    public WoodenBucketItem(final Fluid fluid, final Properties settings) {
        super(fluid, settings);
    }

    /**
     * Attempts to fill the bucket with the given fluid.
     * Should return {@link net.minecraft.world.item.Items#AIR} if the bucket cannot be filled with the current fluid.
     *
     * @param sourceStack The item stack that contains the bucket to be filled.
     * @param fluid       The fluid with which the bucket should be filled.
     *
     * @return An item stack containing the bucket which has been filled with the block, or {@link ItemStack#EMPTY} if
     * the bucket could not be filled with the given fluid.
     *
     * @since 2.0.0+alpha.1
     */
    @Override
    @SuppressWarnings("deprecation")
    public ItemStack getFilledItemStack(final ItemStack sourceStack, final Fluid fluid) {
        if (fluid.is(FluidTags.WATER)) {
            return createItemStack(BucketItems.WOODEN_WATER_BUCKET.get(), sourceStack);
        }
        return ItemStack.EMPTY;
    }

    /**
     * Gets an item which represents the bucket, filled with snow.
     *
     * @return The item which represents the snow-filled bucket.
     *
     * @since 2.0.0+alpha.1
     */
    @Override
    public @Nonnull Item getPowderSnowFilledItem() {
        return BucketItems.WOODEN_POWDER_SNOW_BUCKET.get();
    }

    /**
     * Attempts to shove an entity into the bucket.
     * Should return {@link ItemStack#EMPTY} if the entity doesn't want to be in the bucket.
     *
     * @param sourceStack The bucket into which the entity is being unceremoniously stuffed.
     * @param entity      The entity being stuffed into the bucket.
     *
     * @return The item stack containing the bucket with the entity inside, or {@link ItemStack#EMPTY} if the bucket
     * could not be filled with the entity.
     */
    @Override
    @SuppressWarnings("deprecation")
    public ItemStack getFilledItemStack(final ItemStack sourceStack, final LivingEntity entity) {
        if (this.fluid.is(FluidTags.WATER)) {
            final EntityType<?> type = entity.getType();
            if (EntityType.AXOLOTL.equals(type)) {
                return createItemStack(BucketItems.WOODEN_AXOLOTL_BUCKET.get(), sourceStack);
            } else if (EntityType.COD.equals(type)) {
                return createItemStack(BucketItems.WOODEN_COD_BUCKET.get(), sourceStack);
            } else if (EntityType.PUFFERFISH.equals(type)) {
                return createItemStack(BucketItems.WOODEN_PUFFERFISH_BUCKET.get(), sourceStack);
            } else if (EntityType.SALMON.equals(type)) {
                return createItemStack(BucketItems.WOODEN_SALMON_BUCKET.get(), sourceStack);
            } else if (EntityType.TROPICAL_FISH.equals(type)) {
                return createItemStack(BucketItems.WOODEN_TROPICAL_FISH_BUCKET.get(), sourceStack);
            } else if (EntityType.TADPOLE.equals(type)) {
                return createItemStack(BucketItems.WOODEN_TADPOLE_BUCKET.get(), sourceStack);
            }
        }

        return ItemStack.EMPTY;
    }

    /**
     * Attempts to fill the bucket with cow juice.
     * Should return {@link ItemStack#EMPTY} if the concept of cow juice is too weird for the bucket.
     *
     * @param sourceStack The item stack of the bucket being filled.
     *
     * @return The filled item stack.
     */
    @Override
    public ItemStack getMilkBucketItemStack(final ItemStack sourceStack) {
        if (Fluids.EMPTY.equals(this.fluid)) {
            return BucketItems.createItemStack(BucketItems.WOODEN_MILK_BUCKET.get(), sourceStack);
        }
        return ItemStack.EMPTY;
    }

    /**
     * Gets the emptied item for this bucket.
     *
     * @return The emptied item for this bucket
     *
     * @since 1.0.0
     */
    @Override
    public Item getEmptyItem() {
        return BucketItems.WOODEN_BUCKET.get();
    }
}
