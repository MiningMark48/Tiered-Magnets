package com.miningmark48.tieredmagnets.init.registry.container;

import net.minecraft.inventory.container.ContainerType;

public class ScreenContainerRegistryContainer extends RegistryContainer<ContainerType<?>, ScreenContainerObjectBuilder> {

    public ScreenContainerRegistryContainer() {
        super();
    }

    public void clientSetup() {
        for (ScreenContainerObjectBuilder builder : getBuilders()) {
            builder.registerScreen();
        }
    }


}
