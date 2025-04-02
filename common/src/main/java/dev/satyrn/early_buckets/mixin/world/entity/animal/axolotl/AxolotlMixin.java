package dev.satyrn.early_buckets.mixin.world.entity.animal.axolotl;

import dev.satyrn.early_buckets.world.item.BucketItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LerpingModel;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for the {@link net.minecraft.world.entity.animal.axolotl.Axolotl} class.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
@Unique
@Mixin(Axolotl.class)
public abstract class AxolotlMixin extends Animal implements LerpingModel, Bucketable {
    AxolotlMixin(final EntityType<? extends Animal> entityType, final Level level) {
        super(entityType, level);
        throw new AssertionError();
    }

    /**
     * Injects functionality for custom tropical fish buckets into {@link net.minecraft.world.entity.animal.axolotl.Axolotl}{@code .eat(}{@link net.minecraft.world.entity.player.Player}{@code , }{@link net.minecraft.world.InteractionHand}{@code , }{@link net.minecraft.world.item.ItemStack}{@code )}.
     *
     * @param player The player who is feeding the axolotl.
     * @param hand   The hand in which the player is holding the food.
     * @param stack  The food item stack.
     * @param ci     Callback information.
     */
    @Inject(method = "usePlayerItem", at = @At("HEAD"), cancellable = true)
    public void early_buckets$usePlayerItem(final Player player,
                                            final InteractionHand hand,
                                            final ItemStack stack,
                                            final CallbackInfo ci) {
        if (stack.is(BucketItems.CERAMIC_TROPICAL_FISH_BUCKET.get())) {
            final ItemStack returnedStack = BucketItems.createItemStack(BucketItems.CERAMIC_WATER_BUCKET.get(), stack);
            returnedStack.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(hand));
            player.setItemInHand(hand, returnedStack);
            ci.cancel();
        } else if (stack.is(BucketItems.WOODEN_TROPICAL_FISH_BUCKET.get())) {
            final ItemStack returnedStack = BucketItems.createItemStack(BucketItems.CERAMIC_WATER_BUCKET.get(), stack);
            returnedStack.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(hand));
            player.setItemInHand(hand, returnedStack);
            ci.cancel();
        }
    }
}
