package dev.satyrn.early_buckets.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for NBT tag modification.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public final class NbtUtils {
    // This class cannot be instantiated.
    private NbtUtils() {
    }

    /**
     * Copies enchantment tags between a source and target stack
     *
     * @param sourceStack The item stack from which the enchantments should be copied.
     * @param targetStack The item stack to which the enchantments should be copied.
     *
     * @since 1.0.0
     */
    public static void copyEnchantmentItemTags(final @NotNull ItemStack sourceStack,
                                               final @NotNull ItemStack targetStack) {
        final CompoundTag targetTags = targetStack.getOrCreateTag();
        if (!targetTags.contains("Enchantments", 9)) {
            targetTags.put("Enchantments", new ListTag());
        }
        final ListTag targetEnchants = targetTags.getList("Enchantments", 10);
        targetEnchants.addAll(sourceStack.getEnchantmentTags());
        targetStack.setTag(targetTags);
    }
}
