package dev.satyrn.early_buckets.client;

import dev.architectury.registry.menu.MenuRegistry;
import dev.satyrn.early_buckets.client.gui.screens.inventory.KilnScreen;
import dev.satyrn.early_buckets.world.inventory.BucketMenuTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class BucketModClient {
    private BucketModClient() {
    }

    public static void initClient() {
        MenuRegistry.registerScreenFactory(BucketMenuTypes.KILN_MENU.get(), KilnScreen::new);
    }
}
