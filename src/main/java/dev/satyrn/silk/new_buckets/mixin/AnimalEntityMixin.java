package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntityMixin {

}
