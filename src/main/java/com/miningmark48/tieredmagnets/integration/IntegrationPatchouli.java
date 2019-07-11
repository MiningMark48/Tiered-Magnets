package com.miningmark48.tieredmagnets.integration;

import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.util.ModLogger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class IntegrationPatchouli {

    public static ItemStack magnet_manual;

    public static void init() {
        ModLogger.info("Integration - Getting Patchouli items...");

        Item manual = Item.REGISTRY.getObject(new ResourceLocation(Reference.PATCHOULI, "guide_book"));
        if (manual != null) {
            magnet_manual = new ItemStack(manual);
            if (!magnet_manual.hasTagCompound()){
                magnet_manual.setTagCompound(new NBTTagCompound());
                assert magnet_manual.getTagCompound() != null;
                magnet_manual.getTagCompound().setString("patchouli:book", "tieredmagnets:magnets_book");
            }
        }

        ModLogger.info("Integration - Got Patchouli items. Continuing...");

    }

}
