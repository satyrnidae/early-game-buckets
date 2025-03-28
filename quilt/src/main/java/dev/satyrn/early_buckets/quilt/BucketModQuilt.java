package dev.satyrn.early_buckets.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import dev.satyrn.early_buckets.fabriclike.BucketModFabricLike;

@SuppressWarnings("unused")
public final class BucketModQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        // Run the Fabric-like setup.
        BucketModFabricLike.init();
    }
}
