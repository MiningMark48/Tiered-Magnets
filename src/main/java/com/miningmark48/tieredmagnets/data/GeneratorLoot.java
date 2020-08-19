package com.miningmark48.tieredmagnets.data;

import com.google.common.collect.ImmutableList;
import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GeneratorLoot extends LootTableProvider {

    public GeneratorLoot(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(Blocks::new, LootParameterSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationresults) {
        map.forEach((name, table) -> LootTableManager.func_227508_a_(validationresults, name, table));
    }

    @SuppressWarnings("ConstantConditions")
    private class Blocks extends BlockLootTables {

        @Override
        protected void addTables() {
            this.registerDropSelfLootTable(ModBlocks.MAGNETIC_INSULATOR.get());
            this.registerDropSelfLootTable(ModBlocks.MAGNETIC_PROJECTOR.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }

    }

}
