package com.miningmark48.magnets.network.packets;

import com.miningmark48.magnets.item.base.ItemMagnetBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketChangeRangeMagnetFilter extends PacketEmpty {

    private int rangeChange;

    public PacketChangeRangeMagnetFilter() {
        rangeChange = 0;
    }

    public PacketChangeRangeMagnetFilter(int rangeChange) {
        this.rangeChange = rangeChange;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        rangeChange = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(rangeChange);
    }

    public static class Handler implements IMessageHandler<PacketChangeRangeMagnetFilter, IMessage> {
        @Override
        public IMessage onMessage(PacketChangeRangeMagnetFilter message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketChangeRangeMagnetFilter message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;

            ItemStack heldItem = ItemMagnetBase.getMagnet(playerEntity);
            if (heldItem.isEmpty()) return;

            if (heldItem.getItem() instanceof ItemMagnetBase) {
                ItemMagnetBase magnet = (ItemMagnetBase) heldItem.getItem();

                int newRange = magnet.getRange(heldItem) + message.rangeChange;
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
