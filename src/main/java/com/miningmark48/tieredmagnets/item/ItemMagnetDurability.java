package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.client.particle.EnumParticles;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.reference.NBTKeys;
import com.miningmark48.tieredmagnets.reference.Translations.Tooltips;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ItemMagnetDurability extends ItemMagnetBase {

    private final boolean isMagic;
    private final int tier;

    public ItemMagnetDurability(int tier, boolean isMagic) {
        super(new Properties(), isMagic);

        this.isMagic = isMagic;
        this.tier = tier;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<ITextComponent> list, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, list, advanced);

        if (KeyChecker.isHoldingShift()) {
            list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + ModTranslate.toLocal(Tooltips.I_MAGNET_DURABILITY.getTooltip()) + TextFormatting.AQUA + " " + (stack.getMaxDamage() - stack.getDamage())));
        }

    }

    @Override
    public void doCost(ItemStack stack, World world, Entity entity) {
        if (ModConfig.COMMON.vanilla_hasCost.get()) {
            int damageAmount = 1;
            if (isMagic) damageAmount *= ModConfig.COMMON.vanilla_multiplierMagic.get();
            if (stack.attemptDamageItem(damageAmount, new Random(), null)) {
                stack.shrink(1);
            }
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
    public int getMaxDamage(ItemStack stack) {
        return calculateAmount(ModConfig.COMMON.vanilla_baseDurability.get(), ModConfig.COMMON.vanilla_multiplierDurability.get(), tier);
    }

    @Override
    public int getDefaultRange() {
        return ModConfig.isCommonCfgLoaded() ? calculateAmount(ModConfig.COMMON.vanilla_baseRange.get(), ModConfig.COMMON.vanilla_multiplierRange.get(), tier) : super.getDefaultRange();
    }

    @Override
    public double getSpeed() {
        return ModConfig.COMMON.vanilla_speed.get();
    }

    @Nonnull
    @Override
    public EnumParticles getParticle() {
        return EnumParticles.VANILLA;
    }

}
