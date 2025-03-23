package org.syju.bronze_age_tachibana.recipes;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import net.createmod.catnip.platform.CatnipServices;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;
import org.syju.bronze_age_tachibana.registry.BronzeBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public abstract class BronzeProcessingRecipeGen extends ProcessingRecipeGen {
    protected static final List<ProcessingRecipeGen> GENERATORS = new ArrayList<>();
    // Fields provided the supplier of item
    protected static class I {
        static TagKey<Item> bronze() {
            return AllTags.forgeItemTag("ingots/bronze");
        }
        static ItemLike bronzeCasing() {
            return BronzeBlocks.BRONZE_CASING.get();
        }
    }

    public static void registerAll(DataGenerator gen, PackOutput output){
        GENERATORS.add(new BronzeItemApplicationRecipeGen(output));

        gen.addProvider(true, new DataProvider() {
            @Override
            public String getName() {
                return "Crate: Bronze Age's Processing Recipes";
            }

            @Override
            public CompletableFuture<?> run(CachedOutput dc) {
                return CompletableFuture.allOf(GENERATORS.stream()
                        .map(gen -> gen.run(dc))
                        .toArray(CompletableFuture[]::new));
            }
        });
    }

    // Constructor of related class
    public BronzeProcessingRecipeGen(PackOutput generator) {
        super(generator);
    }

    // Define package-private methods to register recipes conveniently
    <T extends ProcessingRecipe<?>> GeneratedRecipe create(String name,
                                                           UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(BronzeAgeTachibana.asResource(name), transform);
    }
    <T extends ProcessingRecipe<?>> GeneratedRecipe create(Supplier<ItemLike> singleIngredient,
                                                           UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(BronzeAgeTachibana.MODID, singleIngredient, transform);
    }

    // Override those methods to add recipes using namespace bronze_age_tachibana instead create
    @Override
    protected Supplier<ResourceLocation> idWithSuffix(Supplier<ItemLike> item, String suffix) {
        return () -> {
            ResourceLocation registryName = CatnipServices.REGISTRIES.getKeyOrThrow(item.get()
                    .asItem());
            return BronzeAgeTachibana.asResource(registryName.getPath() + suffix);
        };
    }

//    @Override
//    public String getName() {
//        return "Crate: Bronze Age's Processing Recipes: " + getRecipeType().getId()
//                .getPath();
//    }
}
