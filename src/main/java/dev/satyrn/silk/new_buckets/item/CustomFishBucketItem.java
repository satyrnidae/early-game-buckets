package dev.satyrn.silk.new_buckets.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Custom implementation for fish buckets.
 * @author Isabel Maskrey; Mojang
 * @since 1.0.0
 */
public abstract class CustomFishBucketItem extends CustomBucketItem {
    private final EntityType<?> fishType;

    /**
     * Initializes the custom fish bucket.
     * @param type The entity type.
     * @param fluid The fluid that the bucket contains.
     * @param settings The item initialization settings.
     * @since 1.0.0
     */
    public CustomFishBucketItem(EntityType<?> type, Fluid fluid, Settings settings) {
        super(fluid, settings);
        this.fishType = type;
    }

    /**
     * Spawns a fish in the world when the bucket is emptied.
     * @param world The world into which the fish should spawn.
     * @param stack The item stack.
     * @param pos The position to dispense the fish.
     * @since 1.0.0
     */
    public void onEmptied(World world, ItemStack stack, BlockPos pos) {
        if (world instanceof ServerWorld) {
            this.spawnFish((ServerWorld)world, stack, pos);
        }
    }

    /**
     * Plays the emptying sound for the bucket.
     * @param player The player entity.
     * @param world The world in which the sound should play.
     * @param pos The position of the sound source.
     * @since 1.0.0
     */
    protected void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }

    /**
     * Spawns a fish in the world.
     * @param serverWorld The server world into which the fish should spawn.
     * @param stack The item stack.
     * @param pos The position at which to spawn the fish.
     * @since 1.0.0
     */
    private void spawnFish(ServerWorld serverWorld, ItemStack stack, BlockPos pos) {
        Entity entity = this.fishType.spawnFromItemStack(serverWorld, stack, null, pos, SpawnReason.BUCKET, true, false);
        if (entity != null) {
            ((FishEntity)entity).setFromBucket(true);
        }

    }

    /**
     * Appends a tooltip to the bucket text.
     * @param stack The item stack.
     * @param world The world instance.
     * @param tooltip The tooltip of the item.
     * @param context The tooltip context.
     * @since 1.0.0
     */
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (this.fishType == EntityType.TROPICAL_FISH) {
            CompoundTag compoundTag = stack.getTag();
            if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
                int i = compoundTag.getInt("BucketVariantTag");
                Formatting[] formattings = new Formatting[]{Formatting.ITALIC, Formatting.GRAY};
                String string = "color.minecraft." + TropicalFishEntity.getBaseDyeColor(i);
                String string2 = "color.minecraft." + TropicalFishEntity.getPatternDyeColor(i);

                for(int j = 0; j < TropicalFishEntity.COMMON_VARIANTS.length; ++j) {
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
}
