package dev.satyrn.silk.new_buckets.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Shadows members from Block
 * @author Isabel Maskrey
 * @since 1.0.1
 */
@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlockMixin implements ItemConvertible { }
