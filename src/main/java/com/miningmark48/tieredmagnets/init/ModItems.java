package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.item.ItemMagnetite;
import com.miningmark48.tieredmagnets.item.base.EnumMagnetTiers;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {

    public static final Item.Properties ITEM_GROUP = new Item.Properties().group(ModCreativeTab.Magnets_Tab);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);

    //Items
        //Magnets - Durability
    public static final RegistryObject<Item> MAGNET_DURABILITY_STONE = ITEMS.register("magnet_durability_stone", EnumMagnetTiers.T1_D_REG::createDurability);
    public static final RegistryObject<Item> MAGNET_DURABILITY_IRON = ITEMS.register("magnet_durability_iron", EnumMagnetTiers.T2_D_REG::createDurability);
    public static final RegistryObject<Item> MAGNET_DURABILITY_REDSTONE = ITEMS.register("magnet_durability_redstone", EnumMagnetTiers.T3_D_REG::createDurability);
    public static final RegistryObject<Item> MAGNET_DURABILITY_GOLD = ITEMS.register("magnet_durability_gold", EnumMagnetTiers.T4_D_REG::createDurability);
    public static final RegistryObject<Item> MAGNET_DURABILITY_OBSIDIAN = ITEMS.register("magnet_durability_obsidian", EnumMagnetTiers.T5_D_REG::createDurability);
    public static final RegistryObject<Item> MAGNET_DURABILITY_LAPIS = ITEMS.register("magnet_durability_lapis", EnumMagnetTiers.T6_D_REG::createDurability);
    public static final RegistryObject<Item> MAGNET_DURABILITY_DIAMOND = ITEMS.register("magnet_durability_diamond", EnumMagnetTiers.T7_D_REG::createDurability);
    public static final RegistryObject<Item> MAGNET_DURABILITY_EMERALD = ITEMS.register("magnet_durability_emerald", EnumMagnetTiers.T8_D_REG::createDurability);
    public static final RegistryObject<Item> MAGNET_MAGIC_DURABILITY_STONE = ITEMS.register("magnet_magic_durability_stone", EnumMagnetTiers.T1_D_MAGIC::createDurability);
    public static final RegistryObject<Item> MAGNET_MAGIC_DURABILITY_IRON = ITEMS.register("magnet_magic_durability_iron", EnumMagnetTiers.T2_D_MAGIC::createDurability);
    public static final RegistryObject<Item> MAGNET_MAGIC_DURABILITY_REDSTONE = ITEMS.register("magnet_magic_durability_redstone", EnumMagnetTiers.T3_D_MAGIC::createDurability);
    public static final RegistryObject<Item> MAGNET_MAGIC_DURABILITY_GOLD = ITEMS.register("magnet_magic_durability_gold", EnumMagnetTiers.T4_D_MAGIC::createDurability);
    public static final RegistryObject<Item> MAGNET_MAGIC_DURABILITY_OBSIDIAN = ITEMS.register("magnet_magic_durability_obsidian", EnumMagnetTiers.T5_D_MAGIC::createDurability);
    public static final RegistryObject<Item> MAGNET_MAGIC_DURABILITY_LAPIS = ITEMS.register("magnet_magic_durability_lapis", EnumMagnetTiers.T6_D_MAGIC::createDurability);
    public static final RegistryObject<Item> MAGNET_MAGIC_DURABILITY_DIAMOND = ITEMS.register("magnet_magic_durability_diamond", EnumMagnetTiers.T7_D_MAGIC::createDurability);
    public static final RegistryObject<Item> MAGNET_MAGIC_DURABILITY_EMERALD = ITEMS.register("magnet_magic_durability_emerald", EnumMagnetTiers.T8_D_MAGIC::createDurability);
        //Magnets - Energy
    public static final RegistryObject<Item> MAGNET_ENERGY_LEADSTONE = ITEMS.register("magnet_energy_leadstone", EnumMagnetTiers.T1_E_REG::createEnergy);
    public static final RegistryObject<Item> MAGNET_ENERGY_HARDENED = ITEMS.register("magnet_energy_hardened", EnumMagnetTiers.T2_E_REG::createEnergy);
    public static final RegistryObject<Item> MAGNET_ENERGY_REINFORCED = ITEMS.register("magnet_energy_reinforced", EnumMagnetTiers.T3_E_REG::createEnergy);
    public static final RegistryObject<Item> MAGNET_ENERGY_SIGNALUM = ITEMS.register("magnet_energy_signalum", EnumMagnetTiers.T4_E_REG::createEnergy);
    public static final RegistryObject<Item> MAGNET_ENERGY_RESONANT = ITEMS.register("magnet_energy_resonant", EnumMagnetTiers.T5_E_REG::createEnergy);
    public static final RegistryObject<Item> MAGNET_MAGIC_ENERGY_LEADSTONE = ITEMS.register("magnet_magic_energy_leadstone", EnumMagnetTiers.T1_E_MAGIC::createEnergy);
    public static final RegistryObject<Item> MAGNET_MAGIC_ENERGY_HARDENED = ITEMS.register("magnet_magic_energy_hardened", EnumMagnetTiers.T2_E_MAGIC::createEnergy);
    public static final RegistryObject<Item> MAGNET_MAGIC_ENERGY_REINFORCED = ITEMS.register("magnet_magic_energy_reinforced", EnumMagnetTiers.T3_E_MAGIC::createEnergy);
    public static final RegistryObject<Item> MAGNET_MAGIC_ENERGY_SIGNALUM = ITEMS.register("magnet_magic_energy_signalum", EnumMagnetTiers.T4_E_MAGIC::createEnergy);
    public static final RegistryObject<Item> MAGNET_MAGIC_ENERGY_RESONANT = ITEMS.register("magnet_magic_energy_resonant", EnumMagnetTiers.T5_E_MAGIC::createEnergy);
        //Magnets - Free
    public static final RegistryObject<Item> MAGNET_FREE = ITEMS.register("magnet_free", EnumMagnetTiers.F_REG::createFree);
    public static final RegistryObject<Item> MAGNET_MAGIC_FREE = ITEMS.register("magnet_magic_free", EnumMagnetTiers.F_MAGIC::createFree);
        //Regular Items
    public static final RegistryObject<Item> MAGNETITE = ITEMS.register("magnetite", ItemMagnetite::new);

    //Block Items
    public static final RegistryObject<Item> MAGNETIC_INSULATOR = ITEMS.register("magnetic_insulator", () -> new BlockItem(ModBlocks.MAGNETIC_INSULATOR.get(), ITEM_GROUP));
    public static final RegistryObject<Item> MAGNETIC_PROJECTOR = ITEMS.register("magnetic_projector", () -> new BlockItem(ModBlocks.MAGNETIC_PROJECTOR.get(), ITEM_GROUP));
}
