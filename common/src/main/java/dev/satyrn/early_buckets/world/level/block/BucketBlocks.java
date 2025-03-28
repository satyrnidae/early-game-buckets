package dev.satyrn.early_buckets.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.mixin.accessor.BlocksAccessor;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public final class BucketBlocks {
    static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BucketModCommon.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final RegistrySupplier<Block> KILN = BLOCKS.register("kiln", () -> new KilnBlock(BlockBehaviour.Properties.of(
            Material.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(BlocksAccessor.invokeLitBlockEmission(13))));

    private BucketBlocks() {}

    public static void register() {
        BLOCKS.register();
    }
}
