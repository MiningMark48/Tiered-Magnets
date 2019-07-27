package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.init.registry.RegistryObjectBuilder;
import com.miningmark48.tieredmagnets.init.registry.container.RegistryContainer;
import com.miningmark48.tieredmagnets.item.*;
import com.miningmark48.tieredmagnets.item.base.EnumMagnetTiers;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Function;

@ObjectHolder(Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    private ModItems() {

    }

    private static final RegistryContainer<Item, RegistryObjectBuilder<Item, Item.Properties>> container = new RegistryContainer<>();

    //Durability Magnets
    @ObjectHolder(ItemReference.MAGNET_DURABILITY_STONE)
    public static ItemMagnetDurability ItemMagnetDurabilityStone;
    @ObjectHolder(ItemReference.MAGNET_DURABILITY_IRON)
    public static ItemMagnetDurability ItemMagnetDurabilityIron;
    @ObjectHolder(ItemReference.MAGNET_DURABILITY_REDSTONE)
    public static ItemMagnetDurability ItemMagnetDurabilityRedstone;
    @ObjectHolder(ItemReference.MAGNET_DURABILITY_GOLD)
    public static ItemMagnetDurability ItemMagnetDurabilityGold;
    @ObjectHolder(ItemReference.MAGNET_DURABILITY_OBSIDIAN)
    public static ItemMagnetDurability ItemMagnetDurabilityObsidian;
    @ObjectHolder(ItemReference.MAGNET_DURABILITY_LAPIS)
    public static ItemMagnetDurability ItemMagnetDurabilityLapis;
    @ObjectHolder(ItemReference.MAGNET_DURABILITY_DIAMOND)
    public static ItemMagnetDurability ItemMagnetDurabilityDiamond;
    @ObjectHolder(ItemReference.MAGNET_DURABILITY_EMERALD)
    public static ItemMagnetDurability ItemMagnetDurabilityEmerald;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_DURABILITY_STONE)
    public static ItemMagnetDurability ItemMagnetMagicDurabilityStone;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_DURABILITY_IRON)
    public static ItemMagnetDurability ItemMagnetMagicDurabilityIron;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_DURABILITY_REDSTONE)
    public static ItemMagnetDurability ItemMagnetMagicDurabilityRedstone;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_DURABILITY_GOLD)
    public static ItemMagnetDurability ItemMagnetMagicDurabilityGold;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_DURABILITY_OBSIDIAN)
    public static ItemMagnetDurability ItemMagnetMagicDurabilityObsidian;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_DURABILITY_LAPIS)
    public static ItemMagnetDurability ItemMagnetMagicDurabilityLapis;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_DURABILITY_DIAMOND)
    public static ItemMagnetDurability ItemMagnetMagicDurabilityDiamond;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_DURABILITY_EMERALD)
    public static ItemMagnetDurability ItemMagnetMagicDurabilityEmerald;

    //Electromagnets
    @ObjectHolder(ItemReference.MAGNET_ENERGY_LEADSTONE)
    public static ItemMagnetRF ItemMagnetElectromagnetLeadstone;
    @ObjectHolder(ItemReference.MAGNET_ENERGY_HARDENED)
    public static ItemMagnetRF ItemMagnetElectromagnetHardened;
    @ObjectHolder(ItemReference.MAGNET_ENERGY_REINFORCED)
    public static ItemMagnetRF ItemMagnetElectromagnetReinforced;
    @ObjectHolder(ItemReference.MAGNET_ENERGY_SIGNALUM)
    public static ItemMagnetRF ItemMagnetElectromagnetSignalum;
    @ObjectHolder(ItemReference.MAGNET_ENERGY_RESONANT)
    public static ItemMagnetRF ItemMagnetElectromagnetResonant;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_ENERGY_LEADSTONE)
    public static ItemMagnetRF ItemMagnetMagicElectromagnetLeadstone;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_ENERGY_HARDENED)
    public static ItemMagnetRF ItemMagnetMagicElectromagnetHardened;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_ENERGY_REINFORCED)
    public static ItemMagnetRF ItemMagnetMagicElectromagnetReinforced;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_ENERGY_SIGNALUM)
    public static ItemMagnetRF ItemMagnetMagicElectromagnetSignalum;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_ENERGY_RESONANT)
    public static ItemMagnetRF ItemMagnetMagicElectromagnetResonant;


    //Free Magnets
    @ObjectHolder(ItemReference.MAGNET_FREE)
    public static ItemMagnetFree ItemMagnetFree;
    @ObjectHolder(ItemReference.MAGNET_MAGIC_FREE)
    public static ItemMagnetFree ItemMagnetMagicFree;

    public static void init() {
        //Durability Magnets
        addItemBuilder(ItemReference.MAGNET_DURABILITY_STONE_RL, unstackableItemProperties(), EnumMagnetTiers.T1_D_REG::createDurability);
        addItemBuilder(ItemReference.MAGNET_DURABILITY_IRON_RL, unstackableItemProperties(), EnumMagnetTiers.T2_D_REG::createDurability);
        addItemBuilder(ItemReference.MAGNET_DURABILITY_REDSTONE_RL, unstackableItemProperties(), EnumMagnetTiers.T3_D_REG::createDurability);
        addItemBuilder(ItemReference.MAGNET_DURABILITY_GOLD_RL, unstackableItemProperties(), EnumMagnetTiers.T4_D_REG::createDurability);
        addItemBuilder(ItemReference.MAGNET_DURABILITY_OBSIDIAN_RL, unstackableItemProperties(), EnumMagnetTiers.T5_D_REG::createDurability);
        addItemBuilder(ItemReference.MAGNET_DURABILITY_LAPIS_RL, unstackableItemProperties(), EnumMagnetTiers.T6_D_REG::createDurability);
        addItemBuilder(ItemReference.MAGNET_DURABILITY_DIAMOND_RL, unstackableItemProperties(), EnumMagnetTiers.T7_D_REG::createDurability);
        addItemBuilder(ItemReference.MAGNET_DURABILITY_EMERALD_RL, unstackableItemProperties(), EnumMagnetTiers.T8_D_REG::createDurability);
        addItemBuilder(ItemReference.MAGNET_MAGIC_DURABILITY_STONE_RL, unstackableItemProperties(), EnumMagnetTiers.T1_D_MAGIC::createDurability);
        addItemBuilder(ItemReference.MAGNET_MAGIC_DURABILITY_IRON_RL, unstackableItemProperties(), EnumMagnetTiers.T2_D_MAGIC::createDurability);
        addItemBuilder(ItemReference.MAGNET_MAGIC_DURABILITY_REDSTONE_RL, unstackableItemProperties(), EnumMagnetTiers.T3_D_MAGIC::createDurability);
        addItemBuilder(ItemReference.MAGNET_MAGIC_DURABILITY_GOLD_RL, unstackableItemProperties(), EnumMagnetTiers.T4_D_MAGIC::createDurability);
        addItemBuilder(ItemReference.MAGNET_MAGIC_DURABILITY_OBSIDIAN_RL, unstackableItemProperties(), EnumMagnetTiers.T5_D_MAGIC::createDurability);
        addItemBuilder(ItemReference.MAGNET_MAGIC_DURABILITY_LAPIS_RL, unstackableItemProperties(), EnumMagnetTiers.T6_D_MAGIC::createDurability);
        addItemBuilder(ItemReference.MAGNET_MAGIC_DURABILITY_DIAMOND_RL, unstackableItemProperties(), EnumMagnetTiers.T7_D_MAGIC::createDurability);
        addItemBuilder(ItemReference.MAGNET_MAGIC_DURABILITY_EMERALD_RL, unstackableItemProperties(), EnumMagnetTiers.T8_D_MAGIC::createDurability);

        //Electromagnets
        addItemBuilder(ItemReference.MAGNET_ENERGY_LEADSTONE_RL, unstackableItemProperties(), EnumMagnetTiers.T1_E_REG::createEnergy);
        addItemBuilder(ItemReference.MAGNET_ENERGY_HARDENED_RL, unstackableItemProperties(), EnumMagnetTiers.T2_E_REG::createEnergy);
        addItemBuilder(ItemReference.MAGNET_ENERGY_REINFORCED_RL, unstackableItemProperties(), EnumMagnetTiers.T3_E_REG::createEnergy);
        addItemBuilder(ItemReference.MAGNET_ENERGY_SIGNALUM_RL, unstackableItemProperties(), EnumMagnetTiers.T4_E_REG::createEnergy);
        addItemBuilder(ItemReference.MAGNET_ENERGY_RESONANT_RL, unstackableItemProperties(), EnumMagnetTiers.T5_E_REG::createEnergy);
        addItemBuilder(ItemReference.MAGNET_MAGIC_ENERGY_LEADSTONE_RL, unstackableItemProperties(), EnumMagnetTiers.T1_E_MAGIC::createEnergy);
        addItemBuilder(ItemReference.MAGNET_MAGIC_ENERGY_HARDENED_RL, unstackableItemProperties(), EnumMagnetTiers.T2_E_MAGIC::createEnergy);
        addItemBuilder(ItemReference.MAGNET_MAGIC_ENERGY_REINFORCED_RL, unstackableItemProperties(), EnumMagnetTiers.T3_E_MAGIC::createEnergy);
        addItemBuilder(ItemReference.MAGNET_MAGIC_ENERGY_SIGNALUM_RL, unstackableItemProperties(), EnumMagnetTiers.T4_E_MAGIC::createEnergy);
        addItemBuilder(ItemReference.MAGNET_MAGIC_ENERGY_RESONANT_RL, unstackableItemProperties(), EnumMagnetTiers.T5_E_MAGIC::createEnergy);

        //Free Magnets
        addItemBuilder(ItemReference.MAGNET_FREE_RL, unstackableItemProperties(), EnumMagnetTiers.F_REG::createFree);
        addItemBuilder(ItemReference.MAGNET_MAGIC_FREE_RL, unstackableItemProperties(), EnumMagnetTiers.F_MAGIC::createFree);

    }

    private static void addItemBuilder(ResourceLocation registryName, Item.Properties properties, Function<Item.Properties, Item> factory) {
        container.add(new RegistryObjectBuilder<Item, Item.Properties>(registryName)
                .builder(properties)
                .factory(factory));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        container.register(event);
    }

    static Item.Properties itemProperties() {
        return new Item.Properties().group(ModCreativeTab.Magnets_Tab);
    }

    static Item.Properties unstackableItemProperties() {
        return itemProperties().maxStackSize(1);
    }

    public static void cleanup() {
        container.clear();
    }

    public static final class ItemReference {
        //Durability
        public static final String MAGNET_DURABILITY_STONE = Reference.MOD_ID + ":magnet_durability_stone";
        public static final String MAGNET_DURABILITY_IRON = Reference.MOD_ID + ":magnet_durability_iron";
        public static final String MAGNET_DURABILITY_REDSTONE = Reference.MOD_ID + ":magnet_durability_redstone";
        public static final String MAGNET_DURABILITY_GOLD = Reference.MOD_ID + ":magnet_durability_gold";
        public static final String MAGNET_DURABILITY_OBSIDIAN = Reference.MOD_ID + ":magnet_durability_obsidian";
        public static final String MAGNET_DURABILITY_LAPIS = Reference.MOD_ID + ":magnet_durability_lapis";
        public static final String MAGNET_DURABILITY_DIAMOND = Reference.MOD_ID + ":magnet_durability_diamond";
        public static final String MAGNET_DURABILITY_EMERALD = Reference.MOD_ID + ":magnet_durability_emerald";
        public static final String MAGNET_MAGIC_DURABILITY_STONE = Reference.MOD_ID + ":magnet_magic_durability_stone";
        public static final String MAGNET_MAGIC_DURABILITY_IRON = Reference.MOD_ID + ":magnet_magic_durability_iron";
        public static final String MAGNET_MAGIC_DURABILITY_REDSTONE = Reference.MOD_ID + ":magnet_magic_durability_redstone";
        public static final String MAGNET_MAGIC_DURABILITY_GOLD = Reference.MOD_ID + ":magnet_magic_durability_gold";
        public static final String MAGNET_MAGIC_DURABILITY_OBSIDIAN = Reference.MOD_ID + ":magnet_magic_durability_obsidian";
        public static final String MAGNET_MAGIC_DURABILITY_LAPIS = Reference.MOD_ID + ":magnet_magic_durability_lapis";
        public static final String MAGNET_MAGIC_DURABILITY_DIAMOND = Reference.MOD_ID + ":magnet_magic_durability_diamond";
        public static final String MAGNET_MAGIC_DURABILITY_EMERALD = Reference.MOD_ID + ":magnet_magic_durability_emerald";

        //Electromagnets
        public static final String MAGNET_ENERGY_LEADSTONE = Reference.MOD_ID + ":magnet_energy_leadstone";
        public static final String MAGNET_ENERGY_HARDENED = Reference.MOD_ID + ":magnet_energy_hardened";
        public static final String MAGNET_ENERGY_REINFORCED = Reference.MOD_ID + ":magnet_energy_reinforced";
        public static final String MAGNET_ENERGY_SIGNALUM = Reference.MOD_ID + ":magnet_energy_signalum";
        public static final String MAGNET_ENERGY_RESONANT = Reference.MOD_ID + ":magnet_energy_resonant";
        public static final String MAGNET_MAGIC_ENERGY_LEADSTONE = Reference.MOD_ID + ":magnet_magic_energy_leadstone";
        public static final String MAGNET_MAGIC_ENERGY_HARDENED = Reference.MOD_ID + ":magnet_magic_energy_hardened";
        public static final String MAGNET_MAGIC_ENERGY_REINFORCED = Reference.MOD_ID + ":magnet_magic_energy_reinforced";
        public static final String MAGNET_MAGIC_ENERGY_SIGNALUM = Reference.MOD_ID + ":magnet_magic_energy_signalum";
        public static final String MAGNET_MAGIC_ENERGY_RESONANT = Reference.MOD_ID + ":magnet_magic_energy_resonant";

        //Free Magnets
        public static final String MAGNET_FREE = Reference.MOD_ID + ":magnet_free";
        public static final String MAGNET_MAGIC_FREE = Reference.MOD_ID + ":magnet_magic_free";

        //Durability RL
        public static final ResourceLocation MAGNET_DURABILITY_STONE_RL = new ResourceLocation(MAGNET_DURABILITY_STONE);
        public static final ResourceLocation MAGNET_DURABILITY_IRON_RL = new ResourceLocation(MAGNET_DURABILITY_IRON);
        public static final ResourceLocation MAGNET_DURABILITY_REDSTONE_RL = new ResourceLocation(MAGNET_DURABILITY_REDSTONE);
        public static final ResourceLocation MAGNET_DURABILITY_GOLD_RL = new ResourceLocation(MAGNET_DURABILITY_GOLD);
        public static final ResourceLocation MAGNET_DURABILITY_OBSIDIAN_RL = new ResourceLocation(MAGNET_DURABILITY_OBSIDIAN);
        public static final ResourceLocation MAGNET_DURABILITY_LAPIS_RL = new ResourceLocation(MAGNET_DURABILITY_LAPIS);
        public static final ResourceLocation MAGNET_DURABILITY_DIAMOND_RL = new ResourceLocation(MAGNET_DURABILITY_DIAMOND);
        public static final ResourceLocation MAGNET_DURABILITY_EMERALD_RL = new ResourceLocation(MAGNET_DURABILITY_EMERALD);
        public static final ResourceLocation MAGNET_MAGIC_DURABILITY_STONE_RL = new ResourceLocation(MAGNET_MAGIC_DURABILITY_STONE);
        public static final ResourceLocation MAGNET_MAGIC_DURABILITY_IRON_RL = new ResourceLocation(MAGNET_MAGIC_DURABILITY_IRON);
        public static final ResourceLocation MAGNET_MAGIC_DURABILITY_REDSTONE_RL = new ResourceLocation(MAGNET_MAGIC_DURABILITY_REDSTONE);
        public static final ResourceLocation MAGNET_MAGIC_DURABILITY_GOLD_RL = new ResourceLocation(MAGNET_MAGIC_DURABILITY_GOLD);
        public static final ResourceLocation MAGNET_MAGIC_DURABILITY_OBSIDIAN_RL = new ResourceLocation(MAGNET_MAGIC_DURABILITY_OBSIDIAN);
        public static final ResourceLocation MAGNET_MAGIC_DURABILITY_LAPIS_RL = new ResourceLocation(MAGNET_MAGIC_DURABILITY_LAPIS);
        public static final ResourceLocation MAGNET_MAGIC_DURABILITY_DIAMOND_RL = new ResourceLocation(MAGNET_MAGIC_DURABILITY_DIAMOND);
        public static final ResourceLocation MAGNET_MAGIC_DURABILITY_EMERALD_RL = new ResourceLocation(MAGNET_MAGIC_DURABILITY_EMERALD);

        //Electromagnets RL
        public static final ResourceLocation MAGNET_ENERGY_LEADSTONE_RL = new ResourceLocation(MAGNET_ENERGY_LEADSTONE);
        public static final ResourceLocation MAGNET_ENERGY_HARDENED_RL = new ResourceLocation(MAGNET_ENERGY_HARDENED);
        public static final ResourceLocation MAGNET_ENERGY_REINFORCED_RL = new ResourceLocation(MAGNET_ENERGY_REINFORCED);
        public static final ResourceLocation MAGNET_ENERGY_SIGNALUM_RL = new ResourceLocation(MAGNET_ENERGY_SIGNALUM);
        public static final ResourceLocation MAGNET_ENERGY_RESONANT_RL = new ResourceLocation(MAGNET_ENERGY_RESONANT);
        public static final ResourceLocation MAGNET_MAGIC_ENERGY_LEADSTONE_RL = new ResourceLocation(MAGNET_MAGIC_ENERGY_LEADSTONE);
        public static final ResourceLocation MAGNET_MAGIC_ENERGY_HARDENED_RL = new ResourceLocation(MAGNET_MAGIC_ENERGY_HARDENED);
        public static final ResourceLocation MAGNET_MAGIC_ENERGY_REINFORCED_RL = new ResourceLocation(MAGNET_MAGIC_ENERGY_REINFORCED);
        public static final ResourceLocation MAGNET_MAGIC_ENERGY_SIGNALUM_RL = new ResourceLocation(MAGNET_MAGIC_ENERGY_SIGNALUM);
        public static final ResourceLocation MAGNET_MAGIC_ENERGY_RESONANT_RL = new ResourceLocation(MAGNET_MAGIC_ENERGY_RESONANT);

        //Free Magnets RL
        public static final ResourceLocation MAGNET_FREE_RL = new ResourceLocation(MAGNET_FREE);
        public static final ResourceLocation MAGNET_MAGIC_FREE_RL = new ResourceLocation(MAGNET_MAGIC_FREE);

    }

}
