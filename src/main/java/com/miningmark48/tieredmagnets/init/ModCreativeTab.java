package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModCreativeTab {

    public static final ItemGroup Magnets_Tab = new ItemGroup(Reference.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.MAGNET_DURABILITY_DIAMOND.get());
        }
    };

}
