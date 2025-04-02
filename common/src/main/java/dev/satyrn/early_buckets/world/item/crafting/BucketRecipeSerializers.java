package dev.satyrn.early_buckets.world.item.crafting;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;

public final class BucketRecipeSerializers {
    public static final RegistrySupplier<SimpleCookingSerializer<FiringRecipe>> FIRING_SERIALIZER;
    public static final RegistrySupplier<RecipeSerializer<BreakableShapedRecipe>> CRAFTING_SHAPED_BREAKABLE_SERIALIZER;

    static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
            BucketModCommon.MOD_ID, Registry.RECIPE_SERIALIZER_REGISTRY);

    static {
        FIRING_SERIALIZER = RECIPE_SERIALIZERS.register(BucketModCommon.FIRING_RECIPE_ID,
                () -> new SimpleCookingSerializer<>(FiringRecipe::new, 100));
        CRAFTING_SHAPED_BREAKABLE_SERIALIZER = RECIPE_SERIALIZERS.register(
                BucketModCommon.CRAFTING_SHAPED_BREAKABLE_RECIPE_ID, BreakableShapedRecipe.Serializer::new);
    }

    private BucketRecipeSerializers() {
    }

    public static void register() {
        RECIPE_SERIALIZERS.register();
    }
}
