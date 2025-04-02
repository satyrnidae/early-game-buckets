package dev.satyrn.early_buckets.recipe;

import com.google.gson.JsonObject;
import dev.satyrn.early_buckets.BucketMod;
import dev.satyrn.early_buckets.mixin.accessors.ShapedRecipeInvoker;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Random;

import static dev.satyrn.early_buckets.item.BucketItems.createItemStack;

/**
 * Represents a shaped recipe that damages remaining items.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class BreakableShapedRecipe extends ShapedRecipe {
    // The recipe's group.
    private final String group;
    // The recipe width.
    private final int width;
    // The recipe height.
    private final int height;
    // The recipe ingredient list.
    private final DefaultedList<Ingredient> ingredients;
    // The recipe output.
    private final ItemStack output;
    // The damage value applied to damageable item stacks by the recipe.
    private final int damage;
    // Randomizer for the class.
    private final @NotNull Random random = new Random();

    /**
     * Initializes a new shaped recipe with transferable damage values.
     *
     * @param id          The recipe ID.
     * @param group       The recipe group.
     * @param width       The width of the recipe.
     * @param height      The height of the recipe.
     * @param ingredients The recipe ingredients.
     * @param output      The recipe output.
     * @since 1.0.0
     */
    public BreakableShapedRecipe(Identifier id, String group, int width, int height, DefaultedList<Ingredient> ingredients, int damage, ItemStack output) {
        super(id, group, width, height, ingredients, output);
        this.group = group;
        this.width = width;
        this.height = height;
        this.ingredients = ingredients;
        this.damage = damage;
        this.output = output;
    }

    /**
     * Gets a list of items remaining after crafting.
     *
     * @param inventory The crafting inventory.
     * @return A list of remaining items.
     */
    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for (int i = 0; i < defaultedList.size(); ++i) {
            final ItemStack itemStack = inventory.getStack(i);
            final Item item = itemStack.getItem();

            if (item.hasRecipeRemainder()) {
                final Item remainder = item.getRecipeRemainder();
                if (remainder != null) {
                    @NotNull ItemStack remainingItem = new ItemStack(remainder);
                    if (remainder.isDamageable()) {
                        remainingItem = createItemStack(remainder, itemStack);

                        // Apply damage to the new stack. Random is now per-instance instead of per-invocation.
                        // Why didn't I just do it this way before...?
                        if (remainingItem.damage(this.damage, this.random, null)) {
                            // That item just got destroyed, so skip it.
                            continue;
                        }
                    }
                    // We still want the remainder, even if we didn't damage it >:O
                    defaultedList.set(i, remainingItem);
                }
            }
        }

        return defaultedList;
    }

    /**
     * Gets the serializer for the recipe.
     *
     * @return {@link BucketMod#SHAPED_RECIPE_WITH_DAMAGE_SERIALIZER}.
     */
    @Override
    public RecipeSerializer<?> getSerializer() {
        return BucketMod.SHAPED_RECIPE_WITH_DAMAGE_SERIALIZER;
    }

    /**
     * Serializer implementation for a {@link BreakableShapedRecipe}.
     *
     * @author Isabel Maskrey
     * @since 1.0.0
     */
    public static class Serializer implements RecipeSerializer<BreakableShapedRecipe> {
        /**
         * Reads the recipe from a JSON file.
         *
         * @param identifier The recipe identifier.
         * @param jsonObject The JSON data.
         * @return The deserialized recipe.
         */
        @Override
        public BreakableShapedRecipe read(Identifier identifier, JsonObject jsonObject) {
            final String group = JsonHelper.getString(jsonObject, "group", "");
            final int damage = JsonHelper.getInt(jsonObject, "damage", 1);
            final Map<String, Ingredient> symbols = ShapedRecipeInvoker.callReadSymbols(JsonHelper.getObject(jsonObject, "key"));
            final String[] pattern = ShapedRecipeInvoker.callRemovePadding(
                    ShapedRecipeInvoker.callGetPattern(JsonHelper.getArray(jsonObject, "pattern")));
            final int width = pattern.length > 0 ? pattern[0].length() : 0;
            final int height = pattern.length;
            final DefaultedList<Ingredient> ingredients = ShapedRecipeInvoker.callCreatePatternMatrix(pattern, symbols, width, height);
            final ItemStack output = outputFromJson(JsonHelper.getObject(jsonObject, "result"));
            return new BreakableShapedRecipe(identifier, group, width, height, ingredients, damage, output);
        }

        /**
         * Reads the recipe from a packet byte buffer.
         *
         * @param identifier    The recipe identifier.
         * @param packetByteBuf The packet byte buffer.
         * @return The deserialized recipe.
         */
        @Override
        public BreakableShapedRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            final int width = packetByteBuf.readVarInt();
            final int height = packetByteBuf.readVarInt();
            final int damage = packetByteBuf.readVarInt();
            final String group = packetByteBuf.readString();
            final DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(width * height, Ingredient.EMPTY);

            for (int index = 0; index < ingredients.size(); ++index) {
                ingredients.set(index, Ingredient.fromPacket(packetByteBuf));
            }

            ItemStack output = packetByteBuf.readItemStack();
            return new BreakableShapedRecipe(identifier, group, width, height, ingredients, damage, output);
        }

        /**
         * Writes the recipe to a packet byte buffer.
         *
         * @param packetByteBuf The packet byte buffer.
         * @param recipe        The recipe.
         */
        @Contract(value = "_, _ -> _", mutates = "param1")
        @Override
        public void write(PacketByteBuf packetByteBuf, BreakableShapedRecipe recipe) {
            packetByteBuf.writeVarInt(recipe.width);
            packetByteBuf.writeVarInt(recipe.height);
            packetByteBuf.writeVarInt(recipe.damage);
            packetByteBuf.writeString(recipe.group);
            for (final Ingredient ingredient : recipe.ingredients) {
                ingredient.write(packetByteBuf);
            }
            packetByteBuf.writeItemStack(recipe.output);
        }
    }
}
