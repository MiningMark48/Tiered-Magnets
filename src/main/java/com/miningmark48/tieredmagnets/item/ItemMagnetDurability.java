package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.init.ModConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ItemMagnetDurability extends ItemMagnetBase {

    private final boolean isMagic;

    public ItemMagnetDurability(int tier, boolean isMagic) {
        super(ModConfig.vanillaConfigs.baseRange + (ModConfig.vanillaConfigs.baseRange * ModConfig.vanillaConfigs.multiplierRange * tier), ModConfig.vanillaConfigs.speed, isMagic);
        setMaxDamage((int) Math.round(ModConfig.vanillaConfigs.baseDurability + (ModConfig.vanillaConfigs.baseDurability * ModConfig.vanillaConfigs.multiplierDurability * tier)));

        this.isMagic = isMagic;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> list, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, list, advanced);
        list.add(TextFormatting.LIGHT_PURPLE + ModTranslate.toLocal("tooltip.item.magnet_base.durability") + TextFormatting.AQUA + " " + (stack.getMaxDamage() - stack.getItemDamage()));
    }

    @Override
    public void doCost(ItemStack stack) {
        int damageAmount = 1;
        if (isMagic) damageAmount *= ModConfig.vanillaConfigs.multiplierMagic;
        if (stack.attemptDamageItem(damageAmount, new Random(), null)) {
            stack.shrink(1);
        }
    }

    @Override
    public int getParticle() {
        return 0;
    }

}
