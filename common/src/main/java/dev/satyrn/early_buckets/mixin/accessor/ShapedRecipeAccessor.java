package dev.satyrn.early_buckets.mixin.accessor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

/**
 * Invoker mixin for the {@link ShapedRecipe} class.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
@SuppressWarnings("unused")
@Mixin(ShapedRecipe.class)
public interface ShapedRecipeAccessor extends CraftingRecipe {
    /**
     * Calls {@code ShapedRecipe.removePadding(String...)}.
     *
     * @param pattern Padding will be removed from this pattern.
     *
     * @return The pattern with padding stripped.
     */
    @Invoker()
    static String[] callShrink(String... pattern) {
        throw new AssertionError();
    }

    /**
     * Calls {@link ShapedRecipe}{@code .callReadSymbols(}{@link JsonObject}{@code )}.
     *
     * @param json The serialized symbol list.
     *
     * @return A map of ingredients indexed by symbol.
     */
    @Invoker()
    static Map<String, Ingredient> callKeyFromJson(JsonObject json) {
        throw new AssertionError();
    }

    /**
     * Calls {@link ShapedRecipe}{@code .callCreatePatternMatrix(}{@link String}{@code [], }{@link Map}{@code <}{@link String}{@code , }{@link Ingredient}{@code >, int, int)}.
     *
     * @param pattern The recipe pattern.
     * @param symbols The recipe symbols.
     * @param width   The width of the recipe.
     * @param height  The height of the recipe.
     *
     * @return A list of ingredients for each character in {@code pattern}.
     */
    @Invoker()
    static NonNullList<Ingredient> callDissolvePattern(String[] pattern,
                                                       Map<String, Ingredient> symbols,
                                                       int width,
                                                       int height) {
        throw new AssertionError();
    }

    /**
     * Calls {@link ShapedRecipe}{@code .callGetPattern(}{@link JsonArray}{@code )}.
     *
     * @param json The JSON array containing the pattern.
     *
     * @return The pattern.
     */
    @Invoker()
    static String[] callPatternFromJson(JsonArray json) {
        throw new AssertionError();
    }

}
