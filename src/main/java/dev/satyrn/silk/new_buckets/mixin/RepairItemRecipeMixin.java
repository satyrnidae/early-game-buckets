package dev.satyrn.silk.new_buckets.mixin;

import dev.satyrn.silk.new_buckets.item.DisableRepairItem;
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
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(RepairItemRecipe.class)
public abstract class RepairItemRecipeMixin {

    /**
     * Injects into the matches method of the RepairItemRecipe class.
     * @param craftingInventory The crafting inventory.
     * @param world The current world.
     * @param cir The callback information.
     * @since 1.0.0
     */
    @Inject(method = "matches", at = @At("HEAD"), cancellable = true)
    public void injectMatches(CraftingInventory craftingInventory, World world, CallbackInfoReturnable<Boolean> cir) {
        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack = craftingInventory.getStack(i);
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof DisableRepairItem) {
                if (!((DisableRepairItem)itemStack.getItem()).canRepairViaCrafting(itemStack)) {
                    cir.setReturnValue(false);
                    cir.cancel();
                    break;
                }
            }
        }
    }
}
