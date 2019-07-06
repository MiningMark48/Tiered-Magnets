package com.miningmark48.magnets.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketChangeRangeInsulator extends PacketEmpty {

    private BlockPos pos;
    private int rangeChange;

    public PacketChangeRangeInsulator() {
        rangeChange = 0;
    }

    public PacketChangeRangeInsulator(BlockPos pos, int rangeChange) {
        this.pos = pos;
        this.rangeChange = rangeChange;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        rangeChange = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(rangeChange);
    }

    public static class Handler implements IMessageHandler<PacketChangeRangeInsulator, IMessage> {
        @Override
        public IMessage onMessage(PacketChangeRangeInsulator message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketChangeRangeInsulator message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.world;
            BlockPos pos = message.pos;
            TileEntity te = world.getTileEntity(pos);

            if (te == null) return;

            te.getTileData().setInteger("range", te.getTileData().getInteger("range") + message.rangeChange);
            te.markDirty();

        }
    }

}
