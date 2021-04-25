package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Shadows and accessors for mob entities.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntityMixin {
}
