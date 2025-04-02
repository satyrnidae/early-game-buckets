package dev.satyrn.early_buckets.forge.data.provider.server;

import dev.satyrn.early_buckets.tags.BucketTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BucketModEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public BucketModEntityTypeTagsProvider(final DataGenerator arg,
                                           final String modId,
                                           final ExistingFileHelper existingFileHelper) {
        super(arg, modId, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Early Game Buckets Entity Type Tag Provider";
    }

    @Override
    protected void addTags() {
        this.tag(BucketTags.EntityTypes.FISH)
                .add(EntityType.COD)
                .add(EntityType.SALMON)
                .add(EntityType.PUFFERFISH)
                .add(EntityType.TROPICAL_FISH);
    }
}
