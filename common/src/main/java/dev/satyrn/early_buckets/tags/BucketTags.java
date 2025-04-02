package dev.satyrn.early_buckets.tags;

import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public final class BucketTags {

    private BucketTags() {
    }

    public static final class Items {
        public static TagKey<Item> CERAMIC_BUCKETS;
        public static TagKey<Item> CLAY_BUCKETS;
        public static TagKey<Item> WOODEN_BUCKETS;
        public static TagKey<Item> MILK_BUCKETS;
        public static TagKey<Item> EGGS;
        public static TagKey<Item> SUGAR;
        public static TagKey<Item> WHEAT;
        public static TagKey<Item> BRICKS;
        public static TagKey<Item> SAND;

        static {
            CERAMIC_BUCKETS = TagKey.create(Registry.ITEM_REGISTRY,
                    new ResourceLocation(BucketModCommon.MOD_ID, "ceramic_buckets"));
            CLAY_BUCKETS = TagKey.create(Registry.ITEM_REGISTRY,
                    new ResourceLocation(BucketModCommon.MOD_ID, "clay_buckets"));
            WOODEN_BUCKETS = TagKey.create(Registry.ITEM_REGISTRY,
                    new ResourceLocation(BucketModCommon.MOD_ID, "wooden_buckets"));
            MILK_BUCKETS = TagKey.create(Registry.ITEM_REGISTRY,
                    new ResourceLocation(BucketModCommon.MOD_ID, "milk_buckets"));
            EGGS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "eggs"));
            SUGAR = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "sugar"));
            WHEAT = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "wheat"));
            BRICKS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "bricks"));
            SAND = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "sand"));
        }

        private Items() {
        }
    }

    public static final class EntityTypes {
        public static TagKey<EntityType<?>> FISH;

        static {
            FISH = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "fish"));
        }

        private EntityTypes() {
        }
    }
}
