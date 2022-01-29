package dev.satyrn.early_buckets.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.FluidTags;
import org.jetbrains.annotations.NotNull;

import static dev.satyrn.early_buckets.item.BucketItems.createItemStack;

/**
 * Implementation for ceramic buckets.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class CeramicBucketItem extends CustomBucketItem {
    /**
     * Represents a wooden bucket
     *
     * @param fluid    The fluid which this bucket contains.
     * @param settings The item initialization settings.
     * @since 1.0.0
     */
    public CeramicBucketItem(Fluid fluid, Item.Settings settings) {
        super(fluid, settings);
    }

    /**
     * Attempts to fill the bucket with the given fluid.
     * Should return {@link Items#AIR} if the bucket cannot be filled with the current fluid.
     *
     * @param sourceStack The item stack that contains the bucket to be filled.
     * @param fluid       The fluid with which the bucket should be filled.
     * @return An item stack containing the bucket which has been filled with the block, or {@link ItemStack#EMPTY} if
     * the bucket could not be filled with the given fluid.
     * @since 2.0.0+alpha.1
     */
    public @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, @NotNull Fluid fluid) {
        Item filledItem = null;
        if (fluid.isIn(FluidTags.WATER)) {
            filledItem = BucketItems.CERAMIC_WATER_BUCKET;
        } else if (fluid.isIn(FluidTags.LAVA)) {
            filledItem = BucketItems.CERAMIC_LAVA_BUCKET;
        }
        return filledItem != null ? BucketItems.createItemStack(filledItem, sourceStack) : ItemStack.EMPTY;
    }

    /**
     * Gets an item which represents the bucket, filled with snow.
     *
     * @return The item which represents the snow-filled bucket.
     * @since 2.0.0+alpha.1
     */
    @Override
    public @NotNull Item getPowderSnowFilledItem() {
        return BucketItems.CERAMIC_POWDER_SNOW_BUCKET;
    }

    /**
     * Attempts to shove an entity into the bucket.
     * Should return {@link ItemStack#EMPTY} if the entity doesn't want to be in the bucket.
     *
     * @param sourceStack The bucket into which the entity is being unceremoniously stuffed.
     * @param entity      The entity being stuffed into the bucket.
     * @return The item stack containing the bucket with the entity inside, or {@link ItemStack#EMPTY} if the bucket
     * could not be filled with the entity.
     */
    @Override
    public @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, LivingEntity entity) {
        if (this.fluid.isIn(FluidTags.WATER)) {
            final EntityType<?> type = entity.getType();
            if (EntityType.AXOLOTL.equals(type)) {
                return createItemStack(BucketItems.CERAMIC_AXOLOTL_BUCKET, sourceStack);
            } else if (EntityType.COD.equals(type)) {
                return createItemStack(BucketItems.CERAMIC_COD_BUCKET, sourceStack);
            } else if (EntityType.PUFFERFISH.equals(type)) {
                return createItemStack(BucketItems.CERAMIC_PUFFERFISH_BUCKET, sourceStack);
            } else if (EntityType.SALMON.equals(type)) {
                return createItemStack(BucketItems.CERAMIC_SALMON_BUCKET, sourceStack);
            } else if (EntityType.TROPICAL_FISH.equals(type)) {
                return createItemStack(BucketItems.CERAMIC_TROPICAL_FISH_BUCKET, sourceStack);
            }
        }

        return ItemStack.EMPTY;
    }

    /**
     * Attempts to fill the bucket with cow juice.
     * Should return {@link ItemStack#EMPTY} if the concept of cow juice is too weird for the bucket.
     *
     * @param sourceStack The item stack of the bucket being filled.
     * @return The filled item stack.
     */
    @Override
    public @NotNull ItemStack getMilkBucketItemStack(@NotNull ItemStack sourceStack) {
        if (Fluids.EMPTY.equals(this.fluid)) {
            return BucketItems.createItemStack(BucketItems.CERAMIC_MILK_BUCKET, sourceStack);
        }
        return ItemStack.EMPTY;
    }

    /**
     * Gets the emptied item for this bucket.
     *
     * @return The emptied item for this bucket
     * @since 1.0.0
     */
    @Override
    public @NotNull Item getEmptyItem() {
        return BucketItems.CERAMIC_BUCKET;
    }

    /**
     * Gets the translation key for an item stack.
     *
     * @param stack The stack that requires a translation key.
     * @return The translation key. If the bucket stack damage is over 87.5%, appends .cracked to the translation key.
     */
    public String getTranslationKey(ItemStack stack) {
        String translationKey = this.getTranslationKey();
        if ((float) stack.getDamage() / stack.getMaxDamage() >= 0.875F) {
            translationKey += ".cracked";
        }

        return translationKey;
    }
}
