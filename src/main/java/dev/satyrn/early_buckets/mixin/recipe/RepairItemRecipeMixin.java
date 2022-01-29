package dev.satyrn.early_buckets.mixin.recipe;

import dev.satyrn.early_buckets.item.Repairable;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RepairItemRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin class for the Repair Item recipe.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(RepairItemRecipe.class)
public abstract class RepairItemRecipeMixin {
    /**
     * Injects into the {@link RepairItemRecipe#matches(CraftingInventory, World)} method of the RepairItemRecipe class.
     *
     * @param craftingInventory The crafting inventory.
     * @param world             The current world.
     * @param cir               The callback information.
     * @since 1.0.0
     */
    @Inject(method = "matches*", at = @At("HEAD"), cancellable = true)
    public void injectMatches(CraftingInventory craftingInventory, World world, CallbackInfoReturnable<Boolean> cir) {
        for (int index = 0; index < craftingInventory.size(); ++index) {
            final ItemStack stack = craftingInventory.getStack(index);

            if (!stack.isEmpty() && stack.getItem() instanceof final Repairable craftingRepairable) {
                if (!craftingRepairable.canRepairByCrafting()) {
                    cir.setReturnValue(false);
                    cir.cancel();
                }
            }
        }
    }
}
