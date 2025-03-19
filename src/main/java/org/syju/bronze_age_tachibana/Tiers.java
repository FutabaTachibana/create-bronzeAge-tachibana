package org.syju.bronze_age_tachibana;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import org.syju.bronze_age_tachibana.registry.AllItems;

public class Tiers {
    // Constructor:
    // int level, int uses (durability), float speed, float attackDamageBonus, TagKey<Block> tags, Supplier<Ingredient> repairIngredient
    public static final Tier BRONZE = new ForgeTier(
            2,
            420,
            6.5f,
            2.0f,
            15,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(AllItems.BRONZE_INGOT.get()));
}
