package dev.satyrn.early_buckets.forge.events;

import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = BucketModCommon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BucketModLifecycleEvents {
    private BucketModLifecycleEvents() {
    }

    @SubscribeEvent
    static void onCommonSetup(final FMLCommonSetupEvent commonSetupEvent) {
        BucketModCommon.postInit();
    }
}
