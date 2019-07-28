package com.miningmark48.tieredmagnets.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.Objects;

public class InventoryMagnetFilter implements IInventory {

    private final ItemStack invItem;
    public static final int INV_SIZE = 9;
    public NonNullList<ItemStack> inventory = NonNullList.withSize(INV_SIZE, ItemStack.EMPTY);

    public InventoryMagnetFilter(ItemStack stack){

        invItem = stack;

        if(!stack.hasTag()){
            stack.setTag(new CompoundNBT());
        }

        readFromNBT(stack.getTag());

    }

    @Override
    public int getSizeInventory() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory.get(index);
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {

        ItemStack stack = getStackInSlot(index);
        if(stack != null){
            if(stack.getCount() > count){
                stack = stack.split(count);
                markDirty();
            }else{
                setInventorySlotContents(index, ItemStack.EMPTY);
            }
        }

        return stack;
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);
        setInventorySlotContents(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        inventory.set(index, stack);

        if(stack != ItemStack.EMPTY && stack.getCount() > getInventoryStackLimit()){
            stack.setCount(getInventoryStackLimit());
        }

        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        for(int i = 0; i < getSizeInventory(); i++){
            if(getStackInSlot(i) != ItemStack.EMPTY && getStackInSlot(i).getCount() == 0){
                inventory.set(i, ItemStack.EMPTY);
            }
        }

        writeToNBT(invItem.getTag());
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void openInventory(PlayerEntity player) {

    }

    @Override
    public void closeInventory(PlayerEntity player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    public void readFromNBT(CompoundNBT compound){
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        ItemStackHelper.loadAllItems(compound, this.inventory);
    }

    public void writeToNBT(CompoundNBT compound){
        ItemStackHelper.saveAllItems(compound, this.inventory);
    }

//    @Override
//    public int getField(int id) {
//        return 0;
//    }
//
//    @Override
//    public void setField(int id, int value) {
//
//    }
//
//    @Override
//    public int getFieldCount() {
//        return 0;
//    }

    @Override
    public void clear() {

    }

//    @Override
//    public String getName() {
//        return null;
//    }
//
//    @Override
//    public boolean hasCustomName() {
//        return false;
//    }
//
//    @Override
//    public ITextComponent getDisplayName() {
//        return null;
//    }

}
