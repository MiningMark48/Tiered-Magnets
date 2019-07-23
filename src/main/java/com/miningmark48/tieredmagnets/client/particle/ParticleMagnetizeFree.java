package com.miningmark48.tieredmagnets.client.particle;

import net.minecraft.world.World;

public class ParticleMagnetizeFree extends ParticleMagnetize {

    public ParticleMagnetizeFree(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    }

    @Override
    public void onUpdate() {

        if (this.particleAge-- <= 0) {
            this.setExpired();
        }

        if (this.particleAge % 4 == 0) {
            this.particleGreen = (float) 0.25;
        } else if (this.particleAge % 4 == 1) {
            this.particleGreen = (float) 0.5;
        } else if (this.particleAge % 4 == 2) {
            this.particleGreen = (float) 0.75;
        } else {
            this.particleGreen = (float) 1;
        }

    }

}
