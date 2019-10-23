package com.miningmark48.tieredmagnets.client.events;

import com.miningmark48.tieredmagnets.item.ItemMagnetDurability;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventAnvilRepair {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        if ((event.getLeft().getItem() instanceof ItemMagnetDurability) && (event.getRight().getItem() == Items.IRON_INGOT)) {
            event.setCost(2);
            event.setMaterialCost(1);
            ItemStack newItem = event.getLeft().copy();
            newItem.setDamage(0);
            event.setOutput(newItem);
        }
    }

}
