package com.miningmark48.magnets.util;

import net.minecraft.client.gui.FontRenderer;

public class UtilGui {

    public static int getXCenter(String text, FontRenderer renderer, int width){
        int x = (width / 2) - (renderer.getStringWidth(text) / 2);
        return x;
    }

}
