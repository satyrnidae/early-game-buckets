package dev.satyrn.early_buckets.mixin.accessors;

import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accessor for {@link FluidBlock}.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
@Mixin(FluidBlock.class)
public interface FluidBlockAccessor {
    /**
     * Gets the {@link FlowableFluid} within the block.
     *
     * @return The fluid for the block.
     */
    @Accessor("fluid")
    FlowableFluid getFluid();
}
