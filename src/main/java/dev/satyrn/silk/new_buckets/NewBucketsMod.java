package dev.satyrn.silk.new_buckets;

import dev.satyrn.silk.new_buckets.item.*;
import dev.satyrn.silk.new_buckets.recipe.ShapedRecipeWithDamage;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * The initializer for the New Buckets mod.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class NewBucketsMod implements ModInitializer {
    private static final int CERAMIC_BUCKET_MAX_DAMAGE = 64;
    private static final int WOODEN_BUCKET_MAX_DAMAGE = 32;

    public static final Item CERAMIC_BUCKET = new CeramicBucketItem(Fluids.EMPTY, new Item.Settings().group(ItemGroup.MISC).maxDamage(CERAMIC_BUCKET_MAX_DAMAGE));
    public static final Item CERAMIC_COD_BUCKET = new CeramicFishBucketItem(EntityType.COD, Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(CERAMIC_BUCKET_MAX_DAMAGE).recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_LAVA_BUCKET = new CeramicBucketItem(Fluids.LAVA, new Item.Settings().group(ItemGroup.MISC).maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)); // Lava ceramic buckets are consumed.
    public static final Item CERAMIC_MILK_BUCKET = new CustomMilkBucketItem(new Item.Settings().group(ItemGroup.MISC).maxDamage(CERAMIC_BUCKET_MAX_DAMAGE).recipeRemainder(CERAMIC_BUCKET)) { @Override protected Item getEmptyItem() { return CERAMIC_BUCKET; }};
    public static final Item CERAMIC_PUFFERFISH_BUCKET = new CeramicFishBucketItem(EntityType.PUFFERFISH, Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(CERAMIC_BUCKET_MAX_DAMAGE).recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_SALMON_BUCKET = new CeramicFishBucketItem(EntityType.SALMON, Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(CERAMIC_BUCKET_MAX_DAMAGE).recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_TROPICAL_FISH_BUCKET = new CeramicFishBucketItem(EntityType.TROPICAL_FISH, Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(CERAMIC_BUCKET_MAX_DAMAGE).recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_WATER_BUCKET = new CeramicBucketItem(Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(CERAMIC_BUCKET_MAX_DAMAGE).recipeRemainder(CERAMIC_BUCKET));
    public static final Item CLAY_BUCKET = new Item(new Item.Settings().maxCount(16).group(ItemGroup.MISC));
    public static final Item WOODEN_BUCKET = new WoodenBucketItem(Fluids.EMPTY, new Item.Settings().group(ItemGroup.MISC).maxDamage(WOODEN_BUCKET_MAX_DAMAGE));
    public static final Item WOODEN_COD_BUCKET = new WoodenFishBucketItem(EntityType.COD, Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(WOODEN_BUCKET_MAX_DAMAGE).recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_MILK_BUCKET = new CustomMilkBucketItem(new Item.Settings().group(ItemGroup.MISC).maxDamage(WOODEN_BUCKET_MAX_DAMAGE).recipeRemainder(WOODEN_BUCKET)) { @Override protected Item getEmptyItem() { return WOODEN_BUCKET; }};
    public static final Item WOODEN_PUFFERFISH_BUCKET = new WoodenFishBucketItem(EntityType.PUFFERFISH, Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(WOODEN_BUCKET_MAX_DAMAGE).recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_SALMON_BUCKET = new WoodenFishBucketItem(EntityType.SALMON, Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(WOODEN_BUCKET_MAX_DAMAGE).recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_TROPICAL_FISH_BUCKET = new WoodenFishBucketItem(EntityType.TROPICAL_FISH, Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(WOODEN_BUCKET_MAX_DAMAGE).recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_WATER_BUCKET = new WoodenBucketItem(Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(WOODEN_BUCKET_MAX_DAMAGE).recipeRemainder(WOODEN_BUCKET));

    public static final RecipeType<ShapedRecipeWithDamage> SHAPED_RECIPE_WITH_DAMAGE = RecipeType.register("new_buckets:crafting_shaped_damageable");
    public static final RecipeSerializer<ShapedRecipeWithDamage> SHAPED_RECIPE_WITH_DAMAGE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier("new_buckets", "crafting_shaped_damageable"), new ShapedRecipeWithDamage.Serializer());

    /**
     * Initializes the New Buckets mod.
     * @since 1.0.0
     */
    @Override
    public void onInitialize() {
        // Items
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "wooden_bucket"), WOODEN_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "wooden_water_bucket"), WOODEN_WATER_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "wooden_milk_bucket"), WOODEN_MILK_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "wooden_pufferfish_bucket"), WOODEN_PUFFERFISH_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "wooden_salmon_bucket"), WOODEN_SALMON_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "wooden_cod_bucket"), WOODEN_COD_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "wooden_tropical_fish_bucket"), WOODEN_TROPICAL_FISH_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "clay_bucket"), CLAY_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "ceramic_bucket"), CERAMIC_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "ceramic_water_bucket"), CERAMIC_WATER_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "ceramic_lava_bucket"), CERAMIC_LAVA_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "ceramic_milk_bucket"), CERAMIC_MILK_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "ceramic_pufferfish_bucket"), CERAMIC_PUFFERFISH_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "ceramic_salmon_bucket"), CERAMIC_SALMON_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "ceramic_cod_bucket"), CERAMIC_COD_BUCKET);
        Registry.register(Registry.ITEM, new Identifier("new_buckets", "ceramic_tropical_fish_bucket"), CERAMIC_TROPICAL_FISH_BUCKET);

        // Fuels
        FuelRegistry.INSTANCE.add(WOODEN_BUCKET, 200);
        FuelRegistry.INSTANCE.add(CERAMIC_LAVA_BUCKET, 20000); // but consumes the bucket!
    }

    /**
     * Copies enchantment tags between a source and target stack
     * @param sourceStack The item stack from which the enchantments should be copied.
     * @param targetStack The item stack to which the enchantments should be copied.
     * @since 1.0.0
     */
    // TODO: Excellent candidate for moon moth API.
    public static void copyEnchantmentItemTags(ItemStack sourceStack, ItemStack targetStack) {
        CompoundTag targetTags = targetStack.getOrCreateTag();
        if (!targetTags.contains("Enchantments", 9)) {
            targetTags.put("Enchantments", new ListTag());
        }
        ListTag targetEnchants = targetTags.getList("Enchantments", 10);
        targetEnchants.addAll(sourceStack.getEnchantments());
        targetStack.setTag(targetTags);
    }
}