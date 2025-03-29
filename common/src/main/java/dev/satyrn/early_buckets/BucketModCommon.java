package dev.satyrn.early_buckets;

import dev.satyrn.early_buckets.advancements.criterion.CustomFilledBucketTrigger;
import dev.satyrn.early_buckets.core.cauldron.CustomBucketCauldronInteractions;
import dev.satyrn.early_buckets.mixin.accessor.CriteriaTriggersAccessor;
import dev.satyrn.early_buckets.sounds.BucketSoundEvents;
import dev.satyrn.early_buckets.stats.BucketStats;
import dev.satyrn.early_buckets.world.inventory.BucketMenuTypes;
import dev.satyrn.early_buckets.world.item.BucketItems;
import dev.satyrn.early_buckets.world.item.crafting.BucketRecipeSerializers;
import dev.satyrn.early_buckets.world.item.crafting.BucketRecipeTypes;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import dev.satyrn.early_buckets.world.level.block.entity.BucketBlockEntityTypes;
import net.minecraft.resources.ResourceLocation;

public final class BucketModCommon {
    public static final String MOD_ID = "early_buckets";

    public static final ResourceLocation CRAFTING_SHAPED_BREAKABLE_RECIPE_ID;
    public static final ResourceLocation FIRING_RECIPE_ID;
    public static final CustomFilledBucketTrigger FILL_BUCKET;

    static {
        CRAFTING_SHAPED_BREAKABLE_RECIPE_ID = new ResourceLocation(MOD_ID, "crafting_shaped_breakable");
        FIRING_RECIPE_ID = new ResourceLocation(MOD_ID, "firing");
        FILL_BUCKET = CriteriaTriggersAccessor.callRegister(new CustomFilledBucketTrigger());
    }

    public static void init() {
        BucketStats.register();
        BucketBlocks.register();
        BucketItems.register();
        BucketRecipeTypes.register();
        BucketRecipeSerializers.register();
        BucketBlockEntityTypes.register();
        BucketSoundEvents.register();
        BucketMenuTypes.register();
    }

    public static void postInit() {
        BucketStats.postRegister();
        BucketItems.postRegister();
        CustomBucketCauldronInteractions.postRegister();
    }
}
