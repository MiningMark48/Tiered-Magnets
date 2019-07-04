package com.miningmark48.magnets.util;

import org.lwjgl.input.Keyboard;

public class KeyChecker {

    public static boolean isHoldingShift(){
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
            return true;
        }else{
            return false;
        }
    }

}
