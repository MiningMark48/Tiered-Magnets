package com.miningmark48.magnets.network.packets;

import com.miningmark48.magnets.tileentity.TileEntityMagneticInsulator;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTogglePreview extends PacketEmpty {

    private BlockPos pos;

    public PacketTogglePreview() {

    }

    public PacketTogglePreview(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
    }

    public static class Handler implements IMessageHandler<PacketTogglePreview, IMessage> {
        @Override
        public IMessage onMessage(PacketTogglePreview message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        @SuppressWarnings("Duplicates")
        private void handle(PacketTogglePreview message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.world;
            BlockPos pos = message.pos;
            TileEntity te = world.getTileEntity(pos);

            if (te == null) return;

            if (te instanceof TileEntityMagneticInsulator) {
                TileEntityMagneticInsulator mi = (TileEntityMagneticInsulator) te;

                mi.setDoPreview(!mi.getDoPreview());

                mi.markDirty();
            }

        }
    }

}
