package dev.satyrn.early_buckets.forge.data.provider.server;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class BucketModBlockTagsProvider extends BlockTagsProvider {

    public BucketModBlockTagsProvider(DataGenerator arg,
                                      String modId,
                                      @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {

    }

}
