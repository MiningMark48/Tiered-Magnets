package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.client.particle.ParticleMagnetize.Particles;
import com.miningmark48.tieredmagnets.init.ModConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
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
    }

    @Override
    public Particles getParticle() {
        return Particles.FREE;
    }

}
