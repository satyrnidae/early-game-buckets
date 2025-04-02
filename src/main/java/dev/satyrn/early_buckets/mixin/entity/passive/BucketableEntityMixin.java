package dev.satyrn.early_buckets.mixin.entity.passive;

import dev.satyrn.early_buckets.item.Bucket;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({AxolotlEntity.class, FishEntity.class})
public class BucketableEntityMixin {

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    public void injectInteractMob(final PlayerEntity player, final Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        final LivingEntity livingEntityThis = (LivingEntity)(Object)this;
        final Bucketable bucketableThis = (Bucketable)livingEntityThis;

        final ItemStack stackInHand = player.getStackInHand(hand);
        final Item itemInHand = stackInHand.getItem();

        if (livingEntityThis.isAlive() && itemInHand instanceof final Bucket bucket) {
            final ItemStack filledItemStack = bucket.getFilledItemStack(stackInHand, livingEntityThis);

            // ItemStack will be empty if the fill fails.
            if (!filledItemStack.isEmpty()) {
                livingEntityThis.playSound(bucketableThis.getBucketedSound(), 1.0F, 1.0F);
                bucketableThis.copyDataToStack(filledItemStack);

                final ItemStack exchangedStack = ItemUsage.exchangeStack(stackInHand, player, filledItemStack, false);

                player.setStackInHand(hand, exchangedStack);

                final World world = livingEntityThis.world;
                if (!world.isClient) {
                    Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity) player, filledItemStack);
                }

                livingEntityThis.discard();

                cir.setReturnValue(ActionResult.success(world.isClient));
                cir.cancel();
            }
        }
    }
}
