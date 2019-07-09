package com.miningmark48.magnets.init;

import com.miningmark48.magnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.magnets.tileentity.TileEntityMagneticProjector;
import com.miningmark48.magnets.tileentity.renderer.RendererMagneticInsulator;
import com.miningmark48.magnets.tileentity.renderer.RendererMagneticProjector;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntityMagneticInsulator.class, "magnetic_insulator");
        GameRegistry.registerTileEntity(TileEntityMagneticProjector.class, "magnetic_projector");
    }

    @SideOnly(Side.CLIENT)
    public static void bindTileRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagneticInsulator.class, new RendererMagneticInsulator());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagneticProjector.class, new RendererMagneticProjector());
    }

}
