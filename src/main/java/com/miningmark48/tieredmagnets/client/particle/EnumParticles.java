package com.miningmark48.tieredmagnets.client.particle;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.world.World;

import java.awt.*;

public enum EnumParticles {

    VANILLA(new Color(0, 100, 0)),
    ENERGY(new Color(255, 0, 0)),
    FREE(new Color(125, 125, 125)),
    FLAME(ParticleTypes.FLAME);

    private final Color color;
    private final BasicParticleType basicParticle;

    EnumParticles(Color color) {
        this.color = color;
        this.basicParticle = null;
    }

    EnumParticles(BasicParticleType basicParticle) {
        this.basicParticle = basicParticle;
        this.color = null;
    }

    public void spawnParticleRedstone(World world, double x, double y, double z, float alpha) {
        spawnParticleRedstone(world, x, y, z, 0, 0, 0, alpha);
    }

    public void spawnParticleRedstone(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, float alpha) {
        if (color == null) return;
        world.addParticle(new RedstoneParticleData(convertColor(color.getRed()), convertColor(color.getGreen()), convertColor(color.getBlue()), alpha), x, y, z, xSpeed, ySpeed, zSpeed);
    }

    public void spawnParticleBasic(World world, double x, double y, double z) {
        spawnParticleBasic(world, x, y, z, 0, 0, 0);
    }

    public void spawnParticleBasic(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        if (basicParticle == null) return;
        world.addParticle(basicParticle, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    private float convertColor(int color) {
        return (float) color / 255;
    }

}
