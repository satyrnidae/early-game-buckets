package dev.satyrn.early_buckets.world.inventory;

import dev.satyrn.early_buckets.world.item.crafting.BucketRecipeTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;

public class KilnMenu extends AbstractFurnaceMenu {

    public KilnMenu(int containerId, Inventory playerInventory) {
        super(BucketMenuTypes.KILN_MENU.get(), BucketRecipeTypes.FIRING.get(), RecipeBookType.FURNACE, containerId,
                playerInventory);
    }

    public KilnMenu(int containerId, Inventory playerInventory, Container kilnContainer, ContainerData kilnData) {
        super(BucketMenuTypes.KILN_MENU.get(), BucketRecipeTypes.FIRING.get(), RecipeBookType.FURNACE, containerId,
                playerInventory, kilnContainer, kilnData);
    }
}
