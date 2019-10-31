package com.miningmark48.tieredmagnets.tileentity;

import com.miningmark48.tieredmagnets.block.BlockMagneticInsulator;
import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.reference.NBTKeys;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import java.util.List;

public class TileEntityMagneticInsulator extends TileEntity implements ITickableTileEntity {

    private int range = 1;// = OldConfig.utilityBlockConfigs.insulatorRange;
    private boolean doPreview = false;

    public TileEntityMagneticInsulator(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public TileEntityMagneticInsulator()
    {
        this(ModBlocks.MAGNETIC_INSULATOR_TILE.get());
    }

    @Override
    public void tick() {
        World world = getWorld();
        BlockPos pos = getPos();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int r = getRange();

        if (world == null) return;

        List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(x - r, y - r, z - r, x + r, y + r, z + r));
        if (!world.isBlockPowered(pos)) {
            items.forEach(item -> item.getPersistentData().putBoolean(NBTKeys.NO_MAGNET.getKey(), true));
        } else {
            items.forEach(item -> item.getPersistentData().putBoolean(NBTKeys.NO_MAGNET.getKey(), false));
        }

//        if (getDoPreview()) {
//            double offset = 0.5D;
//            double pSpeed = 0.075D * (r * 0.5D);
//            EnumParticles particle = EnumParticles.FLAME;
//
//            // Square - Corner 1
//            particle.spawnParticleBasic(world, x - r + offset, y + offset * 2, z - r + offset, 0D, 0D, pSpeed);
//            particle.spawnParticleBasic(world, x - r + offset, y + offset * 2, z - r + offset, pSpeed, 0D, 0D);
//            // Square - Corner 2
//            particle.spawnParticleBasic(world, x + r + offset, y + offset * 2, z + r + offset, 0D, 0D, -pSpeed);
//            particle.spawnParticleBasic(world, x + r + offset, y + offset * 2, z + r + offset, -pSpeed, 0D, 0D);
//            // Square - Corner 3
//            particle.spawnParticleBasic(world, x + r + offset, y + offset * 2, z - r + offset, -pSpeed, 0D, 0D);
//            particle.spawnParticleBasic(world, x + r + offset, y + offset * 2, z - r + offset, 0D, 0D, pSpeed);
//            // Square - Corner 4
//            particle.spawnParticleBasic(world, x - r + offset, y + offset * 2, z + r + offset, pSpeed, 0D, 0D);
//            particle.spawnParticleBasic(world, x - r + offset, y + offset * 2, z + r + offset, 0D, 0D, -pSpeed);
//            // Verticals
//            particle.spawnParticleBasic(world, x + offset, y + r + offset, z + offset, 0D, -pSpeed, 0D);
//            particle.spawnParticleBasic(world, x + offset, y - r + offset, z + offset, 0D, pSpeed, 0D);
//            // Vert Cross - Up
//            particle.spawnParticleBasic(world, x + offset, y + r + offset, z + offset, 0D, 0D, pSpeed);
//            particle.spawnParticleBasic(world, x + offset, y + r + offset, z + offset, 0D, 0D, -pSpeed);
//            particle.spawnParticleBasic(world, x + offset, y + r + offset, z + offset, pSpeed, 0D, 0D);
//            particle.spawnParticleBasic(world, x + offset, y + r + offset, z + offset, -pSpeed, 0D, 0D);
//            // Vert Cross - Down
//            particle.spawnParticleBasic(world, x + offset, y - r + offset, z + offset, 0D, 0D, pSpeed);
//            particle.spawnParticleBasic(world, x + offset, y - r + offset, z + offset, 0D, 0D, -pSpeed);
//            particle.spawnParticleBasic(world, x + offset, y - r + offset, z + offset, pSpeed, 0D, 0D);
//            particle.spawnParticleBasic(world, x + offset, y - r + offset, z + offset, -pSpeed, 0D, 0D);
//        }

        if (world.getBlockState(pos).getBlock() == ModBlocks.MAGNETIC_INSULATOR.get()){
            BlockMagneticInsulator solar = (BlockMagneticInsulator) world.getBlockState(pos).getBlock();
            solar.setState(world, pos, world.isBlockPowered(pos));
        }

    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getDefaultRange() {
        return ModConfig.COMMON.ub_insulatorRange.get();
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
        setRange(compound.getInt(NBTKeys.RANGE.getKey()));
        setDoPreview(compound.getBoolean(NBTKeys.PREVIEW.getKey()));
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt(NBTKeys.RANGE.getKey(), getRange());
        compound.putBoolean(NBTKeys.PREVIEW.getKey(), getDoPreview());
        return super.write(compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        this.read(pkt.getNbtCompound());
        sendUpdates();
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(pos, 1, this.write(new CompoundNBT()));
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }

    private void sendUpdates() {
        assert world != null;
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.NOTIFY_NEIGHBORS);
        markDirty();
    }

}
