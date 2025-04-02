package dev.satyrn.early_buckets.mixin.advancements;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Invoker mixin for {@link CriteriaTriggers}.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
@Mixin(CriteriaTriggers.class)
public interface CriteriaTriggersAccessor {

    /**
     * Calls {@code Criteria.register(Criterion)}.
     *
     * @param object The object to register.
     * @param <T>    The criterion type.
     *
     * @return The registered criterion.
     */
    @Invoker()
    static <T extends CriterionTrigger<?>> T callRegister(T object) {
        throw new AssertionError();
    }
}
