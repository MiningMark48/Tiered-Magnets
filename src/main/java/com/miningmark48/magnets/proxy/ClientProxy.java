package com.miningmark48.magnets.proxy;

import com.miningmark48.magnets.init.ModRegistry;
import com.miningmark48.magnets.init.ModTileEntities;
import com.miningmark48.magnets.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);
    }

    @Override
    public void init(FMLInitializationEvent event){
        registerOBJRenders();
    }

    @Override
    public void registerRenders(){
        ModRegistry.registerRenderItems();
        ModRegistry.registerRenderBlocks();
        ModTileEntities.bindTileRenders();
    }

    @Override
    public void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    private void registerOBJRenders() {
    }

}
