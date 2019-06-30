package com.miningmark48.magnets.init;

import com.miningmark48.magnets.item.*;
import net.minecraft.item.Item;

public class ModItems {

    //Durability Magnets
    public static Item ItemMagnetDurabilityStone;
    public static Item ItemMagnetDurabilityIron;
    public static Item ItemMagnetDurabilityRedstone;
    public static Item ItemMagnetDurabilityGold;
    public static Item ItemMagnetDurabilityLapis;
    public static Item ItemMagnetDurabilityDiamond;
    public static Item ItemMagnetDurabilityEmerald;

    //Electromagnets
    public static Item ItemMagnetElectromagnetLeadstone;
    public static Item ItemMagnetElectromagnetHardened;
    public static Item ItemMagnetElectromagnetReinforced;

    public static void init() {
        //Durability Magnets
        ItemMagnetDurabilityStone = new ItemMagnetDurability(0).setUnlocalizedName("magnet_durability_stone").setRegistryName("magnet_durability_stone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityIron = new ItemMagnetDurability(1).setUnlocalizedName("magnet_durability_iron").setRegistryName("magnet_durability_iron").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityRedstone = new ItemMagnetDurability(2).setUnlocalizedName("magnet_durability_redstone").setRegistryName("magnet_durability_redstone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityGold = new ItemMagnetDurability(3).setUnlocalizedName("magnet_durability_gold").setRegistryName("magnet_durability_gold").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityLapis = new ItemMagnetDurability(4).setUnlocalizedName("magnet_durability_lapis").setRegistryName("magnet_durability_lapis").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityDiamond = new ItemMagnetDurability(5).setUnlocalizedName("magnet_durability_diamond").setRegistryName("magnet_durability_diamond").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityEmerald = new ItemMagnetDurability(6).setUnlocalizedName("magnet_durability_emerald").setRegistryName("magnet_durability_emerald").setCreativeTab(ModCreativeTab.Magnets_Tab);

        //Electromagnets
        ItemMagnetElectromagnetLeadstone = new ItemMagnetRF(0).setUnlocalizedName("magnet_energy_leadstone").setRegistryName("magnet_energy_leadstone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetElectromagnetHardened = new ItemMagnetRF(1).setUnlocalizedName("magnet_energy_hardened").setRegistryName("magnet_energy_hardened").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetElectromagnetReinforced = new ItemMagnetRF(2).setUnlocalizedName("magnet_energy_reinforced").setRegistryName("magnet_energy_reinforced").setCreativeTab(ModCreativeTab.Magnets_Tab);

    }

}
