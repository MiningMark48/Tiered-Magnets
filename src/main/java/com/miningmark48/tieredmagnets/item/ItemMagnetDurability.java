package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.client.particle.base.ParticleMagnetize.Particles;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.init.config.OldConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

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

            int damageAmount = 1;
            if (isMagic) damageAmount *= ModConfig.MODULE_VANILLA.defaultMultiplierMagic;
            list.add(new StringTextComponent(TextFormatting.DARK_RED + ModTranslate.toLocal("tooltip.item.magnet_base.cost") + " " + TextFormatting.LIGHT_PURPLE + damageAmount + TextFormatting.AQUA + " " + ModTranslate.toLocal("tooltip.item.magnet_base.cost.durability") + " " + ModTranslate.toLocal("tooltip.item.magnet_base.cost.blocks1") + " " + TextFormatting.LIGHT_PURPLE + OldConfig.miscconfigs.costForDistance + TextFormatting.AQUA + " " + ModTranslate.toLocal("tooltip.item.magnet_base.cost.blocks2")));

            assert stack.getTag() != null;
            list.add(new StringTextComponent(TextFormatting.GRAY + stack.getTag().toString()));

        }

    }

    @Override
    public void doCost(ItemStack stack) {
        if (ModConfig.MODULE_VANILLA.hasCost.get()) {
            int damageAmount = 1;
            if (isMagic) damageAmount *= ModConfig.MODULE_VANILLA.multiplierMagic.get();
            if (stack.attemptDamageItem(damageAmount, new Random(), null)) {
                stack.shrink(1);
            }
        }
    }

    @Override
    public void setTagDefaults(ItemStack stack) {
        super.setTagDefaults(stack);
        assert stack.getTag() != null;
        stack.getTag().putInt("range", calculateRange(ModConfig.MODULE_VANILLA.defaultBaseRange, ModConfig.MODULE_VANILLA.defaultMultiplierRange, tier));
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
//        int baseDurability = ModConfig.MODULE_VANILLA.baseDurability.get();
//        return baseDurability + (baseDurability * ModConfig.MODULE_VANILLA.multiplierDurability.get() * tier);
        int baseDurability = ModConfig.MODULE_VANILLA.defaultBaseDurability;
        return baseDurability + (baseDurability * ModConfig.MODULE_VANILLA.defaultMultiplierDurability * tier);
    }

    @Override
    public int getDefaultRange() {
        return calculateRange(ModConfig.MODULE_VANILLA.baseRange.get(), ModConfig.MODULE_VANILLA.multiplierRange.get(), tier);
    }

    @Override
    public double getSpeed() {
        return ModConfig.MODULE_VANILLA.speed.get();
    }

    @Override
    public Particles getParticle() {
        return Particles.VANILLA;
    }

}
