package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.capability.energy.CapabilityProviderEnergy;
import com.miningmark48.tieredmagnets.client.particle.EnumParticles;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.reference.NBTKeys;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import com.miningmark48.tieredmagnets.util.UtilCapability.EnergyUtil;
import com.miningmark48.tieredmagnets.util.exceptions.ExceptionCapabilityNotPresent;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemMagnetRF extends ItemMagnetBase {

    private final int tier;
    private final boolean isMagic;

    public ItemMagnetRF(Properties properties, int tier, boolean isMagic) {
        super(properties, isMagic);

        this.tier = tier;
        this.isMagic = isMagic;

//        this.transfer = OldConfig.thermalExpansionConfigs.transferRate;

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<ITextComponent> list, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, list, advanced);

        if (KeyChecker.isHoldingShift() && ModConfig.isServerConfigLoaded()) {
            stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(energy -> list.add(new StringTextComponent(TextFormatting.RED + ModTranslate.toLocal("tooltip.item.magnet_base.energy1") + TextFormatting.AQUA + " " + energy.getEnergyStored() + " / " + energy.getMaxEnergyStored() + " " + TextFormatting.RED + ModTranslate.toLocal("tooltip.item.magnet_base.energy2"))));
        }

    }

    public int getEnergyMax() {
        return calculateAmount(ModConfig.COMMON.te_baseEnergy.get(), ModConfig.COMMON.te_multiplierEnergy.get(), tier);
    }

    public int getEnergyCost() {
        int usageEnergy = calculateAmount(ModConfig.COMMON.te_baseUsageEnergy.get(), ModConfig.COMMON.te_multiplierUsageEnergy.get(), tier);
        if (isMagic) usageEnergy *= ModConfig.COMMON.te_multiplierMagic.get();
        return usageEnergy;
    }

    @Override
    public boolean canMagnet(ItemStack stack) {
        if (ModConfig.COMMON.vanilla_hasCost.get()) {
            IEnergyStorage energy = EnergyUtil.getCap(stack).orElseThrow(ExceptionCapabilityNotPresent::new);
            return getEnergyCost() <= energy.getEnergyStored();
        }
        return false;
    }

    @Override
    public void doCost(ItemStack stack, World world, Entity entity) {
        if (ModConfig.COMMON.vanilla_hasCost.get()) {
            IEnergyStorage energy = EnergyUtil.getCap(stack).orElseThrow(ExceptionCapabilityNotPresent::new);
            energy.extractEnergy(getEnergyCost(), false);
        }
    }

    @Override
    public void setTagDefaults(ItemStack stack) {
        if (!stack.hasTag()) {
            super.setTagDefaults(stack);
            assert stack.getTag() != null;
            stack.getTag().putInt(NBTKeys.RANGE.getKey(), getDefaultRange());
        }
    }

    @Override
    public int getDefaultRange() {
        return ModConfig.isServerConfigLoaded() ? calculateAmount(ModConfig.COMMON.te_baseRange.get(), ModConfig.COMMON.te_multiplierRange.get(), tier) : super.getDefaultRange();
    }

    @Override
    public double getSpeed() {
        return ModConfig.COMMON.te_speed.get();
    }

    @Nonnull
    @Override
    public EnumParticles getParticle() {
        return EnumParticles.ENERGY;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return EnergyUtil.returnBooleanIfPresent(stack,
                energy -> energy.getEnergyStored() != energy.getMaxEnergyStored(),
                () -> super.showDurabilityBar(stack));
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return EnergyUtil.returnDoubleIfPresent(stack,
                (energy -> 1D - (energy.getEnergyStored() / (double) energy.getMaxEnergyStored())),
                () -> super.getDurabilityForDisplay(stack));
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        IEnergyStorage energy = EnergyUtil.getCap(stack).orElseThrow(ExceptionCapabilityNotPresent::new);
        float color = (float) energy.getEnergyStored() / energy.getMaxEnergyStored();
        return MathHelper.rgb((1f - color), color, 0);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new CapabilityProviderEnergy(stack, this::getEnergyMax);
    }

}
