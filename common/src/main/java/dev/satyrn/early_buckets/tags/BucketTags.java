package dev.satyrn.early_buckets.tags;

import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public final class BucketTags {

    private BucketTags() {}

    public static final class Items {
        public static TagKey<Item> CERAMIC_BUCKETS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "ceramic_buckets"));
        public static TagKey<Item> CLAY_BUCKETS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "clay_buckets"));
        public static TagKey<Item> WOODEN_BUCKETS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "wooden_buckets"));
        public static TagKey<Item> MILK_BUCKETS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "milk_buckets"));
        public static TagKey<Item> EGGS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "eggs"));
        public static TagKey<Item> SUGAR = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "sugar"));
        public static TagKey<Item> WHEAT = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "wheat"));
        public static TagKey<Item> BRICKS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "bricks"));
        public static TagKey<Item> SAND = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "sand"));

        private Items() {}
    }

    public static final class EntityTypes {
        public static TagKey<EntityType<?>> FISH = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(BucketModCommon.MOD_ID, "fish"));

        private EntityTypes() {}
    }
}
