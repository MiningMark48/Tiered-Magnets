package com.miningmark48.tieredmagnets.network.packets;

import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketFilterToggle extends PacketEmpty {

    public static void encode(PacketFilterToggle msg, PacketBuffer buffer) {
    }

    public static PacketFilterToggle decode(PacketBuffer buffer) {
        return new PacketFilterToggle();
    }

    public static class Handler  {

        public static void handle(PacketFilterToggle msg, Supplier<NetworkEvent.Context> ctx) {
            ServerPlayerEntity playerEntity = ctx.get().getSender();

            assert playerEntity != null;
            ItemStack heldItem = ItemMagnetBase.getMagnet(playerEntity);
            if (heldItem.isEmpty()) return;

            if (heldItem.getItem() instanceof ItemMagnetBase) {
                if (!heldItem.hasTag()) {
                    heldItem.setTag(new CompoundNBT());
                    assert heldItem.getTag() != null;
                    heldItem.getTag().putBoolean("filterModeBlacklist", true);
                }

                assert heldItem.getTag() != null;
                heldItem.getTag().putBoolean("filterModeBlacklist", !heldItem.getTag().getBoolean("filterModeBlacklist"));

            }
        }
    }

}
