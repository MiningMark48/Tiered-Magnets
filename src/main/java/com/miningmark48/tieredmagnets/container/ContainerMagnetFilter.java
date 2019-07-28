package com.miningmark48.tieredmagnets.container;

import com.miningmark48.tieredmagnets.container.slot.SlotFilter;
import com.miningmark48.tieredmagnets.init.ModContainers;
import com.miningmark48.tieredmagnets.inventory.InventoryMagnetFilter;
import com.miningmark48.tieredmagnets.util.ModLogger;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.ModLoadingContext;

public class ContainerMagnetFilter extends Container {

    public final InventoryMagnetFilter inventory;
    public final PlayerEntity player;
    private static final int INV_START = InventoryMagnetFilter.INV_SIZE, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1, HOTBAR_END = HOTBAR_START + 8;

    public ContainerMagnetFilter(int windowId, PlayerInventory invPlayer, PacketBuffer packetBuffer) {
        this(windowId, invPlayer, packetBuffer.readItemStack());
    }

    public ContainerMagnetFilter(int windowId, PlayerInventory invPlayer, ItemStack stack) {
        super(ModContainers.CONTAINER_MAGNET_FILTER, windowId);
        this.inventory = new InventoryMagnetFilter(stack);
        this.player = invPlayer.player;
        addOwnSlots();
        addPlayerSlots(invPlayer);
    }

    public void addOwnSlots() {
        for (int i = 0; i < InventoryMagnetFilter.INV_SIZE; i++){
            this.addSlot(new SlotFilter(this.inventory, i, 26 + (18 * (int)(i%3)), 17 + (18 * (int)(i/3))));
        }
    }

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
        return inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index){
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
    public ItemStack slotClick(int slot, int button, ClickType flag, PlayerEntity player){
        if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem(Hand.MAIN_HAND)){
            return ItemStack.EMPTY;
        }
        return super.slotClick(slot, button, flag, player);
    }

}
