package com.miningmark48.tieredmagnets.container;

import com.miningmark48.tieredmagnets.init.ModContainers;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerMagneticProjector extends Container {

    private TileEntityMagneticProjector te;

    public ContainerMagneticProjector(int windowId, PlayerInventory invPlayer, PacketBuffer packetBuffer) {
        this(windowId, invPlayer, (TileEntityMagneticProjector) Minecraft.getInstance().world.getTileEntity(packetBuffer.readBlockPos()));
    }

    public ContainerMagneticProjector(int windowId, PlayerInventory invPlayer, TileEntityMagneticProjector te) {
        super(ModContainers.CONTAINER_MAGNETIC_PROJECTOR, windowId);
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

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index){
        ItemStack itemCopy = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack item = slot.getStack();
            itemCopy = item.copy();

            if (index < 15)
            {
                if (!this.mergeItemStack(item, 15, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(item, 0, 15, false))
            {
                return ItemStack.EMPTY;
            }

            if (item.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemCopy;

    }

    public TileEntityMagneticProjector getTe() {
        return te;
    }

}
