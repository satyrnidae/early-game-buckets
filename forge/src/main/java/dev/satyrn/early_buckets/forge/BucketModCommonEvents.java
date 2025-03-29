package dev.satyrn.early_buckets.forge;

import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = BucketModCommon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BucketModCommonEvents {
    private BucketModCommonEvents() {
    }

    @SubscribeEvent
    static void onCommonSetup(final @NotNull FMLCommonSetupEvent commonSetupEvent) {
        BucketModCommon.postInit();
    }
}
