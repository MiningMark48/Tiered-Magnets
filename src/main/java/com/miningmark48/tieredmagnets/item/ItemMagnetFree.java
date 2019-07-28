package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.client.particle.base.ParticleMagnetize.Particles;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagnetFree extends ItemMagnetBase {

    public ItemMagnetFree(Properties properties, boolean isMagic) {
        super(properties, ModConfig.cursedMagnetsConfigs.range, ModConfig.cursedMagnetsConfigs.speed, isMagic);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<ITextComponent> list, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, list, advanced);
        if (KeyChecker.isHoldingShift()) {
            list.add(new StringTextComponent(TextFormatting.GOLD + ModTranslate.toLocal("tooltip.item.magnet_base.free")));
        }

        if (ModConfig.cursedMagnetsConfigs.vanishing && !EnchantmentHelper.hasVanishingCurse(stack)) list.add(new StringTextComponent(TextFormatting.RED + ModTranslate.toLocal("enchantment.vanishing_curse")));
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity entity, int count) {
        if (ModConfig.cursedMagnetsConfigs.vanishing && !EnchantmentHelper.hasVanishingCurse(stack)) stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);
        super.onUsingTick(stack, entity, count);
    }

    @Override
    public Particles getParticle() {
        return Particles.FREE;
    }

}
