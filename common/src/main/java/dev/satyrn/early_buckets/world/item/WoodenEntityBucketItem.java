package dev.satyrn.early_buckets.world.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

/**
 * Implementation for wooden fish buckets.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class WoodenEntityBucketItem extends CustomEntityBucketItem {
    /**
     * Initializes a new wooden fish bucket item.
     *
     * @param type     The entity type.
     * @param fluid    The fluid.
     * @param settings The item initialization settings.
     *
     * @since 1.0.0
     */
    public WoodenEntityBucketItem(final EntityType<?> type,
                                  final Fluid fluid,
                                  final SoundEvent emptyingSound,
                                  final Properties settings) {
        super(type, fluid, emptyingSound, settings);
    }

    /**
     * Gets the emptied item for this bucket.
     *
     * @return The emptied item for this bucket
     *
     * @since 1.0.0
     */
    @Override
    public Item getEmptyItem() {
        return BucketItems.WOODEN_BUCKET.get();
    }
}
