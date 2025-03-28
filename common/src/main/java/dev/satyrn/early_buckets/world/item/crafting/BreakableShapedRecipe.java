package dev.satyrn.early_buckets.world.item.crafting;

import com.google.gson.JsonObject;
import dev.satyrn.early_buckets.mixin.accessor.ShapedRecipeAccessor;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static dev.satyrn.early_buckets.world.item.BucketItems.createItemStack;

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
    private final NonNullList<Ingredient> ingredients;
    // The recipe output.
    private final ItemStack output;
    // The damage value applied to damageable item stacks by the recipe.
    private final int damage;
    // Randomizer for the class.
    private final @NotNull RandomSource random;

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
    public BreakableShapedRecipe(ResourceLocation id, String group, int width, int height, NonNullList<Ingredient> ingredients, int damage, ItemStack output) {
        super(id, group, width, height, ingredients, output);
        this.group = group;
        this.width = width;
        this.height = height;
        this.ingredients = ingredients;
        this.damage = damage;
        this.output = output;
        this.random = RandomSource.create();
    }

    /**
     * Gets a list of items remaining after crafting.
     *
     * @param inventory The crafting inventory.
     * @return A list of remaining items.
     */
    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingContainer inventory) {
        NonNullList<ItemStack> defaultedList = NonNullList.withSize(inventory.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < defaultedList.size(); ++i) {
            final ItemStack itemStack = inventory.getItem(i);
            final Item item = itemStack.getItem();

            if (item.hasCraftingRemainingItem()) {
                final Item remainder = item.getCraftingRemainingItem();
                if (remainder != null) {
                    @NotNull ItemStack remainingItem = new ItemStack(remainder);
                    if (remainder.canBeDepleted()) {
                        remainingItem = createItemStack(remainder, itemStack);

                        // Apply damage to the new stack. Random is now per-instance instead of per-invocation.
                        // Why didn't I just do it this way before...?
                        if (remainingItem.hurt(this.damage, this.random, null)) {
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
     * @return {@link BucketRecipeSerializers#CRAFTING_SHAPED_BREAKABLE_SERIALIZER}.
     */
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return BucketRecipeSerializers.CRAFTING_SHAPED_BREAKABLE_SERIALIZER.get();
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
        public @NotNull BreakableShapedRecipe fromJson(ResourceLocation identifier, JsonObject jsonObject) {
            final String group = GsonHelper.getAsString(jsonObject, "group", "");
            final int damage = GsonHelper.getAsInt(jsonObject, "damage", 1);
            final Map<String, Ingredient> symbols = ShapedRecipeAccessor.callKeyFromJson(GsonHelper.getAsJsonObject(jsonObject, "key"));
            final String[] pattern = ShapedRecipeAccessor.callShrink(
                    ShapedRecipeAccessor.callPatternFromJson(GsonHelper.getAsJsonArray(jsonObject, "pattern")));
            final int width = pattern.length > 0 ? pattern[0].length() : 0;
            final int height = pattern.length;
            final NonNullList<Ingredient> ingredients = ShapedRecipeAccessor.callDissolvePattern(pattern, symbols, width, height);
            final ItemStack output = itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
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
        public @NotNull BreakableShapedRecipe fromNetwork(ResourceLocation identifier, FriendlyByteBuf packetByteBuf) {
            final int width = packetByteBuf.readVarInt();
            final int height = packetByteBuf.readVarInt();
            final int damage = packetByteBuf.readVarInt();
            final String group = packetByteBuf.readUtf();
            final NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);

            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(packetByteBuf));

            ItemStack output = packetByteBuf.readItem();
            return new BreakableShapedRecipe(identifier, group, width, height, ingredients, damage, output);
        }

        /**
         * Writes the recipe to a packet byte buffer.
         *
         * @param packetByteBuf The packet byte buffer.
         * @param recipe        The recipe.
         */
        @Override
        public void toNetwork(FriendlyByteBuf packetByteBuf, BreakableShapedRecipe recipe) {
            packetByteBuf.writeVarInt(recipe.width);
            packetByteBuf.writeVarInt(recipe.height);
            packetByteBuf.writeVarInt(recipe.damage);
            packetByteBuf.writeUtf(recipe.group);
            for (final Ingredient ingredient : recipe.ingredients) {
                ingredient.toNetwork(packetByteBuf);
            }
            packetByteBuf.writeItem(recipe.output);
        }
    }
}
