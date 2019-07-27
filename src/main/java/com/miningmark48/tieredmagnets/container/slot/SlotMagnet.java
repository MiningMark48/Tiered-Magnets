package com.miningmark48.tieredmagnets.container.slot;

import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotMagnet extends Slot {

    public SlotMagnet(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemMagnetBase;
    }
}
