package dev.satyrn.silk.new_buckets.item;

import dev.satyrn.silk.new_buckets.NewBucketsMod;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

/**
 * Implementation for ceramic fish buckets.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class CeramicFishBucketItem extends CustomFishBucketItem {
    /**
     * Initializes a new ceramic fish bucket item.
     * @param type The entity type.
     * @param fluid The fluid.
     * @param settings The item initialization settings.
     * @since 1.0.0
     */
    public CeramicFishBucketItem(EntityType<?> type, Fluid fluid, Settings settings) {
        super(type, fluid, settings);
    }

    /**
     * Gets the emptied item for this bucket.
     * @return The emptied item for this bucket
     * @since 1.0.0
     */
    @Override
    protected Item getEmptyItem() {
        return NewBucketsMod.CERAMIC_BUCKET;
    }
}
