package dev.satyrn.early_buckets.forge.data.provider.server;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BucketModBlockTagsProvider extends BlockTagsProvider {

    public BucketModBlockTagsProvider(final DataGenerator arg,
                                      final String modId,
                                      final ExistingFileHelper existingFileHelper) {
        super(arg, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
    }

    @Override
    public String getName() {
        return "Early Game Buckets Block Tag Provider";
    }
}
