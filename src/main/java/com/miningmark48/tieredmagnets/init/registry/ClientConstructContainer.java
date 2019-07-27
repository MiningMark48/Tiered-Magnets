package com.miningmark48.tieredmagnets.init.registry;

import com.miningmark48.tieredmagnets.init.registry.container.RegistryContainer;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ClientConstructContainer<T extends IForgeRegistryEntry<T>, B extends RegistryObjectBuilder<T, ?>> extends RegistryContainer<T, B> {

    public void clientInit() {

    }

}
