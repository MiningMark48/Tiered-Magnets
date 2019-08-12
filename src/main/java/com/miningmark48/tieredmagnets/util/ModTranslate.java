package com.miningmark48.tieredmagnets.util;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModTranslate {

    public static String toLocal(String key){
        return I18n.format(key);
    }

}
