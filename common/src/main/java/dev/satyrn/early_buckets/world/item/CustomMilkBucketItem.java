package dev.satyrn.early_buckets.world.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;

/**
 * Custom item for milk bucket implementations.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public abstract class CustomMilkBucketItem extends MilkBucketItem implements Repairable {

    /**
     * Initializes the custom milk bucket class.
     *
     * @param settings The item initialization settings.
     *
     * @since 1.0.0
     */
    public CustomMilkBucketItem(final Properties settings) {
        super(settings);
    }

    /**
     * Finishes using the item.
     *
     * @param stack The item being used.
     * @param world The world in which the item is being used.
     * @param user  The user of the item.
     *
     * @return The resulting item stack.
     *
     * @since 1.0.0
     */
    @Override
    public ItemStack finishUsingItem(final ItemStack stack,
                                     final Level world,
                                     final LivingEntity user) {
        if (user instanceof final ServerPlayer serverPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.awardStat(Stats.ITEM_USED.get(this));
        }

        final InteractionHand hand = user.getMainHandItem() == stack
                ? InteractionHand.MAIN_HAND
                : InteractionHand.OFF_HAND;

        if (!world.isClientSide) {
            user.removeAllEffects();
        }

        ItemStack emptyBucket = user instanceof Player player && !player.getAbilities().instabuild
                ? BucketItems.createItemStack(this.getEmptyItem(), stack)
                : stack;
        emptyBucket.setDamageValue(stack.getDamageValue());
        emptyBucket.hurtAndBreak(1, user, entity -> entity.broadcastBreakEvent(hand));

        return emptyBucket.isEmpty() ? ItemStack.EMPTY : emptyBucket;
    }

    /**
     * Gets the empty bucket item for this bucket of milk.
     *
     * @return The empty bucket item.
     *
     * @since 1.0.0
     */
    protected abstract Item getEmptyItem();

    /**
     * Checks if the item can be repaired with a given ingredient.
     *
     * @param stack      The stack to repair.
     * @param ingredient The ingredient to repair with.
     *
     * @return {@code false}, as milk buckets cannot be repaired.
     *
     * @since 1.0.0
     */
    @Override
    public boolean isValidRepairItem(final ItemStack stack, final ItemStack ingredient) {
        return false;
    }

    /**
     * Disables repair of this item via the crafting grid.
     *
     * @return {@code false}, as milk buckets cannot be repaired.
     *
     * @since 1.0.0
     */
    @Override
    public boolean canRepairByCrafting() {
        return false;
    }


}
