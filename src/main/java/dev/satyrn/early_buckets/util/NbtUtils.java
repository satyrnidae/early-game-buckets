package dev.satyrn.early_buckets.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for NBT tag modification.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public final class NbtUtils {
    // This class cannot be instantiated.
    private NbtUtils() { }

    /**
     * Copies enchantment tags between a source and target stack
     *
     * @param sourceStack The item stack from which the enchantments should be copied.
     * @param targetStack The item stack to which the enchantments should be copied.
     * @since 1.0.0
     */
    @Contract(mutates = "param2")
    public static void copyEnchantmentItemTags(final @NotNull ItemStack sourceStack, final @NotNull ItemStack targetStack) {
        final NbtCompound targetTags = targetStack.getOrCreateNbt();
        if (!targetTags.contains("Enchantments", 9)) {
            targetTags.put("Enchantments", new NbtList());
        }
        final NbtList targetEnchants = targetTags.getList("Enchantments", 10);
        targetEnchants.addAll(sourceStack.getEnchantments());
        targetStack.setNbt(targetTags);
    }
}
