package dev.satyrn.early_buckets.forge.client;

import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.client.BucketModClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = BucketModCommon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BucketModClientEvents {
    private BucketModClientEvents() {
    }

    @SubscribeEvent
    static void onClientSetup(final FMLClientSetupEvent event) {
        // Client initialization
        BucketModClient.initClient();
    }
}
