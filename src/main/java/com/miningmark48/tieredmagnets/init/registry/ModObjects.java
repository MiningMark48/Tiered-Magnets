package com.miningmark48.tieredmagnets.init.registry;

import com.miningmark48.tieredmagnets.init.ModContainers;

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
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(event -> {
//            ClientRegistry.bindTileEntityRenderer(TileEntityMagneticInsulator.class, new RendererMagneticInsulator());
//            ClientRegistry.bindTileEntityRenderer(TileEntityMagneticProjector.class, new RendererMagneticProjector());
//        });
    }

}
