package dev.satyrn.early_buckets.forge.data.provider.client;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.forge.data.provider.server.BucketModAdvancementProvider;
import dev.satyrn.early_buckets.world.item.BucketItems;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

public class BucketModLanguageProvider extends LanguageProvider {
    private final String locale;
    public BucketModLanguageProvider(final @NotNull DataGenerator gen,
                                     final @NotNull String modid,
                                     final @NotNull String locale) {
        super(gen, modid, locale);
        this.locale = locale;
    }

    @Override
    public @NotNull String getName() {
        return "Early Game Buckets: Language Provider: en_us";
    }

    @Override
    protected void addTranslations() {
        if (!this.locale.equals("en_us")) {
            return;
        }

        this.add(BucketBlocks.KILN.get(), "Kiln");
        // Container description
        this.add(Util.makeDescriptionId("container", BucketBlocks.KILN.getId()), "Kiln");
        // Kiln sound description
        this.add(Util.makeDescriptionId("subtitles.block", BucketBlocks.KILN.getId()) + ".fire_crackle", "Kiln crackles");

        //this.add(KilnRecipeBookComponent.KILN_RECIPE_BOOK_FILTER_NAME, "Showing Fired");

        this.addCeramicBucket(BucketItems.CERAMIC_BUCKET, "Ceramic Bucket");
        this.addCeramicBucket(BucketItems.CERAMIC_AXOLOTL_BUCKET, "Ceramic Bucket of Axolotl");
        this.addCeramicBucket(BucketItems.CERAMIC_COD_BUCKET, "Ceramic Bucket of Cod");
        this.addCeramicBucket(BucketItems.CERAMIC_LAVA_BUCKET, "Ceramic Lava Bucket");
        this.addCeramicBucket(BucketItems.CERAMIC_MILK_BUCKET, "Ceramic Milk Bucket");
        this.addCeramicBucket(BucketItems.CERAMIC_POWDER_SNOW_BUCKET, "Ceramic Powder Snow Bucket");
        this.addCeramicBucket(BucketItems.CERAMIC_PUFFERFISH_BUCKET, "Ceramic Bucket of Pufferfish");
        this.addCeramicBucket(BucketItems.CERAMIC_SALMON_BUCKET, "Ceramic Bucket of Salmon");
        this.addCeramicBucket(BucketItems.CERAMIC_TADPOLE_BUCKET, "Ceramic Bucket of Tadpole");
        this.addCeramicBucket(BucketItems.CERAMIC_TROPICAL_FISH_BUCKET, "Ceramic Bucket of Tropical Fish");
        this.addCeramicBucket(BucketItems.CERAMIC_WATER_BUCKET, "Ceramic Water Bucket");

        this.add(BucketItems.CLAY_BUCKET.get(), "Clay Bucket");
        this.add(BucketItems.CLAY_AXOLOTL_BUCKET.get(), "Clay Bucket of Axolotl");
        this.add(BucketItems.CLAY_WATER_BUCKET.get(), "Clay Water Bucket");
        this.add(BucketItems.CLAY_POWDER_SNOW_BUCKET.get(), "Clay Powder Snow Bucket");

        this.add(BucketItems.WOODEN_BUCKET.get(), "Wooden Bucket");
        this.add(BucketItems.WOODEN_AXOLOTL_BUCKET.get(), "Wooden Bucket of Axolotl");
        this.add(BucketItems.WOODEN_COD_BUCKET.get(), "Wooden Bucket of Cod");
        this.add(BucketItems.WOODEN_MILK_BUCKET.get(), "Wooden Milk Bucket");
        this.add(BucketItems.WOODEN_POWDER_SNOW_BUCKET.get(), "Wooden Powder Snow Bucket");
        this.add(BucketItems.WOODEN_PUFFERFISH_BUCKET.get(), "Wooden Bucket of Pufferfish");
        this.add(BucketItems.WOODEN_SALMON_BUCKET.get(), "Wooden Bucket of Salmon");
        this.add(BucketItems.WOODEN_TADPOLE_BUCKET.get(), "Wooden Bucket of Tadpole");
        this.add(BucketItems.WOODEN_TROPICAL_FISH_BUCKET.get(), "Wooden Bucket of Tropical Fish");
        this.add(BucketItems.WOODEN_WATER_BUCKET.get(), "Wooden Water Bucket");

        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("root")) + ".title", "Early-Game Buckets");
        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("root")) + ".description", "Simple buckets for the early game.");

        this.add(Util.makeDescriptionId("advancements", BucketItems.WOODEN_BUCKET.getId()) + ".title", "Crude, but Effective");
        this.add(Util.makeDescriptionId("advancements", BucketItems.WOODEN_BUCKET.getId()) + ".description", "Craft a wooden bucket.");

        this.add(Util.makeDescriptionId("advancements", BucketItems.CLAY_BUCKET.getId()) + ".title", "Pottery Class");
        this.add(Util.makeDescriptionId("advancements", BucketItems.CLAY_BUCKET.getId()) + ".description", "Craft an unfired bucket out of clay.");

        this.add(Util.makeDescriptionId("advancements", BucketItems.CERAMIC_BUCKET.getId()) + ".title", "You're Kiln Me");
        this.add(Util.makeDescriptionId("advancements", BucketItems.CERAMIC_BUCKET.getId()) + ".description", "Fire a clay bucket in a furnace or kiln.");

        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("clay_bucket_dissolved")) + ".title", "Turned to Mush");
        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("clay_bucket_dissolved")) + ".description", "That less well than you'd hoped.");

        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("we_will_persevere")) + ".title", "We Shall Persevere");
        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("we_will_persevere")) + ".description", "Finally, a water-filled clay bucket!");

        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("feels_like_home")) + ".title", "Feels Like Home");
        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("feels_like_home")) + ".description", "Carry an axolotl in a clay bucket.");

        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("flash_fire")) + ".title", "No Kiln, No Problem");
        this.add(Util.makeDescriptionId("advancements", BucketModAdvancementProvider.getDefaultTransLoc("flash_fire")) + ".description", "Flash-fire a clay bucket in some lava.");
    }

    private void addCeramicBucket(RegistrySupplier<Item> bucket, String translationBase) {
        this.add(bucket.get(), translationBase);
        this.add(Util.makeDescriptionId("item", bucket.getId()) + ".cracked", "Cracked " + translationBase);
    }
}
