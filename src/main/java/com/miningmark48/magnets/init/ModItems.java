package com.miningmark48.magnets.init;

import com.miningmark48.magnets.item.*;
import net.minecraft.item.Item;

public class ModItems {

    //Durability Magnets
    public static Item ItemMagnetDurabilityStone;
    public static Item ItemMagnetDurabilityIron;
    public static Item ItemMagnetDurabilityRedstone;
    public static Item ItemMagnetDurabilityGold;

    public static void init() {
        //Durability Magnets
        ItemMagnetDurabilityStone = new ItemMagnetDurability(0).setUnlocalizedName("magnet_durability_stone").setRegistryName("magnet_durability_stone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityIron = new ItemMagnetDurability(1).setUnlocalizedName("magnet_durability_iron").setRegistryName("magnet_durability_iron").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityRedstone = new ItemMagnetDurability(2).setUnlocalizedName("magnet_durability_redstone").setRegistryName("magnet_durability_redstone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityGold = new ItemMagnetDurability(3).setUnlocalizedName("magnet_durability_gold").setRegistryName("magnet_durability_gold").setCreativeTab(ModCreativeTab.Magnets_Tab);
    }

}
