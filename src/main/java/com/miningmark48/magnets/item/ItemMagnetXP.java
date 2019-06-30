package com.miningmark48.magnets.item;

import com.miningmark48.magnets.item.base.ItemMagnetBase;
import com.miningmark48.mininglib.utility.ModLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemMagnetXP extends ItemMagnetBase {

    public ItemMagnetXP() {
        super(16, 0.05f);
    }

    @Override
    public boolean canMagnet(EntityPlayer player) {
        ModLogger.info(player.experienceTotal);
        return player.experienceTotal > 1;
    }

    @Override
    public void doCost(EntityPlayer player, ItemStack stack) {
        player.addExperience(-1);
    }
}
