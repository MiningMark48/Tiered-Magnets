package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.client.particle.base.ParticleMagnetize.Particles;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagnetRF extends ItemMagnetBase {

    private final int maxPower;
    private final int transfer;
    private int usageEnergy;

    public ItemMagnetRF(Properties properties, int tier, boolean isMagic) {
        super(properties, ModConfig.thermalExpansionConfigs.baseRange + (ModConfig.thermalExpansionConfigs.baseRange * ModConfig.thermalExpansionConfigs.multiplierRange * tier), ModConfig.thermalExpansionConfigs.speed, isMagic);

        this.maxPower = ModConfig.thermalExpansionConfigs.baseEnergy + (ModConfig.thermalExpansionConfigs.baseEnergy * ModConfig.thermalExpansionConfigs.multiplierEnergy * tier);
        this.transfer = ModConfig.thermalExpansionConfigs.transferRate;
        this.usageEnergy = ModConfig.thermalExpansionConfigs.baseUsageEnergy + (ModConfig.thermalExpansionConfigs.baseUsageEnergy * ModConfig.thermalExpansionConfigs.multiplierUsageEnergy * tier);

        if (isMagic) usageEnergy *= ModConfig.thermalExpansionConfigs.multiplierMagic;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<ITextComponent> list, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, list, advanced);

        if (KeyChecker.isHoldingShift()) {
//            list.add(new StringTextComponent(TextFormatting.RED + ModTranslate.toLocal("tooltip.item.magnet_base.energy1") + TextFormatting.AQUA + " " + this.getEnergyStored(stack) + " / " + this.getMaxEnergyStored(stack) + " " + TextFormatting.RED + ModTranslate.toLocal("tooltip.item.magnet_base.energy2")));
//            list.add(new StringTextComponent(TextFormatting.DARK_RED + ModTranslate.toLocal("tooltip.item.magnet_base.cost") + " " + TextFormatting.RED + usageEnergy + TextFormatting.AQUA + " " + ModTranslate.toLocal("tooltip.item.magnet_base.cost.energy") + " " + ModTranslate.toLocal("tooltip.item.magnet_base.cost.blocks1") + " " + TextFormatting.LIGHT_PURPLE + ModConfig.miscconfigs.costForDistance + TextFormatting.AQUA + " " + ModTranslate.toLocal("tooltip.item.magnet_base.cost.blocks2")));
        }
    }

    @Override
    public boolean canMagnet(ItemStack stack) {
        return true; //this.getEnergyStored(stack) >= usageEnergy;
    }

    @Override
    public void doCost(ItemStack stack) {
//        if (ModConfig.thermalExpansionConfigs.hasCost) this.extractEnergyInternal(stack, usageEnergy, false);
    }

    @Override
    public Particles getParticle() {
        return Particles.ENERGY;
    }

//    @Override
//    public boolean showDurabilityBar(ItemStack stack) {
//        return this.getEnergyStored(stack) != this.getMaxEnergyStored(stack);
//    }
//
//    @Override
//    public double getDurabilityForDisplay(ItemStack stack) {
//        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
//            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
//            if (storage != null) {
//                double maxAmount = storage.getMaxEnergyStored();
//                double energyDif = maxAmount - storage.getEnergyStored();
//                return energyDif / maxAmount;
//            }
//        }
//        return super.getDurabilityForDisplay(stack);
//    }
//
//    @Override
//    public int getRGBDurabilityForDisplay(ItemStack stack) {
//        float color = (float) this.getEnergyStored(stack) / this.getMaxEnergyStored(stack);
//        return MathHelper.rgb((1f - color), color, 0);
//    }
//
//    public void setEnergy(ItemStack stack, int energy) {
//        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
//            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
//            if (storage instanceof CustomEnergyStorage) {
//                ((CustomEnergyStorage) storage).setEnergyStored(energy);
//            }
//        }
//    }
//
//    public int receiveEnergyInternal(ItemStack stack, int maxReceive, boolean simulate) {
//        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
//            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
//            if (storage instanceof CustomEnergyStorage) {
//                ((CustomEnergyStorage) storage).receiveEnergyInternal(maxReceive, simulate);
//            }
//        }
//        return 0;
//    }
//
//    public int extractEnergyInternal(ItemStack stack, int maxExtract, boolean simulate) {
//        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
//            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
//            if (storage instanceof CustomEnergyStorage) {
//                ((CustomEnergyStorage) storage).extractEnergyInternal(maxExtract, simulate);
//            }
//        }
//        return 0;
//    }
//
//    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
//        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
//            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
//            if (storage != null) { return storage.receiveEnergy(maxReceive, simulate); }
//        }
//        return 0;
//    }
//
//    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
//        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
//            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
//            if (storage != null) { return storage.extractEnergy(maxExtract, simulate); }
//        }
//        return 0;
//    }
//
//    public int getEnergyStored(ItemStack stack) {
//        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
//            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
//            if (storage != null) { return storage.getEnergyStored(); }
//        }
//        return 0;
//    }
//
//    public int getMaxEnergyStored(ItemStack stack) {
//        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
//            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
//            if (storage != null) { return storage.getMaxEnergyStored(); }
//        }
//        return 0;
//    }
//
//    @Override
//    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
//        return new EnergyCapabilityProvider(stack, this);
//    }
//
//    private static class EnergyCapabilityProvider implements ICapabilityProvider {
//
//        public final CustomEnergyStorage storage;
//
//        public EnergyCapabilityProvider(final ItemStack stack, ItemMagnetRF item) {
//            this.storage = new CustomEnergyStorage(item.maxPower, item.transfer, item.transfer) {
//                @Override
//                public int getEnergyStored() {
//                    if (stack.hasTag()) {
//                        return stack.getTag().getInt("Energy");
//                    } else {
//                        return 0;
//                    }
//                }
//
//                @Override
//                public void setEnergyStored(int energy) {
//                    if (!stack.hasTag()) {
//                        stack.setTag(new CompoundNBT());
//                    }
//
//                    stack.getTag().putInt("Energy", energy);
//                }
//            };
//        }
//
//        @Nonnull
//        @Override
//        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
//            if (capability == CapabilityEnergy.ENERGY) {
//                return CapabilityEnergy.ENERGY.cast(this.storage);
//            }
//            return null;
//        }
//
//    }


}
