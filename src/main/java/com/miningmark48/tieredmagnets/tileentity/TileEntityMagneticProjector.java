package com.miningmark48.tieredmagnets.tileentity;

import com.miningmark48.tieredmagnets.block.BlockMagneticProjector;
import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

@SuppressWarnings("Duplicates")
public class TileEntityMagneticProjector extends TileEntity implements ITickableTileEntity, IInventory {

    protected NonNullList<ItemStack> inventory;
    private ItemMagnetBase magnet;

    public TileEntityMagneticProjector(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
        this.inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    }

    public TileEntityMagneticProjector() {
        this(ModBlocks.ModTileEntities.MAGNETIC_PROJECTOR);
    }

    @Override
    public void tick() {
        if (!world.isBlockPowered(pos)) {
            ItemStack stack = this.getStackInSlot(0);
            if (stack.getItem() instanceof ItemMagnetBase) {
                magnet = (ItemMagnetBase) stack.getItem();
                magnet.doUpdate(stack, this.getWorld(), this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D, false);
            }

            TileEntity te = this.getWorld().getTileEntity(this.getPos().down());
            if (te != null) {
                if (te instanceof IInventory) {
                    int range = 1;
                    double x = this.getPos().getX() + 0.5D;
                    double y = this.getPos().getY() + 0.5D;
                    double z = this.getPos().getZ() + 0.5D;
                    List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                    items.forEach(e -> {
                        ItemStack left = HopperTileEntity.putStackInInventoryAllSlots(this, (IInventory) te, e.getItem(), null);
                        e.setItem(left);
                    });
                }
            }
        }

        if (this.world.getBlockState(pos).getBlock() == ModBlocks.BlockMagneticProjector){
            BlockMagneticProjector solar = (BlockMagneticProjector) this.world.getBlockState(pos).getBlock();
            solar.setState(this.world, this.pos, world.isBlockPowered(pos));
        }

    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        ItemStackHelper.loadAllItems(compound, this.inventory);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);

        ItemStackHelper.saveAllItems(compound, this.inventory);

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

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.inventory)
        {
            if(!itemstack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.inventory, index, count);

        if (!itemstack.isEmpty())
        {
            this.markDirty();
        }

        return itemstack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return false;
    }

    @Override
    public void openInventory(PlayerEntity player) {

    }

    @Override
    public void closeInventory(PlayerEntity player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

//    @Override
//    public int getField(int id) {
//        return 0;
//    }
//
//    @Override
//    public void setField(int id, int value) {
//
//    }
//
//    @Override
//    public int getFieldCount() {
//        return 0;
//    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

}
