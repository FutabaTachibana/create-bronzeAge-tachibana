package org.syju.bronze_age_tachibana;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import static org.syju.bronze_age_tachibana.registry.Registrate.REGISTRATE;

public class Tags {
    // A helper method to register items that have Forge tag.
    @SafeVarargs
    public static ItemEntry<Item> tagIngredient(String name, TagKey<Item> ... tags){
        return REGISTRATE.item(name, Item::new).tag(tags).register();
    }
    // A helper method to add forge tags to an object (etc. items, blocks).
    public static <T> TagKey<T> forgeTag(IForgeRegistry<T> registry, String tag){
        return registry.tags().createTagKey(ResourceLocation.fromNamespaceAndPath("forge", tag));
    }
    public static TagKey<Item> forgeItemTag(String tag){
        return forgeTag(ForgeRegistries.ITEMS, tag);
    }
    public static TagKey<Block> forgeBlockTag(String tag){
        return forgeTag(ForgeRegistries.BLOCKS, tag);
    }
}


