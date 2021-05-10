package dev.satyrn.silk.new_buckets.mixin;

import dev.satyrn.silk.new_buckets.NewBucketsMod;
import dev.satyrn.silk.new_buckets.item.CustomBucketItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Overrides the interact action to allow cauldrons to be filled by non-iron buckets
 * @author Isabel Maskrey
 * @since 1.0.1
 */
@Mixin(CauldronBlock.class)
public abstract class CauldronBlockMixin extends BlockMixin {

    /**
     * The cauldron water level
     * @since 1.0.1
     */
    @Final
    @Shadow
    public static IntProperty LEVEL;

    /**
     * Injects block interactions into the cauldron onUse function
     * @param state The block state.
     * @param world The world that the block is in.
     * @param pos The position of the block.
     * @param user The player which interacted with the block.
     * @param hand The hand which the user is interacting with.
     * @param hit The hit result for the block.
     * @param cir The callback information.
     * @since 1.0.1
     */
    @Inject(method = "onUse", at=@At("HEAD"), cancellable = true)
    public void injectOnUse(BlockState state, World world, BlockPos pos, PlayerEntity user, Hand hand,
                            BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack =  user.getStackInHand(hand);
        if (itemStack.isEmpty()) {
            cir.setReturnValue(ActionResult.PASS);
            cir.cancel();
            return;
        }

        int level = state.get(LEVEL);
        Item item = itemStack.getItem();

        if (item == NewBucketsMod.WOODEN_WATER_BUCKET || item == NewBucketsMod.CERAMIC_WATER_BUCKET) {
            CustomBucketItem bucket = (CustomBucketItem) item;
            if (level < 3 && !world.isClient) {
                if (!user.abilities.creativeMode) {
                    itemStack.damage(bucket.getDamageForFluid(bucket.fluid), user, (playerEntity) -> playerEntity.sendToolBreakStatus(hand));
                }
                user.incrementStat(Stats.FILL_CAULDRON);
                this.setLevel(world, pos, state, 3);
                user.setStackInHand(hand, ((CustomBucketItem)item).getEmptiedStack(itemStack, user));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1F, 1F);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
            cir.cancel();
            return;
        }

        if (item == NewBucketsMod.WOODEN_BUCKET || item == NewBucketsMod.CERAMIC_BUCKET) {
            CustomBucketItem bucket = (CustomBucketItem)item;
            if (level == 3 && !world.isClient) {
                ItemStack filled = ItemUsage.method_30012(itemStack, user, new ItemStack(bucket.getFilledItem(Fluids.WATER)));
                if(!user.abilities.creativeMode) {
                    filled.setDamage(itemStack.getDamage());
                    itemStack.decrement(1);
                    if (itemStack.isEmpty()) {
                        user.setStackInHand(hand, filled);
                    } else if (!user.inventory.insertStack(filled)) {
                        user.dropItem(filled, false);
                    }
                }
                user.incrementStat(Stats.FILL_CAULDRON);
                this.setLevel(world, pos, state, 0);
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1F, 1F);
            }

            cir.setReturnValue(ActionResult.success(world.isClient));
            cir.cancel();
        }
    }

    /**
     * Sets the cauldron fill level
     * @param world The world to set the fill level in.
     * @param pos The position of the block.
     * @param state The block state.
     * @param level The new fill level for the cauldron.
     * @since 1.0.1
     */
    @Shadow
    public abstract void setLevel(World world, BlockPos pos, BlockState state, int level);
}
