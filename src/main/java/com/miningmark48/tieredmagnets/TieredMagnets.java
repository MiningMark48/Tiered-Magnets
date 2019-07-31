package com.miningmark48.tieredmagnets;

import com.miningmark48.tieredmagnets.client.events.EventStitchParticles;
import com.miningmark48.tieredmagnets.init.ModCraftingConditions;
import com.miningmark48.tieredmagnets.init.ModRegistry;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.init.registry.BuildingObjects;
import com.miningmark48.tieredmagnets.network.PacketHandler;
import com.miningmark48.tieredmagnets.proxy.ClientProxy;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Consumer;

@Mod(value = Reference.MOD_ID)
public class TieredMagnets {

    private static TieredMagnets instance = null;

    public static TieredMagnets getInstance() {
        assert instance != null;
        return instance;
    }

    public TieredMagnets() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(Type.SERVER, ModConfig.SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(Type.CLIENT, ModConfig.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(Type.COMMON, ModConfig.COMMON_CONFIG);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::finishLoad);

        eventBus.addListener(ModConfig::onLoad);
        eventBus.addListener(ModConfig::onFileChange);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener((Consumer<FMLClientSetupEvent>) event -> ClientProxy.clientSetup(eventBus));
//            ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> GuiMod::openScreen);
        });

        BuildingObjects.init();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    private void setup(FMLCommonSetupEvent event) {
        instance = (TieredMagnets) ModLoadingContext.get().getActiveContainer().getMod();

        DeferredWorkQueue.runLater(() -> {
            PacketHandler.registerMessages();
            ModCraftingConditions.init();
        });

        MinecraftForge.EVENT_BUS.register(new ModRegistry());
//        MinecraftForge.EVENT_BUS.register(proxy);

        MinecraftForge.EVENT_BUS.register(new EventStitchParticles());

        PacketHandler.registerMessages();

    }

    private void finishLoad(FMLLoadCompleteEvent event) {
        BuildingObjects.cleanup();
    }

}
