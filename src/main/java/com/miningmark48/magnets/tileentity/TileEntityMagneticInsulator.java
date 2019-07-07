package com.miningmark48.magnets.tileentity;

import com.miningmark48.magnets.block.BlockMagneticInsulator;
import com.miningmark48.magnets.init.ModBlocks;
import com.miningmark48.magnets.init.ModConfig;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class TileEntityMagneticInsulator extends TileEntity implements ITickable {

    private int range = ModConfig.insulatorConfigs.range;
    private boolean doPreview = false;

    public TileEntityMagneticInsulator() {

    }

    @Override
    public void update() {
        World world = getWorld();
        BlockPos pos = getPos();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int r = getRange();

        List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - r, y - r, z - r, x + r, y + r, z + r));
        if (!world.isBlockPowered(pos)) {
            items.forEach(item -> item.getEntityData().setBoolean("noMagnet", true));
        } else {
            items.forEach(item -> item.getEntityData().setBoolean("noMagnet", false));
        }

        if (getDoPreview()) {
            double offset = 0.5D;
            double pSpeed = 0.075D * (r * 0.5D);
            EnumParticleTypes particle = EnumParticleTypes.FLAME;

            // Square - Corner 1
            world.spawnParticle(particle, x - r + offset, y + offset * 2, z - r + offset, 0D, 0D, pSpeed);
            world.spawnParticle(particle, x - r + offset, y + offset * 2, z - r + offset, pSpeed, 0D, 0D);
            // Square - Corner 2
            world.spawnParticle(particle, x + r + offset, y + offset * 2, z + r + offset, 0D, 0D, -pSpeed);
            world.spawnParticle(particle, x + r + offset, y + offset * 2, z + r + offset, -pSpeed, 0D, 0D);
            // Square - Corner 3
            world.spawnParticle(particle, x + r + offset, y + offset * 2, z - r + offset, -pSpeed, 0D, 0D);
            world.spawnParticle(particle, x + r + offset, y + offset * 2, z - r + offset, 0D, 0D, pSpeed);
            // Square - Corner 4
            world.spawnParticle(particle, x - r + offset, y + offset * 2, z + r + offset, pSpeed, 0D, 0D);
            world.spawnParticle(particle, x - r + offset, y + offset * 2, z + r + offset, 0D, 0D, -pSpeed);
            // Verticals
            world.spawnParticle(particle, x + offset, y + r + offset, z + offset, 0D, -pSpeed, 0D);
            world.spawnParticle(particle, x + offset, y - r + offset, z + offset, 0D, pSpeed, 0D);
            // Vert Cross - Up
            world.spawnParticle(particle, x + offset, y + r + offset, z + offset, 0D, 0D, pSpeed);
            world.spawnParticle(particle, x + offset, y + r + offset, z + offset, 0D, 0D, -pSpeed);
            world.spawnParticle(particle, x + offset, y + r + offset, z + offset, pSpeed, 0D, 0D);
            world.spawnParticle(particle, x + offset, y + r + offset, z + offset, -pSpeed, 0D, 0D);
            // Vert Cross - Down
            world.spawnParticle(particle, x + offset, y - r + offset, z + offset, 0D, 0D, pSpeed);
            world.spawnParticle(particle, x + offset, y - r + offset, z + offset, 0D, 0D, -pSpeed);
            world.spawnParticle(particle, x + offset, y - r + offset, z + offset, pSpeed, 0D, 0D);
            world.spawnParticle(particle, x + offset, y - r + offset, z + offset, -pSpeed, 0D, 0D);

        }

        if (this.world.getBlockState(pos).getBlock() == ModBlocks.BlockMagneticInsulator){
            BlockMagneticInsulator solar = (BlockMagneticInsulator) this.world.getBlockState(pos).getBlock();
            solar.setState(this.world, this.pos, world.isBlockPowered(pos));
        }

    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getDefaultRange() {
        return ModConfig.insulatorConfigs.range;
    }

    public boolean getDoPreview() {
        return doPreview;
    }

    public void setDoPreview(boolean doPreview) {
        this.doPreview = doPreview;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("range")) {
            setRange(compound.getInteger("range"));
        }
        if (compound.hasKey("doPreview")) {
            setDoPreview(compound.getBoolean("doPreview"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("range", getRange());
        compound.setBoolean("doPreview", getDoPreview());
        return compound;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(pos, getBlockMetadata(), this.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public void sendUpdates() {
        world.markBlockRangeForRenderUpdate(pos, pos);
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
        markDirty();
    }

}
