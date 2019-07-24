package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.client.particle.ParticleMagnetize.Particles;
import com.miningmark48.tieredmagnets.init.ModConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModLogger;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagnetFree extends ItemMagnetBase {

    public ItemMagnetFree(boolean isMagic) {
        super(ModConfig.cursedMagnetsConfigs.range, ModConfig.cursedMagnetsConfigs.speed, isMagic);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> list, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, list, advanced);
        if (KeyChecker.isHoldingShift()) {
            list.add(TextFormatting.GOLD + ModTranslate.toLocal("tooltip.item.magnet_base.free"));
        }

        if (ModConfig.cursedMagnetsConfigs.vanishing && !EnchantmentHelper.hasVanishingCurse(stack)) list.add(TextFormatting.RED + ModTranslate.toLocal("enchantment.vanishing_curse"));
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (ModConfig.cursedMagnetsConfigs.vanishing && !EnchantmentHelper.hasVanishingCurse(stack)) stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);
        super.onUpdate(stack, world, entity, itemSlot, isSelected);
    }

    @Override
    public Particles getParticle() {
        return Particles.FREE;
    }

}
