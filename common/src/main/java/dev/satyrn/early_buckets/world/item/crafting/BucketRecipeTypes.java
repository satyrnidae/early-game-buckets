package dev.satyrn.early_buckets.world.item.crafting;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeType;

public final class BucketRecipeTypes {
    public static final RegistrySupplier<RecipeType<FiringRecipe>> FIRING;
    public static final RegistrySupplier<RecipeType<BreakableShapedRecipe>> CRAFTING_SHAPED_BREAKABLE;

    static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BucketModCommon.MOD_ID,
            Registry.RECIPE_TYPE_REGISTRY);

    static {
        FIRING = RECIPE_TYPES.register(BucketModCommon.FIRING_RECIPE_ID, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return BucketModCommon.FIRING_RECIPE_ID.getPath();
            }
        });
        CRAFTING_SHAPED_BREAKABLE = RECIPE_TYPES.register(BucketModCommon.CRAFTING_SHAPED_BREAKABLE_RECIPE_ID,
                () -> new RecipeType<>() {
                    @Override
                    public String toString() {
                        return BucketModCommon.CRAFTING_SHAPED_BREAKABLE_RECIPE_ID.toString();
                    }
                });
    }

    private BucketRecipeTypes() {
    }

    public static void register() {
        RECIPE_TYPES.register();
    }
}
