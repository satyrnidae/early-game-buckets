package dev.satyrn.early_buckets.mixin.entity.passive;

import dev.satyrn.early_buckets.item.BucketItems;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for the {@link AxolotlEntity} class.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
@Mixin(AxolotlEntity.class)
public class AxolotlEntityMixin {
    /**
     * Injects functionality for custom tropical fish buckets into {@link AxolotlEntity}{@code .eat(}{@link PlayerEntity}{@code , }{@link Hand}{@code , }{@link ItemStack}{@code )}.
     * @param player The player who is feeding the axolotl.
     * @param hand The hand in which the player is holding the food.
     * @param stack The food item stack.
     * @param ci Callback information.
     */
    @Inject(method = "eat", at = @At("HEAD"), cancellable = true)
    public void injectEat(final PlayerEntity player, final Hand hand, final ItemStack stack, CallbackInfo ci) {
        if (stack.isOf(BucketItems.CERAMIC_TROPICAL_FISH_BUCKET)) {
            final ItemStack returnedStack = BucketItems.createItemStack(BucketItems.CERAMIC_WATER_BUCKET, stack);
            returnedStack.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(hand));
            player.setStackInHand(hand, returnedStack);
            ci.cancel();
        } else if (stack.isOf(BucketItems.WOODEN_TROPICAL_FISH_BUCKET)) {
            final ItemStack returnedStack = BucketItems.createItemStack(BucketItems.CERAMIC_WATER_BUCKET, stack);
            returnedStack.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(hand));
            player.setStackInHand(hand, returnedStack);
            ci.cancel();
        }
    }
}
