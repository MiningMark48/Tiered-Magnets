package com.miningmark48.tieredmagnets.tileentity;

import com.miningmark48.tieredmagnets.block.BlockMagneticInsulator;
import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.init.config.OldConfig;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class TileEntityMagneticInsulator extends TileEntity implements ITickableTileEntity {

    private int range = OldConfig.utilityBlockConfigs.insulatorRange;
    private boolean doPreview = false;

    public TileEntityMagneticInsulator(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public TileEntityMagneticInsulator()
    {
        this(ModBlocks.ModTileEntities.MAGNETIC_INSULATOR);
    }

    @Override
    public void tick() {
        World world = getWorld();
        BlockPos pos = getPos();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int r = getRange();

        List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(x - r, y - r, z - r, x + r, y + r, z + r));
        if (!world.isBlockPowered(pos)) {
            items.forEach(item -> item.getEntityData().putBoolean("noMagnet", true));
        } else {
            items.forEach(item -> item.getEntityData().putBoolean("noMagnet", false));
        }

        if (getDoPreview()) {
            double offset = 0.5D;
            double pSpeed = 0.075D * (r * 0.5D);
            BasicParticleType particle = ParticleTypes.FLAME;

            // Square - Corner 1
            world.addParticle(particle, x - r + offset, y + offset * 2, z - r + offset, 0D, 0D, pSpeed);
            world.addParticle(particle, x - r + offset, y + offset * 2, z - r + offset, pSpeed, 0D, 0D);
            // Square - Corner 2
            world.addParticle(particle, x + r + offset, y + offset * 2, z + r + offset, 0D, 0D, -pSpeed);
            world.addParticle(particle, x + r + offset, y + offset * 2, z + r + offset, -pSpeed, 0D, 0D);
            // Square - Corner 3
            world.addParticle(particle, x + r + offset, y + offset * 2, z - r + offset, -pSpeed, 0D, 0D);
            world.addParticle(particle, x + r + offset, y + offset * 2, z - r + offset, 0D, 0D, pSpeed);
            // Square - Corner 4
            world.addParticle(particle, x - r + offset, y + offset * 2, z + r + offset, pSpeed, 0D, 0D);
            world.addParticle(particle, x - r + offset, y + offset * 2, z + r + offset, 0D, 0D, -pSpeed);
            // Verticals
            world.addParticle(particle, x + offset, y + r + offset, z + offset, 0D, -pSpeed, 0D);
            world.addParticle(particle, x + offset, y - r + offset, z + offset, 0D, pSpeed, 0D);
            // Vert Cross - Up
            world.addParticle(particle, x + offset, y + r + offset, z + offset, 0D, 0D, pSpeed);
            world.addParticle(particle, x + offset, y + r + offset, z + offset, 0D, 0D, -pSpeed);
            world.addParticle(particle, x + offset, y + r + offset, z + offset, pSpeed, 0D, 0D);
            world.addParticle(particle, x + offset, y + r + offset, z + offset, -pSpeed, 0D, 0D);
            // Vert Cross - Down
            world.addParticle(particle, x + offset, y - r + offset, z + offset, 0D, 0D, pSpeed);
            world.addParticle(particle, x + offset, y - r + offset, z + offset, 0D, 0D, -pSpeed);
            world.addParticle(particle, x + offset, y - r + offset, z + offset, pSpeed, 0D, 0D);
            world.addParticle(particle, x + offset, y - r + offset, z + offset, -pSpeed, 0D, 0D);

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
        return OldConfig.utilityBlockConfigs.insulatorRange;
    }

    public boolean getDoPreview() {
        return doPreview;
    }

    public void setDoPreview(boolean doPreview) {
        this.doPreview = doPreview;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if (compound.hasUniqueId("range")) {
            setRange(compound.getInt("range"));
        }
        if (compound.hasUniqueId("doPreview")) {
            setDoPreview(compound.getBoolean("doPreview"));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("range", getRange());
        compound.putBoolean("doPreview", getDoPreview());
        return compound;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        this.read(pkt.getNbtCompound());
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(pos, 0, this.write(new CompoundNBT()));
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }

    public void sendUpdates() {
//        world.block(pos, pos);
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
//        world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
        markDirty();
    }

}
