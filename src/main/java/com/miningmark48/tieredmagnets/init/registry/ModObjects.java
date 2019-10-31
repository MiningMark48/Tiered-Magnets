package com.miningmark48.tieredmagnets.init.registry;

import com.miningmark48.tieredmagnets.init.ModContainers;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import com.miningmark48.tieredmagnets.tileentity.renderer.RendererMagneticInsulator;
import com.miningmark48.tieredmagnets.tileentity.renderer.RendererMagneticProjector;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModObjects {

    public static void init() {
        ModContainers.init();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ModObjects::clientInit);
    }

    public static void clientSetup() {
        ModContainers.clientSetup();
    }

    public static void cleanup() {
        ModContainers.cleanup();
    }

    private static void clientInit() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagneticInsulator.class, new RendererMagneticInsulator());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagneticProjector.class, new RendererMagneticProjector());
    }

}
