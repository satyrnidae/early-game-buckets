package dev.satyrn.early_buckets.item;

import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
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
    public CeramicEntityBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
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
        return BucketItems.CERAMIC_BUCKET;
    }

    /**
     * Gets the translation key for an item stack.
     *
     * @param stack The stack that requires a translation key.
     * @return The translation key. If the bucket stack damage is over 87.5%, appends .cracked to the translation key.
     */
    public String getTranslationKey(ItemStack stack) {
        String translationKey = this.getTranslationKey();
        if ((float) stack.getDamage() / stack.getMaxDamage() >= 0.875F) {
            translationKey += ".cracked";
        }

        return translationKey;
    }
}
