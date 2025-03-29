package dev.satyrn.early_buckets.world.inventory;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.early_buckets.BucketModCommon;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;

public final class BucketMenuTypes {
    public static final RegistrySupplier<MenuType<KilnMenu>> KILN;

    static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BucketModCommon.MOD_ID,
            Registry.MENU_REGISTRY);

    static {
        KILN = MENU_TYPES.register("kiln", () -> new MenuType<>(KilnMenu::new));
    }

    private BucketMenuTypes() {
    }

    public static void register() {
        MENU_TYPES.register();
    }
}
