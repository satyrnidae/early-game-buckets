package dev.satyrn.early_buckets.item;

import dev.satyrn.early_buckets.BucketMod;
import dev.satyrn.early_buckets.mixin.accessors.FluidBlockAccessor;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A custom bucket handler for the buckets implemented by this mod.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public abstract class CustomBucketItem extends BucketItem implements Repairable, Bucket {
    // The fluid contained within this bucket.
    public final Fluid fluid;

    /**
     * Initializes a new breakable bucket.
     *
     * @param fluid    The fluid which this bucket contains.
     * @param settings The item initialization settings.
     * @since 1.0.0
     */
    protected CustomBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings);
        this.fluid = fluid;
    }

    /**
     * Gets the damage that the bucket will accrue when dispensing a specific liquid.
     *
     * @param fluid The fluid being dispensed
     * @return The amount of damage that the stack should take.
     * @since 1.0.0
     */
    @Override
    public int getDamageOnDispenseFluid(@NotNull Fluid fluid) {
        if (fluid == Fluids.EMPTY) {
            return 0;
        }
        if (fluid.isIn(FluidTags.LAVA)) {
            return 2;
        }
        return 1;
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
    @Override
    public @NotNull ItemStack getEmptyItemStack(@NotNull ItemStack sourceStack, @NotNull PlayerEntity player) {
        if (player.getAbilities().creativeMode) {
            return sourceStack;
        }

        return BucketItems.createItemStack(this.getEmptyItem(), sourceStack);
    }

    /**
     * Gets the empty item for the bucket.
     *
     * @return The empty item.
     */
    public abstract @NotNull Item getEmptyItem();

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
        return ItemStack.EMPTY;
    }

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
    public @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, @NotNull Block block) {
        if (Fluids.EMPTY.equals(this.fluid)) {
            if (Blocks.POWDER_SNOW.equals(block)) {
                Item powderSnowItem = this.getPowderSnowFilledItem();
                return powderSnowItem != null ? BucketItems.createItemStack(this.getPowderSnowFilledItem(), sourceStack) : ItemStack.EMPTY;
            }
            if (block instanceof Waterloggable || block instanceof BubbleColumnBlock) {
                return getFilledItemStack(sourceStack, Fluids.WATER);
            }
            if (block instanceof final FluidBlock fluidBlock) {
                FlowableFluid fluid = ((FluidBlockAccessor) fluidBlock).getFluid();
                return getFilledItemStack(sourceStack, fluid);
            }
        }
        return ItemStack.EMPTY;
    }

    /**
     * Gets an item which represents the bucket, filled with snow.
     *
     * @return The item which represents the snow-filled bucket.
     * @since 2.0.0+alpha.1
     */
    public @Nullable Item getPowderSnowFilledItem() {
        return null;
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
    public @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, LivingEntity entity) {
        return ItemStack.EMPTY;
    }

    /**
     * Attempts to fill the bucket with cow juice.
     * Should return {@link ItemStack#EMPTY} if the concept of cow juice is too weird for the bucket.
     *
     * @param sourceStack The item stack of the bucket being filled.
     * @return The filled item stack.
     */
    public @NotNull ItemStack getMilkBucketItemStack(@NotNull ItemStack sourceStack) {
        return ItemStack.EMPTY;
    }

    /**
     * Checks if the item can be repaired by another stack.
     *
     * @param stack      The item to repair.
     * @param ingredient The ingredient to repair with.
     * @return {@code false} if there is any fluid in the bucket; otherwise, calls super implementation.
     * @since 1.0.0
     */
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        if (this.fluid != Fluids.EMPTY) {
            return false;
        }
        return super.canRepair(stack, ingredient);
    }

    /**
     * Disables crafting repair for items which contain a fluid.
     *
     * @return {@code true} if the bucket is empty and can be repaired in the crafting grid; otherwise, {@code false}.
     * @since 1.0.0
     */
    public boolean canRepairByCrafting() {
        return this.fluid == Fluids.EMPTY;
    }

    /**
     * Replicates normal bucket functionality, but returns a custom bucket item when filled.
     * NOTE: Cognitive complexity is a bit high for this function. Blame Mojang lol
     *
     * @param world The world wherein the bucket is being used.
     * @param user  The player who is using the bucket.
     * @param hand  The hand in which the bucket is being held.
     * @return Whether the event passed or failed, as well as the resultant item stack.
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // We need to replicate the bucket functionality -_-
        final ItemStack stackInHand = user.getStackInHand(hand);
        final BlockHitResult blockHitResult = raycast(world, user, Fluids.EMPTY.equals(this.fluid) ? FluidHandling.SOURCE_ONLY : FluidHandling.NONE);
        if (!Type.BLOCK.equals(blockHitResult.getType())) {
            // We either missed or didn't hit a block...
            return TypedActionResult.pass(stackInHand);
        } else {
            // We hit a block!
            final BlockPos hitBlockPos = blockHitResult.getBlockPos();
            final Direction hitSide = blockHitResult.getSide();
            final BlockPos offsetByHitSideBlockPos = hitBlockPos.offset(hitSide);

            if (world.canPlayerModifyAt(user, hitBlockPos) && user.canPlaceOn(offsetByHitSideBlockPos, hitSide, stackInHand)) {
                final BlockState hitBlockState = world.getBlockState(hitBlockPos);

                // Make sure we are empty before trying to fill ourselves.
                if (Fluids.EMPTY.equals(this.fluid)) {
                    // Did we just hit a drainable fluid?
                    final Block hitBlock = hitBlockState.getBlock();

                    if (hitBlock instanceof final FluidDrainable fluidDrainable) {
                        // First check if we can even fill this stack with our current item.
                        ItemStack filledBucketStack = this.getFilledItemStack(stackInHand, hitBlock);

                        if (!filledBucketStack.isEmpty()) {
                            final ItemStack drainedFluidStack = fluidDrainable.tryDrainFluid(world, hitBlockPos, hitBlockState);

                            // Then check if the block drained successfully
                            if (!drainedFluidStack.isEmpty()) {
                                if (Items.CLAY_BALL.equals(filledBucketStack.getItem()) && !world.isClient && world.random.nextInt(20) == 0) {
                                    filledBucketStack = BucketItems.createItemStack(BucketItems.CLAY_WATER_BUCKET, stackInHand);
                                }

                                // And we have successfully drained the block!
                                user.incrementStat(Stats.USED.getOrCreateStat(this));
                                fluidDrainable.getBucketFillSound()
                                        .ifPresent((sound) -> user.playSound(sound, 1.0F, 1.0F));
                                this.playSoundOnFill(user, filledBucketStack);
                                world.emitGameEvent(user, GameEvent.FLUID_PICKUP, hitBlockPos);
                                if (!world.isClient) {
                                    BucketMod.FILL_BUCKET.trigger((ServerPlayerEntity) user, stackInHand, filledBucketStack);
                                    Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity) user, filledBucketStack);
                                }
                                final ItemStack exchangedStack = ItemUsage.exchangeStack(stackInHand, user, filledBucketStack);

                                return TypedActionResult.success(exchangedStack, world.isClient);
                            }
                        }
                    }
                    return TypedActionResult.fail(stackInHand);
                } else {
                    // Hey! We aren't empty!
                    final BlockPos placeFluidAtBlockPos = hitBlockState.getBlock() instanceof FluidFillable && Fluids.WATER.equals(this.fluid) ? hitBlockPos : offsetByHitSideBlockPos;
                    if (this.placeFluid(user, world, placeFluidAtBlockPos, blockHitResult)) {
                        this.onEmptied(user, world, stackInHand, placeFluidAtBlockPos);

                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        final ItemStack emptyItemStack = this.getEmptyItemStack(stackInHand, user);
                        emptyItemStack.damage(this.getDamageOnDispenseFluid(this.fluid), user, player -> player.sendToolBreakStatus(hand));

                        if (user instanceof final ServerPlayerEntity serverPlayer) {
                            Criteria.PLACED_BLOCK.trigger(serverPlayer, placeFluidAtBlockPos, stackInHand);
                        }

                        return TypedActionResult.success(emptyItemStack, world.isClient);
                    }
                }
            }
        }

        return TypedActionResult.fail(stackInHand);
    }

    /**
     * Gets the fluid for this bucket.
     *
     * @return The fluid.
     */
    @Override
    public @NotNull Fluid getFluid() {
        return this.fluid;
    }
}
