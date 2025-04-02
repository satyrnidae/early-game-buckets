package dev.satyrn.early_buckets.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.world.item.crafting.BucketRecipeTypes;
import dev.satyrn.early_buckets.world.item.crafting.FiringRecipe;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class FiringRecipeCategory implements IRecipeCategory<FiringRecipe> {
    public final static RecipeType<FiringRecipe> FIRING = RecipeType.create(BucketModCommon.MOD_ID,
            BucketModCommon.FIRING_RECIPE_ID.getPath(), FiringRecipe.class);
    private final static ResourceLocation VANILLA_RECIPE_GUI = new ResourceLocation("jei",
            "textures/gui/gui_vanilla.png");
    private final static int REGULAR_COOK_TIME = 100;
    private final IDrawableAnimated animatedFlame;
    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public FiringRecipeCategory(final IGuiHelper guiHelper) {
        IDrawableStatic staticFlame = guiHelper.createDrawable(VANILLA_RECIPE_GUI, 82, 114, 14, 14);
        this.animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 150, IDrawableAnimated.StartDirection.TOP,
                true);
        this.background = guiHelper.createDrawable(VANILLA_RECIPE_GUI, 0, 114, 82, 54);
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(BucketBlocks.KILN.get()));
        this.localizedName = Component.translatable("gui.early_buckets.category.firing");
        this.cachedArrows = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public IDrawableAnimated load(final Integer cookTime) {
                return guiHelper.drawableBuilder(VANILLA_RECIPE_GUI, 82, 128, 24, 17)
                        .buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
            }
        });
    }

    public List<FiringRecipe> getRecipes() {
        return Validator.getValidHandledRecipes(
                Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager(), new Validator(this));
    }

    @Override
    public RecipeType<FiringRecipe> getRecipeType() {
        return FIRING;
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(final IRecipeLayoutBuilder builder, final FiringRecipe recipe, final IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 19).addItemStack(recipe.getResultItem());
    }

    @Override
    public boolean isHandled(final FiringRecipe recipe) {
        return !recipe.isSpecial();
    }

    @Override
    public void draw(final FiringRecipe recipe,
                     final IRecipeSlotsView recipeSlotsView,
                     final PoseStack stack,
                     final double mouseX,
                     final double mouseY) {
        this.animatedFlame.draw(stack, 1, 20);
        this.getArrow(recipe).draw(stack, 24, 18);
        this.drawExperience(recipe, stack);
        this.drawCookTime(recipe, stack);
    }

    private IDrawableAnimated getArrow(final FiringRecipe recipe) {
        int cookTime = recipe.getCookingTime();
        if (cookTime <= 0) {
            cookTime = REGULAR_COOK_TIME;
        }
        return this.cachedArrows.getUnchecked(cookTime);
    }

    private void drawExperience(final FiringRecipe recipe, final PoseStack stack) {
        final float xp = recipe.getExperience();
        if (xp > 0) {
            final var xpString = Component.translatable("gui.early_buckets.category.firing.experience", xp);
            final var fontRenderer = Minecraft.getInstance().font;
            final int strWidth = fontRenderer.width(xpString);
            fontRenderer.draw(stack, xpString, this.getWidth() - strWidth, 0, 0xFF808080);
        }
    }

    private void drawCookTime(final FiringRecipe recipe, final PoseStack stack) {
        final int cookTime = recipe.getCookingTime();
        if (cookTime > 0) {
            final int cookTimeInSeconds = cookTime / 20;
            final var timeComponent = Component.translatable("gui.early_buckets.category.firing.cook_time",
                    cookTimeInSeconds);
            final var fontRenderer = Minecraft.getInstance().font;
            final int strWidth = fontRenderer.width(timeComponent);
            fontRenderer.draw(stack, timeComponent, this.getWidth() - strWidth, 45, 0xFF808080);
        }
    }

    public static class Validator {
        private final FiringRecipeCategory category;

        public Validator(final FiringRecipeCategory category) {
            this.category = category;
        }

        public static List<FiringRecipe> getValidHandledRecipes(final RecipeManager manager,
                                                                final Validator validator) {
            return manager.getAllRecipesFor(BucketRecipeTypes.FIRING.get())
                    .stream()
                    .filter(r -> validator.isRecipeValid(r) && validator.isRecipeHandled(r))
                    .toList();
        }

        private static int getInputs(final List<Ingredient> ingredients) {
            int inputCnt = 0;
            for (final var ingredient : ingredients) {
                final var inputs = ingredient.getItems();
                if (inputs.length > 0) {
                    inputCnt++;
                }
            }
            return inputCnt;
        }

        public boolean isRecipeValid(final FiringRecipe recipe) {
            if (recipe.isSpecial()) {
                return true;
            }

            final var output = recipe.getResultItem();
            if (output.isEmpty()) {
                return false;
            }
            final var ingredients = recipe.getIngredients();
            final int ingredientCnt = getInputs(ingredients);
            return ingredientCnt <= 1;
        }

        public boolean isRecipeHandled(final FiringRecipe recipe) {
            return this.category.isHandled(recipe);
        }
    }
}
