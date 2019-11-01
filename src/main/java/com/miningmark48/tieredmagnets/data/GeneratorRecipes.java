package com.miningmark48.tieredmagnets.data;

import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.init.ModItems;
import com.miningmark48.tieredmagnets.init.config.crafting.ConditionModuleEnabled;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

@SuppressWarnings({"ConstantConditions", "Duplicates"})
public class GeneratorRecipes extends RecipeProvider implements IConditionBuilder {

    public GeneratorRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        registerBlockRecipes(consumer);
        registerItemRecipes(consumer);
    }

    private void registerBlockRecipes(Consumer<IFinishedRecipe> consumer) {

        ConditionalRecipe.builder()
                .addCondition(new ConditionModuleEnabled("projector"))
                .addRecipe(
                        ShapedRecipeBuilder.shapedRecipe(ModBlocks.MAGNETIC_PROJECTOR.get())
                                .patternLine("IPI")
                                .patternLine("GRG")
                                .patternLine("IHI")
                                .key('P', Blocks.GLASS_PANE)
                                .key('G', Items.GOLD_INGOT)
                                .key('I', Items.IRON_INGOT)
                                .key('R', ModItems.MAGNETITE.get())
                                .key('H', Blocks.HOPPER)
                                .addCriterion("has_magnetite", hasItem(ModItems.MAGNETITE.get()))
                                ::build
                ).build(consumer, ModBlocks.MAGNETIC_PROJECTOR.get().getRegistryName());


        ConditionalRecipe.builder()
                .addCondition(new ConditionModuleEnabled("insulator"))
                .addRecipe(
                        ShapedRecipeBuilder.shapedRecipe(ModBlocks.MAGNETIC_INSULATOR.get())
                                .patternLine("MIM")
                                .patternLine("GIG")
                                .patternLine("III")
                                .key('G', Items.GOLD_INGOT)
                                .key('I', Items.IRON_INGOT)
                                .key('M', ModItems.MAGNETITE.get())
                                .addCriterion("has_magnetite", hasItem(ModItems.MAGNETITE.get()))
                                ::build
                ).build(consumer, ModBlocks.MAGNETIC_INSULATOR.get().getRegistryName());

    }

    private void registerItemRecipes(Consumer<IFinishedRecipe> consumer) {
        addMagnetD(consumer, ModItems.MAGNET_DURABILITY_STONE.get(), Blocks.COBBLESTONE.asItem());
        addMagnetD(consumer, ModItems.MAGNET_DURABILITY_IRON.get(), Items.IRON_INGOT);
        addMagnetD(consumer, ModItems.MAGNET_DURABILITY_REDSTONE.get(), Items.REDSTONE);
        addMagnetD(consumer, ModItems.MAGNET_DURABILITY_GOLD.get(), Items.GOLD_INGOT);
        addMagnetD(consumer, ModItems.MAGNET_DURABILITY_OBSIDIAN.get(), Blocks.OBSIDIAN.asItem());
        addMagnetD(consumer, ModItems.MAGNET_DURABILITY_LAPIS.get(), Items.LAPIS_LAZULI);
        addMagnetD(consumer, ModItems.MAGNET_DURABILITY_DIAMOND.get(), Items.DIAMOND);
        addMagnetD(consumer, ModItems.MAGNET_DURABILITY_EMERALD.get(), Items.EMERALD);
        addMagnetDM(consumer, ModItems.MAGNET_MAGIC_DURABILITY_STONE.get(), ModItems.MAGNET_DURABILITY_STONE.get());
        addMagnetDM(consumer, ModItems.MAGNET_MAGIC_DURABILITY_IRON.get(), ModItems.MAGNET_DURABILITY_IRON.get());
        addMagnetDM(consumer, ModItems.MAGNET_MAGIC_DURABILITY_REDSTONE.get(), ModItems.MAGNET_DURABILITY_REDSTONE.get());
        addMagnetDM(consumer, ModItems.MAGNET_MAGIC_DURABILITY_GOLD.get(), ModItems.MAGNET_DURABILITY_GOLD.get());
        addMagnetDM(consumer, ModItems.MAGNET_MAGIC_DURABILITY_OBSIDIAN.get(), ModItems.MAGNET_DURABILITY_OBSIDIAN.get());
        addMagnetDM(consumer, ModItems.MAGNET_MAGIC_DURABILITY_LAPIS.get(), ModItems.MAGNET_DURABILITY_LAPIS.get());
        addMagnetDM(consumer, ModItems.MAGNET_MAGIC_DURABILITY_DIAMOND.get(), ModItems.MAGNET_DURABILITY_DIAMOND.get());
        addMagnetDM(consumer, ModItems.MAGNET_MAGIC_DURABILITY_EMERALD.get(), ModItems.MAGNET_DURABILITY_EMERALD.get());

        addMagnetE_start(consumer, ModItems.MAGNET_ENERGY_LEADSTONE.get());
        addMagnetE(consumer, ModItems.MAGNET_ENERGY_HARDENED.get(), ModItems.MAGNET_ENERGY_LEADSTONE.get(), Blocks.OBSIDIAN.asItem(), Items.IRON_INGOT);
        addMagnetE(consumer, ModItems.MAGNET_ENERGY_REINFORCED.get(), ModItems.MAGNET_ENERGY_HARDENED.get(), Items.GOLD_INGOT, Blocks.OBSIDIAN.asItem());
        addMagnetE(consumer, ModItems.MAGNET_ENERGY_SIGNALUM.get(), ModItems.MAGNET_ENERGY_REINFORCED.get(), Items.REDSTONE, Items.GOLD_INGOT);
        addMagnetE(consumer, ModItems.MAGNET_ENERGY_RESONANT.get(), ModItems.MAGNET_ENERGY_SIGNALUM.get(), Items.ENDER_PEARL, Items.DIAMOND);
        addMagnetEM(consumer, ModItems.MAGNET_MAGIC_ENERGY_LEADSTONE.get(), ModItems.MAGNET_ENERGY_LEADSTONE.get());
        addMagnetEM(consumer, ModItems.MAGNET_MAGIC_ENERGY_HARDENED.get(), ModItems.MAGNET_ENERGY_HARDENED.get());
        addMagnetEM(consumer, ModItems.MAGNET_MAGIC_ENERGY_REINFORCED.get(), ModItems.MAGNET_ENERGY_REINFORCED.get());
        addMagnetEM(consumer, ModItems.MAGNET_MAGIC_ENERGY_SIGNALUM.get(), ModItems.MAGNET_ENERGY_SIGNALUM.get());
        addMagnetEM(consumer, ModItems.MAGNET_MAGIC_ENERGY_RESONANT.get(), ModItems.MAGNET_ENERGY_RESONANT.get());

        addMagnetF(consumer, ModItems.MAGNET_FREE.get());
        addMagnetFM(consumer, ModItems.MAGNET_MAGIC_FREE.get(), ModItems.MAGNET_FREE.get());

    }

    private void addMagnetD(Consumer<IFinishedRecipe> consumer, Item magnet, Item resource) {
        ConditionalRecipe.builder()
                .addCondition(new ConditionModuleEnabled("vanilla"))
                .addRecipe(
                        ShapedRecipeBuilder.shapedRecipe(magnet)
                                .patternLine("RIR")
                                .patternLine("I I")
                                .patternLine("M M")
                                .key('M', ModItems.MAGNETITE.get())
                                .key('I', Items.IRON_INGOT)
                                .key('R', resource)
                                .addCriterion("has_magnetite", hasItem(ModItems.MAGNETITE.get()))
                                ::build
                ).build(consumer, magnet.getRegistryName());
    }

    private void addMagnetDM(Consumer<IFinishedRecipe> consumer, Item magnet, Item magnetPrior) {
        ConditionalRecipe.builder()
                .addCondition(new ConditionModuleEnabled("vanilla_magic"))
                .addRecipe(
                        ShapedRecipeBuilder.shapedRecipe(magnet)
                                .patternLine("GEG")
                                .patternLine("RMR")
                                .patternLine("GEG")
                                .key('R', ModItems.MAGNETITE.get())
                                .key('G', Items.GOLD_INGOT)
                                .key('E', Items.ENDER_PEARL)
                                .key('M', magnetPrior)
                                .addCriterion("has_magnetite", hasItem(ModItems.MAGNETITE.get()))
                                ::build
                ).build(consumer, magnet.getRegistryName());
    }

    private void addMagnetE_start(Consumer<IFinishedRecipe> consumer, Item magnet) {
        ConditionalRecipe.builder()
                .addCondition(new ConditionModuleEnabled("te"))
                .addRecipe(
                        ShapedRecipeBuilder.shapedRecipe(magnet)
                                .patternLine("INI")
                                .patternLine("IRI")
                                .patternLine("IGI")
                                .key('N', Items.GOLD_NUGGET)
                                .key('I', Items.IRON_INGOT)
                                .key('G', Items.GOLD_INGOT)
                                .key('R', Items.REDSTONE)
                                .addCriterion("has_redstone", hasItem(Items.REDSTONE))
                                ::build
                ).build(consumer, magnet.getRegistryName());
    }

    private void addMagnetE(Consumer<IFinishedRecipe> consumer, Item magnet, Item magnetPrior, Item resourceOne, Item resourceTwo) {
        ConditionalRecipe.builder()
                .addCondition(new ConditionModuleEnabled("te"))
                .addRecipe(
                        ShapedRecipeBuilder.shapedRecipe(magnet)
                                .patternLine(" A ")
                                .patternLine("BMB")
                                .patternLine(" A ")
                                .key('M', magnetPrior)
                                .key('A', resourceOne)
                                .key('B', resourceTwo)
                                .addCriterion("has_magnet", hasItem(magnetPrior))
                                ::build
                ).build(consumer, magnet.getRegistryName());
    }

    private void addMagnetEM(Consumer<IFinishedRecipe> consumer, Item magnet, Item magnetPrior) {
        ConditionalRecipe.builder()
                .addCondition(new ConditionModuleEnabled("te_magic"))
                .addRecipe(
                        ShapedRecipeBuilder.shapedRecipe(magnet)
                                .patternLine("GEG")
                                .patternLine("RMR")
                                .patternLine("GEG")
                                .key('R', Items.REDSTONE)
                                .key('G', Items.GOLD_INGOT)
                                .key('E', Items.ENDER_PEARL)
                                .key('M', magnetPrior)
                                .addCriterion("has_redstone", hasItem(Items.REDSTONE))
                                ::build
                ).build(consumer, magnet.getRegistryName());
    }

    private void addMagnetF(Consumer<IFinishedRecipe> consumer, Item magnet) {
        ConditionalRecipe.builder()
                .addCondition(new ConditionModuleEnabled("cursed"))
                .addRecipe(
                        ShapedRecipeBuilder.shapedRecipe(magnet)
                                .patternLine("DGD")
                                .patternLine("ISI")
                                .patternLine("R R")
                                .key('D', Items.DIAMOND)
                                .key('G', Items.GHAST_TEAR)
                                .key('I', Items.IRON_INGOT)
                                .key('S', Blocks.SOUL_SAND.asItem())
                                .key('R', ModItems.MAGNETITE.get())
                                .addCriterion("has_magnetite", hasItem(ModItems.MAGNETITE.get()))
                                ::build
                ).build(consumer, magnet.getRegistryName());
    }

    private void addMagnetFM(Consumer<IFinishedRecipe> consumer, Item magnet, Item magnetPrior) {
        ConditionalRecipe.builder()
                .addCondition(new ConditionModuleEnabled("cursed_magic"))
                .addRecipe(
                        ShapedRecipeBuilder.shapedRecipe(magnet)
                                .patternLine("GEG")
                                .patternLine("RMR")
                                .patternLine("GEG")
                                .key('R', ModItems.MAGNETITE.get())
                                .key('G', Items.GOLD_INGOT)
                                .key('E', Items.ENDER_PEARL)
                                .key('M', magnetPrior)
                                .addCriterion("has_magnetite", hasItem(ModItems.MAGNETITE.get()))
                                ::build
                ).build(consumer, magnet.getRegistryName());
    }

}
