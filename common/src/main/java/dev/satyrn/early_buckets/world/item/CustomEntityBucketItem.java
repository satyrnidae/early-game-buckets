package dev.satyrn.early_buckets.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
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
    final EntityType<?> entityType;
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
    public CustomEntityBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Properties settings) {
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
    @Override
    public void checkExtraContent(@Nullable Player player, Level world, ItemStack stack, BlockPos pos) {
        if (world instanceof final ServerLevel serverLevel) {
            this.spawnEntity(serverLevel, player, stack, pos);
            world.gameEvent(player, GameEvent.ENTITY_PLACE, pos);
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
    @Override
    protected void playEmptySound(@Nullable Player player, LevelAccessor world, BlockPos pos) {
        world.playSound(player, pos, this.emptyingSound, SoundSource.NEUTRAL, 1.0F, 1.0F);
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
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        if (this.entityType == EntityType.TROPICAL_FISH) {
            CompoundTag compoundTag = stack.getTag();
            if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
                int i = compoundTag.getInt("BucketVariantTag");
                ChatFormatting[] formattings = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};
                String string = "color.minecraft." + TropicalFish.getBaseColor(i);
                String string2 = "color.minecraft." + TropicalFish.getPatternColor(i);

                for (int j = 0; j < TropicalFish.COMMON_VARIANTS.length; ++j) {
                    if (i == TropicalFish.COMMON_VARIANTS[j]) {
                        tooltip.add((Component.translatable(TropicalFish.getPredefinedName(j))).withStyle(formattings));
                        return;
                    }
                }

                tooltip.add((Component.translatable(TropicalFish.getPredefinedName(i))).withStyle(formattings));
                MutableComponent mutableText = Component.translatable(string);
                if (!string.equals(string2)) {
                    mutableText.append(", ").append(Component.translatable(string2));
                }

                mutableText.withStyle(formattings);
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
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
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
    @SuppressWarnings("deprecation")
    private void spawnEntity(ServerLevel serverWorld, Player player, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityType.spawn(serverWorld, stack, null, pos, MobSpawnType.BUCKET, true, false);
        if (entity instanceof final Bucketable bucketable && entity instanceof Mob mob) {
            Bucketable.loadDefaultDataFromBucketTag(mob, stack.getOrCreateTag());
            bucketable.setFromBucket(true);
            if (player instanceof ServerPlayer serverPlayer && stack.getItem().canBeDepleted()) {
                if (stack.hurt(this.getDamageOnEntityInteraction(this.entityType), serverWorld.random, serverPlayer)) {
                    stack.shrink(1);
                }
            }
        }
    }
}
