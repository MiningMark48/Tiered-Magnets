package com.miningmark48.tieredmagnets.client.particle;

import com.miningmark48.tieredmagnets.client.particle.base.ParticleMagnetize;
import net.minecraft.world.World;

public class ParticleMagnetizeVanilla extends ParticleMagnetize {

    public ParticleMagnetizeVanilla(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn);
    }

    @Override
    public void tick() {

        if (this.age-- <= 0) {
            this.setExpired();
        }

        if (this.age % 4 == 0) {
            this.particleBlue = (float) 0.25;
        } else if (this.age % 4 == 1) {
            this.particleBlue = (float) 0.5;
        } else if (this.age % 4 == 2) {
            this.particleBlue = (float) 0.75;
        } else {
            this.particleBlue = (float) 1;
        }

    }

}
