package com.miningmark48.magnets.network.packets;

import com.miningmark48.magnets.item.base.ItemMagnetBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketToggleMagnet extends PacketEmpty {

    public static class Handler implements IMessageHandler<PacketToggleMagnet, IMessage> {
        @Override
        public IMessage onMessage(PacketToggleMagnet message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(ctx));
            return null;
        }

        private void handle(MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;

//            playerEntity.inventory.mainInventory.forEach(item -> {
//                if (item.getItem() instanceof ItemMagnetBase) {
//                    ItemMagnetBase.toggleMagnet(item, playerEntity);
//                }
//            });

            ItemStack magnet = playerEntity.inventory.mainInventory.stream().filter(item -> item.getItem() instanceof ItemMagnetBase).findFirst().orElse(ItemStack.EMPTY);
            if (!magnet.isEmpty()) ItemMagnetBase.toggleMagnet(magnet, playerEntity);


//            ItemStack heldItem = ItemMagnetBase.getMagnet(playerEntity);
//            if (heldItem.isEmpty())
//                return;
//
//            if (heldItem.getItem() instanceof ItemMagnetBase) {
//                ItemMagnetBase.toggleMagnet(heldItem, playerEntity);
//            }
        }
    }

}
