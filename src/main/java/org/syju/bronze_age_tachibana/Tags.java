package org.syju.bronze_age_tachibana;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static org.syju.bronze_age_tachibana.registry.Registrate.REGISTRATE;

public class Tags {
    // A helper method to register items that have Forge tag.
    @SafeVarargs
    public static ItemEntry<Item> tagIngredient(String name, TagKey<Item>... tags) {
        return REGISTRATE.item(name, Item::new).tag(tags).register();
    }
}