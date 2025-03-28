package dev.satyrn.early_buckets.mixin.accessor;

import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Map;

@Mixin(ShapedRecipeBuilder.class)
public interface ShapedRecipeBuilderAccessor {
    @Accessor Advancement.Builder getAdvancement();
    @Accessor int getCount();
    @Accessor String getGroup();
    @Accessor List<String> getRows();
    @Accessor Map<Character, Ingredient> getKey();

    @Invoker
    void invokeEnsureValid(ResourceLocation resourceLocation);
}
