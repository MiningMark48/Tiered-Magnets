package com.miningmark48.magnets.network.packets;

import com.miningmark48.magnets.item.base.ItemMagnetBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketChangeRange extends PacketEmpty {

    private int rangeChange;

    public PacketChangeRange() {
        rangeChange = 0;
    }

    public PacketChangeRange(int rangeChange) {
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

    public static class Handler implements IMessageHandler<PacketChangeRange, IMessage> {
        @Override
        public IMessage onMessage(PacketChangeRange message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketChangeRange message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;

            ItemStack heldItem = ItemMagnetBase.getMagnet(playerEntity);
            if (heldItem.isEmpty()) return;

            if (heldItem.getItem() instanceof ItemMagnetBase) {
                ItemMagnetBase magnet = (ItemMagnetBase) heldItem.getItem();

                magnet.setRange(magnet.getRange() + message.rangeChange);

            }
        }
    }

}
