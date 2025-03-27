package org.syju.bronze_age_tachibana.recipes;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.recipe.Mods;
import net.minecraft.data.PackOutput;
import org.syju.bronze_age_tachibana.registry.BronzeItems;

public class BronzePressingRecipeGen extends BronzeProcessingRecipeGen{
    GeneratedRecipe
            TIN = create("tin_ingot", b -> b.require(I.tin()).output(BronzeItems.TIN_SHEET)),
            BRONZE = create("bronze_ingot", b -> b.require(I.bronze()).output(BronzeItems.BRONZE_SHEET));

    GeneratedRecipe moddedPaths(Mods mod, String... blocks) {
        for(String block : blocks) {
            moddedCompacting(mod, block, block + "_path");
        }
        return null;
    }

    GeneratedRecipe iePlates(String... metals) {
        for (String metal : metals)
            create(Mods.IE.recipeId("plate_" + metal), b -> b.require(AllTags.forgeItemTag("ingots/" + metal))
                    .output(Mods.IE, "plate_" + metal)
                    .whenModLoaded(Mods.IE.getId()));
        return null;
    }

    GeneratedRecipe moddedCompacting(Mods mod, String input, String output) {
        return create("compat/" + mod.getId() + "/" + output, b -> b.require(mod, input)
                .output(mod, output)
                .whenModLoaded(mod.getId()));
    }

    public BronzePressingRecipeGen(PackOutput output) {
        super(output);
    }

    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.PRESSING;
    }
}
