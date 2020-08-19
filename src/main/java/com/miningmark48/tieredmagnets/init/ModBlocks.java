package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.block.BlockMagneticInsulator;
import com.miningmark48.tieredmagnets.block.BlockMagneticProjector;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);


    public static final RegistryObject<Block> MAGNETIC_INSULATOR = BLOCKS.register("magnetic_insulator", BlockMagneticInsulator::new);
    public static final RegistryObject<Block> MAGNETIC_PROJECTOR = BLOCKS.register("magnetic_projector", BlockMagneticProjector::new);

    public static final RegistryObject<TileEntityType<TileEntityMagneticInsulator>> MAGNETIC_INSULATOR_TILE = TILE_ENTITIES.register("magnetic_insulator", () -> TileEntityType.Builder.create(TileEntityMagneticInsulator::new, ModBlocks.MAGNETIC_INSULATOR.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityMagneticProjector>> MAGNETIC_PROJECTOR_TILE = TILE_ENTITIES.register("magnetic_projector", () -> TileEntityType.Builder.create(TileEntityMagneticProjector::new, ModBlocks.MAGNETIC_PROJECTOR.get()).build(null));

}
