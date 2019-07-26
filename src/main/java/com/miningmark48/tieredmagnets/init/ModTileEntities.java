package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.reference.ReferenceTileNames;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

public class ModTileEntities {

    @ObjectHolder(ReferenceTileNames.MAGNETIC_INSULATOR)
    public static TileEntityType<?> MAGNETIC_INSULATOR;

    @ObjectHolder(ReferenceTileNames.MAGNETIC_PROJECTOR)
    public static TileEntityType<?> MAGNETIC_PROJECTOR;

    public ModTileEntities() {

    }

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration
    {
        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> e)
        {
            e.getRegistry().registerAll(
                    TileEntityType.Builder.create((Supplier<TileEntity>) TileEntityMagneticInsulator::new, ModBlocks.BlockMagneticInsulator).build(null).setRegistryName(ReferenceTileNames.MAGNETIC_INSULATOR),
                    TileEntityType.Builder.create((Supplier<TileEntity>) TileEntityMagneticProjector::new, ModBlocks.BlockMagneticProjector).build(null).setRegistryName(ReferenceTileNames.MAGNETIC_PROJECTOR)
            );
        }
    }

}
