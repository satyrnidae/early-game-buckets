package dev.satyrn.early_buckets.item;

import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;

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
     * @since 1.0.0
     */
    public WoodenEntityBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
        super(type, fluid, emptyingSound, settings);
    }

    /**
     * Gets the emptied item for this bucket.
     *
     * @return The emptied item for this bucket
     * @since 1.0.0
     */
    @Override
    public @NotNull Item getEmptyItem() {
        return BucketItems.WOODEN_BUCKET;
    }
}
