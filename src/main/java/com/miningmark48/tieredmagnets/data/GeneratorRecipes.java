package com.miningmark48.tieredmagnets.data;

import com.miningmark48.tieredmagnets.init.ModItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class GeneratorRecipes extends RecipeProvider {

    public GeneratorRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(Blocks.DIAMOND_BLOCK.getBlock(), 64)
                .patternLine("XXX")
                .patternLine("XXX")
                .patternLine("XXX")
                .key('X', Blocks.DIRT)
                .setGroup("")
                .addCriterion("has_dirt", hasItem(Blocks.DIRT)) //Doesn't actually print... TODO: nested/conditional advancements?
                .build(consumer);

//        registerBlockRecipes(consumer);
//        registerItemRecipes(consumer);
    }

    private void registerBlockRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(Blocks.DIAMOND_BLOCK.getBlock(), 64)
                .patternLine("XXX")
                .patternLine("XXX")
                .patternLine("XXX")
                .key('X', Blocks.DIRT)
                .setGroup("")
                .addCriterion("has_dirt", hasItem(Blocks.DIRT)) //Doesn't actually print... TODO: nested/conditional advancements?
                .build(consumer);
    }

    private void registerItemRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModItems.MAGNETITE.get())
                .key('c', Items.CLAY_BALL)
                .patternLine("ccc")
                .patternLine("ccc")
                .patternLine("ccc")
                .addCriterion("clay", InventoryChangeTrigger.Instance.forItems(Items.CLAY_BALL))
                .build(consumer);
    }

}
