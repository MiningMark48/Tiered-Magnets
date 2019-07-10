package com.miningmark48.magnets.client.events;

import com.miningmark48.magnets.client.particle.ParticleMagnetizeEnergy;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventStitchParticles {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void stitchTexture(TextureStitchEvent.Pre event)
    {
        event.getMap().registerSprite(ParticleMagnetizeEnergy.texture);
    }

}
