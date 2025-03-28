package dev.satyrn.early_buckets.advancements.criterion;

import com.google.gson.JsonObject;
import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Provides advancement criteria which can check both the item being emptied and the filled item.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public class CustomFilledBucketTrigger extends SimpleCriterionTrigger<CustomFilledBucketTrigger.TriggerInstance> {
    /**
     * The identifier for this criterion
     */
    static final @NotNull ResourceLocation ID = new ResourceLocation(BucketModCommon.MOD_ID, "filled_bucket");

    /**
     * Gets the ID of this criterion.
     *
     * @return The {@link CustomFilledBucketTrigger#ID} of this criterion.
     */
    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    @Override
    protected @NotNull TriggerInstance createInstance(JsonObject jsonObject,
                                                      EntityPredicate.Composite composite,
                                                      DeserializationContext deserializationContext) {
        final @NotNull ItemPredicate emptyItemPredicate = ItemPredicate.fromJson(jsonObject.get("empty"));
        final @NotNull ItemPredicate filledItemPredicate = ItemPredicate.fromJson(jsonObject.get("filled"));
        return new TriggerInstance(composite, emptyItemPredicate, filledItemPredicate);
    }

    public void trigger(ServerPlayer serverPlayer, ItemStack empty, ItemStack filled) {
        super.trigger(serverPlayer, triggerInstance -> triggerInstance.matches(empty, filled));
    }

    /**
     * Provides criteria conditions for {@link CustomFilledBucketTrigger}.
     *
     * @author Isabel Maskrey
     * @since 2.0.0+alpha.1
     */
    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        // The empty item's predicate
        private final ItemPredicate empty;
        // The filled item's predicate
        private final ItemPredicate filled;

        public static TriggerInstance filledCustomBucket(ItemPredicate empty, ItemPredicate filled) {
            return new TriggerInstance(EntityPredicate.Composite.ANY, empty, filled);
        }

        /**
         * Creates a new set of conditions for the criterion.
         *
         * @param empty  The empty item predicate.
         * @param filled The filled item predicate.
         */
        private TriggerInstance(EntityPredicate.Composite composite, ItemPredicate empty, ItemPredicate filled) {
            super(CustomFilledBucketTrigger.ID, composite);
            this.empty = empty;
            this.filled = filled;
        }

        /**
         * Checks whether the empty and filled item stacks match their respective predicates.
         *
         * @param empty  A stack containing the empty bucket item.
         * @param filled A stack containing the full bucket item.
         * @return {@code true} if both predicates return {@code true}; otherwise, {@code false}.
         */
        public boolean matches(ItemStack empty, ItemStack filled) {
            return this.empty.matches(empty) && this.filled.matches(filled);
        }

        /**
         * Serializes this object to json.
         *
         * @param predicateSerializer The serializer.
         * @return The serialized {@link JsonObject}.
         */
        public @NotNull JsonObject serializeToJson(SerializationContext predicateSerializer) {
            final JsonObject json = super.serializeToJson(predicateSerializer);
            json.add("empty", this.empty.serializeToJson());
            json.add("filled", this.filled.serializeToJson());
            return json;
        }
    }
}
