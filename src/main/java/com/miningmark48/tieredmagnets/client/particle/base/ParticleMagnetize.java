package com.miningmark48.tieredmagnets.client.particle.base;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class ParticleMagnetize extends Particle {

    public static ResourceLocation texture = new ResourceLocation(Reference.MOD_ID+ ":particles/magnetize");
    private static int life = 8;

    public ParticleMagnetize(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0, 0, 0);

        this.particleRed = (float) 0;
        this.particleGreen = (float) 0;
        this.particleBlue = (float) 0;

        this.age = life;

//        this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString()));
    }
//
//    @Override
//    public int getFXLayer() {
//        return 1;
//    }


    @Override
    public void renderParticle(BufferBuilder bufferBuilder, ActiveRenderInfo activeRenderInfo, float v, float v1, float v2, float v3, float v4, float v5) {

    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.CUSTOM;
    }

    public enum Particles {
        ENERGY,
        FREE,
        VANILLA
    }

}
