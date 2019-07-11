package com.miningmark48.tieredmagnets.block.base;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

public class ModItemBlock extends ItemBlock {

    public ModItemBlock(Block block) {
        super(block);

        setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

}
