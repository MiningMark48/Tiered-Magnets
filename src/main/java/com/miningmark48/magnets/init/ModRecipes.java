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
                new ResourceLocation(Reference.MOD_ID + ":magnet_durability_lapis"), null, new ItemStack(ModItems.ItemMagnetDurabilityLapis),
                "MIM", "I I", "R R",
                'M', OreDictionary.getOres("gemLapis").stream().findFirst().orElse(ItemStack.EMPTY), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
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
                    " G ", "C R", " G ",
                    'C', IntegrationThermalExpansion.fluxCapacitorHardened , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_reinforced"), null, new ItemStack(ModItems.ItemMagnetElectromagnetReinforced),
                    " G ", "C R", " G ",
                    'C', IntegrationThermalExpansion.fluxCapacitorReinforced , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_signalum"), null, new ItemStack(ModItems.ItemMagnetElectromagnetSignalum),
                    " G ", "C R", " G ",
                    'C', IntegrationThermalExpansion.fluxCapacitorSignalum , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
            );
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_resonant"), null, new ItemStack(ModItems.ItemMagnetElectromagnetResonant),
                    " G ", "C R", " G ",
                    'C', IntegrationThermalExpansion.fluxCapacitorResonant , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
            );
        }

    }

}
