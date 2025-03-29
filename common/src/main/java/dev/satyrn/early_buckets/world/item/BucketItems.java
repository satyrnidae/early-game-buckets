package dev.satyrn.early_buckets.world.item;

import dev.architectury.registry.fuel.FuelRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.utils.NbtUtils;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;


/**
 * Holds references to all the items created by the Early-Game Buckets mod.
 * Also registers all items via the {@link BucketItems#register()} method.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public final class BucketItems {

    public static final RegistrySupplier<Item> CERAMIC_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_AXOLOTL_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_COD_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_MILK_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_PUFFERFISH_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_SALMON_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_TADPOLE_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_TROPICAL_FISH_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_WATER_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_POWDER_SNOW_BUCKET;
    public static final RegistrySupplier<Item> CERAMIC_LAVA_BUCKET;
    public static final RegistrySupplier<Item> CLAY_BUCKET;
    public static final RegistrySupplier<Item> CLAY_WATER_BUCKET;
    public static final RegistrySupplier<Item> CLAY_AXOLOTL_BUCKET;
    public static final RegistrySupplier<Item> CLAY_POWDER_SNOW_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_AXOLOTL_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_COD_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_MILK_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_PUFFERFISH_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_SALMON_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_TADPOLE_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_TROPICAL_FISH_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_WATER_BUCKET;
    public static final RegistrySupplier<Item> WOODEN_POWDER_SNOW_BUCKET;
    public static final RegistrySupplier<Item> KILN;

    static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BucketModCommon.MOD_ID, Registry.ITEM_REGISTRY);

    static {
        CERAMIC_BUCKET = ITEMS.register("ceramic_bucket", () -> new CeramicBucketItem(Fluids.EMPTY,
                new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                        .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)));
        CERAMIC_AXOLOTL_BUCKET = ITEMS.register("ceramic_axolotl_bucket",
                () -> new CeramicEntityBucketItem(EntityType.AXOLOTL, Fluids.WATER, SoundEvents.BUCKET_FILL_AXOLOTL,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)
                                .craftRemainder(CERAMIC_BUCKET.get())));
        CERAMIC_COD_BUCKET = ITEMS.register("ceramic_cod_bucket",
                () -> new CeramicEntityBucketItem(EntityType.COD, Fluids.WATER, SoundEvents.BUCKET_FILL_FISH,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)
                                .craftRemainder(CERAMIC_BUCKET.get())));
        CERAMIC_MILK_BUCKET = ITEMS.register("ceramic_milk_bucket", () -> new CeramicMilkBucketItem(
                new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                        .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)
                        .craftRemainder(CERAMIC_BUCKET.get())));
        CERAMIC_PUFFERFISH_BUCKET = ITEMS.register("ceramic_pufferfish_bucket",
                () -> new CeramicEntityBucketItem(EntityType.PUFFERFISH, Fluids.WATER, SoundEvents.BUCKET_FILL_FISH,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)
                                .craftRemainder(CERAMIC_BUCKET.get())));
        CERAMIC_SALMON_BUCKET = ITEMS.register("ceramic_salmon_bucket",
                () -> new CeramicEntityBucketItem(EntityType.SALMON, Fluids.WATER, SoundEvents.BUCKET_FILL_FISH,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)
                                .craftRemainder(CERAMIC_BUCKET.get())));
        CERAMIC_TADPOLE_BUCKET = ITEMS.register("ceramic_tadpole_bucket",
                () -> new CeramicEntityBucketItem(EntityType.TADPOLE, Fluids.WATER, SoundEvents.BUCKET_FILL_TADPOLE,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)
                                .craftRemainder(CERAMIC_BUCKET.get())));
        CERAMIC_TROPICAL_FISH_BUCKET = ITEMS.register("ceramic_tropical_fish_bucket",
                () -> new CeramicEntityBucketItem(EntityType.TROPICAL_FISH, Fluids.WATER, SoundEvents.BUCKET_FILL_FISH,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)
                                .craftRemainder(CERAMIC_BUCKET.get())));
        CERAMIC_WATER_BUCKET = ITEMS.register("ceramic_water_bucket", () -> new CeramicBucketItem(Fluids.WATER,
                new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                        .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)
                        .craftRemainder(CERAMIC_BUCKET.get())));
        CERAMIC_POWDER_SNOW_BUCKET = ITEMS.register("ceramic_powder_snow_bucket",
                () -> new CeramicPowderSnowBucketItem(Blocks.POWDER_SNOW, SoundEvents.POWDER_SNOW_PLACE,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)
                                .craftRemainder(CERAMIC_BUCKET.get())));
        CERAMIC_LAVA_BUCKET = ITEMS.register("ceramic_lava_bucket", () -> new CeramicBucketItem(Fluids.LAVA,
                new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                        .defaultDurability(CustomBucket.CERAMIC_BUCKET_DURABILITY)));
        CLAY_BUCKET = ITEMS.register("clay_bucket",
                () -> new ClayBucketItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(16)));
        CLAY_WATER_BUCKET = ITEMS.register("clay_water_bucket", () -> new ClayBucketItem(Fluids.WATER,
                new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).craftRemainder(Items.CLAY_BALL)));
        CLAY_AXOLOTL_BUCKET = ITEMS.register("clay_axolotl_bucket",
                () -> new CustomEntityBucketItem(EntityType.AXOLOTL, Fluids.WATER, SoundEvents.BUCKET_FILL_AXOLOTL,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .stacksTo(1)
                                .craftRemainder(Items.CLAY_BALL)) {
                    @Override
                    public Item getEmptyItem() {
                        return Items.CLAY_BALL;
                    }
                });
        CLAY_POWDER_SNOW_BUCKET = ITEMS.register("clay_powder_snow_bucket",
                () -> new CustomPowderSnowBucketItem(Blocks.POWDER_SNOW, SoundEvents.POWDER_SNOW_PLACE, Items.CLAY_BALL,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .craftRemainder(Items.CLAY_BALL)
                                .stacksTo(1)));
        WOODEN_BUCKET = ITEMS.register("wooden_bucket", () -> new WoodenBucketItem(Fluids.EMPTY,
                new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                        .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)));
        WOODEN_AXOLOTL_BUCKET = ITEMS.register("wooden_axolotl_bucket",
                () -> new WoodenEntityBucketItem(EntityType.AXOLOTL, Fluids.WATER, SoundEvents.BUCKET_FILL_AXOLOTL,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)
                                .craftRemainder(WOODEN_BUCKET.get())));
        WOODEN_COD_BUCKET = ITEMS.register("wooden_cod_bucket",
                () -> new WoodenEntityBucketItem(EntityType.COD, Fluids.WATER, SoundEvents.BUCKET_FILL_FISH,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)
                                .craftRemainder(WOODEN_BUCKET.get())));
        WOODEN_MILK_BUCKET = ITEMS.register("wooden_milk_bucket", () -> new CustomMilkBucketItem(
                new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                        .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)
                        .craftRemainder(WOODEN_BUCKET.get())) {
            @Override
            protected Item getEmptyItem() {
                return WOODEN_BUCKET.get();
            }
        });
        WOODEN_PUFFERFISH_BUCKET = ITEMS.register("wooden_pufferfish_bucket",
                () -> new WoodenEntityBucketItem(EntityType.PUFFERFISH, Fluids.WATER, SoundEvents.BUCKET_FILL_FISH,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)
                                .craftRemainder(WOODEN_BUCKET.get())));
        WOODEN_SALMON_BUCKET = ITEMS.register("wooden_salmon_bucket",
                () -> new WoodenEntityBucketItem(EntityType.SALMON, Fluids.WATER, SoundEvents.BUCKET_FILL_FISH,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)
                                .craftRemainder(WOODEN_BUCKET.get())));
        WOODEN_TADPOLE_BUCKET = ITEMS.register("wooden_tadpole_bucket",
                () -> new WoodenEntityBucketItem(EntityType.TADPOLE, Fluids.WATER, SoundEvents.BUCKET_FILL_TADPOLE,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)
                                .craftRemainder(WOODEN_BUCKET.get())));
        WOODEN_TROPICAL_FISH_BUCKET = ITEMS.register("wooden_tropical_fish_bucket",
                () -> new WoodenEntityBucketItem(EntityType.TROPICAL_FISH, Fluids.WATER, SoundEvents.BUCKET_FILL_FISH,
                        new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                                .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)
                                .craftRemainder(WOODEN_BUCKET.get())));
        WOODEN_WATER_BUCKET = ITEMS.register("wooden_water_bucket", () -> new WoodenBucketItem(Fluids.WATER,
                new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                        .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)
                        .craftRemainder(WOODEN_BUCKET.get())));
        WOODEN_POWDER_SNOW_BUCKET = ITEMS.register("wooden_powder_snow_bucket",
                () -> new CustomPowderSnowBucketItem(Blocks.POWDER_SNOW, SoundEvents.POWDER_SNOW_PLACE,
                        WOODEN_BUCKET.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                        .defaultDurability(CustomBucket.WOODEN_BUCKET_DURABILITY)
                        .craftRemainder(WOODEN_BUCKET.get())));
        KILN = ITEMS.register("kiln", () -> new BlockItem(BucketBlocks.KILN.get(),
                new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    }

    // Do not create new instances of BucketItems.
    private BucketItems() {
    }

    /**
     * Registers all items for the mod.
     */
    public static void register() {
        ITEMS.register();
    }

    /**
     * Creates a new item stack with a size of one and with damage and enchantments applied from the previous one.
     *
     * @param item        The item for the new stack.
     * @param sourceStack The source item stack.
     *
     * @return The new item stack.
     */
    public static ItemStack createItemStack(@NotNull Item item, @NotNull ItemStack sourceStack) {
        final ItemStack createdItemStack = new ItemStack(item);

        if (item.canBeDepleted()) {
            createdItemStack.setDamageValue(sourceStack.getDamageValue());
        }
        NbtUtils.copyEnchantmentItemTags(sourceStack, createdItemStack);

        return createdItemStack;
    }

    public static void postRegister() {
        FuelRegistry.register(200, WOODEN_BUCKET.get());
        FuelRegistry.register(20000, CERAMIC_LAVA_BUCKET.get());
    }

}
