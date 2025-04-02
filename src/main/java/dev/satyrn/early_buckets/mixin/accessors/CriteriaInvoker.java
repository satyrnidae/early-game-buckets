package dev.satyrn.early_buckets.mixin.accessors;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.Criterion;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Invoker mixin for {@link Criteria}.
 *
 * @author Isabel Maskrey
 * @since 2.0.0+alpha.1
 */
@Mixin(Criteria.class)
public interface CriteriaInvoker {

    /**
     * Calls {@code Criteria.register(Criterion)}.
     *
     * @param object The object to register.
     * @param <T> The criterion type.
     * @return The registered criterion.
     */
    @Invoker()
    static <T extends Criterion<?>> @NotNull T callRegister(T object) { return object; }
}
