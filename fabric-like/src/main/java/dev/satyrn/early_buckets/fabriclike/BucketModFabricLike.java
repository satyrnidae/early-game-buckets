package dev.satyrn.early_buckets.fabriclike;

import dev.satyrn.early_buckets.BucketModCommon;

public final class BucketModFabricLike {
    private BucketModFabricLike() {
    }

    public static void init() {
        // Run our common setup.
        BucketModCommon.init();
        // Unlike forge we can just do our post init right the fuck now
        BucketModCommon.postInit();
    }
}
