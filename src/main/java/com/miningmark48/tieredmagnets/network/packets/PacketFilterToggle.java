package com.miningmark48.tieredmagnets.network.packets;

import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFilterToggle extends PacketEmpty {

    public static class Handler implements IMessageHandler<PacketFilterToggle, IMessage> {
        @Override
        public IMessage onMessage(PacketFilterToggle message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(ctx));
            return null;
        }

        private void handle(MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;


            ItemStack heldItem = ItemMagnetBase.getMagnet(playerEntity);
            if (heldItem.isEmpty()) return;

            if (heldItem.getItem() instanceof ItemMagnetBase) {
                if (!heldItem.hasTagCompound()) {
                    heldItem.setTagCompound(new NBTTagCompound());
                    heldItem.getTagCompound().setBoolean("filterModeBlacklist", true);
                }

                heldItem.getTagCompound().setBoolean("filterModeBlacklist", !heldItem.getTagCompound().getBoolean("filterModeBlacklist"));

            }
        }
    }

}
