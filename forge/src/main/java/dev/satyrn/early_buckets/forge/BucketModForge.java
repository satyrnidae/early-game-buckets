package dev.satyrn.early_buckets.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BucketModCommon.MOD_ID)
@Mod.EventBusSubscriber(modid = BucketModCommon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BucketModForge {
    public BucketModForge(final FMLJavaModLoadingContext modLoadingContext) {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(BucketModCommon.MOD_ID, modLoadingContext.getModEventBus());

        // Run our common setup.
        BucketModCommon.init();
    }
}
