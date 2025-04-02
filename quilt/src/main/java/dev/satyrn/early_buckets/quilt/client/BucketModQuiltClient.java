package dev.satyrn.early_buckets.quilt.client;

import dev.satyrn.early_buckets.fabriclike.client.BucketModClientFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

@SuppressWarnings("unused")
public class BucketModQuiltClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(final ModContainer modContainer) {
        // Do client initialization
        BucketModClientFabricLike.initClient();
    }
}
