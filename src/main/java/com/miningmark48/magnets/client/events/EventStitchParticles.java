package com.miningmark48.magnets.client.events;

import com.miningmark48.magnets.client.particle.ParticleMagnetizeEnergy;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventStitchParticles {

    @SubscribeEvent
    public void stitchTexture(TextureStitchEvent.Pre event)
    {
        event.getMap().registerSprite(ParticleMagnetizeEnergy.texture);
    }

}
