package dev.satyrn.early_buckets.mixin.world.entity.animal;

import dev.satyrn.early_buckets.world.item.CustomBucket;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Tadpole;
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

@Unique
@Mixin({Axolotl.class, AbstractFish.class, Tadpole.class})
public abstract class BucketableMixin implements Bucketable {

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void early_buckets$mobInteract(final Player player,
                                          final InteractionHand hand,
                                          final CallbackInfoReturnable<InteractionResult> cir) {
        final LivingEntity livingEntityThis = (LivingEntity) (Object) this;
        final Bucketable bucketableThis = (Bucketable) livingEntityThis;

        final ItemStack stackInHand = player.getItemInHand(hand);
        final Item itemInHand = stackInHand.getItem();

        if (livingEntityThis.isAlive() && itemInHand instanceof final CustomBucket bucket) {
            final ItemStack filledItemStack = bucket.getFilledItemStack(stackInHand, livingEntityThis);

            // ItemStack will be empty if the fill fails.
            if (!filledItemStack.isEmpty()) {
                livingEntityThis.playSound(bucketableThis.getPickupSound(), 1.0F, 1.0F);
                bucketableThis.saveToBucketTag(filledItemStack);

                final ItemStack exchangedStack = ItemUtils.createFilledResult(stackInHand, player, filledItemStack,
                        false);

                player.setItemInHand(hand, exchangedStack);

                final Level world = livingEntityThis.getLevel();
                if (!world.isClientSide) {
                    CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, filledItemStack);
                }

                livingEntityThis.discard();

                cir.setReturnValue(InteractionResult.sidedSuccess(world.isClientSide));
                cir.cancel();
            }
        }
    }
}
