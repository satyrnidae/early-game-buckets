package dev.satyrn.early_buckets.forge;

import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.world.item.BucketItems;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = BucketModCommon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BucketModCommonEvents {
    @SubscribeEvent
    public void onCommonSetup(final @NotNull FMLCommonSetupEvent commonSetupEvent) {
        BucketModCommon.postInit();
    }

    @SubscribeEvent
    public void burnTime(FurnaceFuelBurnTimeEvent event) {
        if (!event.getItemStack().isEmpty()) {
            if (event.getItemStack().getItem() == BucketItems.WOODEN_BUCKET.get()) {
                event.setBurnTime(20);
            } else if (event.getItemStack().getItem() == BucketItems.CERAMIC_LAVA_BUCKET.get()) {
                event.setBurnTime(20000);
            }

        }
    }
}
