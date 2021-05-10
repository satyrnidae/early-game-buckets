package dev.satyrn.silk.new_buckets.item;

import dev.satyrn.silk.new_buckets.NewBucketsMod;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.FluidTags;

/**
 * Implementation for a wooden bucket item.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class WoodenBucketItem extends CustomBucketItem {
    /**
     * Represents a wooden bucket
     * @param fluid The fluid which this bucket contains.
     * @param settings The item initialization settings.
     * @since 1.0.0
     */
    public WoodenBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings);
    }

    /**
     * Gets the filled item for the bucket.
     * @param fluid The fluid with which to attempt to fill the bucket.
     * @return The item for a given fluid, or {@code Items.AIR} if the bucket cannot be filled.
     */
    @Override
    public Item getFilledItem(Fluid fluid) {
        if (fluid.isIn(FluidTags.WATER)) {
            return NewBucketsMod.WOODEN_WATER_BUCKET;
        }
        //TODO: support custom liquids (somehow)
        return super.getFilledItem(fluid);
    }

    /**
     * Gets the emptied item for this bucket.
     *
     * @return The emptied item for this bucket
     * @since 1.0.0
     */
    @Override
    protected Item getEmptyItem() {
        return NewBucketsMod.WOODEN_BUCKET;
    }
}
