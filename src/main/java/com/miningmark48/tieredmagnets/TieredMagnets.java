package com.miningmark48.tieredmagnets;

import com.miningmark48.tieredmagnets.client.events.EventStitchParticles;
import com.miningmark48.tieredmagnets.handler.GuiHandler;
import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.init.ModItems;
import com.miningmark48.tieredmagnets.init.ModRegistry;
import com.miningmark48.tieredmagnets.init.ModTileEntities;
import com.miningmark48.tieredmagnets.network.PacketHandler;
import com.miningmark48.tieredmagnets.proxy.CommonProxy;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.util.ModLogger;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.MOD_DEPENDENCIES, acceptedMinecraftVersions = Reference.GAME_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class TieredMagnets {

    @Mod.Instance(Reference.MOD_ID)
    public static TieredMagnets instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

        ModBlocks.init();
        ModTileEntities.init();
        ModItems.init();
        ModRegistry.init();

        MinecraftForge.EVENT_BUS.register(new ModRegistry());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(proxy);

        MinecraftForge.EVENT_BUS.register(new EventStitchParticles());

        PacketHandler.registerMessages();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
        proxy.registerRenders();
        ModLogger.info(ModTranslate.toLocal("log.info.init"));
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        ModLogger.info(ModTranslate.toLocal("log.info.postinit"));
    }

}
