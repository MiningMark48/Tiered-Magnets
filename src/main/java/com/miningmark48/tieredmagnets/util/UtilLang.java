package com.miningmark48.tieredmagnets.util;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public final class UtilLang {

    private UtilLang() {

    }

    public static String getLangKeyPrefix(String type, String... args) {
        return getLangKey(type, args) + ".";
    }

    public static String getLangKey(String type, String... args) {
        return String.join(".", type, Reference.MOD_ID, String.join(".", args));
    }

    public static String getFormattedBlockName(BlockState block) {
        return getFormattedBlockName(block.getBlock());
    }

    public static String getFormattedBlockName(Block block) {
        return block.getNameTextComponent().getFormattedText();
    }

}
