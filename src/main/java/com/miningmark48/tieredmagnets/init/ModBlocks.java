package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.block.BlockMagneticInsulator;
import com.miningmark48.tieredmagnets.block.BlockMagneticProjector;
import com.miningmark48.tieredmagnets.init.registry.block.BlockBuilder;
import com.miningmark48.tieredmagnets.init.registry.block.BlockRegistryContainer;
import com.miningmark48.tieredmagnets.init.registry.block.tile.TileEntityBuilder;
import com.miningmark48.tieredmagnets.init.registry.block.tile.TileEntityRegistryContainer;
import com.miningmark48.tieredmagnets.init.registry.block.tile.TileEntityTypeBuilder;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    private static final BlockRegistryContainer container = new BlockRegistryContainer(ModTileEntities.container);

    public ModBlocks() {

    }

    @ObjectHolder(BlockReference.MAGNETIC_INSULATOR)
    public static BlockMagneticInsulator BlockMagneticInsulator;
    @ObjectHolder(BlockReference.MAGNETIC_PROJECTOR)
    public static BlockMagneticProjector BlockMagneticProjector;

    public static void init() {
        //Magnetic Insulator
        container.add(new BlockBuilder(BlockReference.MAGNETIC_INSULATOR_RL)
            .builder(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f))
            .item(itemProperties())
            .withTileEntity(new TileEntityBuilder<>(TileEntityReference.MAGNETIC_INSULATOR_RL)
                .builder(new TileEntityTypeBuilder<>(TileEntityMagneticInsulator::new))
                .factory(TileEntityTypeBuilder::build))
            .factory(BlockMagneticInsulator::new));
        //Magnetic Projector
        container.add(new BlockBuilder(BlockReference.MAGNETIC_PROJECTOR_RL)
                .builder(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f))
                .item(itemProperties())
                .withTileEntity(new TileEntityBuilder<>(TileEntityReference.MAGNETIC_PROJECTOR_RL)
                        .builder(new TileEntityTypeBuilder<>(TileEntityMagneticProjector::new))
                        .factory(TileEntityTypeBuilder::build))
                .factory(BlockMagneticProjector::new));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        container.register(event);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        container.registerItemBlocks(event);
    }

    static void cleanup() {
        container.clear();
    }

    public static final class BlockReference {

        public static final String MAGNETIC_INSULATOR = Reference.MOD_ID + ":magnetic_insulator";
        public static final String MAGNETIC_PROJECTOR = Reference.MOD_ID + ":magnetic_projector";

        public static final ResourceLocation MAGNETIC_INSULATOR_RL = new ResourceLocation(MAGNETIC_INSULATOR);
        public static final ResourceLocation MAGNETIC_PROJECTOR_RL = new ResourceLocation(MAGNETIC_PROJECTOR);

    }

    @ObjectHolder(Reference.MOD_ID)
    @Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class ModTileEntities {
        private static final TileEntityRegistryContainer container = new TileEntityRegistryContainer();

        private ModTileEntities() {

        }

        @ObjectHolder(TileEntityReference.MAGNETIC_INSULATOR)
        public static TileEntityType<?> MAGNETIC_INSULATOR;
        @ObjectHolder(TileEntityReference.MAGNETIC_PROJECTOR)
        public static TileEntityType<?> MAGNETIC_PROJECTOR;

        static void init() {

        }

        @SubscribeEvent
        public static void registerTiles(RegistryEvent.Register<TileEntityType<?>> event) {
            container.register(event);
        }

        static void clientInit() {
            container.clientInit();
        }

        static void cleanup() {
            container.clear();
        }

    }

    public static final class TileEntityReference {

        public static final String MAGNETIC_INSULATOR = Reference.MOD_ID + ":magnetic_insulator";
        public static final String MAGNETIC_PROJECTOR = Reference.MOD_ID + ":magnetic_projector";

        public static final ResourceLocation MAGNETIC_INSULATOR_RL = new ResourceLocation(MAGNETIC_INSULATOR);
        public static final ResourceLocation MAGNETIC_PROJECTOR_RL = new ResourceLocation(MAGNETIC_PROJECTOR);

    }

}
