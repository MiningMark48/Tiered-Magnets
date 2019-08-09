package com.miningmark48.tieredmagnets.init.registry;

import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.init.ModContainers;
import com.miningmark48.tieredmagnets.init.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class ModObjects {

    public static void init() {
        ModBlocks.init();
        ModItems.init();
        ModBlocks.ModTileEntities.init();
        ModContainers.init();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ModObjects::clientInit);
    }

    public static void clientSetup() {
        ModContainers.clientSetup();
    }

    public static void cleanup() {
        ModBlocks.cleanup();
        ModItems.cleanup();
        ModBlocks.ModTileEntities.cleanup();
        ModContainers.cleanup();
    }

    private static void clientInit() {
        ModBlocks.ModTileEntities.clientInit();
    }

}
