package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.mob.PathAwareEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Shadows and accessors for path-aware entities.
 */
@Mixin(PathAwareEntity.class)
public abstract class PathAwareEntityMixin extends MobEntityMixin {
}
