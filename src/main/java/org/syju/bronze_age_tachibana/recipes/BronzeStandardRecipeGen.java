package org.syju.bronze_age_tachibana.recipes;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;
import org.syju.bronze_age_tachibana.registry.BronzeBlocks;
import org.syju.bronze_age_tachibana.registry.BronzeItems;

import java.util.function.Consumer;

import static com.simibubi.create.AllTags.forgeItemTag;

public class BronzeStandardRecipeGen extends RecipeProvider {
    // Constructor of related class
    public BronzeStandardRecipeGen(PackOutput p_248933_) {
        super(p_248933_);
    }
    Consumer<FinishedRecipe> consumer;
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.consumer = consumer;
        registerIngotToNuggets(BronzeItems.TIN_NUGGET, "ingots/tin");
        registerNuggetsToIngot(BronzeItems.TIN_INGOT, "nuggets/tin");
        registerIngotsToBlock(BronzeBlocks.TIN_BLOCK, "ingots/tin");
        registerBlockToIngots(BronzeItems.TIN_INGOT, "storage_blocks/tin");
        registerIngotToNuggets(BronzeItems.BRONZE_NUGGET, "ingots/bronze");
        registerNuggetsToIngot(BronzeItems.BRONZE_INGOT, "nuggets/bronze");
        registerIngotsToBlock(BronzeBlocks.BRONZE_BLOCK, "ingots/bronze");
        registerBlockToIngots(BronzeItems.BRONZE_INGOT, "storage_blocks/bronze");
        registerArmor(BronzeItems.BRONZE_HELMET, BronzeItems.BRONZE_CHESTPLATE, BronzeItems.BRONZE_LEGGINGS,
                BronzeItems.BRONZE_BOOTS, "ingots/bronze");
        registerTools(BronzeItems.BRONZE_SHOVEL, BronzeItems.BRONZE_PICKAXE, BronzeItems.BRONZE_AXE,
                BronzeItems.BRONZE_HOE, BronzeItems.BRONZE_SWORD, "ingots/bronze");
    }
    private void registerIngotToNuggets(ItemLike result, String input){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 9)
                .requires(asItem(input))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(result.asItem().toString() + "_from_" + input.split("/")[0]));
    }
    private void registerNuggetsToIngot(ItemLike result, String input){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result)
                .requires(asItem(input), 9)
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(result.asItem().toString() + "_from_" + input.split("/")[0]));
    }
    private void registerBlockToIngots(ItemLike result, String input){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 9)
                .requires(asItem(input))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(result.asItem().toString() + "_from_" + input.split("/")[0]));
    }
    private void registerIngotsToBlock(ItemLike result, String input){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(asItem(input), 9)
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(result.asItem().toString() + "_from_" + input.split("/")[0]));
    }
    private void registerTools(ItemLike shovel, ItemLike pickaxe, ItemLike axe, ItemLike hoe, ItemLike sword, String input){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, shovel)
                .pattern("A")
                .pattern("R")
                .pattern("R")
                .define('A', asItem(input))
                .define('R', asItem("rods/wooden"))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(shovel.asItem().toString()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pickaxe)
                .pattern("AAA")
                .pattern(" R ")
                .pattern(" R ")
                .define('A', asItem(input))
                .define('R', asItem("rods/wooden"))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(pickaxe.asItem().toString()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, axe)
                .pattern("AA")
                .pattern("AR")
                .pattern(" R")
                .define('A', asItem(input))
                .define('R', asItem("rods/wooden"))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(axe.asItem().toString()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, hoe)
                .pattern("AA")
                .pattern(" R")
                .pattern(" R")
                .define('A', asItem(input))
                .define('R', asItem("rods/wooden"))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(hoe.asItem().toString()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .pattern("A")
                .pattern("A")
                .pattern("R")
                .define('A', asItem(input))
                .define('R', asItem("rods/wooden"))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(sword.asItem().toString()));
    }
    private void registerArmor(ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots, String input){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("AAA")
                .pattern("A A")
                .define('A', asItem(input))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(helmet.asItem().toString()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', asItem(input))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(chestplate.asItem().toString()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', asItem(input))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(leggings.asItem().toString()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .pattern("A A")
                .pattern("A A")
                .define('A', asItem(input))
                .unlockedBy("has_" + input, has(forgeItemTag(input)))
                .save(consumer, BronzeAgeTachibana.asResource(boots.asItem().toString()));
    }
    private Ingredient asItem(String path){
        return Ingredient.of(forgeItemTag(path));
    }

}
