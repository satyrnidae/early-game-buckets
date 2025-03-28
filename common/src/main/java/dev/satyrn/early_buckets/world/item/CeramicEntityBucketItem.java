package dev.satyrn.early_buckets.world.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation for ceramic fish buckets.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class CeramicEntityBucketItem extends CustomEntityBucketItem {
    /**
     * Initializes a new ceramic fish bucket item.
     *
     * @param type     The entity type.
     * @param fluid    The fluid.
     * @param settings The item initialization settings.
     * @since 1.0.0
     */
    public CeramicEntityBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Properties settings) {
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
        return BucketItems.CERAMIC_BUCKET.get();
    }

    /**
     * Gets the translation key for an item stack.
     *
     * @param stack The stack that requires a translation key.
     * @return The translation key. If the bucket stack damage is over 87.5%, appends .cracked to the translation key.
     */
    @Override
    public @NotNull String getDescriptionId(ItemStack stack) {
        String translationKey = this.getDescriptionId();
        if ((float) stack.getDamageValue() / stack.getMaxDamage() >= 0.875F) {
            translationKey += ".cracked";
        }

        return translationKey;
    }
}
