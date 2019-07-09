package com.miningmark48.magnets.item;

import com.miningmark48.magnets.block.base.CustomEnergyStorage;
import com.miningmark48.magnets.init.ModConfig;
import com.miningmark48.magnets.item.base.ItemMagnetBase;
import com.miningmark48.magnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagnetRF extends ItemMagnetBase {

    private final int maxPower;
    private final int transfer;
    private int usageEnergy;

    public ItemMagnetRF(int tier, boolean isMagic) {
        super(ModConfig.thermalExpansionConfigs.baseRange + (ModConfig.thermalExpansionConfigs.baseRange * ModConfig.thermalExpansionConfigs.multiplierRange * tier), ModConfig.thermalExpansionConfigs.speed, isMagic);

        this.maxPower = ModConfig.thermalExpansionConfigs.baseEnergy + (ModConfig.thermalExpansionConfigs.baseEnergy * ModConfig.thermalExpansionConfigs.multiplierEnergy * tier);
        this.transfer = ModConfig.thermalExpansionConfigs.transferRate;
        this.usageEnergy = ModConfig.thermalExpansionConfigs.baseUsageEnergy + (ModConfig.thermalExpansionConfigs.baseUsageEnergy * ModConfig.thermalExpansionConfigs.multiplierUsageEnergy * tier);

        if (isMagic) usageEnergy *= ModConfig.thermalExpansionConfigs.multiplierMagic;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> list, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, list, advanced);
        list.add(TextFormatting.RED + ModTranslate.toLocal("tooltip.item.magnet_base.energy1") + TextFormatting.AQUA + " " + this.getEnergyStored(stack) + " / " + this.getMaxEnergyStored(stack) + " " + TextFormatting.RED + ModTranslate.toLocal("tooltip.item.magnet_base.energy2") + " (" + usageEnergy + ModTranslate.toLocal("tooltip.item.magnet_base.energy3") + ") ");
    }

    @Override
    public boolean canMagnet(ItemStack stack) {
        return this.getEnergyStored(stack) >= usageEnergy;
    }

    @Override
    public void doCost(ItemStack stack) {
        this.extractEnergyInternal(stack, usageEnergy, false);
    }

    @Override
    public int getParticle() {
        return 1;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return this.getEnergyStored(stack) != this.getMaxEnergyStored(stack);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage != null) {
                double maxAmount = storage.getMaxEnergyStored();
                double energyDif = maxAmount - storage.getEnergyStored();
                return energyDif / maxAmount;
            }
        }
        return super.getDurabilityForDisplay(stack);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        float color = (float) this.getEnergyStored(stack) / this.getMaxEnergyStored(stack);
        return MathHelper.rgb((1f - color), color, 0);

    }

    public void setEnergy(ItemStack stack, int energy) {
        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage instanceof CustomEnergyStorage) {
                ((CustomEnergyStorage) storage).setEnergyStored(energy);
            }
        }
    }

    public int receiveEnergyInternal(ItemStack stack, int maxReceive, boolean simulate) {
        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage instanceof CustomEnergyStorage) {
                ((CustomEnergyStorage) storage).receiveEnergyInternal(maxReceive, simulate);
            }
        }
        return 0;
    }

    public int extractEnergyInternal(ItemStack stack, int maxExtract, boolean simulate) {
        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage instanceof CustomEnergyStorage) {
                ((CustomEnergyStorage) storage).extractEnergyInternal(maxExtract, simulate);
            }
        }
        return 0;
    }

    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage != null) { return storage.receiveEnergy(maxReceive, simulate); }
        }
        return 0;
    }

    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage != null) { return storage.extractEnergy(maxExtract, simulate); }
        }
        return 0;
    }

    public int getEnergyStored(ItemStack stack) {
        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage != null) { return storage.getEnergyStored(); }
        }
        return 0;
    }

    public int getMaxEnergyStored(ItemStack stack) {
        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage != null) { return storage.getMaxEnergyStored(); }
        }
        return 0;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new EnergyCapabilityProvider(stack, this);
    }

    private static class EnergyCapabilityProvider implements ICapabilityProvider {

        public final CustomEnergyStorage storage;

        public EnergyCapabilityProvider(final ItemStack stack, ItemMagnetRF item) {
            this.storage = new CustomEnergyStorage(item.maxPower, item.transfer, item.transfer) {
                @Override
                public int getEnergyStored() {
                    if (stack.hasTagCompound()) {
                        return stack.getTagCompound().getInteger("Energy");
                    } else {
                        return 0;
                    }
                }

                @Override
                public void setEnergyStored(int energy) {
                    if (!stack.hasTagCompound()) {
                        stack.setTagCompound(new NBTTagCompound());
                    }

                    stack.getTagCompound().setInteger("Energy", energy);
                }
            };
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return this.getCapability(capability, facing) != null;
        }

        @Nullable
        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if (capability == CapabilityEnergy.ENERGY) { return CapabilityEnergy.ENERGY.cast(this.storage); }
            return null;
        }
    }


}
