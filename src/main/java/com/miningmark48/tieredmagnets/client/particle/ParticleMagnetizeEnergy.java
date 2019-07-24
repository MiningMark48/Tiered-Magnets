package com.miningmark48.tieredmagnets.client.particle;

import com.miningmark48.tieredmagnets.client.particle.base.ParticleMagnetize;
import net.minecraft.world.World;

public class ParticleMagnetizeEnergy extends ParticleMagnetize {

    public ParticleMagnetizeEnergy(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn);
    }

    @Override
    public void onUpdate() {

        if (this.particleAge-- <= 0) {
            this.setExpired();
        }

        if (this.particleAge % 4 == 0) {
            this.particleRed = (float) 0.25;
        } else if (this.particleAge % 4 == 1) {
            this.particleRed = (float) 0.5;
        } else if (this.particleAge % 4 == 2) {
            this.particleRed = (float) 0.75;
        } else {
            this.particleRed = (float) 1;
        }

    }

}
