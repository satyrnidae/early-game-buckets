package dev.satyrn.early_buckets.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Models a ceramic bucket that is full of milk.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class CeramicMilkBucketItem extends CustomMilkBucketItem {

    /**
     * Initializes the custom milk bucket class.
     *
     * @param settings The item initialization settings.
     * @since 1.0.0
     */
    public CeramicMilkBucketItem(Settings settings) {
        super(settings);
    }

    /**
     * Gets the emptied item for this bucket.
     *
     * @return The emptied item for this bucket
     * @since 1.0.0
     */
    @Override
    protected Item getEmptyItem() {
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
