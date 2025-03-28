package dev.satyrn.early_buckets.forge.data.provider.server;

import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.data.recipes.BreakableShapedRecipeBuilder;
import dev.satyrn.early_buckets.tags.BucketTags;
import dev.satyrn.early_buckets.world.item.BucketItems;
import dev.satyrn.early_buckets.world.item.crafting.BucketRecipeSerializers;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

public class BucketModRecipeProvider extends RecipeProvider {
    public BucketModRecipeProvider(DataGenerator arg) {
        super(arg);
    }

    public static ResourceLocation getFiringRecipeName(ItemLike arg) {
        return new ResourceLocation(BucketModCommon.MOD_ID, Objects.requireNonNull(
                ForgeRegistries.ITEMS.getKey(arg.asItem())).getPath() + "_from_firing");
    }

    public static ResourceLocation getDefaultRecipeName(ItemLike result) {
        return new ResourceLocation(BucketModCommon.MOD_ID, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(result.asItem())).getPath());
    }

    private static @NotNull String getDefaultGroupName(ItemLike result) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(result.asItem())).getPath();
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        BreakableShapedRecipeBuilder.shaped(Blocks.CAKE)
                .group(getDefaultGroupName(Blocks.CAKE))
                .pattern("AAA")
                .pattern("BEB")
                .pattern("CCC")
                .define('A', BucketTags.Items.MILK_BUCKETS)
                .define('B', BucketTags.Items.SUGAR)
                .define('E', BucketTags.Items.EGGS)
                .define('C', BucketTags.Items.WHEAT)
                .unlockedBy("has_egg", has(BucketTags.Items.EGGS))
                .save(consumer, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(Blocks.CAKE.asItem())));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.CLAY_BALL), Items.BRICK, 0.3F, 100,
                        BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Items.BRICK))
                .unlockedBy("has_clay_ball", has(Items.CLAY_BALL))
                .save(consumer, getDefaultRecipeName(Items.BRICK));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemTags.LOGS_THAT_BURN), Items.CHARCOAL, 0.15f, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Items.CHARCOAL))
                .unlockedBy("has_log", has(ItemTags.LOGS_THAT_BURN))
                .save(consumer, getDefaultRecipeName(Items.CHARCOAL));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.CHORUS_FRUIT), Items.POPPED_CHORUS_FRUIT, 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Items.POPPED_CHORUS_FRUIT))
                .unlockedBy("has_chorus_fruit", has(Items.CHORUS_FRUIT))
                .save(consumer, getDefaultRecipeName(Items.POPPED_CHORUS_FRUIT));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(BucketTags.Items.SAND), Blocks.GLASS.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.GLASS))
                .unlockedBy("has_sand", has(BucketTags.Items.SAND))
                .save(consumer, getDefaultRecipeName(Blocks.GLASS));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.CLAY), Blocks.TERRACOTTA.asItem(), 0.35F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.TERRACOTTA))
                .unlockedBy("has_clay_block", has(Blocks.CLAY))
                .save(consumer, getDefaultRecipeName(Blocks.TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.NETHERRACK), Items.NETHER_BRICK, 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Items.NETHER_BRICK))
                .unlockedBy("has_netherrack", has(Blocks.NETHERRACK))
                .save(consumer, getDefaultRecipeName(Items.NETHER_BRICK));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.STONE), Blocks.SMOOTH_STONE.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.SMOOTH_STONE))
                .unlockedBy("has_stone", has(Blocks.STONE))
                .save(consumer, getDefaultRecipeName(Blocks.SMOOTH_STONE));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.SANDSTONE), Blocks.SMOOTH_SANDSTONE.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.SMOOTH_SANDSTONE))
                .unlockedBy("has_sandstone", has(Blocks.SANDSTONE))
                .save(consumer, getDefaultRecipeName(Blocks.SMOOTH_SANDSTONE));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.RED_SANDSTONE), Blocks.SMOOTH_RED_SANDSTONE.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.SMOOTH_RED_SANDSTONE))
                .unlockedBy("has_red_sandstone", has(Blocks.RED_SANDSTONE))
                .save(consumer, getDefaultRecipeName(Blocks.SMOOTH_RED_SANDSTONE));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.QUARTZ_BLOCK), Blocks.SMOOTH_QUARTZ.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.SMOOTH_QUARTZ))
                .unlockedBy("has_quartz_block", has(Blocks.QUARTZ_BLOCK))
                .save(consumer, getDefaultRecipeName(Blocks.SMOOTH_QUARTZ));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.STONE_BRICKS), Blocks.CRACKED_STONE_BRICKS.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.CRACKED_STONE_BRICKS))
                .unlockedBy("has_stone_bricks", has(Blocks.STONE_BRICKS))
                .save(consumer, getDefaultRecipeName(Blocks.CRACKED_STONE_BRICKS));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.BLACK_TERRACOTTA), Blocks.BLACK_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.BLACK_GLAZED_TERRACOTTA))
                .unlockedBy("has_black_terracotta", has(Blocks.BLACK_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.BLACK_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.BLUE_TERRACOTTA), Blocks.BLUE_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.BLUE_GLAZED_TERRACOTTA))
                .unlockedBy("has_blue_terracotta", has(Blocks.BLUE_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.BLUE_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.BROWN_TERRACOTTA), Blocks.BROWN_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.BROWN_GLAZED_TERRACOTTA))
                .unlockedBy("has_brown_terracotta", has(Blocks.BROWN_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.BROWN_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.CYAN_TERRACOTTA), Blocks.CYAN_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.CYAN_GLAZED_TERRACOTTA))
                .unlockedBy("has_cyan_terracotta", has(Blocks.CYAN_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.CYAN_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.GRAY_TERRACOTTA), Blocks.GRAY_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.GRAY_GLAZED_TERRACOTTA))
                .unlockedBy("has_gray_terracotta", has(Blocks.GRAY_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.GRAY_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.GREEN_TERRACOTTA), Blocks.GREEN_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.GREEN_GLAZED_TERRACOTTA))
                .unlockedBy("has_green_terracotta", has(Blocks.GREEN_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.GREEN_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.LIGHT_BLUE_TERRACOTTA), Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA))
                .unlockedBy("has_light_blue_terracotta", has(Blocks.LIGHT_BLUE_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.LIGHT_GRAY_TERRACOTTA), Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA))
                .unlockedBy("has_light_gray_terracotta", has(Blocks.LIGHT_GRAY_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.LIME_TERRACOTTA), Blocks.LIME_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.LIME_GLAZED_TERRACOTTA))
                .unlockedBy("has_lime_terracotta", has(Blocks.LIME_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.LIME_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.MAGENTA_TERRACOTTA), Blocks.MAGENTA_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.MAGENTA_GLAZED_TERRACOTTA))
                .unlockedBy("has_magenta_terracotta", has(Blocks.MAGENTA_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.MAGENTA_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.ORANGE_TERRACOTTA), Blocks.ORANGE_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.ORANGE_GLAZED_TERRACOTTA))
                .unlockedBy("has_orange_terracotta", has(Blocks.ORANGE_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.ORANGE_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.PINK_TERRACOTTA), Blocks.PINK_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.PINK_GLAZED_TERRACOTTA))
                .unlockedBy("has_pink_terracotta", has(Blocks.PINK_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.PINK_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.PURPLE_TERRACOTTA), Blocks.PURPLE_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.PURPLE_GLAZED_TERRACOTTA))
                .unlockedBy("has_purple_terracotta", has(Blocks.PURPLE_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.PURPLE_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.RED_TERRACOTTA), Blocks.RED_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.RED_GLAZED_TERRACOTTA))
                .unlockedBy("has_red_terracotta", has(Blocks.RED_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.RED_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.WHITE_TERRACOTTA), Blocks.WHITE_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.WHITE_GLAZED_TERRACOTTA))
                .unlockedBy("has_white_terracotta", has(Blocks.WHITE_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.WHITE_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.YELLOW_TERRACOTTA), Blocks.YELLOW_GLAZED_TERRACOTTA.asItem(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.YELLOW_GLAZED_TERRACOTTA))
                .unlockedBy("has_yellow_terracotta", has(Blocks.YELLOW_TERRACOTTA))
                .save(consumer, getDefaultRecipeName(Blocks.YELLOW_GLAZED_TERRACOTTA));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.BASALT), Blocks.SMOOTH_BASALT, 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.SMOOTH_BASALT))
                .unlockedBy("has_basalt", has(Blocks.BASALT))
                .save(consumer, getDefaultRecipeName(Blocks.SMOOTH_BASALT));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.COBBLED_DEEPSLATE), Blocks.DEEPSLATE, 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(Blocks.DEEPSLATE))
                .unlockedBy("has_cobbled_deepslate", has(Blocks.COBBLED_DEEPSLATE))
                .save(consumer, getDefaultRecipeName(Blocks.DEEPSLATE));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BucketItems.CLAY_BUCKET.get()), BucketItems.CERAMIC_BUCKET.get(), 0.1F, 200)
                .group(getDefaultGroupName(BucketItems.CERAMIC_BUCKET.get()))
                .unlockedBy("has_clay_bucket", has(BucketTags.Items.CLAY_BUCKETS))
                .unlockedBy("has_ceramic_bucket", has(BucketTags.Items.CERAMIC_BUCKETS))
                .save(consumer, getDefaultRecipeName(BucketItems.CERAMIC_BUCKET.get()));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(BucketItems.CLAY_BUCKET.get()), BucketItems.CERAMIC_BUCKET.get(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(BucketItems.CERAMIC_BUCKET.get()))
                .unlockedBy("has_clay_bucket", has(BucketTags.Items.CLAY_BUCKETS))
                .unlockedBy("has_ceramic_bucket", has(BucketTags.Items.CERAMIC_BUCKETS))
                .save(consumer, getFiringRecipeName(BucketItems.CERAMIC_BUCKET.get()));
        ShapedRecipeBuilder.shaped(BucketItems.WOODEN_BUCKET.get())
                .group(BucketItems.WOODEN_BUCKET.getId().getPath())
                .pattern("X X")
                .pattern("X X")
                .pattern("XXX")
                .define('X', ItemTags.PLANKS)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .unlockedBy("has_wooden_bucket", has(BucketTags.Items.WOODEN_BUCKETS))
                .save(consumer, getDefaultRecipeName(BucketItems.WOODEN_BUCKET.get()));
        ShapedRecipeBuilder.shaped(BucketItems.CLAY_BUCKET.get())
                .group(BucketItems.CLAY_BUCKET.getId().getPath())
                .pattern("X X")
                .pattern(" X ")
                .define('X', Items.CLAY_BALL)
                .unlockedBy("has_clay_ball", has(Items.CLAY_BALL))
                .unlockedBy("has_clay_bucket", has(BucketTags.Items.CLAY_BUCKETS))
                .save(consumer, getDefaultRecipeName(BucketItems.CLAY_BUCKET.get()));
        ShapedRecipeBuilder.shaped(BucketBlocks.KILN.get())
                .group(getDefaultGroupName(BucketBlocks.KILN.get()))
                .pattern("AAA")
                .pattern("ABA")
                .pattern("CCC")
                .define('A', BucketTags.Items.BRICKS)
                .define('B', Blocks.FURNACE)
                .define('C', Blocks.SMOOTH_STONE_SLAB)
                .unlockedBy("has_brick", has(BucketTags.Items.BRICKS))
                .unlockedBy("has_furnace", has(Items.FURNACE))
                .unlockedBy("has_kiln", has(BucketBlocks.KILN.get()))
                .save(consumer, getDefaultRecipeName(BucketBlocks.KILN.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BucketItems.CLAY_WATER_BUCKET.get()), BucketItems.CERAMIC_WATER_BUCKET.get(), 0.1F, 200)
                .group(getDefaultGroupName(BucketItems.CERAMIC_WATER_BUCKET.get()))
                .unlockedBy("has_clay_bucket", has(BucketTags.Items.CLAY_BUCKETS))
                .unlockedBy("has_ceramic_bucket", has(BucketTags.Items.CERAMIC_BUCKETS))
                .save(consumer, getDefaultRecipeName(BucketItems.CERAMIC_WATER_BUCKET.get()));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(BucketItems.CLAY_WATER_BUCKET.get()), BucketItems.CERAMIC_WATER_BUCKET.get(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(BucketItems.CERAMIC_WATER_BUCKET.get()))
                .unlockedBy("has_clay_bucket", has(BucketTags.Items.CLAY_BUCKETS))
                .unlockedBy("has_ceramic_bucket", has(BucketTags.Items.CERAMIC_BUCKETS))
                .save(consumer, getFiringRecipeName(BucketItems.CERAMIC_WATER_BUCKET.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BucketItems.CLAY_AXOLOTL_BUCKET.get()), BucketItems.CERAMIC_AXOLOTL_BUCKET.get(), 0.1F, 200)
                .group(getDefaultGroupName(BucketItems.CERAMIC_AXOLOTL_BUCKET.get()))
                .unlockedBy("has_clay_bucket", has(BucketTags.Items.CLAY_BUCKETS))
                .unlockedBy("has_ceramic_bucket", has(BucketTags.Items.CERAMIC_BUCKETS))
                .save(consumer, getDefaultRecipeName(BucketItems.CERAMIC_AXOLOTL_BUCKET.get()));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(BucketItems.CLAY_AXOLOTL_BUCKET.get()), BucketItems.CERAMIC_WATER_BUCKET.get(), 0.1F, 100, BucketRecipeSerializers.FIRING_SERIALIZER.get())
                .group(getDefaultGroupName(BucketItems.CERAMIC_AXOLOTL_BUCKET.get()))
                .unlockedBy("has_clay_bucket", has(BucketTags.Items.CLAY_BUCKETS))
                .unlockedBy("has_ceramic_bucket", has(BucketTags.Items.CERAMIC_BUCKETS))
                .save(consumer, getFiringRecipeName(BucketItems.CERAMIC_AXOLOTL_BUCKET.get()));
    }

    @Override
    public @NotNull String getName() {
        return "Early Game Buckets Recipe Provider";
    }

}
