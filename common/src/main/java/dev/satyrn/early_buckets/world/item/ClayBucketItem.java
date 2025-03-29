package dev.satyrn.early_buckets.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static dev.satyrn.early_buckets.world.item.BucketItems.createItemStack;

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
     *
     * @since 1.0.0
     */
    public ClayBucketItem(final @NotNull Properties settings) {
        super(Fluids.EMPTY, settings);
    }

    /**
     * Initializes a new clay bucket.
     *
     * @param fluid    The fluid to fill the bucket with.
     * @param settings The item settings.
     */
    public ClayBucketItem(final @NotNull Fluid fluid, final @NotNull Properties settings) {
        super(fluid, settings);
    }

    /**
     * Gets the empty item for the bucket.
     *
     * @return The empty item.
     */
    @Override
    public Item getEmptyItem() {
        return Items.CLAY_BALL;
    }

    /**
     * Attempts to fill the bucket with the given fluid.
     * Should return {@link Items#AIR} if the bucket cannot be filled with the current fluid.
     *
     * @param sourceStack The item stack that contains the bucket to be filled.
     * @param fluid       The fluid with which the bucket should be filled.
     *
     * @return An item stack containing the bucket which has been filled with the block, or {@link net.minecraft.world.item.ItemStack#EMPTY} if
     * the bucket could not be filled with the given fluid.
     *
     * @since 2.0.0+alpha.1
     */
    @Override
    @SuppressWarnings("deprecation")
    public ItemStack getFilledItemStack(final @NotNull ItemStack sourceStack, final @NotNull Fluid fluid) {
        if (fluid.is(FluidTags.WATER)) {
            return createItemStack(Items.CLAY_BALL, sourceStack);
        } else if (fluid.is(FluidTags.LAVA)) {
            final ItemStack flashFiredCeramicBucket = createItemStack(BucketItems.CERAMIC_LAVA_BUCKET.get(),
                    sourceStack);
            // Flash-fired clay buckets only have 1/8 of their original max damage.
            flashFiredCeramicBucket.setDamageValue(
                    CustomBucket.CERAMIC_BUCKET_DURABILITY - (CustomBucket.CERAMIC_BUCKET_DURABILITY / 8));
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
     *
     * @return The item stack containing the bucket with the entity inside, or {@link ItemStack#EMPTY} if the bucket
     * could not be filled with the entity.
     */
    @Override
    @SuppressWarnings("deprecation")
    public ItemStack getFilledItemStack(final @NotNull ItemStack sourceStack, final @NotNull LivingEntity entity) {
        if (this.fluid.is(FluidTags.WATER) && EntityType.AXOLOTL.equals(entity.getType())) {
            return createItemStack(BucketItems.CLAY_AXOLOTL_BUCKET.get(), sourceStack);
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
    public Item getPowderSnowFilledItem() {
        return BucketItems.CLAY_POWDER_SNOW_BUCKET.get();
    }

    /**
     * Plays a sound when the bucket is filled, based on the item which was returned.
     *
     * @param user              The player that used the bucket.
     * @param returnedItemStack The filled bucket item stack.
     */
    @Override
    public void playSoundOnFill(final @NotNull Player user, final @NotNull ItemStack returnedItemStack) {
        final Item returnedItem = returnedItemStack.getItem();
        System.out.println("Clay bucket filled returned as " +
                Objects.requireNonNull(Registry.ITEM.getKey(returnedItem)).getPath());
        if (BucketItems.CERAMIC_LAVA_BUCKET.get().equals(returnedItem)) {
            user.getLevel()
                    .playSound(user, new BlockPos(user.position()), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS,
                            0.5F, 2.6F + (user.getRandom().nextFloat()) * 0.8F);
        } else if (Items.CLAY_BALL.equals(returnedItem)) {
            user.getLevel()
                    .playSound(user, new BlockPos(user.position()), SoundEvents.GRAVEL_BREAK, SoundSource.PLAYERS, 0.5F,
                            2.6F + (user.getRandom().nextFloat()) * 0.8F);
        }
    }
}
