package com.miningmark48.tieredmagnets.network.packets;

import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

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

    public static void encode(PacketChangeRangeInsulator msg, PacketBuffer buffer) {
        buffer.writeBlockPos(msg.pos);
        buffer.writeInt(msg.rangeChange);
    }

    public static PacketChangeRangeInsulator decode(PacketBuffer buffer) {
        return new PacketChangeRangeInsulator(buffer.readBlockPos(), buffer.readInt());
    }

    public static class Handler {

        @SuppressWarnings("Duplicates")
        public static void handle(PacketChangeRangeInsulator msg, Supplier<NetworkEvent.Context> ctx) {
            ServerPlayerEntity playerEntity = ctx.get().getSender();
            assert playerEntity != null;
            World world = playerEntity.world;
            BlockPos pos = msg.pos;
            TileEntity te = world.getTileEntity(pos);

            if (te == null) return;

            if (te instanceof TileEntityMagneticInsulator) {
                TileEntityMagneticInsulator mi = (TileEntityMagneticInsulator) te;

                int newRange = mi.getRange() + msg.rangeChange;
                if (newRange > mi.getDefaultRange()) {
                    mi.setRange(mi.getDefaultRange());
                } else if (newRange <= 0) {
                    mi.setRange(1);
                } else {
                    mi.setRange(newRange);
                }
                mi.markDirty();
            }

        }
    }

}
