package dev.satyrn.early_buckets.item;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Duck interface for bucket operations.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public interface Bucket {
    /**
     * If the bucket is damageable, it should take this much damage when dispensing a specific fluid.
     *
     * @param fluid The fluid to dispense.
     * @return The amount of damage accrued.
     * @since 2.0.0+alpha.1
     */
    int getDamageOnDispenseFluid(@NotNull Fluid fluid);

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
    @NotNull ItemStack getEmptyItemStack(final @NotNull ItemStack sourceStack, final @NotNull PlayerEntity player);

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
    @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, @NotNull Fluid fluid);

    /**
     * Attempts to fill the bucket with the given block.
     * Should return {@link ItemStack#EMPTY} if the bucket cannot be filled with the given block.
     * Note: Blocks can be waterlogged, so return {@link Bucket#getFilledItemStack(ItemStack, Fluid)} passing
     * {@link net.minecraft.fluid.Fluids#WATER} as the second parameter.
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
    default void playSoundOnFill(PlayerEntity user, ItemStack returnedItemStack) {
    }
}
