package com.miningmark48.magnets.container;

import com.miningmark48.magnets.container.slot.SlotFilter;
import com.miningmark48.magnets.inventory.InventoryMagnetFilter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ContainerMagnetFilter extends Container {

    public final InventoryMagnetFilter inventory;
    public EntityPlayer player;
    private static final int INV_START = InventoryMagnetFilter.INV_SIZE, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1, HOTBAR_END = HOTBAR_START + 8;

    public ContainerMagnetFilter(EntityPlayer player, InventoryPlayer invPlayer, InventoryMagnetFilter item) {
        this.inventory = item;
        this.player = player;

        int i;
        for (i = 0; i < InventoryMagnetFilter.INV_SIZE; i++){
            this.addSlotToContainer(new SlotFilter(this.inventory, i, 26 + (18 * (int)(i%3)), 17 + (18 * (int)(i/3))));
        }

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
        return inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index){
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            stack = itemstack1.copy();

            if (index < INV_START){
                if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)){
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, stack);
            }else{
                if(!this.mergeItemStack(itemstack1, 0, INV_START, false)){
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.getCount() == 0){
                slot.putStack(ItemStack.EMPTY);
            }else{
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == stack.getCount()){
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);

        }

        return stack;

    }

    @Override
    public ItemStack slotClick(int slot, int button, ClickType flag, EntityPlayer player){
        if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem(EnumHand.MAIN_HAND)){
            return ItemStack.EMPTY;
        }
        return super.slotClick(slot, button, flag, player);
    }

}
