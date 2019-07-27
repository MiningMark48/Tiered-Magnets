package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.client.gui.GuiMagnetFilter;
import com.miningmark48.tieredmagnets.client.gui.GuiMagneticInsulator;
import com.miningmark48.tieredmagnets.client.gui.GuiMagneticProjector;
import com.miningmark48.tieredmagnets.container.ContainerMagnetFilter;
import com.miningmark48.tieredmagnets.container.ContainerMagneticInsulator;
import com.miningmark48.tieredmagnets.container.ContainerMagneticProjector;
import com.miningmark48.tieredmagnets.init.registry.container.ScreenContainerBuilder;
import com.miningmark48.tieredmagnets.init.registry.container.ScreenContainerObjectBuilder;
import com.miningmark48.tieredmagnets.init.registry.container.ScreenContainerRegistryContainer;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainers {

    private static final ScreenContainerRegistryContainer container = new ScreenContainerRegistryContainer();

    private ModContainers() {

    }

    @ObjectHolder(ContainerReference.MAGNET_FILTER)
    public static ContainerType<ContainerMagnetFilter> CONTAINER_MAGNET_FILTER;
    @ObjectHolder(ContainerReference.MAGNETIC_INSULATOR)
    public static ContainerType<ContainerMagneticInsulator> CONTAINER_MAGNETIC_INSULATOR;
    @ObjectHolder(ContainerReference.MAGNETIC_PROJECTOR)
    public static ContainerType<ContainerMagneticInsulator> CONTAINER_MAGNETIC_PROJECTOR;

    @SuppressWarnings({"Convert2Diamond"})
    public static void init() {
        container.add(
                new ScreenContainerObjectBuilder(ContainerReference.MAGNET_FILTER_RL)
                        .builder(new ScreenContainerBuilder<ContainerMagnetFilter, GuiMagnetFilter>(
                                ContainerMagnetFilter::new, () -> () -> GuiMagnetFilter::new))
        );
        container.add(
                new ScreenContainerObjectBuilder(ContainerReference.MAGNETIC_INSULATOR_RL)
                        .builder(new ScreenContainerBuilder<ContainerMagneticInsulator, GuiMagneticInsulator>(
                                ContainerMagneticInsulator::new, () -> () -> GuiMagneticInsulator::new))
        );
        container.add(
                new ScreenContainerObjectBuilder(ContainerReference.MAGNETIC_PROJECTOR_RL)
                        .builder(new ScreenContainerBuilder<ContainerMagneticProjector, GuiMagneticProjector>(
                                ContainerMagneticProjector::new, () -> () -> GuiMagneticProjector::new))
        );
    }

    public static void cleanup() {
        container.clear();
    }

    public static void clientSetup() {
        container.clientSetup();
    }

    @SubscribeEvent
    public static void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
        container.register(event);
    }

    public static final class ContainerReference {

        public static final String MAGNET_FILTER = Reference.MOD_ID + ":magnet_filter";
        public static final String MAGNETIC_INSULATOR = Reference.MOD_ID + ":magnetic_insulator";
        public static final String MAGNETIC_PROJECTOR = Reference.MOD_ID + ":magnetic_projector";

        public static final ResourceLocation MAGNET_FILTER_RL = new ResourceLocation(MAGNET_FILTER);
        public static final ResourceLocation MAGNETIC_INSULATOR_RL = new ResourceLocation(MAGNETIC_INSULATOR);
        public static final ResourceLocation MAGNETIC_PROJECTOR_RL = new ResourceLocation(MAGNETIC_PROJECTOR);

        private ContainerReference() {

        }

    }

}
