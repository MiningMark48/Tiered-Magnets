package com.miningmark48.tieredmagnets.client.events;

import com.miningmark48.tieredmagnets.client.KeyBindings;
import com.miningmark48.tieredmagnets.network.PacketHandler;
import com.miningmark48.tieredmagnets.network.packets.PacketToggleMagnet;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT)
public class EventKeyInput {

    @SubscribeEvent
    public static void handleEventInput(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || event.phase == TickEvent.Phase.START)
            return;

        if (KeyBindings.toggleMagnet.isPressed()) {
            PacketHandler.sendToServer(new PacketToggleMagnet());
        }

    }

}
