package com.miningmark48.magnets.client.particle;

import com.miningmark48.magnets.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleMagnetizeVanilla extends Particle {

    public static ResourceLocation texture = new ResourceLocation(Reference.MOD_ID+ ":particles/magnetize");

    public ParticleMagnetizeVanilla(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        this.particleRed = (float) 0;
        this.particleGreen = (float) 0;
        this.particleBlue = (float) 0;

        this.particleAge = ParticleMagnetizeEnergy.life;

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
