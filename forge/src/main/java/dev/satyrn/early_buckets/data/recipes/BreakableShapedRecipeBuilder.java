package dev.satyrn.early_buckets.data.recipes;

import dev.satyrn.early_buckets.mixin.accessor.ShapedRecipeBuilderAccessor;
import dev.satyrn.early_buckets.world.item.crafting.BucketRecipeSerializers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class BreakableShapedRecipeBuilder extends ShapedRecipeBuilder {
    private BreakableShapedRecipeBuilder(ItemLike itemLike, int i) {
        super(itemLike, i);
        this.group(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(itemLike.asItem())).getPath());
    }

    public static BreakableShapedRecipeBuilder shaped(ItemLike result) {
        return new BreakableShapedRecipeBuilder(result, 1);
    }

    @Override
    public void save(final @NotNull Consumer<FinishedRecipe> consumer, final @NotNull ResourceLocation arg) {
        ((ShapedRecipeBuilderAccessor)this).invokeEnsureValid(arg);
        ((ShapedRecipeBuilderAccessor)this).getAdvancement()
                .parent(ROOT_RECIPE_ADVANCEMENT)
                    .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(arg))
                    .rewards(AdvancementRewards.Builder.recipe(arg))
                    .requirements(RequirementsStrategy.OR);
        consumer.accept(
                new Result(
                        arg,
                        this.getResult(),
                        ((ShapedRecipeBuilderAccessor) this).getCount(),
                        ((ShapedRecipeBuilderAccessor) this).getGroup() == null ? "" : ((ShapedRecipeBuilderAccessor) this).getGroup(),
                        ((ShapedRecipeBuilderAccessor) this).getRows(),
                        ((ShapedRecipeBuilderAccessor) this).getKey(),
                        ((ShapedRecipeBuilderAccessor) this).getAdvancement(),
                        new ResourceLocation(arg.getNamespace(), "recipes/" + Objects.requireNonNull(
                                this.getResult().getItemCategory()).getRecipeFolderName() + "/" + arg.getPath())
                )
        );
    }

    public static class Result extends ShapedRecipeBuilder.Result {

        public Result(ResourceLocation arg,
                      Item arg2,
                      int i,
                      String string,
                      List<String> list,
                      Map<Character, Ingredient> map,
                      Advancement.Builder arg3,
                      ResourceLocation arg4) {
            super(arg, arg2, i, string, list, map, arg3, arg4);
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
            return BucketRecipeSerializers.CRAFTING_SHAPED_BREAKABLE_SERIALIZER.get();
        }
    }
}
