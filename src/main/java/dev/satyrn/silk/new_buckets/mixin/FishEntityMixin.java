package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixes into the fish entities to provide handlers for custom buckets.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(FishEntity.class)
public abstract class FishEntityMixin extends WaterCreatureEntityMixin {
    /**
     * Handles custom bucket interaction for fish.
     * @param player The player which interacted with the cow.
     * @param hand The hand with which the player interacted.
     * @param cir Returnable callback info.
     * @since 1.0.0
     */
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void injectInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        //TODO: Wooden and ceramic bucket interactions
    }
}
