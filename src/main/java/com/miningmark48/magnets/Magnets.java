package com.miningmark48.magnets;

import com.miningmark48.mininglib.utility.ModLogger;
import com.miningmark48.mininglib.utility.ModTranslate;
import com.miningmark48.magnets.handler.ConfigurationHandler;
import com.miningmark48.magnets.init.*;
import com.miningmark48.magnets.proxy.CommonProxy;
import com.miningmark48.magnets.reference.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.MOD_DEPENDENCIES, acceptedMinecraftVersions = Reference.GAME_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Magnets {

    @Mod.Instance(Reference.MOD_ID)
    public static Magnets instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());

        ModBlocks.init();
        ModTileEntities.init();
        ModItems.init();
        ModRegistry.init();

        MinecraftForge.EVENT_BUS.register(new ModRegistry());
//        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.preInit(event);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

        proxy.registerRenders();
        proxy.init(event);

//        ModRecipes.init();
//        ModSoundEvents.registerSounds();

//        MinecraftForge.EVENT_BUS.register(new ModSoundEvents());

        ModLogger.info(ModTranslate.toLocal("log.info.init"));

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

        ModLogger.info(ModTranslate.toLocal("log.info.postinit"));

    }

}
