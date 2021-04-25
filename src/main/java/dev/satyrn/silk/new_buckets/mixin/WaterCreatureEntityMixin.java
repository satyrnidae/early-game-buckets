package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.mob.WaterCreatureEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Shadows and accessors for water creature entities.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(WaterCreatureEntity.class)
public abstract class WaterCreatureEntityMixin extends PathAwareEntityMixin {
}
