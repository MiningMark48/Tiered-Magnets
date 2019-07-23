package com.miningmark48.tieredmagnets.client.particle;

import net.minecraft.world.World;

public class ParticleMagnetizeVanilla extends ParticleMagnetize {

    public ParticleMagnetizeVanilla(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    }

    @Override
    public void onUpdate() {

        if (this.particleAge-- <= 0) {
            this.setExpired();
        }

        if (this.particleAge % 4 == 0) {
            this.particleBlue = (float) 0.25;
        } else if (this.particleAge % 4 == 1) {
            this.particleBlue = (float) 0.5;
        } else if (this.particleAge % 4 == 2) {
            this.particleBlue = (float) 0.75;
        } else {
            this.particleBlue = (float) 1;
        }

    }

}
