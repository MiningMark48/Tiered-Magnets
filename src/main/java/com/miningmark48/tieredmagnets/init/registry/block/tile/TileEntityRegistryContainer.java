package com.miningmark48.tieredmagnets.init.registry.block.tile;

import com.miningmark48.tieredmagnets.init.registry.ClientConstructContainer;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.util.ModLogger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TileEntityRegistryContainer extends ClientConstructContainer<TileEntityType<?>, TileEntityBuilder<?>> {

    private Map<ResourceLocation, TileEntityBuilder<?>> reverseMapping;

    public TileEntityRegistryContainer() {
        this.reverseMapping = new HashMap<>();
    }

    @Override
    public void clientInit() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerTERs);
    }

    @Override
    public void add(TileEntityBuilder<?> builder) {
        super.add(builder);
        reverseMapping.put(builder.getRegistryName(), builder);
    }

    private void registerTERs(FMLClientSetupEvent event) {
        ModLogger.info("Registering %s TileEntityRenderer's", Reference.MOD_ID);
        int count = 0;
        for (TileEntityBuilder<?> builder : getBuilders()) {
            if (builder.hasRenderer()) {
                builder.registerRenderer();
                ++ count;
            }
        }
        ModLogger.info("Finished registering %s %s TileEntityRenderer's", count, Reference.MOD_ID);
    }

    @Nullable
    public TileEntityBuilder<?> getBuilderWithId(ResourceLocation id) {
        return reverseMapping.get(Objects.requireNonNull(id));
    }

    @Override
    public void clear() {
        super.clear();
        this.reverseMapping.clear();
    }

}
