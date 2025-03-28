package dev.satyrn.early_buckets.forge.data.provider.client;

import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.world.item.BucketItems;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class BucketModBlockStateProvider extends BlockStateProvider {
    public BucketModBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        final @NotNull var kilnModel = this.models()
                .orientableWithBottom(BucketBlocks.KILN.getId().toString(), new ResourceLocation(BucketModCommon.MOD_ID,
                                "block/" + BucketBlocks.KILN.getId().getPath() + "_side"),
                        new ResourceLocation(BucketModCommon.MOD_ID,
                                "block/" + BucketBlocks.KILN.getId().getPath() + "_front"),
                        new ResourceLocation(BucketModCommon.MOD_ID,
                                "block/" + BucketBlocks.KILN.getId().getPath() + "_bottom"),
                        new ResourceLocation(BucketModCommon.MOD_ID,
                                "block/" + BucketBlocks.KILN.getId().getPath() + "_top"));
        final @NotNull var kilnOnModel = this.models()
                .orientableWithBottom(new ResourceLocation(BucketModCommon.MOD_ID,
                                BucketBlocks.KILN.getId().getPath() + "_on").toString(),
                        new ResourceLocation(BucketModCommon.MOD_ID,
                                "block/" + BucketBlocks.KILN.getId().getPath() + "_side"),
                        new ResourceLocation(BucketModCommon.MOD_ID,
                                "block/" + BucketBlocks.KILN.getId().getPath() + "_front_on"),
                        new ResourceLocation(BucketModCommon.MOD_ID,
                                "block/" + BucketBlocks.KILN.getId().getPath() + "_bottom"),
                        new ResourceLocation(BucketModCommon.MOD_ID,
                                "block/" + BucketBlocks.KILN.getId().getPath() + "_top"));


        this.getVariantBuilder(BucketBlocks.KILN.get())
                .partialState()
                .with(AbstractFurnaceBlock.FACING, Direction.EAST)
                .with(AbstractFurnaceBlock.LIT, false)
                    .modelForState()
                    .modelFile(kilnModel)
                    .rotationY(90)
                    .addModel()
                .partialState()
                .with(AbstractFurnaceBlock.FACING, Direction.EAST)
                .with(AbstractFurnaceBlock.LIT, true)
                    .modelForState()
                    .modelFile(kilnOnModel)
                    .rotationY(90)
                    .addModel()
                .partialState()
                .with(AbstractFurnaceBlock.FACING, Direction.NORTH)
                .with(AbstractFurnaceBlock.LIT, false)
                    .modelForState()
                    .modelFile(kilnModel)
                    .addModel()
                .partialState()
                .with(AbstractFurnaceBlock.FACING, Direction.NORTH)
                .with(AbstractFurnaceBlock.LIT, true)
                    .modelForState()
                    .modelFile(kilnOnModel)
                    .addModel()
                .partialState()
                .with(AbstractFurnaceBlock.FACING, Direction.SOUTH)
                .with(AbstractFurnaceBlock.LIT, false)
                    .modelForState()
                    .modelFile(kilnModel)
                    .rotationY(180)
                    .addModel()
                .partialState()
                .with(AbstractFurnaceBlock.FACING, Direction.SOUTH)
                .with(AbstractFurnaceBlock.LIT, true)
                    .modelForState()
                    .modelFile(kilnOnModel)
                    .rotationY(180)
                    .addModel()
                .partialState()
                .with(AbstractFurnaceBlock.FACING, Direction.WEST)
                .with(AbstractFurnaceBlock.LIT, false)
                    .modelForState()
                    .modelFile(kilnModel)
                    .rotationY(270)
                    .addModel()
                .partialState()
                .with(AbstractFurnaceBlock.FACING, Direction.WEST)
                .with(AbstractFurnaceBlock.LIT, true)
                    .modelForState()
                    .modelFile(kilnOnModel)
                    .rotationY(270)
                    .addModel();

        this.itemModels().getBuilder(BucketItems.KILN.getId().toString())
                .parent(kilnModel);
    }

    @Override
    public @NotNull String getName() {
        return "Early Game Buckets: Block States and Block Models";
    }
}
