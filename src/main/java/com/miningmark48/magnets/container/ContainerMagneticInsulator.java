package com.miningmark48.magnets.container;

import com.miningmark48.magnets.inventory.InventoryMagnetFilter;
import com.miningmark48.magnets.tileentity.TileEntityMagneticInsulator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerMagneticInsulator extends Container {

    private static final int INV_START = InventoryMagnetFilter.INV_SIZE, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1, HOTBAR_END = HOTBAR_START + 8;

    public ContainerMagneticInsulator(IInventory invPlayer, TileEntityMagneticInsulator te) {
        int i;
        for (i = 0; i < 3; i++){
            for (int j = 0; j < 9; j++){
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; i++){
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
