package com.miningmark48.tieredmagnets.init.registry;

import com.google.common.base.Preconditions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Objects;
import java.util.function.Function;

public class RegistryObjectBuilder<T extends IForgeRegistryEntry<T>, B> {
    private final ResourceLocation registryName;
    private Function<B,T> factory;
    private B builder;

    public RegistryObjectBuilder(String registryName) {
        this(new ResourceLocation(Objects.requireNonNull(registryName)));
    }

    public RegistryObjectBuilder(ResourceLocation registryName) {
        this.registryName = Objects.requireNonNull(registryName);
    }

    public RegistryObjectBuilder<T,B> factory(Function<B,T> factory) {
        this.factory = Objects.requireNonNull(factory);
        return this;
    }

    public RegistryObjectBuilder<T,B> builder(B builder) {
        this.builder = Objects.requireNonNull(builder);
        return this;
    }

    protected ResourceLocation getRegistryName() {
        return registryName;
    }

    public T construct() {
        Preconditions.checkState(factory!=null,"Cannot construct %s without a Factory function!",getRegistryName());
        Preconditions.checkState(builder!=null,"Cannot construct %s without a Builder!",getRegistryName());
        return factory.apply(builder).setRegistryName(getRegistryName());
    }

    protected B getBuilder() {
        return builder;
    }
}
