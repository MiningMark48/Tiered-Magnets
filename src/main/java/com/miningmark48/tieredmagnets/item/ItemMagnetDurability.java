package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.client.particle.EnumParticles;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.reference.NBTKeys;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
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

    public ItemMagnetDurability(Properties properties, int tier, boolean isMagic) {
        super(properties.maxStackSize(1), isMagic);

        this.isMagic = isMagic;
        this.tier = tier;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<ITextComponent> list, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, list, advanced);

        if (KeyChecker.isHoldingShift()) {
            list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + ModTranslate.toLocal("tooltip.item.magnet_base.durability") + TextFormatting.AQUA + " " + (stack.getMaxDamage() - stack.getDamage())));

//            assert stack.getTag() != null;
//            list.add(new StringTextComponent(TextFormatting.GRAY + stack.getTag().toString()));

        }

    }

    @Override
    public void doCost(ItemStack stack) {
        if (ModConfig.SERVER.vanilla_hasCost.get()) {
            int damageAmount = 1;
            if (isMagic) damageAmount *= ModConfig.SERVER.vanilla_multiplierMagic.get();
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
        return calculateAmount(ModConfig.SERVER.vanilla_baseDurability.get(), ModConfig.SERVER.vanilla_multiplierDurability.get(), tier);
    }

    @Override
    public int getDefaultRange() {
        return ModConfig.isServerConfigLoaded() ? calculateAmount(ModConfig.SERVER.vanilla_baseRange.get(), ModConfig.SERVER.vanilla_multiplierRange.get(), tier) : super.getDefaultRange();
    }

    @Override
    public double getSpeed() {
        return ModConfig.SERVER.vanilla_speed.get();
    }

    @Nonnull
    @Override
    public EnumParticles getParticle() {
        return EnumParticles.VANILLA;
    }

}
