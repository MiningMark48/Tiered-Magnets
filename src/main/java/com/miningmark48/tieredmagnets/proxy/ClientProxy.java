package com.miningmark48.tieredmagnets.proxy;

import com.miningmark48.tieredmagnets.client.KeyBindings;
import com.miningmark48.tieredmagnets.init.registry.ModObjects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientProxy {

    public static void clientSetup(final IEventBus eventBus) {
        DeferredWorkQueue.runLater(KeyBindings::init);
        ModObjects.clientSetup();
    }

}
