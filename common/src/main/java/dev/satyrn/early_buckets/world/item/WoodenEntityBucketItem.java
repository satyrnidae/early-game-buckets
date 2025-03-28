package dev.satyrn.early_buckets.world.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
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
    public WoodenEntityBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Properties settings) {
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
        return BucketItems.WOODEN_BUCKET.get();
    }
}
