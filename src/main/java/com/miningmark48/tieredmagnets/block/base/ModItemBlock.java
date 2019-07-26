package com.miningmark48.tieredmagnets.block.base;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

import java.util.Objects;

public class ModItemBlock extends BlockItem {

    public ModItemBlock(Block block, Properties properties) {
        super(block, properties);

        setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

}
