package com.miningmark48.magnets.init;

import com.miningmark48.magnets.integration.IntegrationThermalExpansion;
import com.miningmark48.magnets.reference.Reference;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

    public static void init(RegistryEvent.Register<IRecipe> e) {
        if (ModConfig.modules.thermalExpansionModule && Loader.isModLoaded(Reference.TE)) {
            IntegrationThermalExpansion.init();
        }

        // Vanilla
        if (ModConfig.modules.vanillaModule) {
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_stone"), null, new ItemStack(ModItems.ItemMagnetDurabilityStone),
                    "MMM", "M M", "R R",
                    'M', new ItemStack(Blocks.COBBLESTONE), 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_iron"), null, new ItemStack(ModItems.ItemMagnetDurabilityIron),
                    "MMM", "M M", "R R",
                    'M', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_redstone"), null, new ItemStack(ModItems.ItemMagnetDurabilityRedstone),
                    "MIM", "I I", "R R",
                    'M', new ItemStack(Items.REDSTONE), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_gold"), null, new ItemStack(ModItems.ItemMagnetDurabilityGold),
                    "MIM", "I I", "R R",
                    'M', new ItemStack(Items.GOLD_INGOT), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_obsidian"), null, new ItemStack(ModItems.ItemMagnetDurabilityObsidian),
                    "MIM", "I I", "R R",
                    'M', new ItemStack(Blocks.OBSIDIAN), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_lapis"), null, new ItemStack(ModItems.ItemMagnetDurabilityLapis),
                    "MIM", "I I", "R R",
                    'M', new ItemStack(Items.DYE, 1, 4), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_diamond"), null, new ItemStack(ModItems.ItemMagnetDurabilityDiamond),
                    "MIM", "I I", "R R",
                    'M', new ItemStack(Items.DIAMOND), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_emerald"), null, new ItemStack(ModItems.ItemMagnetDurabilityEmerald),
                    "MIM", "I I", "R R",
                    'M', new ItemStack(Items.EMERALD), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_stone"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityStone),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetDurabilityStone), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_iron"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityIron),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetDurabilityIron), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_redstone"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityRedstone),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetDurabilityRedstone), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_gold"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityGold),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetDurabilityGold), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_obsidian"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityObsidian),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetDurabilityObsidian), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_lapis"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityLapis),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetDurabilityLapis), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_diamond"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityDiamond),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetDurabilityDiamond), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_emerald"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityEmerald),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetDurabilityEmerald), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
        }

        // Thermal Expansion
        if (ModConfig.modules.thermalExpansionModule && Loader.isModLoaded(Reference.TE)) {
            GameRegistry.addShapedRecipe(
                new ResourceLocation(Reference.MOD_ID + ":magnet_energy_leadstone"), null, new ItemStack(ModItems.ItemMagnetElectromagnetLeadstone),
                " G ", "C R", " G ",
                'C', IntegrationThermalExpansion.fluxCapacitorBasic , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_hardened"), null, new ItemStack(ModItems.ItemMagnetElectromagnetHardened),
                    " R ", "XMX", "RYR",
                    'M', ModItems.ItemMagnetElectromagnetLeadstone , 'X', IntegrationThermalExpansion.ingotInvar, 'Y', IntegrationThermalExpansion.ingotTin, 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_reinforced"), null, new ItemStack(ModItems.ItemMagnetElectromagnetReinforced),
                    " R ", "XMX", "RYR",
                    'M', ModItems.ItemMagnetElectromagnetHardened , 'X', IntegrationThermalExpansion.ingotElectrum, 'Y', IntegrationThermalExpansion.hardenedGlass, 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_signalum"), null, new ItemStack(ModItems.ItemMagnetElectromagnetSignalum),
                    " R ", "XMX", "RYR",
                    'M', ModItems.ItemMagnetElectromagnetReinforced , 'X', IntegrationThermalExpansion.ingotSignalum, 'Y', IntegrationThermalExpansion.dustCryotheum, 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_resonant"), null, new ItemStack(ModItems.ItemMagnetElectromagnetResonant),
                    " R ", "XMX", "RYR",
                    'M', ModItems.ItemMagnetElectromagnetSignalum , 'X', IntegrationThermalExpansion.ingotEnderium, 'Y', IntegrationThermalExpansion.dustPyrotheum, 'R', new ItemStack(Items.REDSTONE)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_hardened2"), null, new ItemStack(ModItems.ItemMagnetElectromagnetHardened),
                    " G ", "C R", " G ",
                    'C', IntegrationThermalExpansion.fluxCapacitorHardened , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_reinforced2"), null, new ItemStack(ModItems.ItemMagnetElectromagnetReinforced),
                    " G ", "C R", " G ",
                    'C', IntegrationThermalExpansion.fluxCapacitorReinforced , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_signalum2"), null, new ItemStack(ModItems.ItemMagnetElectromagnetSignalum),
                    " G ", "C R", " G ",
                    'C', IntegrationThermalExpansion.fluxCapacitorSignalum , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_resonant2"), null, new ItemStack(ModItems.ItemMagnetElectromagnetResonant),
                    " G ", "C R", " G ",
                    'C', IntegrationThermalExpansion.fluxCapacitorResonant , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_leadstone"), null, new ItemStack(ModItems.ItemMagnetMagicElectromagnetLeadstone),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetLeadstone), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_hardened"), null, new ItemStack(ModItems.ItemMagnetMagicElectromagnetHardened),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetHardened), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_reinforced"), null, new ItemStack(ModItems.ItemMagnetMagicElectromagnetReinforced),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetReinforced), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_signalum"), null, new ItemStack(ModItems.ItemMagnetMagicElectromagnetSignalum),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetSignalum), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_resonant"), null, new ItemStack(ModItems.ItemMagnetMagicElectromagnetResonant),
                    " E ", "GMG", " E ",
                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetResonant), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
            );
        }

        // Vanilla - Magic
        if (ModConfig.modules.vanillaMagicModule) {
            //TODO
        }

        // Thermal Expansion - Magic
        if (ModConfig.modules.thermalExpansionMagicModule) {
            //TODO
        }

    }

}
