package com.miningmark48.tieredmagnets.client.events;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventLootLoad {

    @SubscribeEvent
    public void lootLoad(LootTableLoadEvent event) {
        if (event.getName().toString().equals("minecraft:blocks/iron_ore")) {
            event.getTable().addPool(LootPool.builder()
                .addEntry(TableLootEntry.builder(new ResourceLocation(Reference.MOD_ID, "inject/iron_ore")))
                .name("iron_ore_magnetite").build());
        }
    }

}
