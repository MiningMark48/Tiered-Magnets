package com.miningmark48.tieredmagnets.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import org.lwjgl.glfw.GLFW;

public class KeyChecker {

    public static boolean isHoldingShift(){
        return isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT) || isKeyDown(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    private static boolean isKeyDown(int keycode) {
        return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), keycode);
    }

}
