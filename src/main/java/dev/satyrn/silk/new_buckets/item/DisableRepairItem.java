package dev.satyrn.silk.new_buckets.item;

import net.minecraft.item.ItemStack;

/**
 * Interface for items which can optionally disable the vanilla repair feature.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
//TODO: Definite candidate for the Moon Moth API.
public interface DisableRepairItem {
    /**
     * Gets a boolean which will determine whether or not this item should be repairable in the crafting grid.
     * @param stack The item stack to repair.
     * @return {@code true} if the item should be able to be repaired, otherwise {@code false}
     */
    default boolean canRepairViaCrafting(ItemStack stack) {
        return true;
    }
}
