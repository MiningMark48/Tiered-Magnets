package com.miningmark48.tieredmagnets.client.particle;

import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.world.World;

import java.awt.*;

public enum EnumParticles {

    VANILLA(new Color(0, 100, 0)),
    ENERGY(new Color(255, 0, 0)),
    FREE(new Color(125, 125, 125));

    private final Color color;

    EnumParticles(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void spawnParticle(World world, double x, double y, double z, float alpha) {
        world.addParticle(new RedstoneParticleData(convertColor(getColor().getRed()), convertColor(getColor().getGreen()), convertColor(getColor().getBlue()), alpha), x, y, z, 0, 0, 0);
    }

    private float convertColor(int color) {
        return (float) color / 255;
    }

}
