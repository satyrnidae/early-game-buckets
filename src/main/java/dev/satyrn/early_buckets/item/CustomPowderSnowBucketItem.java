package dev.satyrn.early_buckets.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Powder snow item for custom buckets.
 * Most of this is copied from ItemBlock to prevent overriding the item for the Powder Snow block.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public class CustomPowderSnowBucketItem extends Item implements Bucket, Repairable {
    // The item returned when this bucket is emptied.
    private final @NotNull Item returnedItem;
    // The block to place when this bucket is emptied.
    private final @NotNull Block block;
    // The sound to make when the block is placed.
    private final @NotNull SoundEvent placeSound;

    /**
     * Creates a new custom powder snow bucket item.
     *
     * @param block        The block to place.
     * @param placeSound   The sound that should play when the block is placed.
     * @param returnedItem The item that should be returned when the block is placed.
     * @param settings     The settings for the item,
     */
    public CustomPowderSnowBucketItem(final @NotNull Block block, final @NotNull SoundEvent placeSound, final @NotNull Item returnedItem, final @NotNull Settings settings) {
        super(settings);
        this.block = block;
        this.placeSound = placeSound;
        this.returnedItem = returnedItem;
    }

    /**
     * Attempts to use the item on a block.
     *
     * @param context The usage context.
     * @return An {@link ActionResult} describing what happens.
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        final PlayerEntity playerInContext = context.getPlayer();

        if (playerInContext != null) {
            final Hand handInContext = context.getHand();
            final ItemStack stackInHand = playerInContext.getStackInHand(handInContext);

            ActionResult actionResult = this.place(new ItemPlacementContext(context));

            if (actionResult.isAccepted() && !playerInContext.isCreative()) {
                final ItemStack returnedItemStack = this.getEmptyItemStack(stackInHand, playerInContext);
                returnedItemStack.damage(1, playerInContext, player -> player.sendToolBreakStatus(handInContext));
                playerInContext.setStackInHand(handInContext, returnedItemStack);
            }

            return actionResult;
        }

        return ActionResult.FAIL;
    }

    /**
     * Attempts to place the block.
     *
     * @param context The item placement context.
     * @return Action result indicating whether the block could be placed.
     */
    private ActionResult place(final ItemPlacementContext context) {
        if (!context.canPlace()) {
            return ActionResult.FAIL;
        } else {
            final BlockState placementState = this.getBlock().getPlacementState(context);
            if (placementState == null || !this.canPlace(context, placementState)) {
                return ActionResult.FAIL;
            } else if (!context.getWorld().setBlockState(context.getBlockPos(), placementState, 11)) {
                return ActionResult.FAIL;
            } else {
                final BlockPos blockPosInContext = context.getBlockPos();
                final World worldInContext = context.getWorld();
                final PlayerEntity playerInContext = context.getPlayer();
                final ItemStack stackInContext = context.getStack();

                final BlockState blockState = worldInContext.getBlockState(blockPosInContext);
                if (blockState.isOf(placementState.getBlock())) {
                    blockState.getBlock()
                            .onPlaced(worldInContext, blockPosInContext, blockState, playerInContext, stackInContext);
                    if (playerInContext instanceof final ServerPlayerEntity serverPlayer) {
                        Criteria.PLACED_BLOCK.trigger(serverPlayer, blockPosInContext, stackInContext);
                    }
                }

                final BlockSoundGroup blockSoundGroup = blockState.getSoundGroup();
                worldInContext.playSound(playerInContext, blockPosInContext, this.placeSound, SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
                worldInContext.emitGameEvent(playerInContext, GameEvent.BLOCK_PLACE, blockPosInContext);
                if (playerInContext == null || !playerInContext.getAbilities().creativeMode) {
                    stackInContext.decrement(1);
                }

                return ActionResult.success(worldInContext.isClient);
            }
        }
    }

    /**
     * Checks if the block can be placed at the given location.
     *
     * @param context The item placement context.
     * @param state   The placement state of the block.
     * @return {@code true} if the block can be placed; otherwise, {@code false}.
     */
    private boolean canPlace(final ItemPlacementContext context, final BlockState state) {
        final PlayerEntity playerEntity = context.getPlayer();
        final ShapeContext shapeContext = playerEntity == null ? ShapeContext.absent() : ShapeContext.of(playerEntity);
        return (state.canPlaceAt(context.getWorld(), context.getBlockPos())) && context.getWorld()
                .canPlace(state, context.getBlockPos(), shapeContext);
    }

    /**
     * Gets the block that this item places.
     *
     * @return The block that this item places.
     */
    public @NotNull Block getBlock() {
        return this.block;
    }

    /**
     * If the bucket is damageable, it should take this much damage when dispensing a specific fluid.
     *
     * @param fluid The fluid to dispense.
     * @return The amount of damage accrued.
     * @since 2.0.0+alpha.1
     */
    @Override
    public int getDamageOnDispenseFluid(@NotNull Fluid fluid) {
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

        return BucketItems.createItemStack(this.returnedItem, sourceStack);
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
        return ItemStack.EMPTY;
    }

    /**
     * Attempts to fill the bucket with the given block.
     * Should return {@link ItemStack#EMPTY} if the bucket cannot be filled with the given block.
     * Note: Blocks can be waterlogged, so return {@link Bucket#getFilledItemStack(ItemStack, Fluid)} passing
     * {@link Fluids#WATER} as the second parameter.
     *
     * @param sourceStack The source item stack
     * @param block       The block to fill the bucket
     * @return An item stack containing the bucket which has been filled with the block, or {@link ItemStack#EMPTY} if
     * the bucket could not be filled with the given block.
     * @since 2.0.0+alpha.1
     */
    @Override
    public @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, @NotNull Block block) {
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
     * @since 2.0.0+alpha.1
     */
    @Override
    public @NotNull ItemStack getFilledItemStack(@NotNull ItemStack sourceStack, LivingEntity entity) {
        return ItemStack.EMPTY;
    }

    /**
     * Attempts to fill the bucket with cow juice.
     * Should return {@link ItemStack#EMPTY} if the concept of cow juice is too weird for the bucket.
     *
     * @param sourceStack The item stack of the bucket being filled.
     * @return The filled item stack.
     * @since 2.0.0+alpha.1
     */
    @Override
    public @NotNull ItemStack getMilkBucketItemStack(@NotNull ItemStack sourceStack) {
        return ItemStack.EMPTY;
    }

    /**
     * Gets the fluid for this bucket.
     *
     * @return The fluid.
     */
    @Override
    public @NotNull Fluid getFluid() {
        return Fluids.EMPTY;
    }
}
