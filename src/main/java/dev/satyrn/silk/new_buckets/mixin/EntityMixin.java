package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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

    /**
     * Plays a sound from the entity.
     * @param sound The sound to play.
     * @param volume The volume at which to play the sound.
     * @param pitch The pitch of the sound.
     * @since 1.0.0
     */
    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    /**
     * Removes an entity from the world.
     * @since 1.0.0
     */
    @Shadow
    public abstract void remove();
}
