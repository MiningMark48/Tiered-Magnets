package com.miningmark48.tieredmagnets.util;

public class UtilColors {

    public static float[] getWheelColor(float pos) {
        if (pos < 85.0f) { return new float[] { pos * 3.0F, 255.0f - pos * 3.0f, 0.0f }; }
        if (pos < 170.0f) { return new float[] { 255.0f - (pos -= 85.0f) * 3.0f, 0.0f, pos * 3.0f }; }
        return new float[] { 0.0f, (pos -= 170.0f) * 3.0f, 255.0f - pos * 3.0f };
    }

}
