package dev.satyrn.early_buckets.stats;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public final class BucketStats {
    public static final RegistrySupplier<ResourceLocation> INTERACT_WITH_KILN;
    
    static final DeferredRegister<ResourceLocation> CUSTOM_STATS = DeferredRegister.create(BucketModCommon.MOD_ID,
            Registry.CUSTOM_STAT_REGISTRY);

    static {
        final ResourceLocation interactWithKilnId = new ResourceLocation(BucketModCommon.MOD_ID, "interact_with_kiln");
        INTERACT_WITH_KILN = CUSTOM_STATS.register(interactWithKilnId, () -> interactWithKilnId);
    }

    private BucketStats() {
    }

    public static void register() {
        CUSTOM_STATS.register();
    }

    public static void postRegister() {
        Stats.CUSTOM.get(BucketStats.INTERACT_WITH_KILN.getId(), StatFormatter.DEFAULT);
    }
}
