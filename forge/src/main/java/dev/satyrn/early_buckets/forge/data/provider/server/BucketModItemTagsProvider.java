package dev.satyrn.early_buckets.forge.data.provider.server;

import dev.satyrn.early_buckets.tags.BucketTags;
import dev.satyrn.early_buckets.world.item.BucketItems;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BucketModItemTagsProvider extends ItemTagsProvider {

    public BucketModItemTagsProvider(DataGenerator arg,
                                     BlockTagsProvider arg2,
                                     String modId,
                                     @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, arg2, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        final var conventionalMilkBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "buckets/milk"));
        this.tag(BucketTags.Items.MILK_BUCKETS)
                .add(Items.MILK_BUCKET)
                .add(BucketItems.WOODEN_MILK_BUCKET.get())
                .add(BucketItems.CERAMIC_MILK_BUCKET.get())
                .addOptionalTag(conventionalMilkBucketsTag.location());
        this.tag(conventionalMilkBucketsTag)
                .addTag(BucketTags.Items.MILK_BUCKETS);
        final var forgeMilkTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "milk"));
        this.tag(forgeMilkTag)
                .addTag(BucketTags.Items.MILK_BUCKETS);
        this.tag(BucketTags.Items.SUGAR)
                .add(Items.SUGAR);
        this.tag(BucketTags.Items.EGGS)
                .add(Items.EGG)
                .addOptionalTag(new ResourceLocation("c", "eggs"))
                .addOptionalTag(Tags.Items.EGGS.location());
        this.tag(BucketTags.Items.WHEAT)
                .add(Items.WHEAT)
                .addOptionalTag(Tags.Items.CROPS_WHEAT.location());
        this.tag(ItemTags.AXOLOTL_TEMPT_ITEMS)
                .add(BucketItems.CERAMIC_TROPICAL_FISH_BUCKET.get())
                .add(BucketItems.WOODEN_TROPICAL_FISH_BUCKET.get());
        final var forgeCeramicBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "buckets/ceramic"));
        this.tag(forgeCeramicBucketsTag)
                .add(BucketItems.CERAMIC_BUCKET.get())
                .add(BucketItems.CERAMIC_MILK_BUCKET.get())
                .add(BucketItems.CERAMIC_AXOLOTL_BUCKET.get())
                .add(BucketItems.CERAMIC_COD_BUCKET.get())
                .add(BucketItems.CERAMIC_LAVA_BUCKET.get())
                .add(BucketItems.CERAMIC_TROPICAL_FISH_BUCKET.get())
                .add(BucketItems.CERAMIC_POWDER_SNOW_BUCKET.get())
                .add(BucketItems.CERAMIC_PUFFERFISH_BUCKET.get())
                .add(BucketItems.CERAMIC_SALMON_BUCKET.get())
                .add(BucketItems.CERAMIC_TADPOLE_BUCKET.get())
                .add(BucketItems.CERAMIC_WATER_BUCKET.get());
        final var conventionalCeramicBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "buckets/ceramic"));
        this.copyItemTag(forgeCeramicBucketsTag, conventionalCeramicBucketsTag);
        this.tag(BucketTags.Items.CERAMIC_BUCKETS)
                .addOptionalTag(forgeCeramicBucketsTag.location())
                .addOptionalTag(conventionalCeramicBucketsTag.location());
        final var forgeWoodenBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "buckets/wooden"));
        this.tag(forgeWoodenBucketsTag)
                .add(BucketItems.WOODEN_BUCKET.get())
                .add(BucketItems.WOODEN_MILK_BUCKET.get())
                .add(BucketItems.WOODEN_AXOLOTL_BUCKET.get())
                .add(BucketItems.WOODEN_COD_BUCKET.get())
                .add(BucketItems.WOODEN_PUFFERFISH_BUCKET.get())
                .add(BucketItems.WOODEN_TROPICAL_FISH_BUCKET.get())
                .add(BucketItems.WOODEN_POWDER_SNOW_BUCKET.get())
                .add(BucketItems.WOODEN_SALMON_BUCKET.get())
                .add(BucketItems.WOODEN_TADPOLE_BUCKET.get())
                .add(BucketItems.WOODEN_WATER_BUCKET.get());
        final var conventionalWoodenBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "buckets/wooden"));
        this.copyItemTag(forgeWoodenBucketsTag, conventionalWoodenBucketsTag);
        this.tag(BucketTags.Items.WOODEN_BUCKETS)
                .addOptionalTag(forgeWoodenBucketsTag.location())
                .addOptionalTag(conventionalCeramicBucketsTag.location());
        final var forgeClayBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "buckets/clay"));
        this.tag(forgeClayBucketsTag)
                .add(BucketItems.CLAY_BUCKET.get())
                .add(BucketItems.CLAY_AXOLOTL_BUCKET.get())
                .add(BucketItems.CLAY_WATER_BUCKET.get())
                .add(BucketItems.CLAY_POWDER_SNOW_BUCKET.get());
        final var conventionalClayBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "buckets/clay"));
        this.copyItemTag(forgeClayBucketsTag, conventionalClayBucketsTag);
        this.tag(BucketTags.Items.CLAY_BUCKETS)
                .addOptionalTag(forgeClayBucketsTag.location())
                .addOptionalTag(conventionalClayBucketsTag.location());
        final var forgeIronBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "buckets/iron"));
        this.tag(forgeIronBucketsTag)
                .add(Items.BUCKET)
                .add(Items.MILK_BUCKET)
                .add(Items.AXOLOTL_BUCKET)
                .add(Items.COD_BUCKET)
                .add(Items.PUFFERFISH_BUCKET)
                .add(Items.TROPICAL_FISH_BUCKET)
                .add(Items.POWDER_SNOW_BUCKET)
                .add(Items.SALMON_BUCKET)
                .add(Items.TADPOLE_BUCKET)
                .add(Items.WATER_BUCKET)
                .add(Items.LAVA_BUCKET);
        final var conventionalIronBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "buckets/iron"));
        this.copyItemTag(forgeIronBucketsTag, conventionalIronBucketsTag);
        final var forgeBucketsTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "buckets"));
        this.tag(forgeBucketsTag)
                .addTag(forgeIronBucketsTag)
                .addTag(forgeClayBucketsTag)
                .addTag(forgeCeramicBucketsTag)
                .addTag(forgeWoodenBucketsTag);
        this.tag(BucketTags.Items.BRICKS)
                .add(Items.BRICK)
                .addOptionalTag(new ResourceLocation("c", "bricks/normal"))
                .addOptionalTag(Tags.Items.INGOTS_BRICK.location());
        this.tag(BucketTags.Items.SAND)
                .addOptionalTag(new ResourceLocation("sand"))
                .addOptionalTag(new ResourceLocation("smelts_to_sand"));
        final var phc2ForgeMilk = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "milk/milk"));
        this.tag(phc2ForgeMilk)
                .addTag(BucketTags.Items.MILK_BUCKETS);
    }

    private void copyItemTag(TagKey<Item> source, TagKey<Item> destination) {
        TagBuilder destBuilder = this.getOrCreateRawBuilder(destination);
        TagBuilder sourceBuilder = this.getOrCreateRawBuilder(source);
        final var sourceTags = sourceBuilder.build();
        Objects.requireNonNull(destBuilder);
        sourceTags.forEach(destBuilder::add);
    }
}
