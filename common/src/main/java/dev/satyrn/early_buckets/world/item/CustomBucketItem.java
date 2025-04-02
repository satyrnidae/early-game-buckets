package dev.satyrn.early_buckets.world.item;

import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.mixin.world.level.block.LiquidBlockAccessor;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

/**
 * A custom bucket handler for the buckets implemented by this mod.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public abstract class CustomBucketItem extends BucketItem implements Repairable, CustomBucket {
    // The fluid contained within this bucket.
    public final Fluid fluid;

    /**
     * Initializes a new breakable bucket.
     *
     * @param fluid    The fluid which this bucket contains.
     * @param settings The item initialization settings.
     *
     * @since 1.0.0
     */
    protected CustomBucketItem(final Fluid fluid, final Properties settings) {
        super(fluid, settings);
        this.fluid = fluid;
    }

    /**
     * Gets an item stack which will be exchanged when the player empties a bucket.
     * Should return {@code itemStack} if the bucket should not be emptied.
     *
     * @param sourceStack The item stack for the bucket which was emptied.
     * @param player      The player who emptied the bucket.
     *
     * @return An item stack containing the empty bucket.
     *
     * @since 2.0.0+alpha.1
     */
    @Override
    public ItemStack getEmptyItemStack(final ItemStack sourceStack, final Player player) {
        if (player.getAbilities().instabuild) {
            return sourceStack;
        }

        return BucketItems.createItemStack(this.getEmptyItem(), sourceStack);
    }

    /**
     * Gets the empty item for the bucket.
     *
     * @return The empty item.
     */
    public abstract Item getEmptyItem();

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
    public ItemStack getFilledItemStack(final ItemStack sourceStack, final Fluid fluid) {
        return ItemStack.EMPTY;
    }

    /**
     * Attempts to fill the bucket with the given block.
     * Should return {@link ItemStack#EMPTY} if the bucket cannot be filled with the given block.
     * Note: Blocks can be waterlogged, so return {@link CustomBucket#getFilledItemStack(ItemStack, Fluid)} passing
     * {@link Fluids#WATER} as the second parameter.
     *
     * @return An item stack containing the bucket which has been filled with the block, or {@link ItemStack#EMPTY} if
     * the bucket could not be filled with the given block.
     *
     * @since 2.0.0+alpha.1
     */
    public ItemStack getFilledItemStack(final ItemStack sourceStack, final Block block) {
        if (Fluids.EMPTY.equals(this.fluid)) {
            if (Blocks.POWDER_SNOW.equals(block)) {
                @Nullable Item powderSnowItem = this.getPowderSnowFilledItem();
                return powderSnowItem != null
                        ? BucketItems.createItemStack(this.getPowderSnowFilledItem(), sourceStack)
                        : ItemStack.EMPTY;
            }
            if (block instanceof BubbleColumnBlock || block instanceof SimpleWaterloggedBlock) {
                return getFilledItemStack(sourceStack, Fluids.WATER);
            }
            if (block instanceof final LiquidBlock liquidBlock) {
                FlowingFluid fluid = ((LiquidBlockAccessor) liquidBlock).getFluid();
                return getFilledItemStack(sourceStack, fluid);
            }
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
    public @Nullable Item getPowderSnowFilledItem() {
        return null;
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
    public ItemStack getFilledItemStack(final ItemStack sourceStack, final LivingEntity entity) {
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
    public ItemStack getMilkBucketItemStack(final ItemStack sourceStack) {
        return ItemStack.EMPTY;
    }

    /**
     * Checks if the item can be repaired by another stack.
     *
     * @param stack      The item to repair.
     * @param ingredient The ingredient to repair with.
     *
     * @return {@code false} if there is any fluid in the bucket; otherwise, calls super implementation.
     *
     * @since 1.0.0
     */
    @Override
    public boolean isValidRepairItem(final ItemStack stack, final ItemStack ingredient) {
        if (this.fluid != Fluids.EMPTY) {
            return false;
        }
        return super.isValidRepairItem(stack, ingredient);
    }

    /**
     * Disables crafting repair for items which contain a fluid.
     *
     * @return {@code true} if the bucket is empty and can be repaired in the crafting grid; otherwise, {@code false}.
     *
     * @since 1.0.0
     */
    @Override
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
     *
     * @return Whether the event passed or failed, as well as the resultant item stack.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(final Level world, final Player user, final InteractionHand hand) {
        // We need to replicate the bucket functionality -_-
        final ItemStack stackInHand = user.getItemInHand(hand);
        final BlockHitResult blockHitResult = getPlayerPOVHitResult(world, user,
                Fluids.EMPTY.equals(this.fluid) ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        if (!HitResult.Type.BLOCK.equals(blockHitResult.getType())) {
            // We either missed or didn't hit a block...
            return InteractionResultHolder.pass(stackInHand);
        } else {
            // We hit a block!
            final BlockPos hitBlockPos = blockHitResult.getBlockPos();
            final Direction hitSide = blockHitResult.getDirection();
            final BlockPos offsetByHitSideBlockPos = hitBlockPos.offset(hitSide.getNormal());

            if (world.mayInteract(user, hitBlockPos) &&
                    user.mayUseItemAt(offsetByHitSideBlockPos, hitSide, stackInHand)) {
                final BlockState hitBlockState = world.getBlockState(hitBlockPos);

                // Make sure we are empty before trying to fill ourselves.
                if (Fluids.EMPTY.equals(this.fluid)) {
                    // Did we just hit a drainable fluid?
                    final Block hitBlock = hitBlockState.getBlock();

                    if (hitBlock instanceof BucketPickup pickup) {
                        // First check if we can even fill this stack with our current item.
                        ItemStack filledBucketStack = this.getFilledItemStack(stackInHand, hitBlock);

                        if (!filledBucketStack.isEmpty()) {
                            final ItemStack drainedFluidStack = pickup.pickupBlock(world, hitBlockPos, hitBlockState);

                            // Then check if the block drained successfully
                            if (!drainedFluidStack.isEmpty()) {
                                if (Items.CLAY_BALL.equals(filledBucketStack.getItem()) &&
                                        !world.isClientSide &&
                                        world.random.nextInt(20) == 0) {
                                    filledBucketStack = BucketItems.createItemStack(BucketItems.CLAY_WATER_BUCKET.get(),
                                            stackInHand);
                                }

                                // And we have successfully drained the block!
                                user.awardStat(Stats.ITEM_USED.get(this));
                                pickup.getPickupSound().ifPresent((sound) -> user.playSound(sound, 1.0F, 1.0F));
                                this.playSoundOnFill(user, filledBucketStack);
                                world.gameEvent(user, GameEvent.FLUID_PICKUP, hitBlockPos);
                                if (!world.isClientSide && user instanceof final ServerPlayer serverPlayer) {
                                    BucketModCommon.FILL_BUCKET.trigger(serverPlayer, stackInHand, filledBucketStack);
                                    CriteriaTriggers.FILLED_BUCKET.trigger(serverPlayer, filledBucketStack);
                                }
                                final ItemStack exchangedStack = ItemUtils.createFilledResult(stackInHand, user,
                                        filledBucketStack);

                                return InteractionResultHolder.sidedSuccess(exchangedStack, world.isClientSide);
                            }
                        }
                    }
                    return InteractionResultHolder.fail(stackInHand);
                } else {
                    // Hey! We aren't empty!
                    final BlockPos placeFluidAtBlockPos = hitBlockState.getBlock() instanceof LiquidBlockContainer &&
                            Fluids.WATER.equals(this.fluid) ? hitBlockPos : offsetByHitSideBlockPos;
                    if (this.emptyContents(user, world, placeFluidAtBlockPos, blockHitResult)) {
                        this.checkExtraContent(user, world, stackInHand, placeFluidAtBlockPos);

                        user.awardStat(Stats.ITEM_USED.get(this));
                        final ItemStack emptyItemStack = this.getEmptyItemStack(stackInHand, user);
                        emptyItemStack.hurtAndBreak(this.getDamageOnDispenseFluid(this.fluid), user,
                                player -> player.broadcastBreakEvent(hand));

                        if (user instanceof final ServerPlayer serverPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, placeFluidAtBlockPos, stackInHand);
                        }

                        return InteractionResultHolder.sidedSuccess(emptyItemStack, world.isClientSide);
                    }
                }
            }
        }

        return InteractionResultHolder.fail(stackInHand);
    }

    /**
     * Gets the fluid for this bucket.
     *
     * @return The fluid.
     */
    @Override
    public Fluid getFluid() {
        return this.fluid;
    }
}
