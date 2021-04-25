package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Provides hierarchy for animal entity mixins.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntityMixin { }
