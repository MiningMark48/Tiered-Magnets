package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTab {

    public static final CreativeTabs Magnets_Tab = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.ItemMagnetDurabilityStone);
        }
    };

}
