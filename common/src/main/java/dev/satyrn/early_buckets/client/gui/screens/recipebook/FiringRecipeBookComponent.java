package dev.satyrn.early_buckets.client.gui.screens.recipebook;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

import java.util.Set;

@Environment(EnvType.CLIENT)
public class FiringRecipeBookComponent extends AbstractFurnaceRecipeBookComponent {
    private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.smeltable");

    public FiringRecipeBookComponent() {
        super();
    }

    @Override
    protected Component getRecipeFilterName() {
        return FILTER_NAME;
    }

    @Override
    protected Set<Item> getFuelItems() {
        return AbstractFurnaceBlockEntity.getFuel().keySet();
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public void init(final int i,
                     final int j,
                     final Minecraft minecraft,
                     final boolean bl,
                     final RecipeBookMenu<?> recipeBookMenu) {
        super.init(i, j, minecraft, bl, recipeBookMenu);
        this.setVisible(false);
    }

    @Override
    public void initVisuals() {
        // Do nothing
    }

    @Override
    public void render(final PoseStack poseStack, final int i, final int j, final float f) {
        // Render nothing
    }
}
