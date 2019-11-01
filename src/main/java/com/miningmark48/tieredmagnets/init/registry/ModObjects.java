package com.miningmark48.tieredmagnets.init.registry;

import com.miningmark48.tieredmagnets.init.ModContainers;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import com.miningmark48.tieredmagnets.tileentity.renderer.RendererMagneticInsulator;
import com.miningmark48.tieredmagnets.tileentity.renderer.RendererMagneticProjector;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModObjects {

    public static void init() {
        ModContainers.init();
//        DistExecutor.runWhenOn(Dist.CLIENT, () -> ModObjects::clientInit);
    }

    public static void clientSetup() {
        ModContainers.clientSetup();
    }

    public static void cleanup() {
        ModContainers.cleanup();
    }

    public static void clientInit() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(event -> {
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagneticInsulator.class, new RendererMagneticInsulator());
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagneticProjector.class, new RendererMagneticProjector());
        });
    }

}
