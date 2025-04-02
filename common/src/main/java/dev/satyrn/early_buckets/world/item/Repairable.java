package dev.satyrn.early_buckets.world.item;

/**
 * Interface for items which can optionally disable the vanilla repair feature.
 * Renamed from DisableRepairItem.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public interface Repairable {
    /**
     * Gets a boolean which will determine whether this item should be repairable in the crafting grid.
     * Renamed form canRepairViaCrafting(ItemStack).
     *
     * @return {@code true} if the item should be able to be repaired, otherwise {@code false}
     *
     * @since 2.0.0+alpha.1
     */
    default boolean canRepairByCrafting() {
        return true;
    }
}
