package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Shadows and accessors for living entities.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {
}
