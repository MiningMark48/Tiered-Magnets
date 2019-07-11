package com.miningmark48.tieredmagnets.network;

import com.miningmark48.tieredmagnets.network.packets.*;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
    private static int packetId = 0;

    public enum Side {
        CLIENT, SERVER, BOTH;
    }

    public static void registerMessages() {
        // Server side
        registerMessage(PacketToggleMagnet.Handler.class, PacketToggleMagnet.class, Side.SERVER);
        registerMessage(PacketFilterToggle.Handler.class, PacketFilterToggle.class, Side.SERVER);
        registerMessage(PacketChangeRangeMagnetFilter.Handler.class, PacketChangeRangeMagnetFilter.class, Side.SERVER);
        registerMessage(PacketChangeRangeInsulator.Handler.class, PacketChangeRangeInsulator.class, Side.SERVER);
        registerMessage(PacketTogglePreview.Handler.class, PacketTogglePreview.class, Side.SERVER);
    }

    private static void registerMessage(Class handler, Class packet, Side side) {
        if (side != Side.CLIENT)
            registerMessage(handler, packet, net.minecraftforge.fml.relauncher.Side.SERVER);

        if (side != Side.SERVER)
            registerMessage(handler, packet, net.minecraftforge.fml.relauncher.Side.CLIENT);
    }

    private static void registerMessage(Class handler, Class packet, net.minecraftforge.fml.relauncher.Side side) {
        INSTANCE.registerMessage(handler, packet, packetId++, side);
    }

}
