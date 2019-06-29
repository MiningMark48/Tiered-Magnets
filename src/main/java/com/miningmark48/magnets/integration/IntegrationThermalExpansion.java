package com.miningmark48.magnets.integration;

import com.miningmark48.magnets.reference.Reference;
import com.miningmark48.mininglib.utility.ModLogger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class IntegrationThermalExpansion {

    public static ItemStack fluxCapacitorBasic;
    public static ItemStack fluxCapacitorHardened;
    public static ItemStack fluxCapacitorReinforced;
    public static ItemStack fluxCapacitorSignalum;
    public static ItemStack fluxCapacitorResonant;
    public static ItemStack redstoneReceptionCoil;

    public static void init() {
        ModLogger.info("Integration - Getting Thermal Expansion items...");

        Item fluxCapacitorItem = Item.REGISTRY.getObject(new ResourceLocation(Reference.TE, "capacitor"));
        if (fluxCapacitorItem != null) {
            fluxCapacitorBasic = new ItemStack(fluxCapacitorItem, 1, 0);
            fluxCapacitorHardened = new ItemStack(fluxCapacitorItem, 1, 1);
            fluxCapacitorReinforced = new ItemStack(fluxCapacitorItem, 1, 2);
            fluxCapacitorSignalum = new ItemStack(fluxCapacitorItem, 1, 3);
            fluxCapacitorResonant = new ItemStack(fluxCapacitorItem, 1, 4);
        }

        Item teMaterialsItem = Item.REGISTRY.getObject(new ResourceLocation(Reference.TF, "material"));
        if (teMaterialsItem != null) {
            redstoneReceptionCoil = new ItemStack(teMaterialsItem, 1, 513);
        }

        ModLogger.info("Integration - Got Thermal Expansion items. Continuing...");

    }

}
