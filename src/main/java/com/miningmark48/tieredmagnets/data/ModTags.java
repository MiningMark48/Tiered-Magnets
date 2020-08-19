//package com.miningmark48.tieredmagnets.data;
//
//import com.miningmark48.tieredmagnets.reference.Reference;
//import net.minecraft.block.Block;
//import net.minecraft.item.Item;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.tags.ItemTags;
//import net.minecraft.tags.Tag;
//import net.minecraft.util.ResourceLocation;
//
//import java.util.function.Function;
//
//public class ModTags {
//
//    public static class Blocks extends ModTags {
//
////        public static final Tag<Block> SAND = modTag("sand");
//
//        static Tag<Block> tag(String modid, String name) {
//            return tag(BlockTags.Wrapper::new, modid, name);
//        }
//
//        static Tag<Block> modTag(String name) {
//            return tag(Reference.MOD_ID, name);
//        }
//
//        static Tag<Block> compatTag(String name) {
//            return tag("forge", name);
//        }
//    }
//
//    public static class Items extends ModTags {
//
//        public static final Tag<Item> MAGNETITE = compatTag("gems/magnetite");
//
//        static Tag<Item> tag(String modid, String name) {
//            return tag(ItemTags.Wrapper::new, modid, name);
//        }
//
//        static Tag<Item> modTag(String name) {
//            return tag(Reference.MOD_ID, name);
//        }
//
//        static Tag<Item> compatTag(String name) {
//            return tag("forge", name);
//        }
//    }
//
//    static <T extends Tag<?>> T tag(Function<ResourceLocation, T> creator, String modid, String name) {
//        return creator.apply(new ResourceLocation(modid, name));
//    }
//
//    static <T extends Tag<?>> T modTag(Function<ResourceLocation, T> creator, String name) {
//        return tag(creator, Reference.MOD_ID, name);
//    }
//
//    static <T extends Tag<?>> T compatTag(Function<ResourceLocation, T> creator, String name) {
//        return tag(creator, "forge", name);
//    }
//
//}
