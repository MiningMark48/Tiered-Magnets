package com.miningmark48.tieredmagnets.network.packets;

import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketTogglePreview extends PacketEmpty {

    private BlockPos pos;

    public PacketTogglePreview() {

    }

    public PacketTogglePreview(BlockPos pos) {
        this.pos = pos;
    }

    public static void encode(PacketTogglePreview msg, PacketBuffer buffer) {
        buffer.writeBlockPos(msg.pos);
    }

    public static PacketTogglePreview decode(PacketBuffer buffer) {
        return new PacketTogglePreview(buffer.readBlockPos());
    }

    public static class Handler  {

        @SuppressWarnings("Duplicates")
        public static void handle(PacketTogglePreview msg, Supplier<NetworkEvent.Context> ctx) {
            ServerPlayerEntity playerEntity = ctx.get().getSender();
            assert playerEntity != null;
            World world = playerEntity.world;
            BlockPos pos = msg.pos;
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
