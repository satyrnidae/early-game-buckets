package dev.satyrn.early_buckets.world.level.block.entity;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class BucketBlockEntityTypes {

    public static final RegistrySupplier<BlockEntityType<KilnBlockEntity>> KILN;

    static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BucketModCommon.MOD_ID,
            Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    static {
        final ResourceLocation kilnId = new ResourceLocation(BucketModCommon.MOD_ID, "kiln");
        KILN = BLOCK_ENTITIES.register(kilnId,
                () -> BlockEntityType.Builder.of(KilnBlockEntity::new, BucketBlocks.KILN.get())
                        .build(Util.fetchChoiceType(References.BLOCK_ENTITY, kilnId.toString())));
    }

    private BucketBlockEntityTypes() {
    }

    public static void register() {
        BLOCK_ENTITIES.register();
    }
}
