package dev.satyrn.early_buckets.world.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
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
    public CeramicPowderSnowBucketItem(final @NotNull Block block,
                                       final @NotNull SoundEvent placeSound,
                                       final @NotNull Properties settings) {
        super(block, placeSound, BucketItems.CERAMIC_BUCKET.get(), settings);
    }

    /**
     * Gets the translation key for an item stack.
     *
     * @param stack The stack that requires a translation key.
     *
     * @return The translation key. If the bucket stack damage is over 87.5%, appends .cracked to the translation key.
     */
    @Override
    public String getDescriptionId(final @NotNull ItemStack stack) {
        String translationKey = this.getDescriptionId();
        if ((float) stack.getDamageValue() / stack.getMaxDamage() >= 0.875F) {
            translationKey += ".cracked";
        }

        return translationKey;
    }
}
