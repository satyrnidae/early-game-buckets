package dev.satyrn.early_buckets;

import dev.satyrn.early_buckets.advancement.criterion.AdvancedFillBucketCriterion;
import dev.satyrn.early_buckets.block.cauldron.BucketCauldronBehaviors;
import dev.satyrn.early_buckets.item.BucketItems;
import dev.satyrn.early_buckets.mixin.accessors.CriteriaInvoker;
import dev.satyrn.early_buckets.recipe.BreakableShapedRecipe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * The initializer for the New Buckets mod.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
public class BucketMod implements ModInitializer {
    /**
     * The mod ID.
     */
    public static final String MOD_ID = "early_buckets";

    public static final RecipeSerializer<BreakableShapedRecipe> SHAPED_RECIPE_WITH_DAMAGE_SERIALIZER;
    public static final AdvancedFillBucketCriterion FILL_BUCKET = CriteriaInvoker.callRegister(new AdvancedFillBucketCriterion());

    static {
        RecipeType.register(MOD_ID + ":crafting_shaped_breakable");
        SHAPED_RECIPE_WITH_DAMAGE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, "crafting_shaped_breakable"), new BreakableShapedRecipe.Serializer());
    }

    /**
     * Initializes the New Buckets mod.
     *
     * @since 1.0.0
     */
    @Override
    public void onInitialize() {
        BucketItems.registerAll();
        BucketCauldronBehaviors.registerAll();

        // Fuels
        FuelRegistry.INSTANCE.add(BucketItems.WOODEN_BUCKET, 200);
        FuelRegistry.INSTANCE.add(BucketItems.CERAMIC_LAVA_BUCKET, 20000); // but consumes the bucket!
    }
}