package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Shadows and accessors for living entities.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {
    /**
     * Gets a boolean flag indicating whether or not this entity is a baby.
     * @return {@code true} if the entity is a baby; otherwise, {@code false}
     * @since 1.0.0
     */
    @Shadow public abstract boolean isBaby();

    /**
     * Gets a boolean flag indicating whether or not the entity is alive.
     * @return {@code true} if the entity is alive; otherwise, {@code false}
     * @since 1.0.0
     */
    @Shadow public abstract boolean isAlive();
}
