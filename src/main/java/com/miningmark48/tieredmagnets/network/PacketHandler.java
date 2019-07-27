package com.miningmark48.tieredmagnets.network;

import com.miningmark48.tieredmagnets.network.packets.*;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = Integer.toString(2);
    private static short packetId = 0;

    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Reference.MOD_ID, "main_network_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void registerMessages() {
        // Server side

        registerMessage(PacketToggleMagnet.class, PacketToggleMagnet::encode, PacketToggleMagnet::decode, PacketToggleMagnet.Handler::handle);
        registerMessage(PacketChangeRangeInsulator.class, PacketChangeRangeInsulator::encode, PacketChangeRangeInsulator::decode, PacketChangeRangeInsulator.Handler::handle);
        registerMessage(PacketChangeRangeMagnetFilter.class, PacketChangeRangeMagnetFilter::encode, PacketChangeRangeMagnetFilter::decode, PacketChangeRangeMagnetFilter.Handler::handle);
        registerMessage(PacketTogglePreview.class, PacketTogglePreview::encode, PacketTogglePreview::decode, PacketTogglePreview.Handler::handle);
        registerMessage(PacketFilterToggle.class, PacketFilterToggle::encode, PacketFilterToggle::decode, PacketFilterToggle.Handler::handle);

//        registerMessage(PacketFilterToggle.Handler.class, PacketFilterToggle.class, Side.SERVER);
    }

    public static void sendTo(Object msg, ServerPlayerEntity player) {
        if (!(player instanceof FakePlayer)) INSTANCE.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }

    private static <MSG> void registerMessage(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
        INSTANCE.registerMessage(packetId, messageType, encoder, decoder, messageConsumer);
        packetId++;
        if (packetId > 0xFF) throw new RuntimeException("Too many messages!");
    }

}
