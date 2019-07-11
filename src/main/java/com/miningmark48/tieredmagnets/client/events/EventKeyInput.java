package com.miningmark48.tieredmagnets.client.events;

import com.miningmark48.tieredmagnets.client.KeyBindings;
import com.miningmark48.tieredmagnets.network.PacketHandler;
import com.miningmark48.tieredmagnets.network.packets.PacketToggleMagnet;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class EventKeyInput {

    @SubscribeEvent
    public static void onKeyInput(@SuppressWarnings("unused") InputEvent.KeyInputEvent event) {
        handleEventInput();
    }

    @SubscribeEvent
    public static void onMouseInput(@SuppressWarnings("unused") InputEvent.MouseInputEvent event) {
        handleEventInput();
    }

    private static void handleEventInput() {
        if (KeyBindings.toggleMagnet.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new PacketToggleMagnet());
        }
    }

}
