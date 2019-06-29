package com.miningmark48.magnets.init;

import com.miningmark48.magnets.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ModCreativeTab {

    public static final CreativeTabs Magnets_Tab = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.ItemMagnetDurabilityStone);
        }
    };

}
