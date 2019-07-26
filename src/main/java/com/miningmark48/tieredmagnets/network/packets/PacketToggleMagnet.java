package com.miningmark48.tieredmagnets.network.packets;

import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketToggleMagnet extends PacketEmpty {

    public static void encode(PacketToggleMagnet msg, PacketBuffer buffer) {
    }

    public static PacketToggleMagnet decode(PacketBuffer buffer) {
        return new PacketToggleMagnet();
    }

    public static class Handler {

        public static void handle(PacketToggleMagnet msg, Supplier<NetworkEvent.Context> ctx) {
            ServerPlayerEntity playerEntity = ctx.get().getSender();
            assert playerEntity != null;
            ItemStack magnet = playerEntity.inventory.mainInventory.stream().filter(item -> item.getItem() instanceof ItemMagnetBase).findFirst().orElse(ItemStack.EMPTY);
            if (!magnet.isEmpty() && !playerEntity.getCooldownTracker().hasCooldown(magnet.getItem())) ItemMagnetBase.toggleMagnet(magnet, playerEntity);

//            if (Loader.isModLoaded(Reference.BAUBLES)) {
//                handleBaubleMagnet(playerEntity);
//            }
        }

//        @Optional.Method(modid = Reference.BAUBLES)
//        private void handleBaubleMagnet(EntityPlayerMP player) {
//            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
//            for (int i = 0; i < baubles.getSlots(); i++) {
//                ItemStack stack = baubles.getStackInSlot(i);
//                if (!stack.isEmpty()) {
//                    if (stack.getItem() instanceof ItemMagnetBase) {
//                        ItemMagnetBase.toggleMagnet(stack, player);
//                        baubles.setChanged(i, true);
//                    }
//                }
//            }
//        }

    }

}
