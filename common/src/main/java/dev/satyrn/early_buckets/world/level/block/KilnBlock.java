package dev.satyrn.early_buckets.world.level.block;

import dev.satyrn.early_buckets.sounds.BucketSoundEvents;
import dev.satyrn.early_buckets.stats.BucketStats;
import dev.satyrn.early_buckets.world.level.block.entity.BucketBlockEntityTypes;
import dev.satyrn.early_buckets.world.level.block.entity.KilnBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class KilnBlock extends AbstractFurnaceBlock {
    public KilnBlock(final BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final BlockPos blockPos,
                                                final BlockState blockState) {
        return new KilnBlockEntity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(final Level level,
                                                                            final BlockState blockState,
                                                                            final BlockEntityType<T> blockEntityType) {
        return createFurnaceTicker(level, blockEntityType, BucketBlockEntityTypes.KILN.get());
    }

    @Override
    protected void openContainer(final Level level,
                                 final BlockPos blockPos,
                                 final Player player) {
        final @Nullable BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof KilnBlockEntity kiln) {
            player.openMenu(kiln);
            player.awardStat(BucketStats.INTERACT_WITH_KILN.get());
        }
    }

    @Override
    public void animateTick(final BlockState blockState,
                            final Level level,
                            final BlockPos blockPos,
                            final RandomSource randomSource) {
        if (blockState.getValue(LIT)) {
            final double centerX = blockPos.getX() + 0.5;
            final double originY = blockPos.getY();
            final double centerZ = blockPos.getZ() + 0.5;

            if (randomSource.nextDouble() < 0.1) {
                level.playLocalSound(centerX, originY, centerZ, BucketSoundEvents.KILN_FIRE_CRACKLE.get(),
                        SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            final double amplitude = 0.67;
            final double randomOffset = randomSource.nextDouble() * 0.6 - 0.3;
            level.addParticle(ParticleTypes.SMOKE, centerX + randomOffset * amplitude, originY + 1.1,
                    centerZ + randomOffset * amplitude, 0, 0, 0);
            level.addParticle(ParticleTypes.FLAME, centerX + randomOffset * amplitude, originY + 1.1,
                    centerZ + randomOffset * amplitude, 0, 0, 0);
        }
    }
}
