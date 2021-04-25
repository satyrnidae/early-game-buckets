package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.passive.PassiveEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Shadows and accessors for passive entities.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(PassiveEntity.class)
public abstract class PassiveEntityMixin extends PathAwareEntityMixin {
    /**
     * Shadowed method for determining whether or not this entity is a baby.
     * @return {@code true} if the entity is a baby, otherwise {@code false}
     * @since 1.0.0
     */
    @Shadow
    public abstract boolean isBaby();
}
