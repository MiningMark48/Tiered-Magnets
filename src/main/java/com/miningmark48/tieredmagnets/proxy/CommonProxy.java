package com.miningmark48.tieredmagnets.proxy;

import com.miningmark48.tieredmagnets.init.ModRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

    }

    public void registerModel(Item item){

    }

    public void registerRenders(){

    }

    public void init(FMLInitializationEvent event){

    }

    @SubscribeEvent
    public void onRecipeRegistry(RegistryEvent.Register<IRecipe> e) {
        ModRecipes.init(e);
    }

}
