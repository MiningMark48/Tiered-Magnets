package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.item.*;
import net.minecraft.item.Item;

public class ModItems {

    //Durability TieredMagnets
    public static Item ItemMagnetDurabilityStone;
    public static Item ItemMagnetDurabilityIron;
    public static Item ItemMagnetDurabilityRedstone;
    public static Item ItemMagnetDurabilityGold;
    public static Item ItemMagnetDurabilityObsidian;
    public static Item ItemMagnetDurabilityLapis;
    public static Item ItemMagnetDurabilityDiamond;
    public static Item ItemMagnetDurabilityEmerald;
    public static Item ItemMagnetMagicDurabilityStone;
    public static Item ItemMagnetMagicDurabilityIron;
    public static Item ItemMagnetMagicDurabilityRedstone;
    public static Item ItemMagnetMagicDurabilityGold;
    public static Item ItemMagnetMagicDurabilityObsidian;
    public static Item ItemMagnetMagicDurabilityLapis;
    public static Item ItemMagnetMagicDurabilityDiamond;
    public static Item ItemMagnetMagicDurabilityEmerald;

    //Electromagnets
    public static Item ItemMagnetElectromagnetLeadstone;
    public static Item ItemMagnetElectromagnetHardened;
    public static Item ItemMagnetElectromagnetReinforced;
    public static Item ItemMagnetElectromagnetSignalum;
    public static Item ItemMagnetElectromagnetResonant;
    public static Item ItemMagnetMagicElectromagnetLeadstone;
    public static Item ItemMagnetMagicElectromagnetHardened;
    public static Item ItemMagnetMagicElectromagnetReinforced;
    public static Item ItemMagnetMagicElectromagnetSignalum;
    public static Item ItemMagnetMagicElectromagnetResonant;

    public static void init() {
        //Durability TieredMagnets
        ItemMagnetDurabilityStone = new ItemMagnetDurability(0, false).setUnlocalizedName("magnet_durability_stone").setRegistryName("magnet_durability_stone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityIron = new ItemMagnetDurability(1, false).setUnlocalizedName("magnet_durability_iron").setRegistryName("magnet_durability_iron").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityRedstone = new ItemMagnetDurability(2, false).setUnlocalizedName("magnet_durability_redstone").setRegistryName("magnet_durability_redstone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityGold = new ItemMagnetDurability(3, false).setUnlocalizedName("magnet_durability_gold").setRegistryName("magnet_durability_gold").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityObsidian = new ItemMagnetDurability(4, false).setUnlocalizedName("magnet_durability_obsidian").setRegistryName("magnet_durability_obsidian").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityLapis = new ItemMagnetDurability(5, false).setUnlocalizedName("magnet_durability_lapis").setRegistryName("magnet_durability_lapis").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityDiamond = new ItemMagnetDurability(6, false).setUnlocalizedName("magnet_durability_diamond").setRegistryName("magnet_durability_diamond").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetDurabilityEmerald = new ItemMagnetDurability(7, false).setUnlocalizedName("magnet_durability_emerald").setRegistryName("magnet_durability_emerald").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicDurabilityStone = new ItemMagnetDurability(0, true).setUnlocalizedName("magnet_magic_durability_stone").setRegistryName("magnet_magic_durability_stone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicDurabilityIron = new ItemMagnetDurability(1, true).setUnlocalizedName("magnet_magic_durability_iron").setRegistryName("magnet_magic_durability_iron").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicDurabilityRedstone = new ItemMagnetDurability(2, true).setUnlocalizedName("magnet_magic_durability_redstone").setRegistryName("magnet_magic_durability_redstone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicDurabilityGold = new ItemMagnetDurability(3, true).setUnlocalizedName("magnet_magic_durability_gold").setRegistryName("magnet_magic_durability_gold").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicDurabilityObsidian = new ItemMagnetDurability(4, true).setUnlocalizedName("magnet_magic_durability_obsidian").setRegistryName("magnet_magic_durability_obsidian").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicDurabilityLapis = new ItemMagnetDurability(5, true).setUnlocalizedName("magnet_magic_durability_lapis").setRegistryName("magnet_magic_durability_lapis").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicDurabilityDiamond = new ItemMagnetDurability(6, true).setUnlocalizedName("magnet_magic_durability_diamond").setRegistryName("magnet_magic_durability_diamond").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicDurabilityEmerald = new ItemMagnetDurability(7, true).setUnlocalizedName("magnet_magic_durability_emerald").setRegistryName("magnet_magic_durability_emerald").setCreativeTab(ModCreativeTab.Magnets_Tab);

        //Electromagnets
        ItemMagnetElectromagnetLeadstone = new ItemMagnetRF(0, false).setUnlocalizedName("magnet_energy_leadstone").setRegistryName("magnet_energy_leadstone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetElectromagnetHardened = new ItemMagnetRF(1, false).setUnlocalizedName("magnet_energy_hardened").setRegistryName("magnet_energy_hardened").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetElectromagnetReinforced = new ItemMagnetRF(2, false).setUnlocalizedName("magnet_energy_reinforced").setRegistryName("magnet_energy_reinforced").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetElectromagnetSignalum = new ItemMagnetRF(3, false).setUnlocalizedName("magnet_energy_signalum").setRegistryName("magnet_energy_signalum").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetElectromagnetResonant = new ItemMagnetRF(4, false).setUnlocalizedName("magnet_energy_resonant").setRegistryName("magnet_energy_resonant").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicElectromagnetLeadstone = new ItemMagnetRF(0, true).setUnlocalizedName("magnet_magic_energy_leadstone").setRegistryName("magnet_magic_energy_leadstone").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicElectromagnetHardened = new ItemMagnetRF(1, true).setUnlocalizedName("magnet_magic_energy_hardened").setRegistryName("magnet_magic_energy_hardened").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicElectromagnetReinforced = new ItemMagnetRF(2, true).setUnlocalizedName("magnet_magic_energy_reinforced").setRegistryName("magnet_magic_energy_reinforced").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicElectromagnetSignalum = new ItemMagnetRF(3, true).setUnlocalizedName("magnet_magic_energy_signalum").setRegistryName("magnet_magic_energy_signalum").setCreativeTab(ModCreativeTab.Magnets_Tab);
        ItemMagnetMagicElectromagnetResonant = new ItemMagnetRF(4, true).setUnlocalizedName("magnet_magic_energy_resonant").setRegistryName("magnet_magic_energy_resonant").setCreativeTab(ModCreativeTab.Magnets_Tab);

    }

}
