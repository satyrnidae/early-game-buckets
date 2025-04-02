package dev.satyrn.early_buckets.sounds;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public final class BucketSoundEvents {
    public static final RegistrySupplier<SoundEvent> KILN_FIRE_CRACKLE;

    static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BucketModCommon.MOD_ID,
            Registry.SOUND_EVENT_REGISTRY);

    static {
        final ResourceLocation kilnFireCrackleId = new ResourceLocation(BucketModCommon.MOD_ID,
                "block." + BucketModCommon.MOD_ID + ".kiln.fire_crackle");
        KILN_FIRE_CRACKLE = SOUND_EVENTS.register(kilnFireCrackleId, () -> new SoundEvent(kilnFireCrackleId));
    }

    private BucketSoundEvents() {
    }

    public static void register() {
        SOUND_EVENTS.register();
    }
}
