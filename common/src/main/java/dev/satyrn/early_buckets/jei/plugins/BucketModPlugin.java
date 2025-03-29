package dev.satyrn.early_buckets.jei.plugins;

import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.client.gui.screens.inventory.KilnScreen;
import dev.satyrn.early_buckets.jei.FiringRecipeCategory;
import dev.satyrn.early_buckets.world.inventory.BucketMenuTypes;
import dev.satyrn.early_buckets.world.inventory.KilnMenu;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@JeiPlugin
public class BucketModPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = new ResourceLocation(BucketModCommon.MOD_ID, "BucketModPlugin");

    private @Nullable FiringRecipeCategory firingCategory;

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        var jeiHelpers = registration.getJeiHelpers();
        var guiHelpers = jeiHelpers.getGuiHelper();
        registration.addRecipeCategories(
                this.firingCategory = new FiringRecipeCategory(guiHelpers)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Objects.requireNonNull(this.firingCategory);

        registration.addRecipes(FiringRecipeCategory.FIRING, this.firingCategory.getRecipes());
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(KilnScreen.class, 78, 32, 28, 23, FiringRecipeCategory.FIRING,
                RecipeTypes.FUELING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(KilnMenu.class, BucketMenuTypes.KILN.get(), FiringRecipeCategory.FIRING, 0, 1, 3, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BucketBlocks.KILN.get()), FiringRecipeCategory.FIRING);
    }
}
