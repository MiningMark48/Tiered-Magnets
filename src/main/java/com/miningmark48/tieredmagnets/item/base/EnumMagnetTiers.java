package com.miningmark48.tieredmagnets.item.base;

import com.miningmark48.tieredmagnets.init.ModItems.ItemReference;
import com.miningmark48.tieredmagnets.item.ItemMagnetDurability;
import com.miningmark48.tieredmagnets.item.ItemMagnetFree;
import com.miningmark48.tieredmagnets.item.ItemMagnetRF;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public enum EnumMagnetTiers {

    T1_D_REG(ItemReference.MAGNET_DURABILITY_STONE_RL, 0, false),
    T2_D_REG(ItemReference.MAGNET_DURABILITY_IRON_RL, 1, false),
    T3_D_REG(ItemReference.MAGNET_DURABILITY_REDSTONE_RL, 2, false),
    T4_D_REG(ItemReference.MAGNET_DURABILITY_GOLD_RL, 3, false),
    T5_D_REG(ItemReference.MAGNET_DURABILITY_OBSIDIAN_RL, 4, false),
    T6_D_REG(ItemReference.MAGNET_DURABILITY_LAPIS_RL, 5, false),
    T7_D_REG(ItemReference.MAGNET_DURABILITY_DIAMOND_RL, 6, false),
    T8_D_REG(ItemReference.MAGNET_DURABILITY_EMERALD_RL, 7, false),
    T1_D_MAGIC(ItemReference.MAGNET_DURABILITY_MAGIC_STONE_RL, 0, true),
    T2_D_MAGIC(ItemReference.MAGNET_DURABILITY_MAGIC_IRON_RL, 1, true),
    T3_D_MAGIC(ItemReference.MAGNET_DURABILITY_MAGIC_REDSTONE_RL, 2, true),
    T4_D_MAGIC(ItemReference.MAGNET_DURABILITY_MAGIC_GOLD_RL, 3, true),
    T5_D_MAGIC(ItemReference.MAGNET_DURABILITY_MAGIC_OBSIDIAN_RL, 4, true),
    T6_D_MAGIC(ItemReference.MAGNET_DURABILITY_MAGIC_LAPIS_RL, 5, true),
    T7_D_MAGIC(ItemReference.MAGNET_DURABILITY_MAGIC_DIAMOND_RL, 6, true),
    T8_D_MAGIC(ItemReference.MAGNET_DURABILITY_MAGIC_EMERALD_RL, 7, true),
    T1_E_REG(ItemReference.MAGNET_ENERGY_LEADSTONE_RL, 0, false),
    T2_E_REG(ItemReference.MAGNET_ENERGY_HARDENED_RL, 1, false),
    T3_E_REG(ItemReference.MAGNET_ENERGY_REINFORCED_RL, 2, false),
    T4_E_REG(ItemReference.MAGNET_ENERGY_SIGNALUM_RL, 3, false),
    T5_E_REG(ItemReference.MAGNET_ENERGY_RESONANT_RL, 4, false),
    T1_E_MAGIC(ItemReference.MAGNET_ENERGY_MAGIC_LEADSTONE_RL, 0, true),
    T2_E_MAGIC(ItemReference.MAGNET_ENERGY_MAGIC_HARDENED_RL, 1, true),
    T3_E_MAGIC(ItemReference.MAGNET_ENERGY_MAGIC_REINFORCED_RL, 2, true),
    T4_E_MAGIC(ItemReference.MAGNET_ENERGY_MAGIC_SIGNALUM_RL, 3, true),
    T5_E_MAGIC(ItemReference.MAGNET_ENERGY_MAGIC_RESONANT_RL, 4, true),
    F_REG(ItemReference.MAGNET_FREE_RL, 0, false),
    F_MAGIC(ItemReference.MAGNET_MAGIC_FREE_RL, 0, true);

    private final int tier;
    private final boolean isMagic;
    private final ResourceLocation registryName;

    EnumMagnetTiers(ResourceLocation loc, int tier, boolean isMagic) {
        this.registryName = loc;
        this.tier = tier;
        this.isMagic = isMagic;
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }

    public ItemMagnetDurability createDurability(Item.Properties builder) {
        return new ItemMagnetDurability(builder, tier, isMagic);
    }

    public ItemMagnetRF createEnergy(Item.Properties builder) {
        return new ItemMagnetRF(builder, tier, isMagic);
    }

    public ItemMagnetFree createFree(Item.Properties builder) {
        return new ItemMagnetFree(builder, isMagic);
    }

}
