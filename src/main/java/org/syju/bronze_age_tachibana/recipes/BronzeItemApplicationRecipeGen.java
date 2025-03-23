package org.syju.bronze_age_tachibana.recipes;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class BronzeItemApplicationRecipeGen extends BronzeProcessingRecipeGen{
    // Field generate recipes
    GeneratedRecipe BRASS = woodCasingTag("bronze", I::bronze, I::bronzeCasing);

    // Helper methods
    protected GeneratedRecipe woodCasing(String type, Supplier<ItemLike> ingredient, Supplier<ItemLike> output) {
        return woodCasingIngredient(type, () -> Ingredient.of(ingredient.get()), output);
    }

    protected GeneratedRecipe woodCasingTag(String type, Supplier<TagKey<Item>> ingredient, Supplier<ItemLike> output) {
        return woodCasingIngredient(type, () -> Ingredient.of(ingredient.get()), output);
    }

    protected GeneratedRecipe woodCasingIngredient(String type, Supplier<Ingredient> ingredient,
                                                   Supplier<ItemLike> output) {
        create(type + "_casing_from_log", b -> b.require(AllTags.AllItemTags.STRIPPED_LOGS.tag)
                .require(ingredient.get())
                .output(output.get()));
        return create(type + "_casing_from_wood", b -> b.require(AllTags.AllItemTags.STRIPPED_WOOD.tag)
                .require(ingredient.get())
                .output(output.get()));
    }

    // Constructor of related class
    public BronzeItemApplicationRecipeGen(PackOutput generator) {
        super(generator);
    }

    // It provided the recipe type, which heritage crate:item_application
    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.ITEM_APPLICATION;
    }
}
