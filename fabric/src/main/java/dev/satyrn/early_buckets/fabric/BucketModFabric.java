package dev.satyrn.early_buckets.fabric;

import dev.satyrn.early_buckets.fabriclike.BucketModFabricLike;
import net.fabricmc.api.ModInitializer;

public final class BucketModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // Run the Fabric-like setup.
        BucketModFabricLike.init();
    }
}
