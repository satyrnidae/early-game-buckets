package dev.satyrn.early_buckets.forge.data;

import dev.satyrn.early_buckets.BucketModCommon;
import dev.satyrn.early_buckets.forge.data.provider.client.*;
import dev.satyrn.early_buckets.forge.data.provider.server.*;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = BucketModCommon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BucketModDataEvents {
    private BucketModDataEvents() {}

    @SubscribeEvent
    static void onGatherData(final @NotNull GatherDataEvent event) {
        // client
        event.getGenerator()
                .addProvider(event.includeClient(), new BucketModSoundsProvider(event.getGenerator(), event.getModContainer().getModId(), event.getExistingFileHelper()));
        event.getGenerator()
                .addProvider(event.includeClient(), new BucketModLanguageProvider(event.getGenerator(), event.getModContainer().getModId(), "en_us"));
        event.getGenerator()
                .addProvider(event.includeClient(), new BucketModItemModelProvider(event.getGenerator(), event.getModContainer().getModId(), event.getExistingFileHelper()));
        event.getGenerator()
                .addProvider(event.includeClient(), new BucketModBlockStateProvider(event.getGenerator(), event.getModContainer().getModId(), event.getExistingFileHelper()));
        event.getGenerator()
                .addProvider(event.includeClient(), new BucketModPirateLanguageProvider(event.getGenerator(), event.getModContainer().getModId(), "en_pt"));

        // server
        event.getGenerator()
                .addProvider(event.includeServer(), new BucketModEntityTypeTagsProvider(event.getGenerator(), event.getModContainer().getModId(), event.getExistingFileHelper()));
        var blockTagsProvider = new BucketModBlockTagsProvider(event.getGenerator(), event.getModContainer().getModId(), event.getExistingFileHelper());
        event.getGenerator()
                .addProvider(event.includeServer(), blockTagsProvider);
        event.getGenerator()
                .addProvider(event.includeServer(), new BucketModItemTagsProvider(event.getGenerator(), blockTagsProvider, event.getModContainer().getModId(), event.getExistingFileHelper()));
        event.getGenerator()
                .addProvider(event.includeServer(), new BucketModRecipeProvider(event.getGenerator()));
        event.getGenerator()
                .addProvider(event.includeServer(), new BucketModAdvancementProvider(event.getGenerator(), event.getExistingFileHelper()));
    }
}
