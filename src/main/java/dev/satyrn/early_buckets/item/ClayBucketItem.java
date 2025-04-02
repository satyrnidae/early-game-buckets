package dev.satyrn.early_buckets.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static dev.satyrn.early_buckets.item.BucketItems.CERAMIC_LAVA_BUCKET;
import static dev.satyrn.early_buckets.item.BucketItems.createItemStack;

/**
 * Models a clay bucket's behaviors.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public class ClayBucketItem extends CustomBucketItem {

    /**
     * Initializes a new clay bucket.
     *
     * @param settings The item initialization settings.
     * @since 1.0.0
     */
    public ClayBucketItem(Settings settings) {
        super(Fluids.EMPTY, settings);
    }

    /**
     * Initializes a new clay bucket.
     *
     * @param fluid    The fluid to fill the bucket with.
     * @param settings The item settings.
     */
    public ClayBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings);
    }

    /**
     * Gets the empty item for the bucket.
     *
     * @return The empty item.
     */
    @Override
    public @NotNull Item getEmptyItem() {
        return Items.CLAY_BALL;
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
    @Override
    public @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, @NotNull Fluid fluid) {
        if (fluid.isIn(FluidTags.WATER)) {
            return createItemStack(Items.CLAY_BALL, sourceStack);
        } else if (fluid.isIn(FluidTags.LAVA)) {
            final ItemStack flashFiredCeramicBucket = createItemStack(BucketItems.CERAMIC_LAVA_BUCKET, sourceStack);
            // Flash-fired clay buckets only have 1/8 of their original max damage.
            flashFiredCeramicBucket.setDamage(BucketItems.CERAMIC_BUCKET_MAX_DAMAGE - (BucketItems.CERAMIC_BUCKET_MAX_DAMAGE / 8));
            return flashFiredCeramicBucket;
        }
        return ItemStack.EMPTY;
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
        if (this.fluid.isIn(FluidTags.WATER) && EntityType.AXOLOTL.equals(entity.getType())) {
            return createItemStack(BucketItems.CLAY_AXOLOTL_BUCKET, sourceStack);
        }
        return ItemStack.EMPTY;
    }

    /**
     * Gets an item which represents the bucket, filled with snow.
     *
     * @return The item which represents the snow-filled bucket.
     * @since 2.0.0+alpha.1
     */
    @Override
    public @Nullable Item getPowderSnowFilledItem() {
        return BucketItems.CLAY_POWDER_SNOW_BUCKET;
    }

    /**
     * Plays a sound when the bucket is filled, based on the item which was returned.
     *
     * @param user              The player that used the bucket.
     * @param returnedItemStack The filled bucket item stack.
     */
    @Override
    public void playSoundOnFill(PlayerEntity user, ItemStack returnedItemStack) {
        final Item returnedItem = returnedItemStack.getItem();
        System.out.println("Clay bucket filled returned as " + returnedItem.toString());
        if (CERAMIC_LAVA_BUCKET.equals(returnedItem)) {
            user.world.playSound(user, user.getBlockPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5F, 2.6F + (user.getRandom()
                    .nextFloat()) * 0.8F);
        } else if (Items.CLAY_BALL.equals(returnedItem)) {
            user.world.playSound(user, user.getBlockPos(), SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.PLAYERS, 0.5F, 2.6F + (user.getRandom()
                    .nextFloat()) * 0.8F);
        }
    }
}
