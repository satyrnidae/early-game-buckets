package dev.satyrn.early_buckets.quilt;

import dev.satyrn.early_buckets.fabriclike.BucketModFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

@SuppressWarnings("unused")
public final class BucketModQuilt implements ModInitializer {
    @Override
    public void onInitialize(final ModContainer mod) {
        // Run the Fabric-like setup.
        BucketModFabricLike.init();
    }
}
