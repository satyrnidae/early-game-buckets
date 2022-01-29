package dev.satyrn.early_buckets.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

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
     * @since 1.0.0
     */
    public CustomMilkBucketItem(Settings settings) {
        super(settings);
    }

    /**
     * Finishes using the item.
     *
     * @param stack The item being used.
     * @param world The world in which the item is being used.
     * @param user  The user of the item.
     * @return The resulting item stack.
     * @since 1.0.0
     */
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof final ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        Hand hand = user.getStackInHand(Hand.MAIN_HAND) == stack ? Hand.MAIN_HAND : Hand.OFF_HAND;

        if (user instanceof final PlayerEntity player && !player.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        if (!world.isClient) {
            user.clearStatusEffects();
        }

        ItemStack emptyBucket = stack.isEmpty() ? BucketItems.createItemStack(this.getEmptyItem(), stack) : stack;
        emptyBucket.damage(1, user, entity -> entity.sendToolBreakStatus(hand));

        return emptyBucket.isEmpty() ? ItemStack.EMPTY : emptyBucket;
    }

    /**
     * Gets the empty bucket item for this bucket of milk.
     *
     * @return The empty bucket item.
     * @since 1.0.0
     */
    protected abstract Item getEmptyItem();

    /**
     * Checks if the item can be repaired with a given ingredient.
     *
     * @param stack      The stack to repair.
     * @param ingredient The ingredient to repair with.
     * @return {@code false}, as milk buckets cannot be repaired.
     * @since 1.0.0
     */
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    /**
     * Disables repair of this item via the crafting grid.
     *
     * @return {@code false}, as milk buckets cannot be repaired.
     * @since 1.0.0
     */
    @Override
    public boolean canRepairByCrafting() {
        return false;
    }


}
