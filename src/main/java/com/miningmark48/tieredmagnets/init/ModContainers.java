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
import com.miningmark48.tieredmagnets.reference.ReferenceContainers;
import net.minecraft.inventory.container.ContainerType;
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

    @ObjectHolder(ReferenceContainers.MAGNET_FILTER)
    public static ContainerType<ContainerMagnetFilter> CONTAINER_MAGNET_FILTER;
    @ObjectHolder(ReferenceContainers.MAGNETIC_INSULATOR)
    public static ContainerType<ContainerMagneticInsulator> CONTAINER_MAGNETIC_INSULATOR;
    @ObjectHolder(ReferenceContainers.MAGNETIC_PROJECTOR)
    public static ContainerType<ContainerMagneticInsulator> CONTAINER_MAGNETIC_PROJECTOR;

    @SuppressWarnings({"Convert2Diamond"})
    public static void init() {
        container.add(
                new ScreenContainerObjectBuilder(ReferenceContainers.MAGNET_FILTER_RL)
                        .builder(new ScreenContainerBuilder<ContainerMagnetFilter, GuiMagnetFilter>(
                                ContainerMagnetFilter::new, () -> () -> GuiMagnetFilter::new))
        );
        container.add(
                new ScreenContainerObjectBuilder(ReferenceContainers.MAGNETIC_INSULATOR_RL)
                        .builder(new ScreenContainerBuilder<ContainerMagneticInsulator, GuiMagneticInsulator>(
                                ContainerMagneticInsulator::new, () -> () -> GuiMagneticInsulator::new))
        );
        container.add(
                new ScreenContainerObjectBuilder(ReferenceContainers.MAGNETIC_PROJECTOR_RL)
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

}
