package com.miningmark48.tieredmagnets.init.registry.block;

import com.miningmark48.tieredmagnets.init.registry.RegistryObjectBuilder;
import com.miningmark48.tieredmagnets.init.registry.block.tile.TileEntityBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BlockBuilder extends RegistryObjectBuilder<Block, Block.Properties> {

    private Block block;
    private BiFunction<Block, Item.Properties, Item> itemBlockFactory;
    private Item.Properties itemBuilder;
    private boolean hasItem;
    private ResourceLocation tileEntityId;
    private TileEntityBuilder<?> tileEntityBuilder;

    public static BlockBuilder create(String registryName) {
        return new BlockBuilder(registryName);
    }

    public static BlockBuilder create(ResourceLocation registryName) {
        return new BlockBuilder(registryName);
    }

    public BlockBuilder(String registryName) {
        super(registryName);
        this.itemBlockFactory = BlockItem::new;
        this.itemBuilder = new Item.Properties();
        hasItem = true;
    }

    public BlockBuilder(ResourceLocation registryName) {
        super(registryName);
        this.itemBlockFactory = BlockItem::new;
        this.itemBuilder = new Item.Properties();
        hasItem = true;
    }

    public BlockBuilder item(Item.Properties itemBuilder, BiFunction<Block, Item.Properties, Item> itemBlockFactory) {
        this.itemBuilder = Objects.requireNonNull(itemBuilder);
        this.itemBlockFactory = Objects.requireNonNull(itemBlockFactory);
        return this;
    }

    public BlockBuilder item(Item.Properties itemBuilder) {
        return item(itemBuilder,itemBlockFactory);
    }

    public BlockBuilder setHasNoItem() {
        hasItem = false;
        return this;
    }

    @Override
    public BlockBuilder factory(Function<Block.Properties, Block> factory) {
        return (BlockBuilder) super.factory(factory);
    }

    @Override
    public BlockBuilder builder(Block.Properties builder) {
        return (BlockBuilder) super.builder(builder);
    }

    @Override
    public Block construct() {
        block =  super.construct();
        return block;
    }

    public BlockBuilder withTileEntity(ResourceLocation tileId) {
        this.tileEntityId = Objects.requireNonNull(tileId);
        return this;
    }

    public BlockBuilder withTileEntity(TileEntityBuilder<?> tileBuilder) {
        this.tileEntityId = Objects.requireNonNull(tileBuilder.getRegistryName());
        this.tileEntityBuilder = Objects.requireNonNull(tileBuilder);
        this.tileEntityBuilder.addValidBlock(getRegistryName());
        return this;
    }

    Item createItemFromBlock() {
        return itemBlockFactory.apply(block,itemBuilder).setRegistryName(getRegistryName());
    }

    boolean hasItem() {
        return hasItem;
    }

    ResourceLocation getTileEntityId() {
        return tileEntityId;
    }

    TileEntityBuilder<?> getTileEntityBuilder() {
        return tileEntityBuilder;
    }

}
