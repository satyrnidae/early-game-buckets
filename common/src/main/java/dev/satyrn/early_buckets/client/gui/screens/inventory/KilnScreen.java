package dev.satyrn.early_buckets.client.gui.screens.inventory;

import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.client.gui.screens.recipebook.FiringRecipeBookComponent;
import dev.satyrn.early_buckets.mixin.client.accessor.AbstractFurnaceScreenAccessor;
import dev.satyrn.early_buckets.world.inventory.KilnMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class KilnScreen extends AbstractFurnaceScreen<KilnMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(BucketModCommon.MOD_ID,
            "textures/gui/container/kiln.png");

    public KilnScreen(KilnMenu kilnMenu, Inventory inventory, Component component) {
        super(kilnMenu, new FiringRecipeBookComponent(), inventory, component, TEXTURE);
    }

    @Override
    public void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
        ((AbstractFurnaceScreenAccessor) this).setWidthTooNarrow(this.width < 379);
        this.recipeBookComponent.init(this.width, this.height, this.minecraft,
                ((AbstractFurnaceScreenAccessor) this).getWidthTooNarrow(), this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }
}
