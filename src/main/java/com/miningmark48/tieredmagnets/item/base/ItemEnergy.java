package com.miningmark48.tieredmagnets.item.base;

import com.miningmark48.tieredmagnets.capability.energy.CustomEnergyStorage;
import com.miningmark48.tieredmagnets.reference.NBTKeys;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.function.IntSupplier;

public class ItemEnergy extends CustomEnergyStorage {

    private final ItemStack stack;

    public ItemEnergy(ItemStack stack, IntSupplier capacity) {
        super(capacity);
        this.stack = stack;
    }

    protected void writeEnergy() {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
        CompoundNBT nbt = stack.getTag();
        assert nbt != null;
        nbt.putInt(NBTKeys.ENERGY.getKey(), getEnergyStoredCache());
    }

    protected void updateEnergy() {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
        CompoundNBT nbt = stack.getTag();
        assert nbt != null;
        if (nbt.contains(NBTKeys.ENERGY.getKey())) {
            setEnergy(nbt.getInt(NBTKeys.ENERGY.getKey()));
        }
        updateMaxEnergy();
    }

}
