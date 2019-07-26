package com.miningmark48.tieredmagnets.network.packets;

import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketChangeRangeMagnetFilter extends PacketEmpty {

    private int rangeChange;

    public PacketChangeRangeMagnetFilter(int rangeChange) {
        this.rangeChange = rangeChange;
    }

    public static void encode(PacketChangeRangeMagnetFilter msg, PacketBuffer buffer) {
        buffer.writeInt(msg.rangeChange);
    }

    public static PacketChangeRangeMagnetFilter decode(PacketBuffer buffer) {
        return new PacketChangeRangeMagnetFilter(buffer.readInt());
    }

    public static class Handler {

        public static void handle(PacketChangeRangeMagnetFilter msg, Supplier<NetworkEvent.Context> ctx) {
            ServerPlayerEntity playerEntity = ctx.get().getSender();

            ItemStack heldItem = ItemMagnetBase.getMagnet(playerEntity);
            if (heldItem.isEmpty()) return;

            if (heldItem.getItem() instanceof ItemMagnetBase) {
                ItemMagnetBase magnet = (ItemMagnetBase) heldItem.getItem();

                int newRange = magnet.getRange(heldItem) + msg.rangeChange;
                if (newRange > magnet.getDefaultRange()) {
                    magnet.setRange(heldItem, magnet.getDefaultRange());
                } else if (newRange <= 0) {
                    magnet.setRange(heldItem, 1);
                } else {
                    magnet.setRange(heldItem, newRange);
                }

            }
        }
    }

}
