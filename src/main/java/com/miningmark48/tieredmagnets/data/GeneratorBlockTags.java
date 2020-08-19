//package com.miningmark48.tieredmagnets.data;
//
//import net.minecraft.block.Block;
//import net.minecraft.data.BlockTagsProvider;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.tags.Tag;
//
//import java.util.Arrays;
//import java.util.function.IntFunction;
//import java.util.function.Supplier;
//
//public class GeneratorBlockTags extends BlockTagsProvider {
//
//    public GeneratorBlockTags(DataGenerator generatorIn) {
//        super(generatorIn);
//    }
//
//    @Override
//    protected void registerTags() {
//
//    }
//
//    @SafeVarargs
//    private final <T> T[] resolveAll(IntFunction<T[]> creator, Supplier<? extends T>... suppliers) {
//        return Arrays.stream(suppliers).map(Supplier::get).toArray(creator);
//    }
//
//    @SafeVarargs
//    private final void createTag(Tag<Block> tag, Supplier<? extends Block>... blocks) {
//        getBuilder(tag).add(resolveAll(Block[]::new, blocks));
//    }
//
//    @SafeVarargs
//    private final void appendToTag(Tag<Block> tag, Tag<Block>... toAppend) {
//        getBuilder(tag).add(toAppend);
//    }
//
//    @SafeVarargs
//    private final void extendTag(Tag<Block> tag, Tag<Block> toExtend, Supplier<? extends Block>... blocks) {
//        getBuilder(tag).add(toExtend).add(resolveAll(Block[]::new, blocks));
//    }
//
//    @SafeVarargs
//    private final void createAndAppend(Tag<Block> tag, Tag<Block> to, Supplier<? extends Block>... blocks) {
//        createTag(tag, blocks);
//        appendToTag(to, tag);
//    }
//
//    @SafeVarargs
//    private final void extendAndAppend(Tag<Block> tag, Tag<Block> toExtend, Tag<Block> to, Supplier<? extends Block>... blocks) {
//        extendTag(tag, toExtend, blocks);
//        appendToTag(to, tag);
//    }
//
//    @Override
//    public String getName() {
//        return "Tiered Magnets Block Tags";
//    }
//
//}
