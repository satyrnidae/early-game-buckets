package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Shadows members from AbstractBlock
 * @author Isabel Maskrey
 * @since 1.0.1
 */
@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Shadow @Nullable protected Identifier lootTableId;
}
