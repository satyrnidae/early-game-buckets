package dev.satyrn.early_buckets.mixin.world.inventory;

import dev.satyrn.early_buckets.world.item.BucketItems;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.FurnaceFuelSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Unique
@Mixin(FurnaceFuelSlot.class)
public abstract class FurnaceFuelSlotMixin extends Slot {
    FurnaceFuelSlotMixin(Container container, int i, int j, int k) {
        super(container, i, j, k);
        throw new AssertionError();
    }

    @Inject(method = "isBucket", at = @At("HEAD"), cancellable = true)
    private static void early_buckets$isBucket(final @NotNull ItemStack itemStack,
                                               final @NotNull CallbackInfoReturnable<Boolean> cir) {
        if (itemStack.getItem() == BucketItems.WOODEN_BUCKET.get() || itemStack.getItem() == BucketItems.CERAMIC_BUCKET.get()) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
