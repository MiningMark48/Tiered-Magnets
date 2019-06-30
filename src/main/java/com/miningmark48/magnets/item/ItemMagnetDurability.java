package com.miningmark48.magnets.item;

import com.miningmark48.magnets.init.ModConfig;
import com.miningmark48.magnets.item.base.ItemMagnetBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemMagnetDurability extends ItemMagnetBase {

    public ItemMagnetDurability(int tier) {
        super(ModConfig.vanillaConfigs.baseRange + (ModConfig.vanillaConfigs.baseRange * ModConfig.vanillaConfigs.multiplierRange * tier), ModConfig.vanillaConfigs.speed);
        setMaxDamage((int) Math.round(ModConfig.vanillaConfigs.baseDurability + (ModConfig.vanillaConfigs.baseDurability * ModConfig.vanillaConfigs.multiplierDurability * tier)));
    }

    @Override
    public void doCost(EntityPlayer player, ItemStack stack) {
        stack.damageItem(1, player);
    }

}
