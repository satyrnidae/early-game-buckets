package dev.satyrn.early_buckets.mixin.entity.passive;

import dev.satyrn.early_buckets.item.Bucket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixes into the cow entities to provide handlers for custom buckets.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(CowEntity.class)
public class CowEntityMixin {
    /**
     * Handles custom bucket interaction for cows.
     *
     * @param player The player which interacted with the cow.
     * @param hand   The hand with which the player interacted.
     * @param cir    Returnable callback info.
     * @author Isabel Maskrey
     * @since 1.0.0
     */
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void injectInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        final LivingEntity livingEntityThis = (LivingEntity) (Object) this;

        final ItemStack stackInHand = player.getStackInHand(hand);
        final Item itemInHand = stackInHand.getItem();
        if (itemInHand instanceof final Bucket bucket && !livingEntityThis.isBaby()) {
            final ItemStack milkBucketStack = bucket.getMilkBucketItemStack(stackInHand);
            if (!milkBucketStack.isEmpty()) {
                player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
                final ItemStack exchangedStack = ItemUsage.exchangeStack(stackInHand, player, milkBucketStack);
                player.setStackInHand(hand, exchangedStack);

                cir.setReturnValue(ActionResult.success(livingEntityThis.world.isClient));
                cir.cancel();
            }
        }
    }
}
