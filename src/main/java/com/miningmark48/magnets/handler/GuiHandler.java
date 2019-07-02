package com.miningmark48.magnets.handler;

import com.miningmark48.magnets.client.gui.GuiMagnetFilter;
import com.miningmark48.magnets.container.ContainerMagnetFilter;
import com.miningmark48.magnets.inventory.InventoryMagnetFilter;
import com.miningmark48.magnets.reference.ReferenceGUIs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ReferenceGUIs.gui_id_magnet_filter){
            return new ContainerMagnetFilter(player, player.inventory, new InventoryMagnetFilter(player.getHeldItem(EnumHand.MAIN_HAND)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ReferenceGUIs.gui_id_magnet_filter){
            return new GuiMagnetFilter(new ContainerMagnetFilter(player, player.inventory, new InventoryMagnetFilter(player.getHeldItem(EnumHand.MAIN_HAND))), player.getHeldItem(EnumHand.MAIN_HAND));
        }
        return null;
    }

}
