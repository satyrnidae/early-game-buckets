package dev.satyrn.early_buckets.world.item;

import dev.satyrn.early_buckets.tags.BucketTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Duck interface for bucket operations.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public interface CustomBucket {
    /**
     * The maximum damage value that a ceramic bucket may accrue before breaking.
     *
     * @since 1.0.0
     */
    int CERAMIC_BUCKET_DURABILITY = 128;
    /**
     * The maximum damage that a wooden bucket may accrue before breaking.
     *
     * @since 1.0.0
     */
    int WOODEN_BUCKET_DURABILITY = 64;


    /**
     * Gets the damage that the bucket will accrue when dispensing a specific liquid.
     *
     * @param fluid The fluid being dispensed
     * @return The amount of damage that the stack should take.
     * @since 1.0.0
     */
    @SuppressWarnings("deprecation")
    default int getDamageOnDispenseFluid(@NotNull Fluid fluid) {
        if (fluid.is(FluidTags.LAVA)) {
            return 2;
        }
        if (fluid.is(FluidTags.WATER)) {
            return 1;
        }
        return 0;
    }

    /**
     * Gets the damage that the bucket will accrue when dispensing a specific entity.
     *
     * @param entityType The entity being dispensed.
     * @return The damage accrued.
     */
    default int getDamageOnEntityInteraction(@NotNull EntityType<?> entityType) {
        if (entityType.is(BucketTags.EntityTypes.FISH)) {
            return 1;
        }
        return 2;
    }

    /**
     * Gets the damage that the bucket will accrue when dispensing a specific block.
     * @param block The block to dispense.
     * @return The damage accrued.
     */
    default int getDamageOnDispenseBlock(@NotNull Block block) {
        if (block == Blocks.POWDER_SNOW) {
            return 1;
        }
        return 2;
    }

    /**
     * Gets an item stack which will be exchanged when the player empties a bucket.
     * Should return {@code itemStack} if the bucket should not be emptied.
     *
     * @param sourceStack The item stack for the bucket which was emptied.
     * @param player      The player who emptied the bucket.
     * @return An item stack containing the empty bucket.
     * @since 2.0.0+alpha.1
     */
    @Contract("_, _ -> !null")
    @NotNull ItemStack getEmptyItemStack(final @NotNull ItemStack sourceStack, final @NotNull Player player);

    /**
     * Attempts to fill the bucket with the given fluid.
     * Should return {@link net.minecraft.world.item.Items#AIR} if the bucket cannot be filled with the current fluid.
     *
     * @param sourceStack The item stack that contains the bucket to be filled.
     * @param fluid       The fluid with which the bucket should be filled.
     * @return An item stack containing the bucket which has been filled with the block, or {@link ItemStack#EMPTY} if
     * the bucket could not be filled with the given fluid.
     * @since 2.0.0+alpha.1
     */
    @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, @NotNull Fluid fluid);

    /**
     * Attempts to fill the bucket with the given block.
     * Should return {@link ItemStack#EMPTY} if the bucket cannot be filled with the given block.
     * Note: Blocks can be waterlogged, so return {@link CustomBucket#getFilledItemStack(ItemStack, Fluid)} passing
     * {@link net.minecraft.world.level.material.Fluids#WATER} as the second parameter.
     *
     * @return An item stack containing the bucket which has been filled with the block, or {@link ItemStack#EMPTY} if
     * the bucket could not be filled with the given block.
     * @since 2.0.0+alpha.1
     */
    @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, @NotNull Block block);

    /**
     * Attempts to shove an entity into the bucket.
     * Should return {@link ItemStack#EMPTY} if the entity doesn't want to be in the bucket.
     *
     * @param sourceStack The bucket into which the entity is being unceremoniously stuffed.
     * @param entity      The entity being stuffed into the bucket.
     * @return The item stack containing the bucket with the entity inside, or {@link ItemStack#EMPTY} if the bucket
     * could not be filled with the entity.
     * @since 2.0.0+alpha.1
     */
    @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, LivingEntity entity);

    /**
     * Attempts to fill the bucket with cow juice.
     * Should return {@link ItemStack#EMPTY} if the concept of cow juice is too weird for the bucket.
     *
     * @param sourceStack The item stack of the bucket being filled.
     * @return The filled item stack.
     * @since 2.0.0+alpha.1
     */
    @NotNull ItemStack getMilkBucketItemStack(@NotNull ItemStack sourceStack);

    /**
     * Gets the fluid for this bucket.
     *
     * @return The fluid.
     */
    @NotNull Fluid getFluid();

    /**
     * Plays a sound based on the stack that was returned after fill.
     *
     * @param user              The player that used the bucket.
     * @param returnedItemStack The filled bucket item stack.
     */
    default void playSoundOnFill(Player user, ItemStack returnedItemStack) {
    }
}
