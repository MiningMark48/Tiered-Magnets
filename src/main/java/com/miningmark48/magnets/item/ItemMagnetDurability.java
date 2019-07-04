package com.miningmark48.magnets.item;

import com.miningmark48.magnets.init.ModConfig;
import com.miningmark48.magnets.item.base.ItemMagnetBase;
import com.miningmark48.magnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

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
    public void doCost(EntityPlayer player, ItemStack stack) {
        int damageAmount = 1;
        if (isMagic) damageAmount *= ModConfig.vanillaConfigs.multiplierMagic;
        stack.damageItem(damageAmount, player);
    }

    @Override
    public EnumParticleTypes getParticle() {
        return EnumParticleTypes.CRIT;
    }

}
