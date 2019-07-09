package com.miningmark48.magnets.init;

import com.miningmark48.magnets.block.*;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block BlockMagneticInsulator;
    public static Block BlockMagneticProjector;

    public static void init() {
        BlockMagneticInsulator = new BlockMagneticInsulator().setUnlocalizedName("magnetic_insulator").setRegistryName("magnetic_insulator").setCreativeTab(ModCreativeTab.Magnets_Tab);
        BlockMagneticProjector = new BlockMagneticProjector().setUnlocalizedName("magnetic_projector").setRegistryName("magnetic_projector").setCreativeTab(ModCreativeTab.Magnets_Tab);
    }

}
