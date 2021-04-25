package dev.satyrn.silk.new_buckets.item;

import net.minecraft.item.ItemStack;

/**
 * Interface for items which can optionally disable the vanilla repair feature.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public interface DisableRepairItem {
    /**
     *
     * @param stack
     * @return
     */
    default boolean canRepairViaCrafting(ItemStack stack) {
        return true;
    }
}
