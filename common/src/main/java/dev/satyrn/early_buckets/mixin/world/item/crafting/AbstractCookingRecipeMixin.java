package dev.satyrn.early_buckets.mixin.world.item.crafting;

import net.minecraft.core.NonNullList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Objects;

@Unique
@Mixin(AbstractCookingRecipe.class)
public abstract class AbstractCookingRecipeMixin implements Recipe<Container> {
    @Unique private final RandomSource early_buckets$randomSource = RandomSource.create();

    @Override
    public NonNullList<ItemStack> getRemainingItems(Container container) {
        final NonNullList<ItemStack> nonNullList = Recipe.super.getRemainingItems(container);

        // Damage items in the fuel slot if they can be depleted.
        // This might not even work
        final var fuelItem = container.getItem(AbstractFurnaceMenu.FUEL_SLOT).getItem();
        if (fuelItem.hasCraftingRemainingItem()) {
            final var remainingItem = Objects.requireNonNull(fuelItem.getCraftingRemainingItem());
            if (remainingItem.canBeDepleted()) {
                var fuelToDamage = nonNullList.get(AbstractFurnaceMenu.FUEL_SLOT);
                if (fuelToDamage.hurt(1, this.early_buckets$randomSource, null)) {
                    // We just killed that item.
                    nonNullList.set(AbstractFurnaceMenu.FUEL_SLOT, ItemStack.EMPTY);
                }
            }
        }

        return nonNullList;
    }
}
