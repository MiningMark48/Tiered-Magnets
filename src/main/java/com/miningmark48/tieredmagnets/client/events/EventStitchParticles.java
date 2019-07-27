package com.miningmark48.tieredmagnets.client.events;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT)
public class EventStitchParticles {

    @SubscribeEvent
    public void stitchTexture(TextureStitchEvent.Pre event)
    {
//        event.getMap().registerSprite(ParticleMagnetizeEnergy.texture);
    }

}
