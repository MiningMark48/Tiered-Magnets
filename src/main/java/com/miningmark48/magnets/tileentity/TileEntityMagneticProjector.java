package com.miningmark48.magnets.tileentity;

import com.miningmark48.magnets.block.BlockMagneticInsulator;
import com.miningmark48.magnets.block.BlockMagneticProjector;
import com.miningmark48.magnets.init.ModBlocks;
import com.miningmark48.magnets.item.base.ItemMagnetBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("Duplicates")
public class TileEntityMagneticProjector extends TileEntity implements ITickable, IInventory {

    protected NonNullList<ItemStack> inventory;
    private ItemMagnetBase magnet;

    public TileEntityMagneticProjector() {
        this.inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    }

    @Override
    public void update() {
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
                    List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                    for (EntityItem e : items) {
                        ItemStack left = TileEntityHopper.putStackInInventoryAllSlots(this, (IInventory) te, e.getItem(), null);
                        e.setItem(left);
                    }
                }
            }
        }

        if (this.world.getBlockState(pos).getBlock() == ModBlocks.BlockMagneticProjector){
            BlockMagneticProjector solar = (BlockMagneticProjector) this.world.getBlockState(pos).getBlock();
            solar.setState(this.world, this.pos, world.isBlockPowered(pos));
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        ItemStackHelper.loadAllItems(compound, this.inventory);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        ItemStackHelper.saveAllItems(compound, this.inventory);

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
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public String getName() {
        return Objects.requireNonNull(this.getDisplayName()).toString();
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
