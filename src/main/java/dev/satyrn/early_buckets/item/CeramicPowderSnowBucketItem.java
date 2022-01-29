package dev.satyrn.early_buckets.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Models a ceramic bucket that is filled with powder snow.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public class CeramicPowderSnowBucketItem extends CustomPowderSnowBucketItem {
    /**
     * Creates a new custom powder snow bucket item.
     *
     * @param block      The block to place.
     * @param placeSound The sound that should play when the block is placed.
     * @param settings   The settings for the item,
     */
    public CeramicPowderSnowBucketItem(@NotNull Block block, @NotNull SoundEvent placeSound, @NotNull Settings settings) {
        super(block, placeSound, BucketItems.CERAMIC_BUCKET, settings);
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
