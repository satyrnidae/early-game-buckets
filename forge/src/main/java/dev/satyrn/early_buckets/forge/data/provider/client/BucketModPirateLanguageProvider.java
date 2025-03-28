package dev.satyrn.early_buckets.forge.data.provider.client;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.world.item.BucketItems;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

public class BucketModPirateLanguageProvider extends LanguageProvider {
    private final String locale;
    public BucketModPirateLanguageProvider(final @NotNull DataGenerator gen,
                                           final @NotNull String modid,
                                           final @NotNull String locale) {
        super(gen, modid, locale);
        this.locale = locale;
    }

    @Override
    public @NotNull String getName() {
        return "Early Game Buckets: Language Provider: en_pt";
    }

    @Override
    protected void addTranslations() {
        if (!this.locale.equals("en_pt")) {
            return;
        }

        this.add(BucketBlocks.KILN.get(), "Kiln");
        // Container description
        this.add(Util.makeDescriptionId("container", BucketBlocks.KILN.getId()), "Oven what fer Cookin' Clay");
        // Kiln sound description
        this.add(Util.makeDescriptionId("subtitles.block", BucketBlocks.KILN.getId()) + ".fire_crackle", "Oven what fer cookin' clay crackles");

        //this.add(KilnRecipeBookComponent.KILN_RECIPE_BOOK_FILTER_NAME, "Showing Fired");

        this.addCeramicBucket(BucketItems.CERAMIC_BUCKET, "Clay Pail");
        this.addCeramicBucket(BucketItems.CERAMIC_AXOLOTL_BUCKET, "Clay Pail o' Sea Lizard");
        this.addCeramicBucket(BucketItems.CERAMIC_COD_BUCKET, "Clay Pail o' Codfish");
        this.addCeramicBucket(BucketItems.CERAMIC_LAVA_BUCKET, "Clay Pail o' Molten Rock");
        this.addCeramicBucket(BucketItems.CERAMIC_MILK_BUCKET, "Clay Pail o' Bovine's Juice");
        this.addCeramicBucket(BucketItems.CERAMIC_POWDER_SNOW_BUCKET, "Clay Pail o' Snowier Snow");
        this.addCeramicBucket(BucketItems.CERAMIC_PUFFERFISH_BUCKET, "Clay Pail o' Blowfish");
        this.addCeramicBucket(BucketItems.CERAMIC_SALMON_BUCKET, "Clay Pail o' Salmon");
        this.addCeramicBucket(BucketItems.CERAMIC_TADPOLE_BUCKET, "Clay Pail o' Polliwog");
        this.addCeramicBucket(BucketItems.CERAMIC_TROPICAL_FISH_BUCKET, "Clay Pail o' Colorful Fish");
        this.addCeramicBucket(BucketItems.CERAMIC_WATER_BUCKET, "Clay Pail o' Bilge");

        this.add(BucketItems.CLAY_BUCKET.get(), "Mushy Pail");
        this.add(BucketItems.CLAY_AXOLOTL_BUCKET.get(), "Mushy Pail o' Sea Lizard");
        this.add(BucketItems.CLAY_WATER_BUCKET.get(), "Mushy Pail o' Bilge");
        this.add(BucketItems.CLAY_POWDER_SNOW_BUCKET.get(), "Mushy Pail o' Snowier Snow");

        this.add(BucketItems.WOODEN_BUCKET.get(), "Timber-craft Pail");
        this.add(BucketItems.WOODEN_AXOLOTL_BUCKET.get(), "Timber-craft Pail o' Sea Lizard");
        this.add(BucketItems.WOODEN_COD_BUCKET.get(), "Timber-craft Pail o' Codfish");
        this.add(BucketItems.WOODEN_MILK_BUCKET.get(), "Timber-craft Pail o' Bovine's Juice");
        this.add(BucketItems.WOODEN_POWDER_SNOW_BUCKET.get(), "Timber-craft Pail o' Snowier Snow");
        this.add(BucketItems.WOODEN_PUFFERFISH_BUCKET.get(), "Timber-craft Pail o' Blowfish");
        this.add(BucketItems.WOODEN_SALMON_BUCKET.get(), "Timber-craft Pail o' Salmon");
        this.add(BucketItems.WOODEN_TADPOLE_BUCKET.get(), "Timber-craft Pail o' Polliwog");
        this.add(BucketItems.WOODEN_TROPICAL_FISH_BUCKET.get(), "Timber-craft Pail o' Colorful Fish");
        this.add(BucketItems.WOODEN_WATER_BUCKET.get(), "Timber-craft Pail o' Bilge");
    }

    private void addCeramicBucket(RegistrySupplier<Item> bucket, String translationBase) {
        this.add(bucket.get(), translationBase);
        this.add(Util.makeDescriptionId("item", bucket.getId()) + ".cracked", "Busted " + translationBase);
    }
}
