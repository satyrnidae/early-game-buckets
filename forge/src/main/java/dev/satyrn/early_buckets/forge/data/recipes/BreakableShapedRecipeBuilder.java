package dev.satyrn.early_buckets.forge.data.recipes;

import dev.satyrn.early_buckets.mixin.data.recipes.ShapedRecipeBuilderAccessor;
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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class BreakableShapedRecipeBuilder extends ShapedRecipeBuilder {
    private BreakableShapedRecipeBuilder(final ItemLike itemLike, final int i) {
        super(itemLike, i);
        this.group(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(itemLike.asItem())).getPath());
    }

    public static BreakableShapedRecipeBuilder shaped(final ItemLike result) {
        return new BreakableShapedRecipeBuilder(result, 1);
    }

    @Override
    public void save(final Consumer<FinishedRecipe> consumer, final ResourceLocation arg) {
        ((ShapedRecipeBuilderAccessor) this).invokeEnsureValid(arg);
        ((ShapedRecipeBuilderAccessor) this).getAdvancement()
                .parent(ROOT_RECIPE_ADVANCEMENT)
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(arg))
                .rewards(AdvancementRewards.Builder.recipe(arg))
                .requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(arg, this.getResult(), ((ShapedRecipeBuilderAccessor) this).getCount(),
                ((ShapedRecipeBuilderAccessor) this).getGroup() == null
                        ? ""
                        : ((ShapedRecipeBuilderAccessor) this).getGroup(),
                ((ShapedRecipeBuilderAccessor) this).getRows(), ((ShapedRecipeBuilderAccessor) this).getKey(),
                ((ShapedRecipeBuilderAccessor) this).getAdvancement(), new ResourceLocation(arg.getNamespace(),
                "recipes/" +
                        Objects.requireNonNull(this.getResult().getItemCategory()).getRecipeFolderName() +
                        "/" +
                        arg.getPath())));
    }

    public static class Result extends ShapedRecipeBuilder.Result {

        public Result(final ResourceLocation name,
                      final Item result,
                      final int count,
                      final String string,
                      final List<String> pattern,
                      final Map<Character, Ingredient> ingredientMap,
                      final Advancement.Builder advancementBuilder,
                      final ResourceLocation advancementId) {
            super(name, result, count, string, pattern, ingredientMap, advancementBuilder, advancementId);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return BucketRecipeSerializers.CRAFTING_SHAPED_BREAKABLE_SERIALIZER.get();
        }
    }
}
