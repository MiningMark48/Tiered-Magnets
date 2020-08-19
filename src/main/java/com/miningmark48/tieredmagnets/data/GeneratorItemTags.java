//package com.miningmark48.tieredmagnets.data;
//
//import com.miningmark48.tieredmagnets.init.ModItems;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.ItemTagsProvider;
//import net.minecraft.item.Item;
//import net.minecraft.tags.Tag;
//import net.minecraft.util.IItemProvider;
//
//import java.util.Arrays;
//import java.util.function.Supplier;
//
//public class GeneratorItemTags extends ItemTagsProvider {
//
//    public GeneratorItemTags(DataGenerator generatorIn) {
//        super(generatorIn);
//    }
//
//    @Override
//    protected void registerTags() {
//        addItemsToTag(ModTags.Items.MAGNETITE, ModItems.MAGNETITE);
//    }
//
//    @SafeVarargs
//    private final void addItemsToTag(Tag<Item> tag, Supplier<? extends IItemProvider>... items) {
//        getBuilder(tag).add(Arrays.stream(items).map(Supplier::get).map(IItemProvider::asItem).toArray(Item[]::new));
//    }
//
//    @SafeVarargs
//    private final void appendToTag(Tag<Item> tag, Tag<Item>... toAppend) {
//        getBuilder(tag).add(toAppend);
//    }
//
//    @Override
//    public String getName() {
//        return "Tiered Magnets Item Tags";
//    }
//
//}
