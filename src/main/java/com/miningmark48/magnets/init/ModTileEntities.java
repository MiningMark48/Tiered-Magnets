package com.miningmark48.magnets.init;

import com.miningmark48.magnets.tileentity.TileEntityMagneticInsulator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntityMagneticInsulator.class, "magnetic_insulator");
    }

    public static void bindTileRenders() {
//        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagneticInsulator.class, new RendererMagneticInsulator());
    }

}
