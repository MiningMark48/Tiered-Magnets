package com.miningmark48.tieredmagnets.init;

public class ModRegistry {

//    private static List<Item> itemsToRegister = new LinkedList<>();
//    private static List<Block> blocksToRegister = new LinkedList<>();
//
//    public static void init(){
//        registerItems();
//        registerBlocks();
//    }
//
//    private static void registerItems(){
//        //Durability TieredMagnets
//        registerItem(ModItems.ItemMagnetDurabilityStone);
//        registerItem(ModItems.ItemMagnetDurabilityIron);
//        registerItem(ModItems.ItemMagnetDurabilityRedstone);
//        registerItem(ModItems.ItemMagnetDurabilityGold);
//        registerItem(ModItems.ItemMagnetDurabilityObsidian);
//        registerItem(ModItems.ItemMagnetDurabilityLapis);
//        registerItem(ModItems.ItemMagnetDurabilityDiamond);
//        registerItem(ModItems.ItemMagnetDurabilityEmerald);
//        registerItem(ModItems.ItemMagnetMagicDurabilityStone);
//        registerItem(ModItems.ItemMagnetMagicDurabilityIron);
//        registerItem(ModItems.ItemMagnetMagicDurabilityRedstone);
//        registerItem(ModItems.ItemMagnetMagicDurabilityGold);
//        registerItem(ModItems.ItemMagnetMagicDurabilityObsidian);
//        registerItem(ModItems.ItemMagnetMagicDurabilityLapis);
//        registerItem(ModItems.ItemMagnetMagicDurabilityDiamond);
//        registerItem(ModItems.ItemMagnetMagicDurabilityEmerald);
//
//        //Electromagnets
//        registerItem(ModItems.ItemMagnetElectromagnetLeadstone);
//        registerItem(ModItems.ItemMagnetElectromagnetHardened);
//        registerItem(ModItems.ItemMagnetElectromagnetReinforced);
//        registerItem(ModItems.ItemMagnetElectromagnetSignalum);
//        registerItem(ModItems.ItemMagnetElectromagnetResonant);
//        registerItem(ModItems.ItemMagnetMagicElectromagnetLeadstone);
//        registerItem(ModItems.ItemMagnetMagicElectromagnetHardened);
//        registerItem(ModItems.ItemMagnetMagicElectromagnetReinforced);
//        registerItem(ModItems.ItemMagnetMagicElectromagnetSignalum);
//        registerItem(ModItems.ItemMagnetMagicElectromagnetResonant);
//
//        //Free Magnets
//        registerItem(ModItems.ItemMagnetFree);
//        registerItem(ModItems.ItemMagnetMagicFree);
//
//    }
//
//    private static void registerBlocks(){
//        registerBlock(ModBlocks.BlockMagneticInsulator);
//        registerBlock(ModBlocks.BlockMagneticProjector);
//    }
//
//    public static void registerRenderItems(){
//        //Durability Magnets
//        registerItemRender(ModItems.ItemMagnetDurabilityStone);
//        registerItemRender(ModItems.ItemMagnetDurabilityIron);
//        registerItemRender(ModItems.ItemMagnetDurabilityRedstone);
//        registerItemRender(ModItems.ItemMagnetDurabilityGold);
//        registerItemRender(ModItems.ItemMagnetDurabilityObsidian);
//        registerItemRender(ModItems.ItemMagnetDurabilityLapis);
//        registerItemRender(ModItems.ItemMagnetDurabilityDiamond);
//        registerItemRender(ModItems.ItemMagnetDurabilityEmerald);
//        registerItemRender(ModItems.ItemMagnetMagicDurabilityStone);
//        registerItemRender(ModItems.ItemMagnetMagicDurabilityIron);
//        registerItemRender(ModItems.ItemMagnetMagicDurabilityRedstone);
//        registerItemRender(ModItems.ItemMagnetMagicDurabilityGold);
//        registerItemRender(ModItems.ItemMagnetMagicDurabilityObsidian);
//        registerItemRender(ModItems.ItemMagnetMagicDurabilityLapis);
//        registerItemRender(ModItems.ItemMagnetMagicDurabilityDiamond);
//        registerItemRender(ModItems.ItemMagnetMagicDurabilityEmerald);
//
//        //Electromagnets
//        registerItemRender(ModItems.ItemMagnetElectromagnetLeadstone);
//        registerItemRender(ModItems.ItemMagnetElectromagnetHardened);
//        registerItemRender(ModItems.ItemMagnetElectromagnetReinforced);
//        registerItemRender(ModItems.ItemMagnetElectromagnetSignalum);
//        registerItemRender(ModItems.ItemMagnetElectromagnetResonant);
//        registerItemRender(ModItems.ItemMagnetMagicElectromagnetLeadstone);
//        registerItemRender(ModItems.ItemMagnetMagicElectromagnetHardened);
//        registerItemRender(ModItems.ItemMagnetMagicElectromagnetReinforced);
//        registerItemRender(ModItems.ItemMagnetMagicElectromagnetSignalum);
//        registerItemRender(ModItems.ItemMagnetMagicElectromagnetResonant);
//
//        //Free Magnets
//        registerItemRender(ModItems.ItemMagnetFree);
//        registerItemRender(ModItems.ItemMagnetMagicFree);
//
//    }
//
//    public static void registerRenderBlocks(){
//        registerBlockRender(ModBlocks.BlockMagneticInsulator);
//        registerBlockRender(ModBlocks.BlockMagneticProjector);
//    }
//
//    //Registry
//    @SubscribeEvent
//    public void onItemRegistry(RegistryEvent.Register<Item> event){
//        itemsToRegister.forEach(event.getRegistry()::register);
//    }
//
//    @SubscribeEvent
//    public void onBlockRegistry(RegistryEvent.Register<Block> event){
//        blocksToRegister.forEach(event.getRegistry()::register);
//    }
//
//    private static void registerBlock(Block block){
//        blocksToRegister.add(block);
//        registerItem(new ModItemBlock(block));
//    }
//
//    private static void registerItem(Item item){
//        itemsToRegister.add(item);
//    }
//
//    private static void registerBlockRender(Block block){
//        Item item = Item.getItemFromBlock(block);
//        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
//    }
//
//    public static void registerItemRender(Item item){
//        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
//    }
//
//    public static void registerItemRender(Item item, int meta, String name){
//        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + name, "inventory"));
//    }

}
