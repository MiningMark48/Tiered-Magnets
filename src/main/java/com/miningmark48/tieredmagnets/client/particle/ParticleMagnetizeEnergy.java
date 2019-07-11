package com.miningmark48.tieredmagnets.client.particle;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleMagnetizeEnergy extends Particle {

    public static ResourceLocation texture = new ResourceLocation(Reference.MOD_ID+ ":particles/magnetize");
    public static int life = 8;

    public ParticleMagnetizeEnergy(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        this.particleRed = (float) 0;
        this.particleGreen = (float) 0;
        this.particleBlue = (float) 0;

        this.particleAge = life;

        this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString()));

    }

    @Override
    public int getFXLayer() {
        return 1;
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
