package dev.satyrn.early_buckets.advancement.criterion;

import com.google.gson.JsonObject;
import dev.satyrn.early_buckets.BucketMod;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.minecraft.predicate.entity.EntityPredicate.Extended;

/**
 * Provides advancement criteria which can check both the item being emptied and the filled item.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
public class AdvancedFillBucketCriterion extends AbstractCriterion<AdvancedFillBucketCriterion.Conditions> {
    /**
     * The identifier for this criterion
     */
    static final Identifier ID = new Identifier(BucketMod.MOD_ID, "filled_bucket");

    /**
     * Reads the criterion's conditions from a json object.
     *
     * @param json                  The json object.
     * @param playerPredicate       The player predicate.
     * @param predicateDeserializer The deserializer instance.
     * @return The deserialized conditions.
     */
    @Override
    protected Conditions conditionsFromJson(JsonObject json, Extended playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        ItemPredicate emptyItemPredicate = ItemPredicate.fromJson(json.get("empty"));
        ItemPredicate filledItemPredicate = ItemPredicate.fromJson(json.get("filled"));
        return new AdvancedFillBucketCriterion.Conditions(playerPredicate, emptyItemPredicate, filledItemPredicate);
    }

    /**
     * Gets the ID of this criterion.
     *
     * @return The {@link AdvancedFillBucketCriterion#ID} of this criterion.
     */
    @Override
    public Identifier getId() {
        return ID;
    }

    /**
     * Triggers this advancement criterion.
     *
     * @param player The player that the advancement is being triggered for.
     * @param empty  The empty bucket item stack.
     * @param filled The filled bucket item stack.
     */
    public void trigger(ServerPlayerEntity player, ItemStack empty, ItemStack filled) {
        this.trigger(player, conditions -> conditions.matches(empty, filled));
    }

    /**
     * Provides criteria conditions for {@link AdvancedFillBucketCriterion}.
     *
     * @author Isabel Maskrey
     * @since 2.0.0+alpha.1
     */
    public static class Conditions extends AbstractCriterionConditions {
        // The empty item's predicate
        private final ItemPredicate empty;
        // The filled item's predicate
        private final ItemPredicate filled;

        /**
         * Creates a new set of conditions for the criterion.
         *
         * @param player The player.
         * @param empty  The empty item predicate.
         * @param filled The filled item predicate.
         */
        public Conditions(Extended player, ItemPredicate empty, ItemPredicate filled) {
            super(AdvancedFillBucketCriterion.ID, player);
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
            return this.empty.test(empty) && this.filled.test(filled);
        }

        /**
         * Serializes this object to json.
         *
         * @param predicateSerializer The serializer.
         * @return The serialized {@link JsonObject}.
         */
        public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
            final JsonObject json = super.toJson(predicateSerializer);
            json.add("empty", this.empty.toJson());
            json.add("filled", this.filled.toJson());
            return json;
        }
    }
}
