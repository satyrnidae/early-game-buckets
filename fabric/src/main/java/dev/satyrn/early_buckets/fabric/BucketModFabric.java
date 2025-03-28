package dev.satyrn.early_buckets.fabric;

import net.fabricmc.api.ModInitializer;

import dev.satyrn.early_buckets.fabriclike.BucketModFabricLike;

public final class BucketModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run the Fabric-like setup.
        BucketModFabricLike.init();
    }
}
