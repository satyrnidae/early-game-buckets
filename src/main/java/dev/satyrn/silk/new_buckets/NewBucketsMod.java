package dev.satyrn.silk.new_buckets;

import dev.satyrn.silk.new_buckets.item.CustomMilkBucketItem;
import dev.satyrn.silk.new_buckets.item.WoodenBucketItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * The initializer for the New Buckets mod.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class NewBucketsMod implements ModInitializer {
    public static Item WOODEN_BUCKET = new WoodenBucketItem(Fluids.EMPTY, new Item.Settings().group(ItemGroup.MISC).maxDamage(32));
    public static Item WOODEN_WATER_BUCKET = new WoodenBucketItem(Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxDamage(32));
    public static Item WOODEN_MILK_BUCKET = new CustomMilkBucketItem(new Item.Settings().recipeRemainder(WOODEN_BUCKET).maxDamage(32).group(ItemGroup.MISC)) {
        @Override
        protected Item getEmptyItem() { return WOODEN_BUCKET; }
    };

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

        // Fuels
        FuelRegistry.INSTANCE.add(WOODEN_BUCKET, 200);
    }
}