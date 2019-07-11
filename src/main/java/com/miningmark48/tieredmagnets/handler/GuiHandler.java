package com.miningmark48.tieredmagnets.handler;

import com.miningmark48.tieredmagnets.client.gui.GuiMagnetFilter;
import com.miningmark48.tieredmagnets.client.gui.GuiMagneticInsulator;
import com.miningmark48.tieredmagnets.client.gui.GuiMagneticProjector;
import com.miningmark48.tieredmagnets.container.ContainerMagnetFilter;
import com.miningmark48.tieredmagnets.container.ContainerMagneticInsulator;
import com.miningmark48.tieredmagnets.container.ContainerMagneticProjector;
import com.miningmark48.tieredmagnets.inventory.InventoryMagnetFilter;
import com.miningmark48.tieredmagnets.reference.ReferenceGUIs;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ReferenceGUIs.gui_id_magnet_filter){
            return new ContainerMagnetFilter(player, player.inventory, new InventoryMagnetFilter(player.getHeldItem(EnumHand.MAIN_HAND)));
        }
        if (ID == ReferenceGUIs.gui_id_magnetic_insulator){
            return new ContainerMagneticInsulator(player.inventory, (TileEntityMagneticInsulator) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == ReferenceGUIs.gui_id_magnetic_projector){
            return new ContainerMagneticProjector(player.inventory, (TileEntityMagneticProjector) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ReferenceGUIs.gui_id_magnet_filter){
            return new GuiMagnetFilter(new ContainerMagnetFilter(player, player.inventory, new InventoryMagnetFilter(player.getHeldItem(EnumHand.MAIN_HAND))), player.getHeldItem(EnumHand.MAIN_HAND));
        }
        if (ID == ReferenceGUIs.gui_id_magnetic_insulator){
            return new GuiMagneticInsulator(player.inventory, (TileEntityMagneticInsulator) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == ReferenceGUIs.gui_id_magnetic_projector){
            return new GuiMagneticProjector(player.inventory, (TileEntityMagneticProjector) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        return null;
    }

}
