package com.miningmark48.magnets.init;

import com.miningmark48.magnets.integration.IntegrationThermalExpansion;
import com.miningmark48.magnets.reference.Reference;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
        }

        // Thermal Expansion
        if (ModConfig.modules.thermalExpansionModule && Loader.isModLoaded(Reference.TE)) {
            GameRegistry.addShapelessRecipe(
                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_stone"), null, new ItemStack(ModItems.ItemMagnetDurabilityStone),
                    Ingredient.func_193369_a(IntegrationThermalExpansion.fluxCapacitorBasic), Ingredient.func_193369_a(IntegrationThermalExpansion.redstoneReceptionCoil), Ingredient.func_193369_a(new ItemStack(Items.GOLD_NUGGET))
            );
        }

    }

}
