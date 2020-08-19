package com.miningmark48.tieredmagnets.data;

import com.miningmark48.tieredmagnets.init.ModCraftingConditions;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.util.ModLogger;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HandlerGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ModCraftingConditions.init();
        ModLogger.info(event.getGenerator().getOutputFolder().toString());

        if (event.includeServer())
            registerServerProviders(event.getGenerator());

        if (event.includeClient())
            registerClientProviders(event.getGenerator(), event);
    }

    private static void registerServerProviders(DataGenerator generator) {
        generator.addProvider(new GeneratorLoot(generator));
        generator.addProvider(new GeneratorRecipes(generator));
//        generator.addProvider(new GeneratorBlockTags(generator));
//        generator.addProvider(new GeneratorItemTags(generator));
    }

    private static void registerClientProviders(DataGenerator generator, GatherDataEvent event) {
        ExistingFileHelper helper = event.getExistingFileHelper();

//        generator.addProvider(new GeneratorBlockStates(generator, helper));
//        generator.addProvider(new GeneratorItemModels(generator, helper));
        generator.addProvider(new GeneratorLanguage(generator));
    }

}
