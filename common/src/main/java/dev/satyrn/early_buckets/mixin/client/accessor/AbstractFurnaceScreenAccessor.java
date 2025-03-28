package dev.satyrn.early_buckets.mixin.client.accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin(AbstractFurnaceScreen.class)
public interface AbstractFurnaceScreenAccessor {
    @Accessor boolean getWidthTooNarrow();

    @Accessor void setWidthTooNarrow(boolean value);
}
