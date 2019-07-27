package com.miningmark48.tieredmagnets.init.registry.block;

import com.google.common.base.Preconditions;
import com.miningmark48.tieredmagnets.init.registry.block.tile.TileEntityBuilder;
import com.miningmark48.tieredmagnets.init.registry.block.tile.TileEntityRegistryContainer;
import com.miningmark48.tieredmagnets.init.registry.container.RegistryContainer;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.util.ModLogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class BlockRegistryContainer extends RegistryContainer<Block, BlockBuilder> {

    private final TileEntityRegistryContainer tileContainer;

    public BlockRegistryContainer(TileEntityRegistryContainer tileContainer) {
        this.tileContainer = tileContainer;
    }

    @Override
    public void add(BlockBuilder builder) {
        super.add(builder);
        if (builder.getTileEntityBuilder() != null) {
            tileContainer.add(builder.getTileEntityBuilder());
        } else if (builder.getTileEntityId() != null) {
            TileEntityBuilder<?> tileEntityBuilder = tileContainer.getBuilderWithId(builder.getTileEntityId());
            Preconditions.checkArgument(tileEntityBuilder != null, "Attempted to add Block with TileEntity without constructing a TileEntityType!");
            builder.withTileEntity(tileEntityBuilder);
        }
    }

    public void registerItemBlocks(RegistryEvent.Register<Item> event) {
        ModLogger.debug("Registering {} ItemBlocks", Reference.MOD_ID);
        for (BlockBuilder builder:getBuilders()) {
            if (builder.hasItem())
                event.getRegistry().register(builder.createItemFromBlock());
        }
        ModLogger.debug("Finished Registering {} {} ItemBlock's", getBuilders().size(), Reference.MOD_ID);
    }

}
