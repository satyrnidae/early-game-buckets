package dev.satyrn.early_buckets.mixin.accessor;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.ToIntFunction;

@Mixin(Blocks.class)
public interface BlocksAccessor {
    @Invoker
    static ToIntFunction<BlockState> invokeLitBlockEmission(int i) {
        throw new AssertionError();
    }
}
