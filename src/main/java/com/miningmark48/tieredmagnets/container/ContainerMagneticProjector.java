package com.miningmark48.tieredmagnets.container;

import com.miningmark48.tieredmagnets.container.slot.SlotMagnet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMagneticProjector extends Container {

    private IInventory tileInv;

    public ContainerMagneticProjector(IInventory invPlayer, IInventory tileInv) {
        this.tileInv = tileInv;

        int i;
        this.addSlotToContainer(new SlotMagnet(tileInv, 0, 80, 34));

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

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index){
        ItemStack itemCopy = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(index);

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

}
