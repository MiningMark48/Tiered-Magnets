package com.miningmark48.magnets.init;

import com.miningmark48.mininglib.base.item.ModItemBlock;
import com.miningmark48.magnets.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.LinkedList;
import java.util.List;

public class ModRegistry {

    private static List<Item> itemsToRegister = new LinkedList<>();
    private static List<Block> blocksToRegister = new LinkedList<>();

    public static void init(){
        registerItems();
        registerBlocks();
    }

    private static void registerItems(){
        registerItem(ModItems.ItemMagnetDurabilityStone);
        registerItem(ModItems.ItemMagnetDurabilityIron);
        registerItem(ModItems.ItemMagnetDurabilityRedstone);
        registerItem(ModItems.ItemMagnetDurabilityGold);
    }

    private static void registerBlocks(){

    }

    public static void registerRenderItems(){
        registerItemRender(ModItems.ItemMagnetDurabilityStone);
        registerItemRender(ModItems.ItemMagnetDurabilityIron);
        registerItemRender(ModItems.ItemMagnetDurabilityRedstone);
        registerItemRender(ModItems.ItemMagnetDurabilityGold);
    }

    public static void registerRenderBlocks(){

    }

    //Registry
    @SubscribeEvent
    public void onItemRegistry(RegistryEvent.Register<Item> event){
        itemsToRegister.forEach(event.getRegistry()::register);
    }

    @SubscribeEvent
    public void onBlockRegistry(RegistryEvent.Register<Block> event){
        blocksToRegister.forEach(event.getRegistry()::register);
    }

    private static void registerBlock(Block block){
        blocksToRegister.add(block);
        registerItem(new ModItemBlock(block));
    }

    private static void registerItem(Item item){
        itemsToRegister.add(item);
    }

    private static void registerBlockRender(Block block){
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }

    public static void registerItemRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    public static void registerItemRender(Item item, int meta, String name){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + name, "inventory"));
    }

}
