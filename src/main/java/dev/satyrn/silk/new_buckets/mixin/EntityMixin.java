package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Shadows and accessors for entities.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(Entity.class)
public abstract class EntityMixin {
    /**
     * Gets the world that the entity is in.
     * @return The world that the entity inhabits.
     * @since 1.0.0
     */
    @Accessor("world")
    public abstract World getWorld();
}
