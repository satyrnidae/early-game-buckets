package dev.satyrn.early_buckets.mixin.accessor;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accessor for {@link LiquidBlock}.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
@Mixin(LiquidBlock.class)
public interface LiquidBlockAccessor {
    /**
     * Gets the {@link FlowingFluid} within the block.
     *
     * @return The fluid for the block.
     */
    @Accessor()
    FlowingFluid getFluid();
}
