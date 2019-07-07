package com.miningmark48.magnets.network.packets;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.miningmark48.magnets.item.base.ItemMagnetBase;
import com.miningmark48.magnets.reference.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Optional;
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
            ItemStack magnet = playerEntity.inventory.mainInventory.stream().filter(item -> item.getItem() instanceof ItemMagnetBase).findFirst().orElse(ItemStack.EMPTY);
            if (!magnet.isEmpty() && !playerEntity.getCooldownTracker().hasCooldown(magnet.getItem())) ItemMagnetBase.toggleMagnet(magnet, playerEntity);

//            if (Loader.isModLoaded(Reference.BAUBLES)) {
//                handleBaubleMagnet(playerEntity);
//            }
        }

        @Optional.Method(modid = Reference.BAUBLES)
        private void handleBaubleMagnet(EntityPlayerMP player) {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            for (int i = 0; i < baubles.getSlots(); i++) {
                ItemStack stack = baubles.getStackInSlot(i);
                if (stack.getItem() instanceof ItemMagnetBase) {
                    if (!stack.isEmpty()) ItemMagnetBase.toggleMagnet(stack, player);
                }
            }
        }

    }

}
