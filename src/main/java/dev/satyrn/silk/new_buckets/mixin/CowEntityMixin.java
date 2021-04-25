package dev.satyrn.silk.new_buckets.mixin;

import dev.satyrn.silk.new_buckets.NewBucketsMod;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixes into the cow entities to provide handlers for custom buckets.
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Mixin(CowEntity.class)
public abstract class CowEntityMixin extends AnimalEntityMixin {
    /**
     * Handles custom bucket interaction for cows.
     * @param player The player which interacted with the cow.
     * @param hand The hand with which the player interacted.
     * @param cir Returnable callback info.
     * @since 1.0.0
     */
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void injectInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!this.isBaby() && (itemStack.getItem() == NewBucketsMod.WOODEN_BUCKET || itemStack.getItem() == NewBucketsMod.CERAMIC_BUCKET)) {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            ItemStack milkBucketStack = ItemUsage.method_30012(itemStack, player, itemStack.getItem() == NewBucketsMod.WOODEN_BUCKET
                    ? NewBucketsMod.WOODEN_MILK_BUCKET.getDefaultStack()
                    : NewBucketsMod.CERAMIC_MILK_BUCKET.getDefaultStack());
            milkBucketStack.setDamage(itemStack.getDamage());
            NewBucketsMod.copyEnchantmentItemTags(itemStack, milkBucketStack);
            player.setStackInHand(hand, milkBucketStack);

            cir.setReturnValue(ActionResult.success(this.getWorld().isClient));
            cir.cancel();
        }
    }
}
