package dev.satyrn.early_buckets.forge.data.provider.server;

import dev.satyrn.early_buckets.tags.BucketTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BucketModEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public BucketModEntityTypeTagsProvider(DataGenerator arg,
                                           String modId,
                                           @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, modId, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return "Early Game Buckets Entity Type Tags Provider";
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
