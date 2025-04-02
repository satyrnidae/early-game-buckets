package dev.satyrn.early_buckets.world.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;

/**
 * Powder snow item for custom buckets.
 * Most of this is copied from ItemBlock to prevent overriding the item for the Powder Snow block.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public class CustomPowderSnowBucketItem extends Item implements CustomBucket, Repairable {
    // The item returned when this bucket is emptied.
    private final Item returnedItem;
    // The block to place when this bucket is emptied.
    private final Block block;
    // The sound to make when the block is placed.
    private final SoundEvent placeSound;

    /**
     * Creates a new custom powder snow bucket item.
     *
     * @param block        The block to place.
     * @param placeSound   The sound that should play when the block is placed.
     * @param returnedItem The item that should be returned when the block is placed.
     * @param settings     The settings for the item,
     */
    public CustomPowderSnowBucketItem(final Block block,
                                      final SoundEvent placeSound,
                                      final Item returnedItem,
                                      final Properties settings) {
        super(settings);
        this.block = block;
        this.placeSound = placeSound;
        this.returnedItem = returnedItem;
    }

    /**
     * Attempts to use the item on a block.
     *
     * @param context The usage context.
     *
     * @return An {@link net.minecraft.world.InteractionResult} describing what happens.
     */
    @Override
    public InteractionResult useOn(final UseOnContext context) {
        final @Nullable Player playerInContext = context.getPlayer();

        if (playerInContext != null) {
            final InteractionHand handInContext = context.getHand();
            final ItemStack stackInHand = playerInContext.getItemInHand(handInContext);

            InteractionResult actionResult = this.place(new BlockPlaceContext(context));

            if (actionResult.consumesAction() && !playerInContext.isCreative()) {
                final ItemStack returnedItemStack = this.getEmptyItemStack(stackInHand, playerInContext);
                returnedItemStack.hurtAndBreak(1, playerInContext, player -> player.broadcastBreakEvent(handInContext));
                playerInContext.setItemInHand(handInContext, returnedItemStack);
            }

            return actionResult;
        }

        return InteractionResult.FAIL;
    }

    /**
     * Attempts to place the block.
     *
     * @param context The item placement context.
     *
     * @return Action result indicating whether the block could be placed.
     */
    private InteractionResult place(final BlockPlaceContext context) {
        if (!context.canPlace()) {
            return InteractionResult.FAIL;
        } else {
            final @Nullable BlockState placementState = this.getBlock().getStateForPlacement(context);
            if (placementState == null || !this.canPlace(context, placementState)) {
                return InteractionResult.FAIL;
            } else if (!context.getLevel().setBlockAndUpdate(context.getClickedPos(), placementState)) {
                return InteractionResult.FAIL;
            } else {
                final BlockPos blockPosInContext = context.getClickedPos();
                final Level worldInContext = context.getLevel();
                final @Nullable Player playerInContext = context.getPlayer();
                final ItemStack stackInContext = context.getItemInHand();

                final BlockState blockState = worldInContext.getBlockState(blockPosInContext);
                if (blockState.is(placementState.getBlock())) {
                    blockState.getBlock()
                            .setPlacedBy(worldInContext, blockPosInContext, blockState, playerInContext,
                                    stackInContext);
                    if (playerInContext instanceof final ServerPlayer serverPlayer) {
                        CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, blockPosInContext, stackInContext);
                    }
                }

                final SoundType blockSoundGroup = blockState.getSoundType();
                worldInContext.playSound(playerInContext, blockPosInContext, this.placeSound, SoundSource.BLOCKS,
                        (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
                worldInContext.gameEvent(playerInContext, GameEvent.BLOCK_PLACE, blockPosInContext);
                if (playerInContext != null &&
                        !playerInContext.getAbilities().instabuild &&
                        !worldInContext.isClientSide) {
                    stackInContext.hurtAndBreak(this.getDamageOnDispenseBlock(this.block), playerInContext,
                            player -> player.broadcastBreakEvent(context.getHand()));
                }

                return InteractionResult.sidedSuccess(worldInContext.isClientSide);
            }
        }
    }

    /**
     * Checks if the block can be placed at the given location.
     *
     * @param context The item placement context.
     * @param state   The placement state of the block.
     *
     * @return {@code true} if the block can be placed; otherwise, {@code false}.
     */
    private boolean canPlace(final BlockPlaceContext context, final BlockState state) {
        final @Nullable Player playerEntity = context.getPlayer();
        return (playerEntity != null && state.canSurvive(context.getLevel(), context.getClickedPos())) &&
                context.getLevel().mayInteract(playerEntity, context.getClickedPos());
    }

    /**
     * Gets the block that this item places.
     *
     * @return The block that this item places.
     */
    public Block getBlock() {
        return this.block;
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

        return BucketItems.createItemStack(this.returnedItem, sourceStack);
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
    public ItemStack getFilledItemStack(final ItemStack sourceStack, final Fluid fluid) {
        return ItemStack.EMPTY;
    }

    /**
     * Attempts to fill the bucket with the given block.
     * Should return {@link ItemStack#EMPTY} if the bucket cannot be filled with the given block.
     * Note: Blocks can be waterlogged, so return {@link CustomBucket#getFilledItemStack(ItemStack, Fluid)} passing
     * {@link net.minecraft.world.level.material.Fluids#WATER} as the second parameter.
     *
     * @param sourceStack The source item stack
     * @param block       The block to fill the bucket
     *
     * @return An item stack containing the bucket which has been filled with the block, or {@link ItemStack#EMPTY} if
     * the bucket could not be filled with the given block.
     *
     * @since 2.0.0+alpha.1
     */
    @Override
    public ItemStack getFilledItemStack(final ItemStack sourceStack, final Block block) {
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
     *
     * @since 2.0.0+alpha.1
     */
    @Override
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
     *
     * @since 2.0.0+alpha.1
     */
    @Override
    public ItemStack getMilkBucketItemStack(final ItemStack sourceStack) {
        return ItemStack.EMPTY;
    }

    /**
     * Gets the fluid for this bucket.
     *
     * @return The fluid.
     */
    @Override
    public Fluid getFluid() {
        return Fluids.EMPTY;
    }
}
