package dev.satyrn.early_buckets.fabric.client;

import dev.satyrn.early_buckets.fabriclike.client.BucketModClientFabricLike;
import net.fabricmc.api.ClientModInitializer;

public final class BucketModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BucketModClientFabricLike.initClient();
    }
}
