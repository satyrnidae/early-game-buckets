package dev.satyrn.early_buckets.item;

import dev.satyrn.early_buckets.BucketMod;
import dev.satyrn.early_buckets.util.NbtUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

/**
 * Holds references to all the items created by the Early-Game Buckets mod.
 * Also registers all items via the {@link BucketItems#registerAll()} method.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public final class BucketItems {
    /**
     * The maximum damage value that a ceramic bucket may accrue before breaking.
     *
     * @since 1.0.0
     */
    public static final int CERAMIC_BUCKET_MAX_DAMAGE = 128;
    /**
     * The maximum damage that a wooden bucket may accrue before breaking.
     *
     * @since 1.0.0
     */
    public static final int WOODEN_BUCKET_MAX_DAMAGE = 64;

    /**
     * Represents a fired clay bucket.
     * Ceramic buckets may hold water or lava, and are more durable than wooden buckets. The max damage a ceramic
     * bucket can take is defined by {@link BucketItems#CERAMIC_BUCKET_MAX_DAMAGE}.
     *
     * @since 1.0.0
     */
    public static final Item CERAMIC_BUCKET = new CeramicBucketItem(Fluids.EMPTY, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE));

    /**
     * Represents a fired clay bucket, into which an axolotl has been placed.
     * Ceramic buckets may hold water or lava, and are more durable than wooden buckets.
     */
    public static final Item CERAMIC_AXOLOTL_BUCKET = new CeramicEntityBucketItem(EntityType.AXOLOTL, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_AXOLOTL, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)
            .recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_COD_BUCKET = new CeramicEntityBucketItem(EntityType.COD, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)
            .recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_MILK_BUCKET = new CeramicMilkBucketItem(new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)
            .recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_PUFFERFISH_BUCKET = new CeramicEntityBucketItem(EntityType.PUFFERFISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)
            .recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_SALMON_BUCKET = new CeramicEntityBucketItem(EntityType.SALMON, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)
            .recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_TROPICAL_FISH_BUCKET = new CeramicEntityBucketItem(EntityType.TROPICAL_FISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)
            .recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_WATER_BUCKET = new CeramicBucketItem(Fluids.WATER, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)
            .recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_POWDER_SNOW_BUCKET = new CeramicPowderSnowBucketItem(Blocks.POWDER_SNOW, SoundEvents.BLOCK_POWDER_SNOW_PLACE, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)
            .recipeRemainder(CERAMIC_BUCKET));
    public static final Item CERAMIC_LAVA_BUCKET = new CeramicBucketItem(Fluids.LAVA, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(CERAMIC_BUCKET_MAX_DAMAGE)); // Ceramic lava buckets are consumed when crafted.
    public static final Item CLAY_BUCKET = new ClayBucketItem(new Item.Settings().maxCount(16).group(ItemGroup.MISC));
    public static final Item CLAY_WATER_BUCKET = new ClayBucketItem(Fluids.WATER, new Item.Settings().maxCount(1)
            .group(ItemGroup.MISC));
    public static final Item CLAY_AXOLOTL_BUCKET = new CustomEntityBucketItem(EntityType.AXOLOTL, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_AXOLOTL, new Item.Settings().maxCount(1)
            .group(ItemGroup.MISC)) {
        @Override
        public @NotNull Item getEmptyItem() {
            return Items.CLAY_BALL;
        }
    };
    /**
     * Represents a clay bucket filled with powder snow.
     * Clay buckets can pick up powder snow, but revert to clay balls when the snow is placed. Players should fire their
     * clay buckets if they want to get any use out of them.
     *
     * @since 2.0.0+alpha.1
     */
    public static final Item WOODEN_BUCKET = new WoodenBucketItem(Fluids.EMPTY, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(WOODEN_BUCKET_MAX_DAMAGE));
    public static final Item WOODEN_AXOLOTL_BUCKET = new WoodenEntityBucketItem(EntityType.AXOLOTL, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_AXOLOTL, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(WOODEN_BUCKET_MAX_DAMAGE)
            .recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_COD_BUCKET = new WoodenEntityBucketItem(EntityType.COD, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(WOODEN_BUCKET_MAX_DAMAGE)
            .recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_MILK_BUCKET = new CustomMilkBucketItem(new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(WOODEN_BUCKET_MAX_DAMAGE)
            .recipeRemainder(WOODEN_BUCKET)) {
        @Override
        protected Item getEmptyItem() {
            return WOODEN_BUCKET;
        }
    };
    public static final Item WOODEN_PUFFERFISH_BUCKET = new WoodenEntityBucketItem(EntityType.PUFFERFISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(WOODEN_BUCKET_MAX_DAMAGE)
            .recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_SALMON_BUCKET = new WoodenEntityBucketItem(EntityType.SALMON, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(WOODEN_BUCKET_MAX_DAMAGE)
            .recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_TROPICAL_FISH_BUCKET = new WoodenEntityBucketItem(EntityType.TROPICAL_FISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(WOODEN_BUCKET_MAX_DAMAGE)
            .recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_WATER_BUCKET = new WoodenBucketItem(Fluids.WATER, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(WOODEN_BUCKET_MAX_DAMAGE)
            .recipeRemainder(WOODEN_BUCKET));
    public static final Item WOODEN_POWDER_SNOW_BUCKET = new CustomPowderSnowBucketItem(Blocks.POWDER_SNOW, SoundEvents.BLOCK_POWDER_SNOW_PLACE, WOODEN_BUCKET, new Item.Settings().group(ItemGroup.MISC)
            .maxDamage(WOODEN_BUCKET_MAX_DAMAGE)
            .recipeRemainder(WOODEN_BUCKET));
    public static final Item CLAY_POWDER_SNOW_BUCKET = new CustomPowderSnowBucketItem(Blocks.POWDER_SNOW, SoundEvents.BLOCK_POWDER_SNOW_PLACE, Items.CLAY_BALL, new Item.Settings().maxCount(1)
            .group(ItemGroup.MISC));

    // Do not create new instances of BucketItems.
    private BucketItems() {
    }

    /**
     * Registers all items for the mod.
     */
    public static void registerAll() {
        // Wooden bucket items.
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "wooden_bucket"), WOODEN_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "wooden_water_bucket"), WOODEN_WATER_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "wooden_powder_snow_bucket"), WOODEN_POWDER_SNOW_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "wooden_axolotl_bucket"), WOODEN_AXOLOTL_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "wooden_cod_bucket"), WOODEN_COD_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "wooden_pufferfish_bucket"), WOODEN_PUFFERFISH_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "wooden_salmon_bucket"), WOODEN_SALMON_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "wooden_tropical_fish_bucket"), WOODEN_TROPICAL_FISH_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "wooden_milk_bucket"), WOODEN_MILK_BUCKET);

        // Clay bucket items.
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "clay_bucket"), CLAY_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "clay_water_bucket"), CLAY_WATER_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "clay_powder_snow_bucket"), CLAY_POWDER_SNOW_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "clay_axolotl_bucket"), CLAY_AXOLOTL_BUCKET);

        // Ceramic bucket items.
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_bucket"), CERAMIC_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_water_bucket"), CERAMIC_WATER_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_lava_bucket"), CERAMIC_LAVA_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_powder_snow_bucket"), CERAMIC_POWDER_SNOW_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_axolotl_bucket"), CERAMIC_AXOLOTL_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_cod_bucket"), CERAMIC_COD_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_pufferfish_bucket"), CERAMIC_PUFFERFISH_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_salmon_bucket"), CERAMIC_SALMON_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_tropical_fish_bucket"), CERAMIC_TROPICAL_FISH_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(BucketMod.MOD_ID, "ceramic_milk_bucket"), CERAMIC_MILK_BUCKET);
    }

    /**
     * Creates a new item stack with a size of one and with damage and enchantments applied from the previous one.
     *
     * @param item        The item for the new stack.
     * @param sourceStack The source item stack.
     * @return The new item stack.
     */
    public static ItemStack createItemStack(@NotNull Item item, @NotNull ItemStack sourceStack) {
        final ItemStack createdItemStack = new ItemStack(item);

        if (item.isDamageable()) {
            createdItemStack.setDamage(sourceStack.getDamage());
        }
        NbtUtils.copyEnchantmentItemTags(sourceStack, createdItemStack);

        return createdItemStack;
    }

}
