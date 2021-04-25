package dev.satyrn.silk.new_buckets.recipe;

import com.google.gson.JsonObject;
import dev.satyrn.silk.new_buckets.NewBucketsMod;
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

import java.util.Date;
import java.util.Map;
import java.util.Random;

public class ShapedRecipeWithDamage extends ShapedRecipe {
    private final String group;
    private final int width;
    private final int height;
    private final DefaultedList<Ingredient> inputs;
    private final ItemStack output;

    /**
     * Initializes a new shaped recipe with transferable damage values.
     * @param id The recipe ID.
     * @param group The recipe group.
     * @param width The width of the recipe.
     * @param height The height of the recipe.
     * @param ingredients The recipe ingredients.
     * @param output The recipe output.
     */
    public ShapedRecipeWithDamage(Identifier id, String group, int width, int height, DefaultedList<Ingredient> ingredients, ItemStack output) {
        super(id, group, width, height, ingredients, output);
        this.group = group;
        this.width = width;
        this.height = height;
        this.inputs = ingredients;
        this.output = output;
    }

    @Override
    public DefaultedList<ItemStack> getRemainingStacks(CraftingInventory inventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for(int i = 0; i < defaultedList.size(); ++i) {
            ItemStack currentStack = inventory.getStack(i);
            Item item = currentStack.getItem();
            if (item.hasRecipeRemainder()) {
                Item remainder = item.getRecipeRemainder();
                ItemStack newItemStack = new ItemStack(remainder);
                if (newItemStack.isDamageable()) {
                    newItemStack.setDamage(currentStack.getDamage());
                    // Not sure how I feel about spinning off randoms like this.
                    newItemStack.damage(1, new Random(new Date().getTime()), null);
                    if (newItemStack.getDamage() >= newItemStack.getItem().getMaxDamage()) {
                        // Destroy the item when it exceeds max damage
                        newItemStack = ItemStack.EMPTY;
                    }
                }
                if (!newItemStack.isEmpty()) {
                    defaultedList.set(i, newItemStack);
                }
            }
        }

        return defaultedList;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NewBucketsMod.SHAPED_RECIPE_WITH_DAMAGE_SERIALIZER;
    }

    public static class Serializer implements RecipeSerializer<ShapedRecipeWithDamage> {
        @Override
        public ShapedRecipeWithDamage read(Identifier identifier, JsonObject jsonObject) {
            String string = JsonHelper.getString(jsonObject, "group", "");
            Map<String, Ingredient> map = ShapedRecipe.getComponents(JsonHelper.getObject(jsonObject, "key"));
            String[] strings = ShapedRecipe.combinePattern(ShapedRecipe.getPattern(JsonHelper.getArray(jsonObject, "pattern")));
            int i = strings[0].length();
            int j = strings.length;
            DefaultedList<Ingredient> defaultedList = ShapedRecipe.getIngredients(strings, map, i, j);
            ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
            return new ShapedRecipeWithDamage(identifier, string, i, j, defaultedList, itemStack);
        }

        @Override
        public ShapedRecipeWithDamage read(Identifier identifier, PacketByteBuf packetByteBuf) {
            int i = packetByteBuf.readVarInt();
            int j = packetByteBuf.readVarInt();
            String string = packetByteBuf.readString(32767);
            DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i * j, Ingredient.EMPTY);

            for(int k = 0; k < defaultedList.size(); ++k) {
                defaultedList.set(k, Ingredient.fromPacket(packetByteBuf));
            }

            ItemStack itemStack = packetByteBuf.readItemStack();
            return new ShapedRecipeWithDamage(identifier, string, i, j, defaultedList, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, ShapedRecipeWithDamage recipe) {
            packetByteBuf.writeVarInt(recipe.width);
            packetByteBuf.writeVarInt(recipe.height);
            packetByteBuf.writeString(recipe.group);

            for (Ingredient ingredient : recipe.inputs) {
                ingredient.write(packetByteBuf);
            }

            packetByteBuf.writeItemStack(recipe.output);
        }
    }
}
