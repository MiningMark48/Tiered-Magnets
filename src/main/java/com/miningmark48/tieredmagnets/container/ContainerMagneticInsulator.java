package com.miningmark48.tieredmagnets.container;

import com.miningmark48.tieredmagnets.init.ModContainers;
import com.miningmark48.tieredmagnets.inventory.InventoryMagnetFilter;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class ContainerMagneticInsulator extends Container {

    private static final int INV_START = InventoryMagnetFilter.INV_SIZE, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1, HOTBAR_END = HOTBAR_START + 8;
    private TileEntityMagneticInsulator te;

    public ContainerMagneticInsulator(int windowId, PlayerInventory invPlayer, PacketBuffer packetBuffer) {
        this(windowId, invPlayer, (TileEntityMagneticInsulator) Minecraft.getInstance().world.getTileEntity(packetBuffer.readBlockPos()));
    }

    public ContainerMagneticInsulator(int windowId, PlayerInventory invPlayer, TileEntityMagneticInsulator te) {
        super(ModContainers.CONTAINER_MAGNETIC_INSULATOR, windowId);
        this.te = te;
        addPlayerSlots(invPlayer);
    }

    @SuppressWarnings("Duplicates")
    public void addPlayerSlots(PlayerInventory invPlayer) {
        int i;
        for (i = 0; i < 3; i++){
            for (int j = 0; j < 9; j++){
                this.addSlot(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; i++){
            this.addSlot(new Slot(invPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    public TileEntityMagneticInsulator getTe() {
        return te;
    }

}
