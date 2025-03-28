package dev.satyrn.early_buckets.core.cauldron;

import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.world.item.CustomBucket;
import dev.satyrn.early_buckets.world.item.BucketItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Predicate;

/**
 * Custom cauldron behaviors for the Early-Game Buckets mod.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public final class CustomBucketCauldronInteractions {

    // Fill a cauldron with lava.
    private static final CauldronInteraction FILL_WITH_LAVA = (state, world, pos, player, hand, stack) -> emptyBucket(world, pos, player, hand, stack, Blocks.LAVA_CAULDRON.defaultBlockState(), SoundEvents.BUCKET_EMPTY_LAVA);
    // Fill a cauldron with water.
    private static final CauldronInteraction FILL_WITH_WATER = (state, world, pos, player, hand, stack) -> emptyBucket(world, pos, player, hand, stack, Blocks.WATER_CAULDRON.defaultBlockState()
            .setValue(LayeredCauldronBlock.LEVEL, 3), SoundEvents.BUCKET_EMPTY);
    // Fill a cauldron with powder snow.
    private static final CauldronInteraction FILL_WITH_POWDER_SNOW = (state, world, pos, player, hand, stack) -> emptyBucket(world, pos, player, hand, stack, Blocks.POWDER_SNOW_CAULDRON.defaultBlockState()
            .setValue(LayeredCauldronBlock.LEVEL, 3), SoundEvents.BUCKET_EMPTY_POWDER_SNOW);

    // Do not instantiate BucketsCauldronBehavior
    private CustomBucketCauldronInteractions() {}

    /**
     * Registers cauldron behaviors for the new bucket types.
     */
    public static void postRegister() {
        // Fill the cauldron with lava from a ceramic bucket
        CauldronInteraction.EMPTY.put(BucketItems.CERAMIC_LAVA_BUCKET.get(), FILL_WITH_LAVA);
        CauldronInteraction.WATER.put(BucketItems.CERAMIC_LAVA_BUCKET.get(), FILL_WITH_LAVA);
        CauldronInteraction.LAVA.put(BucketItems.CERAMIC_LAVA_BUCKET.get(), FILL_WITH_LAVA);
        CauldronInteraction.POWDER_SNOW.put(BucketItems.CERAMIC_LAVA_BUCKET.get(), FILL_WITH_LAVA);

        // Fill the cauldron with water from a ceramic bucket
        CauldronInteraction.EMPTY.put(BucketItems.CERAMIC_WATER_BUCKET.get(), FILL_WITH_WATER);
        CauldronInteraction.WATER.put(BucketItems.CERAMIC_WATER_BUCKET.get(), FILL_WITH_WATER);
        CauldronInteraction.LAVA.put(BucketItems.CERAMIC_WATER_BUCKET.get(), FILL_WITH_WATER);
        CauldronInteraction.POWDER_SNOW.put(BucketItems.CERAMIC_WATER_BUCKET.get(), FILL_WITH_WATER);

        // Fill the cauldron with powder snow from a ceramic bucket
        CauldronInteraction.EMPTY.put(BucketItems.CERAMIC_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);
        CauldronInteraction.WATER.put(BucketItems.CERAMIC_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);
        CauldronInteraction.LAVA.put(BucketItems.CERAMIC_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);
        CauldronInteraction.POWDER_SNOW.put(BucketItems.CERAMIC_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);

        // Fill the cauldron with water from a clay bucket
        CauldronInteraction.EMPTY.put(BucketItems.CLAY_WATER_BUCKET.get(), FILL_WITH_WATER);
        CauldronInteraction.WATER.put(BucketItems.CLAY_WATER_BUCKET.get(), FILL_WITH_WATER);
        CauldronInteraction.LAVA.put(BucketItems.CLAY_WATER_BUCKET.get(), FILL_WITH_WATER);
        CauldronInteraction.POWDER_SNOW.put(BucketItems.CLAY_WATER_BUCKET.get(), FILL_WITH_WATER);

        // Fill the cauldron with powder snow from a clay bucket
        CauldronInteraction.EMPTY.put(BucketItems.CLAY_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);
        CauldronInteraction.WATER.put(BucketItems.CLAY_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);
        CauldronInteraction.LAVA.put(BucketItems.CLAY_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);
        CauldronInteraction.POWDER_SNOW.put(BucketItems.CLAY_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);

        // Fill the cauldron with water from a wooden bucket
        CauldronInteraction.EMPTY.put(BucketItems.WOODEN_WATER_BUCKET.get(), FILL_WITH_WATER);
        CauldronInteraction.WATER.put(BucketItems.WOODEN_WATER_BUCKET.get(), FILL_WITH_WATER);
        CauldronInteraction.LAVA.put(BucketItems.WOODEN_WATER_BUCKET.get(), FILL_WITH_WATER);
        CauldronInteraction.POWDER_SNOW.put(BucketItems.WOODEN_WATER_BUCKET.get(), FILL_WITH_WATER);

        // Fill the cauldron with powder snow from a wooden bucket
        CauldronInteraction.EMPTY.put(BucketItems.WOODEN_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);
        CauldronInteraction.WATER.put(BucketItems.WOODEN_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);
        CauldronInteraction.LAVA.put(BucketItems.WOODEN_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);
        CauldronInteraction.POWDER_SNOW.put(BucketItems.WOODEN_POWDER_SNOW_BUCKET.get(), FILL_WITH_POWDER_SNOW);

        // Fill a ceramic bucket with lava
        CauldronInteraction.LAVA.put(BucketItems.CERAMIC_BUCKET.get(), (state, world, pos, player, hand, stack) -> fillBucket(state, world, pos, player, hand, stack, blockState -> true, SoundEvents.BUCKET_FILL_LAVA));
        // Fill a clay bucket with lava
        CauldronInteraction.LAVA.put(BucketItems.CLAY_BUCKET.get(), (state, world, pos, player, hand, stack) -> fillBucket(state, world, pos, player, hand, stack, blockState -> true, SoundEvents.BUCKET_FILL_LAVA));

        // Fill a ceramic bucket with water
        CauldronInteraction.WATER.put(BucketItems.CERAMIC_BUCKET.get(), (state, world, pos, player, hand, stack) -> fillBucket(state, world, pos, player, hand, stack, blockState -> blockState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL_LAVA));
        // Fill a clay bucket with water
        CauldronInteraction.WATER.put(BucketItems.CLAY_BUCKET.get(), (state, world, pos, player, hand, stack) -> fillBucket(state, world, pos, player, hand, stack, blockState -> blockState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL));
        // Fill a wooden bucket with water
        CauldronInteraction.WATER.put(BucketItems.WOODEN_BUCKET.get(), (state, world, pos, player, hand, stack) -> fillBucket(state, world, pos, player, hand, stack, blockState -> blockState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL));

        // Fill a ceramic bucket with powder snow
        CauldronInteraction.POWDER_SNOW.put(BucketItems.CERAMIC_BUCKET.get(), (state, world, pos, player, hand, stack) -> fillBucket(state, world, pos, player, hand, stack, blockState -> blockState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL_POWDER_SNOW));
        // Fill a clay bucket with powder snow
        CauldronInteraction.POWDER_SNOW.put(BucketItems.CLAY_BUCKET.get(), (state, world, pos, player, hand, stack) -> fillBucket(state, world, pos, player, hand, stack, blockState -> blockState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL_POWDER_SNOW));
        // Fill a ceramic bucket with water
        CauldronInteraction.POWDER_SNOW.put(BucketItems.WOODEN_BUCKET.get(), (state, world, pos, player, hand, stack) -> fillBucket(state, world, pos, player, hand, stack, blockState -> blockState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL_POWDER_SNOW));
    }

    /**
     * Attempts to fill a cauldron.
     *
     * @param world      The world in which the cauldron is being filled.
     * @param pos        The block position of the cauldron.
     * @param player     The player who is attempting to fill the cauldron.
     * @param hand       The player's hand which is holding the item being used to fill the cauldron.
     * @param stack      The item being used to fill the cauldron.
     * @param state      The new state of the block.
     * @param soundEvent The sound that should be played if the cauldron is filled.
     * @return The action result for the item.
     */
    private static InteractionResult emptyBucket(Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, BlockState state, SoundEvent soundEvent) {
        if (!world.isClientSide) {
            final Item item = stack.getItem();
            ItemStack returningStack = new ItemStack(Items.BUCKET);
            if (item instanceof final CustomBucket bucket) {
                returningStack = bucket.getEmptyItemStack(stack, player);
                // All necessary checks are done in the damage method. ;)
                returningStack.hurtAndBreak(bucket.getDamageOnDispenseFluid(bucket.getFluid()), player, playerEntity -> playerEntity.broadcastBreakEvent(hand));
            }
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, returningStack));
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(item));
            world.setBlockAndUpdate(pos, state);
            world.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
            world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
        }

        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    /**
     * Attempts to empty a cauldron.
     *
     * @param state      The state of the cauldron being emptied.
     * @param world      The world in which the cauldron resides.
     * @param pos        The block position of the cauldron.
     * @param player     The player who is emptying the cauldron.
     * @param hand       The player's hand which is holding the item being used to empty the cauldron.
     * @param stack      The item that the cauldron is being emptied into.
     * @param predicate  Whether the cauldron's current block state will allow the bucket to be filled.
     * @param soundEvent The sound that should be played if the cauldron is emptied.
     * @return The action result for the item.
     */
    private static InteractionResult fillBucket(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, Predicate<BlockState> predicate, SoundEvent soundEvent) {
        InteractionResult result = InteractionResult.sidedSuccess(world.isClientSide);
        if (!world.isClientSide) {
            final ItemStack initialStack = stack.copy();
            ItemStack returnedStack;
            final Item item = initialStack.getItem();

            boolean isWaterCauldron = Blocks.WATER_CAULDRON.equals(state.getBlock());
            boolean isLavaCauldron = Blocks.LAVA_CAULDRON.equals(state.getBlock());
            boolean isPowderSnowCauldron = Blocks.POWDER_SNOW_CAULDRON.equals(state.getBlock());

            if (item instanceof final CustomBucket bucket) {
                if (isWaterCauldron) {
                    returnedStack = bucket.getFilledItemStack(stack, Fluids.WATER);
                } else if (isLavaCauldron) {
                    returnedStack = bucket.getFilledItemStack(stack, Fluids.LAVA);
                } else if (isPowderSnowCauldron) {
                    returnedStack = bucket.getFilledItemStack(stack, Blocks.POWDER_SNOW);
                } else {
                    returnedStack = stack;
                }
            } else {
                final Item returnedItem = isWaterCauldron ? Items.WATER_BUCKET : isLavaCauldron ? Items.LAVA_BUCKET : isPowderSnowCauldron ? Items.POWDER_SNOW_BUCKET : item;
                returnedStack = new ItemStack(returnedItem);
            }

            if (returnedStack.isEmpty()) {
                returnedStack = stack;
            } else if (Items.CLAY_BALL.equals(returnedStack.getItem()) && world.random.nextInt(20) == 0) {
                returnedStack = BucketItems.createItemStack(BucketItems.CLAY_WATER_BUCKET.get(), stack);
            }

            // Keep the returned stack to use later instead of letting it be destroyed by ItemUsage
            final ItemStack returnedStackCopy = returnedStack.copy();

            result = CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, returnedStack, predicate, soundEvent);
            if (result.consumesAction()) {
                if (item instanceof final CustomBucket bucket) {
                    bucket.playSoundOnFill(player, returnedStackCopy);
                }
                if (player instanceof final ServerPlayer serverPlayer) {
                    BucketModCommon.FILL_BUCKET.trigger(serverPlayer, initialStack, returnedStackCopy);
                }
            }
        }

        return result;
    }
}
