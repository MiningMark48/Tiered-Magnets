package com.miningmark48.magnets.item;

import com.miningmark48.magnets.handler.ConfigurationHandler;
import com.miningmark48.magnets.item.base.ItemMagnetBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemMagnetDurability extends ItemMagnetBase {

    public ItemMagnetDurability(int tier) {
        super(ConfigurationHandler.baseRange + (ConfigurationHandler.baseRange * ConfigurationHandler.rangeMultiplier * tier), ConfigurationHandler.speed);
        setMaxDamage(Math.round(ConfigurationHandler.baseDurability + (ConfigurationHandler.baseDurability * ConfigurationHandler.durabilityMultiplier * tier)));
    }

    @Override
    public void doCost(EntityPlayer player, ItemStack stack) {
        stack.damageItem(1, player);
    }

}
