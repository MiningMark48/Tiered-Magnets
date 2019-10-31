package com.miningmark48.tieredmagnets.item.base;

import com.miningmark48.tieredmagnets.item.ItemMagnetDurability;
import com.miningmark48.tieredmagnets.item.ItemMagnetFree;
import com.miningmark48.tieredmagnets.item.ItemMagnetRF;

public enum EnumMagnetTiers {

    T1_D_REG("magnet_durability_stone", 0, false),
    T2_D_REG("magnet_durability_iron", 1, false),
    T3_D_REG("magnet_durability_redstone", 2, false),
    T4_D_REG("magnet_durability_gold", 3, false),
    T5_D_REG("magnet_durability_obsidian", 4, false),
    T6_D_REG("magnet_durability_lapis", 5, false),
    T7_D_REG("magnet_durability_diamond", 6, false),
    T8_D_REG("magnet_durability_emerald", 7, false),
    T1_D_MAGIC("magnet_magic_durability_stone", 0, true),
    T2_D_MAGIC("magnet_magic_durability_iron", 1, true),
    T3_D_MAGIC("magnet_magic_durability_redstone", 2, true),
    T4_D_MAGIC("magnet_magic_durability_gold", 3, true),
    T5_D_MAGIC("magnet_magic_durability_obsidian", 4, true),
    T6_D_MAGIC("magnet_magic_durability_lapis", 5, true),
    T7_D_MAGIC("magnet_magic_durability_diamond", 6, true),
    T8_D_MAGIC("magnet_magic_durability_emerald", 7, true),
    T1_E_REG("magnet_energy_leadstone", 0, false),
    T2_E_REG("magnet_energy_hardened", 1, false),
    T3_E_REG("magnet_energy_reinforced", 2, false),
    T4_E_REG("magnet_energy_signalum", 3, false),
    T5_E_REG("magnet_energy_resonant", 4, false),
    T1_E_MAGIC("magnet_magic_energy_leadstone", 0, true),
    T2_E_MAGIC("magnet_magic_energy_hardened", 1, true),
    T3_E_MAGIC("magnet_magic_energy_reinforced", 2, true),
    T4_E_MAGIC("magnet_magic_energy_signalum", 3, true),
    T5_E_MAGIC("magnet_magic_energy_resonant", 4, true),
    F_REG("magnet_free", 0, false),
    F_MAGIC("magnet_magic_free", 0, true);

    private final int tier;
    private final boolean isMagic;
    private final String name;

    EnumMagnetTiers(String name, int tier, boolean isMagic) {
        this.name = name;
        this.tier = tier;
        this.isMagic = isMagic;
    }

    public String getName() {
        return name;
    }

    public ItemMagnetDurability createDurability() {
        return new ItemMagnetDurability(tier, isMagic);
    }

    public ItemMagnetRF createEnergy() {
        return new ItemMagnetRF(tier, isMagic);
    }

    public ItemMagnetFree createFree() {
        return new ItemMagnetFree(isMagic);
    }

}
