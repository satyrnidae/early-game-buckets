package dev.satyrn.early_buckets.block.cauldron;

import dev.satyrn.early_buckets.BucketMod;
import dev.satyrn.early_buckets.item.Bucket;
import dev.satyrn.early_buckets.item.BucketItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Predicate;

import static net.minecraft.block.cauldron.CauldronBehavior.*;

/**
 * Custom cauldron behaviors for the Early-Game Buckets mod.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public final class BucketCauldronBehaviors {

    // Fill a cauldron with lava.
    private static final CauldronBehavior FILL_WITH_LAVA = (state, world, pos, player, hand, stack) -> fillCauldron(world, pos, player, hand, stack, Blocks.LAVA_CAULDRON.getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
    // Fill a cauldron with water.
    private static final CauldronBehavior FILL_WITH_WATER = (state, world, pos, player, hand, stack) -> fillCauldron(world, pos, player, hand, stack, Blocks.WATER_CAULDRON.getDefaultState()
            .with(LeveledCauldronBlock.LEVEL, 3), SoundEvents.ITEM_BUCKET_EMPTY);
    // Fill a cauldron with powder snow.
    private static final CauldronBehavior FILL_WITH_POWDER_SNOW = (state, world, pos, player, hand, stack) -> fillCauldron(world, pos, player, hand, stack, Blocks.POWDER_SNOW_CAULDRON.getDefaultState()
            .with(LeveledCauldronBlock.LEVEL, 3), SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW);

    // Do not instantiate BucketsCauldronBehavior
    private BucketCauldronBehaviors() {
    }

    /**
     * Registers cauldron behaviors for the new bucket types.
     */
    public static void registerAll() {
        // Fill the cauldron with lava from a ceramic bucket
        EMPTY_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_LAVA_BUCKET, FILL_WITH_LAVA);
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_LAVA_BUCKET, FILL_WITH_LAVA);
        LAVA_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_LAVA_BUCKET, FILL_WITH_LAVA);
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_LAVA_BUCKET, FILL_WITH_LAVA);

        // Fill the cauldron with water from a ceramic bucket
        EMPTY_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_WATER_BUCKET, FILL_WITH_WATER);
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_WATER_BUCKET, FILL_WITH_WATER);
        LAVA_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_WATER_BUCKET, FILL_WITH_WATER);
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_WATER_BUCKET, FILL_WITH_WATER);

        // Fill the cauldron with powder snow from a ceramic bucket
        EMPTY_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
        LAVA_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);

        // Fill the cauldron with water from a clay bucket
        EMPTY_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_WATER_BUCKET, FILL_WITH_WATER);
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_WATER_BUCKET, FILL_WITH_WATER);
        LAVA_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_WATER_BUCKET, FILL_WITH_WATER);
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_WATER_BUCKET, FILL_WITH_WATER);

        // Fill the cauldron with powder snow from a clay bucket
        EMPTY_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
        LAVA_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);

        // Fill the cauldron with water from a wooden bucket
        EMPTY_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_WATER_BUCKET, FILL_WITH_WATER);
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_WATER_BUCKET, FILL_WITH_WATER);
        LAVA_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_WATER_BUCKET, FILL_WITH_WATER);
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_WATER_BUCKET, FILL_WITH_WATER);

        // Fill the cauldron with powder snow from a wooden bucket
        EMPTY_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
        LAVA_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);

        // Fill a ceramic bucket with lava
        LAVA_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, blockState -> true, SoundEvents.ITEM_BUCKET_FILL_LAVA));
        // Fill a clay bucket with lava
        LAVA_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, blockState -> true, SoundEvents.ITEM_BUCKET_FILL_LAVA));

        // Fill a ceramic bucket with water
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, blockState -> blockState.get(LeveledCauldronBlock.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL));
        // Fill a clay bucket with water
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, blockState -> blockState.get(LeveledCauldronBlock.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL));
        // Fill a wooden bucket with water
        WATER_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, blockState -> blockState.get(LeveledCauldronBlock.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL));

        // Fill a ceramic bucket with powder snow
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.CERAMIC_BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, blockState -> blockState.get(LeveledCauldronBlock.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW));
        // Fill a clay bucket with powder snow
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.CLAY_BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, blockState -> blockState.get(LeveledCauldronBlock.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW));
        // Fill a ceramic bucket with water
        POWDER_SNOW_CAULDRON_BEHAVIOR.put(BucketItems.WOODEN_BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, blockState -> blockState.get(LeveledCauldronBlock.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW));
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
    private static ActionResult fillCauldron(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, BlockState state, SoundEvent soundEvent) {
        if (!world.isClient) {
            final Item item = stack.getItem();
            ItemStack returningStack = new ItemStack(Items.BUCKET);
            if (item instanceof final Bucket bucket) {
                returningStack = bucket.getEmptyItemStack(stack, player);
                // All necessary checks are done in the damage method. ;)
                returningStack.damage(bucket.getDamageOnDispenseFluid(bucket.getFluid()), player, playerEntity -> playerEntity.sendToolBreakStatus(hand));
            }
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, returningStack));
            player.incrementStat(Stats.FILL_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, state);
            world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        }

        return ActionResult.success(world.isClient);
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
    private static ActionResult emptyCauldron(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, Predicate<BlockState> predicate, SoundEvent soundEvent) {
        ActionResult result = ActionResult.success(world.isClient);
        if (!world.isClient) {
            final ItemStack initialStack = stack.copy();
            ItemStack returnedStack;
            final Item item = initialStack.getItem();

            boolean isWaterCauldron = Blocks.WATER_CAULDRON.equals(state.getBlock());
            boolean isLavaCauldron = Blocks.LAVA_CAULDRON.equals(state.getBlock());
            boolean isPowderSnowCauldron = Blocks.POWDER_SNOW_CAULDRON.equals(state.getBlock());

            if (item instanceof final Bucket bucket) {
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
                returnedStack = BucketItems.createItemStack(BucketItems.CLAY_WATER_BUCKET, stack);
            }

            // Keep the returned stack to use later instead of letting it be destroyed by ItemUsage
            final ItemStack returnedStackCopy = returnedStack.copy();

            result = CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, returnedStack, predicate, soundEvent);
            if (result.isAccepted()) {
                if (item instanceof final Bucket bucket) {
                    bucket.playSoundOnFill(player, returnedStackCopy);
                }
                if (player instanceof final ServerPlayerEntity serverPlayer) {
                    BucketMod.FILL_BUCKET.trigger(serverPlayer, initialStack, returnedStackCopy);
                }
            }
        }

        return result;
    }
}
