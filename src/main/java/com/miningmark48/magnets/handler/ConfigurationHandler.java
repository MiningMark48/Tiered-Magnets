package com.miningmark48.magnets.handler;

import com.miningmark48.mininglib.utility.ModTranslate;
import com.miningmark48.magnets.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;

    public static boolean doParticles;
    public static float baseRange;
    public static float rangeMultiplier;
    public static int baseDurability;
    public static float durabilityMultiplier;
    public static float speed;

    public static void init(File configFile){

        if (configuration == null){
            configuration = new Configuration(configFile);
            loadConfiguration();
        }

    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){

        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)){
            loadConfiguration();
        }

    }

    private static void loadConfiguration(){

        // Categories
        configuration.addCustomCategoryComment(ModTranslate.toLocal("config.category.clientSide.title"), ModTranslate.toLocal("config.category.clientSide.desc"));
        configuration.addCustomCategoryComment(ModTranslate.toLocal("config.category.magnetItemDurability.title"), ModTranslate.toLocal("config.category.magnetItemDurability.desc"));
        configuration.setCategoryRequiresMcRestart(ModTranslate.toLocal("config.category.magnetItemDurability.title"), true);

        // General
        doParticles = configuration.getBoolean(ModTranslate.toLocal("config.doParticles.title"), ModTranslate.toLocal("config.category.clientSide.title"), true, ModTranslate.toLocal("config.doParticles.desc"));

        // Durability Magnets
        baseRange = configuration.getFloat(ModTranslate.toLocal("config.baseRange.title"), ModTranslate.toLocal("config.category.magnetItemDurability.title"), 2f, 0.5f, 128f, ModTranslate.toLocal("config.baseRange.desc"));
        rangeMultiplier = configuration.getFloat(ModTranslate.toLocal("config.rangeMultiplier.title"), ModTranslate.toLocal("config.category.magnetItemDurability.title"), 4f, 0.0f, Float.MAX_VALUE, ModTranslate.toLocal("config.rangeMultiplier.desc"));

        baseDurability = configuration.getInt(ModTranslate.toLocal("config.baseDurability.title"), ModTranslate.toLocal("config.category.magnetItemDurability.title"), 1024, 1, Integer.MAX_VALUE, ModTranslate.toLocal("config.baseDurability.desc"));
        durabilityMultiplier = configuration.getFloat(ModTranslate.toLocal("config.durabilityMultiplier.title"), ModTranslate.toLocal("config.category.magnetItemDurability.title"), 0.5f, 0.0f, Float.MAX_VALUE, ModTranslate.toLocal("config.durabilityMultiplier.desc"));

        speed = configuration.getFloat(ModTranslate.toLocal("config.speed.title"), ModTranslate.toLocal("config.category.magnetItemDurability.title"), 0.05f, 0f, Float.MAX_VALUE, ModTranslate.toLocal("config.speed.desc"));

        if (configuration.hasChanged()){
            configuration.save();
        }

    }

}
