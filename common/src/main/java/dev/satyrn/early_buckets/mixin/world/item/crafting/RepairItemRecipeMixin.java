package dev.satyrn.early_buckets.mixin.world.item.crafting;

import dev.satyrn.early_buckets.world.item.Repairable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin class for the Repair Item recipe.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Unique
@Mixin(RepairItemRecipe.class)
public abstract class RepairItemRecipeMixin extends CustomRecipe {

    RepairItemRecipeMixin(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    /**
     * Injects into the {@link RepairItemRecipe#matches(net.minecraft.world.inventory.CraftingContainer, net.minecraft.world.level.Level)} method of the RepairItemRecipe class.
     *
     * @param craftingContainer The crafting inventory.
     * @param level             The current world.
     * @param cir               The callback information.
     * @since 1.0.0
     */
    @Inject(method = "matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z", at = @At("HEAD"), cancellable = true)
    public void early_buckets$matches(CraftingContainer craftingContainer,
                                      Level level,
                                      CallbackInfoReturnable<Boolean> cir) {
        for (int index = 0; index < craftingContainer.getContainerSize(); ++index) {
            final ItemStack stack = craftingContainer.getItem(index);

            if (!stack.isEmpty() && stack.getItem() instanceof final Repairable craftingRepairable) {
                if (!craftingRepairable.canRepairByCrafting()) {
                    cir.setReturnValue(false);
                    cir.cancel();
                }
            }
        }
    }
}
