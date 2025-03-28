package dev.satyrn.early_buckets.forge.data.provider.client;

import dev.satyrn.early_buckets.sounds.BucketSoundEvents;
import dev.satyrn.early_buckets.world.level.block.BucketBlocks;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

public class BucketModSoundsProvider extends SoundDefinitionsProvider {
    public BucketModSoundsProvider(final @NotNull DataGenerator generator,
                                   final @NotNull String modId,
                                   final @NotNull ExistingFileHelper helper) {
        super(generator, modId, helper);
    }

    @Override
    public void registerSounds() {
        SoundDefinition.Sound[] fireCrackleSounds = IntStream.range(1,6).mapToObj(i -> SoundDefinition.Sound.sound(new ResourceLocation("block/furnace/fire_crackle" + i), SoundDefinition.SoundType.SOUND)).toArray(
                SoundDefinition.Sound[]::new);

        this.add(BucketSoundEvents.KILN_FIRE_CRACKLE, SoundDefinition.definition()
                .subtitle(Util.makeDescriptionId("subtitles.block", BucketBlocks.KILN.getId()) + ".fire_crackle")
                .with(fireCrackleSounds));
    }

    @Override
    public @NotNull String getName() {
        return "Early Game Buckets: Sound Events";
    }
}
