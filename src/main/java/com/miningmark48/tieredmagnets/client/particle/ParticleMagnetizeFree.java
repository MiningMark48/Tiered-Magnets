package com.miningmark48.tieredmagnets.client.particle;

import net.minecraft.world.World;

public class ParticleMagnetizeFree extends ParticleMagnetize {

    public ParticleMagnetizeFree(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn);
    }

    @Override
    public void onUpdate() {

        if (this.particleAge-- <= 0) {
            this.setExpired();
        }

        if (this.particleAge % 4 == 0) {
            this.particleRed = (float) 0;
            this.particleGreen = (float) 0;
            this.particleBlue = (float) 0;
        } else if (this.particleAge % 4 == 1) {
            this.particleRed = (float) 0.25;
            this.particleGreen = (float) 0.25;
            this.particleBlue = (float) 0.25;
        } else if (this.particleAge % 4 == 2) {
            this.particleRed = (float) 0.5;
            this.particleGreen = (float) 0.5;
            this.particleBlue = (float) 0.5;
        } else {
            this.particleRed = (float) 0.75;
            this.particleGreen = (float) 0.75;
            this.particleBlue = (float) 0.75;
        }

    }

}
