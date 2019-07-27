package com.miningmark48.tieredmagnets.init.registry.container;

import com.google.common.base.Preconditions;
import com.miningmark48.tieredmagnets.init.registry.RegistryObjectBuilder;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.util.ModLogger;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RegistryContainer<T extends IForgeRegistryEntry<T>, B extends RegistryObjectBuilder<T, ?>> {
    private Set<B> builders;
    public RegistryContainer() {
        builders = new HashSet<>();
    }

    public void add(B builder){
        Preconditions.checkArgument(!builders.contains(Objects.requireNonNull(builder)),"Cannot register builder twice!");
        builders.add(builder);
    }

    protected Set<B> getBuilders() {
        return builders;
    }

    public void register(RegistryEvent.Register<T> event) {
        ModLogger.debug(String.format("Registering %s objects to %s.", Reference.MOD_ID, event.getName()));
        for (B builder:getBuilders()) {
            event.getRegistry().register(builder.construct());
        }
        ModLogger.debug(String.format("Finished Registering %s %s objects to %s.", getBuilders().size(), Reference.MOD_ID, event.getName()));
    }

    public void clear() {
        builders.clear();
    }
}
