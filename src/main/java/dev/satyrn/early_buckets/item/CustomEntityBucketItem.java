package dev.satyrn.early_buckets.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Custom implementation for fish buckets.
 *
 * @author Isabel Maskrey; Mojang
 * @since 1.0.0
 */
public abstract class CustomEntityBucketItem extends CustomBucketItem {
    // The entity contained within this bucket.
    private final EntityType<?> entityType;
    // The sound to make when the entity is emptied from the bucket.
    private final SoundEvent emptyingSound;

    /**
     * Initializes the custom fish bucket.
     *
     * @param type     The entity type.
     * @param fluid    The fluid that the bucket contains.
     * @param settings The item initialization settings.
     * @since 1.0.0
     */
    public CustomEntityBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
        super(fluid, settings);
        this.entityType = type;
        this.emptyingSound = emptyingSound;
    }

    /**
     * Occurs when the bucket is emptied.
     *
     * @param player The player that emptied the bucket.
     * @param world  The world in which the bucket was emptied.
     * @param stack  The item stack containing the bucket that was emptied.
     * @param pos    The position at which the bucket was emptied.
     * @since 2.0.0+alpha.1
     */
    public void onEmptied(@Nullable PlayerEntity player, World world, ItemStack stack, BlockPos pos) {
        if (world instanceof ServerWorld) {
            this.spawnEntity((ServerWorld) world, stack, pos);
            world.emitGameEvent(player, GameEvent.ENTITY_PLACE, pos);
        }
    }

    /**
     * Plays the emptying sound for the bucket.
     *
     * @param player The player entity.
     * @param world  The world in which the sound should play.
     * @param pos    The position of the sound source.
     * @since 1.0.0
     */
    protected void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        world.playSound(player, pos, this.emptyingSound, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }

    /**
     * Appends a tooltip to the bucket text.
     *
     * @param stack   The item stack.
     * @param world   The world instance.
     * @param tooltip The tooltip of the item.
     * @param context The tooltip context.
     * @since 1.0.0
     */
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (this.entityType == EntityType.TROPICAL_FISH) {
            NbtCompound compoundTag = stack.getNbt();
            if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
                int i = compoundTag.getInt("BucketVariantTag");
                Formatting[] formattings = new Formatting[]{Formatting.ITALIC, Formatting.GRAY};
                String string = "color.minecraft." + TropicalFishEntity.getBaseDyeColor(i);
                String string2 = "color.minecraft." + TropicalFishEntity.getPatternDyeColor(i);

                for (int j = 0; j < TropicalFishEntity.COMMON_VARIANTS.length; ++j) {
                    if (i == TropicalFishEntity.COMMON_VARIANTS[j]) {
                        tooltip.add((new TranslatableText(TropicalFishEntity.getToolTipForVariant(j))).formatted(formattings));
                        return;
                    }
                }

                tooltip.add((new TranslatableText(TropicalFishEntity.getTranslationKey(i))).formatted(formattings));
                MutableText mutableText = new TranslatableText(string);
                if (!string.equals(string2)) {
                    mutableText.append(", ").append(new TranslatableText(string2));
                }

                mutableText.formatted(formattings);
                tooltip.add(mutableText);
            }
        }
    }

    /**
     * Checks if the item can be repaired with a given ingredient.
     *
     * @param stack      The stack to repair.
     * @param ingredient The ingredient to repair with.
     * @return {@code false}, as fish buckets cannot be repaired.
     * @since 1.0.0
     */
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    /**
     * Disables repair of this item via the crafting grid.
     *
     * @return {@code false}, as fish buckets cannot be repaired.
     * @since 1.0.0
     */
    @Override
    public boolean canRepairByCrafting() {
        return false;
    }

    /**
     * Spawns the contained entity in the world.
     *
     * @param serverWorld The server world into which the fish should spawn.
     * @param stack       The item stack.
     * @param pos         The position at which to spawn the fish.
     * @since 1.0.0
     */
    private void spawnEntity(ServerWorld serverWorld, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityType.spawnFromItemStack(serverWorld, stack, null, pos, SpawnReason.BUCKET, true, false);
        if (entity instanceof final Bucketable bucketable) {
            bucketable.copyDataFromNbt(stack.getOrCreateNbt());
            bucketable.setFromBucket(true);
        }
    }
}
