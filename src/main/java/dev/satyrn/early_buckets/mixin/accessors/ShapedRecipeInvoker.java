package dev.satyrn.early_buckets.mixin.accessors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.HashMap;
import java.util.Map;

/**
 * Invoker mixin for the {@link ShapedRecipe} class.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
@SuppressWarnings("unused")
@Mixin(ShapedRecipe.class)
public interface ShapedRecipeInvoker {
    /**
     * Calls {@code ShapedRecipe.removePadding(String...)}.
     *
     * @param pattern Padding will be removed from this pattern.
     * @return The pattern with padding stripped.
     */
    @Invoker()
    static String[] callRemovePadding(String... pattern) {
        return new String[0];
    }

    /**
     * Calls {@link ShapedRecipe}{@code .callReadSymbols(}{@link JsonObject}{@code )}.
     *
     * @param json The serialized symbol list.
     * @return A map of ingredients indexed by symbol.
     */
    @Invoker()
    static Map<String, Ingredient> callReadSymbols(JsonObject json) {
        return new HashMap<>();
    }

    /**
     * Calls {@link ShapedRecipe}{@code .callCreatePatternMatrix(}{@link String}{@code [], }{@link Map}{@code <}{@link String}{@code , }{@link Ingredient}{@code >, int, int)}.
     *
     * @param pattern The recipe pattern.
     * @param symbols The recipe symbols.
     * @param width   The width of the recipe.
     * @param height  The height of the recipe.
     * @return A list of ingredients for each character in {@code pattern}.
     */
    @Invoker()
    static DefaultedList<Ingredient> callCreatePatternMatrix(String[] pattern, Map<String, Ingredient> symbols, int width, int height) {
        return DefaultedList.ofSize(0, Ingredient.EMPTY);
    }

    /**
     * Calls {@link ShapedRecipe}{@code .callGetPattern(}{@link JsonArray}{@code )}.
     *
     * @param json The JSON array containing the pattern.
     * @return The pattern.
     */
    @Invoker()
    static String[] callGetPattern(JsonArray json) {
        return new String[0];
    }

}
