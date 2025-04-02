package dev.satyrn.early_buckets.forge.data.provider.server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.advancements.criterion.CustomFilledBucketTrigger;
import dev.satyrn.early_buckets.tags.BucketTags;
import dev.satyrn.early_buckets.world.item.BucketItems;
import dev.satyrn.early_buckets.world.item.CeramicBucketItem;
import net.minecraft.Util;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.PredicateManager;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public class BucketModAdvancementProvider extends AdvancementProvider {

    public BucketModAdvancementProvider(final DataGenerator generatorIn, final ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    public static ResourceLocation getTranslationLocation(final String advancement) {
        return new ResourceLocation(BucketModCommon.MOD_ID, advancement);
    }

    private static ResourceLocation getAdvancementLocation(final String advancement) {
        return new ResourceLocation(BucketModCommon.MOD_ID, BucketModCommon.MOD_ID + "/" + advancement);
    }

    private static void addTadpoleCriteria(final Advancement.Builder builder) {
        final var bucketItems = new RegistrySupplier[]{BucketItems.WOODEN_TADPOLE_BUCKET, BucketItems.CERAMIC_TADPOLE_BUCKET};
        addBucketableCriteria(builder, bucketItems);
    }

    private static void addAxolotlCriteria(final Advancement.Builder builder) {
        final var bucketItems = new RegistrySupplier[]{BucketItems.CERAMIC_AXOLOTL_BUCKET, BucketItems.WOODEN_AXOLOTL_BUCKET, BucketItems.CLAY_AXOLOTL_BUCKET};
        addBucketableCriteria(builder, bucketItems);
    }

    private static void addFishCriteria(final Advancement.Builder builder) {
        final var bucketItems = new RegistrySupplier[]{BucketItems.CERAMIC_COD_BUCKET, BucketItems.CERAMIC_TROPICAL_FISH_BUCKET, BucketItems.CERAMIC_PUFFERFISH_BUCKET, BucketItems.CERAMIC_SALMON_BUCKET, BucketItems.WOODEN_COD_BUCKET, BucketItems.WOODEN_TROPICAL_FISH_BUCKET, BucketItems.WOODEN_PUFFERFISH_BUCKET, BucketItems.WOODEN_SALMON_BUCKET};
        addBucketableCriteria(builder, bucketItems);
    }

    @SuppressWarnings("rawtypes")
    private static void addBucketableCriteria(final Advancement.Builder builder,
                                              final RegistrySupplier[] bucketItemSuppliers) {
        for (final var item : bucketItemSuppliers) {
            builder.addCriterion(item.getId().getPath(), FilledBucketTrigger.TriggerInstance.filledBucket(
                    ItemPredicate.Builder.item().of((ItemLike) item.get()).build()));
        }
    }

    private static ResourceLocation getAbsoluteResourceLoc(final ResourceLocation relative) {
        return new ResourceLocation(relative.getNamespace(), "advancements/" + relative.getPath() + ".json");
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void registerAdvancements(final Consumer<Advancement> consumer, final ExistingFileHelper fileHelper) {

        try {
            final Gson gson = new Gson();
            // Alter Minecraft advancements to support fish buckets
            final var fishBucket = new ResourceLocation("husbandry/tactical_fishing");
            try (var reader = fileHelper.getResource(getAbsoluteResourceLoc(fishBucket), PackType.SERVER_DATA)
                    .openAsReader()) {
                var advancement = Advancement.Builder.fromJson(
                        gson.fromJson(reader, JsonElement.class).getAsJsonObject(),
                        new DeserializationContext(getAbsoluteResourceLoc(fishBucket), new PredicateManager()));
                addFishCriteria(advancement);
                advancement.requirements(RequirementsStrategy.OR.createRequirements(advancement.getCriteria().keySet()))
                        .save(consumer, fishBucket, fileHelper);
            }

            final var axolotlBucket = new ResourceLocation("husbandry/axolotl_in_a_bucket");
            try (var reader = fileHelper.getResource(getAbsoluteResourceLoc(axolotlBucket), PackType.SERVER_DATA)
                    .openAsReader()) {
                var advancement = Advancement.Builder.fromJson(
                        gson.fromJson(reader, JsonElement.class).getAsJsonObject(),
                        new DeserializationContext(getAbsoluteResourceLoc(axolotlBucket), new PredicateManager()));
                addAxolotlCriteria(advancement);
                advancement.requirements(RequirementsStrategy.OR.createRequirements(advancement.getCriteria().keySet()))
                        .save(consumer, axolotlBucket, fileHelper);
            }

            final var tadpoleBucket = new ResourceLocation("husbandry/tadpole_in_a_bucket");
            try (var reader = fileHelper.getResource(getAbsoluteResourceLoc(tadpoleBucket), PackType.SERVER_DATA)
                    .openAsReader()) {
                var advancement = Advancement.Builder.fromJson(
                        gson.fromJson(reader, JsonElement.class).getAsJsonObject(),
                        new DeserializationContext(getAbsoluteResourceLoc(tadpoleBucket), new PredicateManager()));
                addTadpoleCriteria(advancement);
                advancement.requirements(RequirementsStrategy.OR.createRequirements(advancement.getCriteria().keySet()))
                        .save(consumer, tadpoleBucket, fileHelper);
            }

            final var lavaBucket = new ResourceLocation("story/lava_bucket");
            try (var reader = fileHelper.getResource(getAbsoluteResourceLoc(lavaBucket), PackType.SERVER_DATA)
                    .openAsReader()) {
                var advancement = Advancement.Builder.fromJson(
                        gson.fromJson(reader, JsonElement.class).getAsJsonObject(),
                        new DeserializationContext(getAbsoluteResourceLoc(lavaBucket), new PredicateManager()));
                advancement.addCriterion(BucketItems.CERAMIC_LAVA_BUCKET.getId().getPath(),
                                new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY,
                                        MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY,
                                        new ItemPredicate[]{ItemPredicate.Builder.item()
                                                .of(BucketItems.CERAMIC_LAVA_BUCKET.get()).build()}))
                        .requirements(RequirementsStrategy.OR.createRequirements(advancement.getCriteria().keySet()))
                        .save(consumer, lavaBucket, fileHelper);
            }

        } catch (IOException ignored) {
            // This should not happen
            throw new AssertionError();
        }

        // Mod advancements
        final var earlyBucketsRoot = Advancement.Builder.advancement()
                .display(new ItemStack(BucketItems.WOODEN_AXOLOTL_BUCKET.get()), Component.translatable(
                                Util.makeDescriptionId("advancements", getTranslationLocation("root")) + ".title"),
                        Component.translatable(Util.makeDescriptionId("advancements", getTranslationLocation("root")) +
                                ".description"),
                        new ResourceLocation(BucketModCommon.MOD_ID, "textures/gui/advancements/backgrounds/water.png"),
                        FrameType.TASK, false, false, false)
                .addCriterion(BucketItems.WOODEN_BUCKET.getId().getPath(),
                        new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY,
                                MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY,
                                new ItemPredicate[]{ItemPredicate.Builder.item()
                                        .of(BucketTags.Items.WOODEN_BUCKETS).build()}))
                .save(consumer, getAdvancementLocation("root"), fileHelper);
        final var woodenBucket = Advancement.Builder.advancement()
                .parent(earlyBucketsRoot)
                .display(new ItemStack(BucketItems.WOODEN_BUCKET.get()), Component.translatable(
                                Util.makeDescriptionId("advancements", BucketItems.WOODEN_BUCKET.getId()) + ".title"),
                        Component.translatable(
                                Util.makeDescriptionId("advancements", BucketItems.WOODEN_BUCKET.getId()) +
                                        ".description"), null, FrameType.TASK, true, true, false)
                .addCriterion(BucketItems.WOODEN_BUCKET.getId().getPath(),
                        new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY,
                                MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY,
                                new ItemPredicate[]{ItemPredicate.Builder.item()
                                        .of(BucketTags.Items.WOODEN_BUCKETS).build()}))
                .save(consumer, getAdvancementLocation(BucketItems.WOODEN_BUCKET.getId().getPath()), fileHelper);
        final var clayBucket = Advancement.Builder.advancement()
                .parent(woodenBucket)
                .display(new ItemStack(BucketItems.CLAY_BUCKET.get()), Component.translatable(
                                Util.makeDescriptionId("advancements", BucketItems.CLAY_BUCKET.getId()) + ".title"),
                        Component.translatable(Util.makeDescriptionId("advancements", BucketItems.CLAY_BUCKET.getId()) +
                                ".description"), null, FrameType.TASK, true, true, false)
                .addCriterion(BucketItems.CLAY_BUCKET.getId().getPath(),
                        new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY,
                                MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY,
                                new ItemPredicate[]{ItemPredicate.Builder.item()
                                        .of(BucketTags.Items.CLAY_BUCKETS).build()}))
                .save(consumer, getAdvancementLocation(BucketItems.CLAY_BUCKET.getId().getPath()), fileHelper);
        Advancement.Builder.advancement()
                .parent(clayBucket)
                .display(new ItemStack(BucketItems.CERAMIC_BUCKET.get()), Component.translatable(
                                Util.makeDescriptionId("advancements", BucketItems.CERAMIC_BUCKET.getId()) + ".title"),
                        Component.translatable(
                                Util.makeDescriptionId("advancements", BucketItems.CERAMIC_BUCKET.getId()) +
                                        ".description"), null, FrameType.TASK, true, true, false)
                .addCriterion(BucketItems.CERAMIC_BUCKET.getId().getPath(),
                        new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY,
                                MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY,
                                new ItemPredicate[]{ItemPredicate.Builder.item()
                                        .of(BucketTags.Items.CERAMIC_BUCKETS).build()}))
                .save(consumer, getAdvancementLocation(BucketItems.CERAMIC_BUCKET.getId().getPath()), fileHelper);
        Advancement.Builder.advancement()
                .parent(clayBucket)
                .display(new ItemStack(Items.CLAY_BALL), Component.translatable(
                        Util.makeDescriptionId("advancements", getTranslationLocation("clay_bucket_dissolved")) +
                                ".title"), Component.translatable(
                        Util.makeDescriptionId("advancements", getTranslationLocation("clay_bucket_dissolved")) +
                                ".description"), null, FrameType.TASK, true, true, true)
                .addCriterion(BucketItems.CLAY_BUCKET.getId().getPath() +
                                "_to_" +
                                Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(Items.CLAY_BALL)).getPath(),
                        CustomFilledBucketTrigger.TriggerInstance.filledCustomBucket(
                                ItemPredicate.Builder.item().of(BucketItems.CLAY_BUCKET.get()).build(),
                                ItemPredicate.Builder.item().of(Items.CLAY_BALL).build()))
                .save(consumer, getAdvancementLocation("clay_bucket_dissolved"), fileHelper);
        final var filledClayBucket = Advancement.Builder.advancement()
                .parent(clayBucket)
                .display(new DisplayInfo(new ItemStack(BucketItems.CLAY_WATER_BUCKET.get()), Component.translatable(
                        Util.makeDescriptionId("advancements", getTranslationLocation("we_will_persevere")) + ".title"),
                        Component.translatable(
                                Util.makeDescriptionId("advancements", getTranslationLocation("we_will_persevere")) +
                                        ".description"), null, FrameType.GOAL, true, true, true))
                .addCriterion(BucketItems.CLAY_BUCKET.getId().getPath() +
                                "_to_" +
                                BucketItems.CLAY_WATER_BUCKET.getId().getPath(),
                        CustomFilledBucketTrigger.TriggerInstance.filledCustomBucket(
                                ItemPredicate.Builder.item().of(BucketItems.CLAY_BUCKET.get()).build(),
                                ItemPredicate.Builder.item().of(BucketItems.CLAY_WATER_BUCKET.get()).build()))
                .save(consumer, getAdvancementLocation("we_will_persevere"), fileHelper);
        Advancement.Builder.advancement()
                .parent(filledClayBucket)
                .display(new DisplayInfo(new ItemStack(BucketItems.CLAY_AXOLOTL_BUCKET.get()), Component.translatable(
                        Util.makeDescriptionId("advancements", getTranslationLocation("feels_like_home")) + ".title"),
                        Component.translatable(
                                Util.makeDescriptionId("advancements", getTranslationLocation("feels_like_home")) +
                                        ".description"), null, FrameType.CHALLENGE, true, true, true))
                .addCriterion(BucketItems.CLAY_AXOLOTL_BUCKET.getId().getPath(),
                        new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY,
                                MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY,
                                new ItemPredicate[]{ItemPredicate.Builder.item()
                                        .of(BucketItems.CLAY_AXOLOTL_BUCKET.get()).build()}))
                .save(consumer, getAdvancementLocation("feels_like_home"), fileHelper);
        final ItemStack ceramicLavaBucket = new ItemStack(BucketItems.CERAMIC_LAVA_BUCKET.get());
        ceramicLavaBucket.setDamageValue(
                (int) Math.ceil(ceramicLavaBucket.getMaxDamage() * CeramicBucketItem.CRACKS_AT_USE_PERCENT));
        Advancement.Builder.advancement()
                .parent(clayBucket)
                .display(new DisplayInfo(ceramicLavaBucket, Component.translatable(
                        Util.makeDescriptionId("advancements", getTranslationLocation("flash_fire")) + ".title"),
                        Component.translatable(
                                Util.makeDescriptionId("advancements", getTranslationLocation("flash_fire")) +
                                        ".description"), null, FrameType.TASK, true, true, true))
                .addCriterion(BucketItems.CLAY_BUCKET.getId().getPath() +
                                "_to_" +
                                BucketItems.CERAMIC_LAVA_BUCKET.getId().getPath(),
                        CustomFilledBucketTrigger.TriggerInstance.filledCustomBucket(
                                ItemPredicate.Builder.item().of(BucketItems.CLAY_BUCKET.get()).build(),
                                ItemPredicate.Builder.item().of(BucketItems.CERAMIC_LAVA_BUCKET.get()).build()))
                .save(consumer, getAdvancementLocation("flash_fire"), fileHelper);
    }

    @Override
    public String getName() {
        return "Early Game Buckets Advancement Provider";
    }
}
