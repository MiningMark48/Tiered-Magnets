package com.miningmark48.magnets.tileentity;

import com.miningmark48.magnets.init.ModConfig;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class TileEntityMagneticInsulator extends TileEntity implements ITickable {

    public TileEntityMagneticInsulator() {

    }

    @Override
    public void update() {

//        ModLogger.info(getTileData());

        if (!getTileData().hasKey("range")) {
            getTileData().setInteger("range", ModConfig.insulatorConfigs.range);
        }

        World world = getWorld();
        BlockPos pos = getPos();

        if (!world.isBlockPowered(pos)) {
            int range = getTileData().getInteger("range");
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

//            if (ModConfig.miscconfigs.doParticles) world.spawnParticle(EnumParticleTypes.SPELL_WITCH, x + 0.5D, y + 0.8D, z + 0.15D, 0.0D, 0.0D, 0.0D);

            List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
            items.forEach(item -> item.getEntityData().setBoolean("noMagnet", true));
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }
}
