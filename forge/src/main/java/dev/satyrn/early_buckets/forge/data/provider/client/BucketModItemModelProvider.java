package dev.satyrn.early_buckets.forge.data.provider.client;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.world.item.BucketItems;
import dev.satyrn.early_buckets.world.item.CeramicBucketItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class BucketModItemModelProvider extends ItemModelProvider {
    public BucketModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return "Early Game Buckets: Item Models";
    }

    @Override
    protected void registerModels() {
        this.ceramicBucketItem(BucketItems.CERAMIC_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_AXOLOTL_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_COD_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_MILK_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_PUFFERFISH_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_SALMON_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_TADPOLE_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_TROPICAL_FISH_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_WATER_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_POWDER_SNOW_BUCKET);
        this.ceramicBucketItem(BucketItems.CERAMIC_LAVA_BUCKET);
        this.basicItem(BucketItems.CLAY_BUCKET.get());
        this.basicItem(BucketItems.CLAY_WATER_BUCKET.get());
        this.basicItem(BucketItems.CLAY_AXOLOTL_BUCKET.get());
        this.basicItem(BucketItems.CLAY_POWDER_SNOW_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_AXOLOTL_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_COD_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_MILK_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_PUFFERFISH_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_SALMON_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_TADPOLE_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_TROPICAL_FISH_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_WATER_BUCKET.get());
        this.basicItem(BucketItems.WOODEN_POWDER_SNOW_BUCKET.get());

    }

    private void ceramicBucketItem(RegistrySupplier<Item> ceramicBucket) {
        this.basicItem(ceramicBucket.get())
                .override()
                .predicate(new ResourceLocation("damage"), CeramicBucketItem.CRACKS_AT_USE_PERCENT)
                .model(this.getBuilder(new ResourceLocation(BucketModCommon.MOD_ID,
                                "cracked_" + ceramicBucket.getId().getPath()).toString())
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", new ResourceLocation(BucketModCommon.MOD_ID,
                                "item/cracked_" + ceramicBucket.getId().getPath())));
    }
}
