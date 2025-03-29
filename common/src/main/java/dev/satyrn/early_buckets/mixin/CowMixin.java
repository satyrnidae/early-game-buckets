package dev.satyrn.early_buckets.mixin;

import dev.satyrn.early_buckets.world.item.CustomBucket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixes into the cow entities to provide handlers for custom buckets.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Unique
@Mixin(Cow.class)
public abstract class CowMixin extends Animal {

    CowMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        throw new AssertionError();
    }

    /**
     * Handles custom bucket interaction for cows.
     *
     * @param player The player which interacted with the cow.
     * @param hand   The hand with which the player interacted.
     * @param cir    Returnable callback info.
     * @author Isabel Maskrey
     * @since 1.0.0
     */
    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void early_buckets$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        final ItemStack stackInHand = player.getItemInHand(hand);
        final Item itemInHand = stackInHand.getItem();
        if (itemInHand instanceof final CustomBucket bucket && !this.isBaby()) {
            final ItemStack milkBucketStack = bucket.getMilkBucketItemStack(stackInHand);
            if (!milkBucketStack.isEmpty()) {
                player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
                final ItemStack exchangedStack = ItemUtils.createFilledResult(stackInHand, player, milkBucketStack);
                player.setItemInHand(hand, exchangedStack);

                cir.setReturnValue(InteractionResult.sidedSuccess(this.level.isClientSide));
                cir.cancel();
            }
        }
    }
}
