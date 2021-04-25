package dev.satyrn.silk.new_buckets.mixin;

import com.ibm.icu.text.AlphabeticIndex;
import dev.satyrn.silk.new_buckets.NewBucketsMod;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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
     * Gets the fish bucket item for this entity.
     * @return An item stack of the fish bucket item.
     * @since 1.0.0
     */
    @Shadow
    protected abstract ItemStack getFishBucketItem();

    /**
     * Copies the entity data to an item stack.
     * @param stack The stack to copy data to.
     */
    @Shadow
    protected abstract void copyDataToStack(ItemStack stack);

    /**
     * Handles custom bucket interaction for fish.
     * @param player The player which interacted with the cow.
     * @param hand The hand with which the player interacted.
     * @param cir Returnable callback info.
     * @since 1.0.0
     */
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void injectInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.isAlive() && (itemStack.getItem() == NewBucketsMod.WOODEN_WATER_BUCKET || itemStack.getItem() == NewBucketsMod.CERAMIC_WATER_BUCKET)) {
            this.playSound(SoundEvents.ITEM_BUCKET_FILL_FISH, 1.0F, 1.0F);

            ItemStack fishBucketStack = this.getFishBucketItem();
            Item bucketItem = fishBucketStack.getItem();
            if (bucketItem == Items.COD_BUCKET) {
                bucketItem = itemStack.getItem() == NewBucketsMod.WOODEN_WATER_BUCKET
                        ? NewBucketsMod.WOODEN_COD_BUCKET
                        : NewBucketsMod.CERAMIC_COD_BUCKET;
            } else if (bucketItem == Items.PUFFERFISH_BUCKET) {
                bucketItem = itemStack.getItem() == NewBucketsMod.WOODEN_WATER_BUCKET
                        ? NewBucketsMod.WOODEN_PUFFERFISH_BUCKET
                        : NewBucketsMod.CERAMIC_PUFFERFISH_BUCKET;
            } else if (bucketItem == Items.SALMON_BUCKET) {
                bucketItem = itemStack.getItem() == NewBucketsMod.WOODEN_WATER_BUCKET
                        ? NewBucketsMod.WOODEN_SALMON_BUCKET
                        : NewBucketsMod.CERAMIC_SALMON_BUCKET;
            } else if (bucketItem == Items.TROPICAL_FISH_BUCKET) {
                bucketItem = itemStack.getItem() == NewBucketsMod.WOODEN_WATER_BUCKET
                        ? NewBucketsMod.WOODEN_TROPICAL_FISH_BUCKET
                        : NewBucketsMod.CERAMIC_TROPICAL_FISH_BUCKET;
            } else {
                //TODO: Custom Fish Handling
                return;
            }

            ItemStack customFishBucketStack = new ItemStack(bucketItem);
            customFishBucketStack.setDamage(itemStack.getDamage());
            itemStack.decrement(1);

            this.copyDataToStack(fishBucketStack);
            this.copyDataToStack(customFishBucketStack);
            NewBucketsMod.copyEnchantmentItemTags(itemStack, customFishBucketStack);
            if (!this.getWorld().isClient) {
                Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity)player, fishBucketStack);
            }
            if (itemStack.isEmpty()) {
                player.setStackInHand(hand, customFishBucketStack);
            } else if (!player.inventory.insertStack(customFishBucketStack)) {
                player.dropItem(customFishBucketStack, false);
            }

            this.remove();
            cir.setReturnValue(ActionResult.success(this.getWorld().isClient));
            cir.cancel();
        }
    }
}
