package org.syju.bronze_age_tachibana.registry;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;

import java.util.List;

public class CreativeTabs {
    private static final List<RegistryEntry> registerCreativeTabsItems = List.of(
            // Items and blocks...
            BronzeItems.TIN_INGOT, BronzeItems.TIN_NUGGET, BronzeBlocks.TIN_BLOCK, BronzeItems.TIN_SHEET,
            BronzeItems.RAW_TIN, BronzeBlocks.RAW_TIN_BLOCK, BronzeBlocks.TIN_ORE, BronzeBlocks.DEEPSLATE_TIN_ORE,

            BronzeItems.BRONZE_INGOT, BronzeItems.BRONZE_NUGGET, BronzeBlocks.BRONZE_BLOCK, BronzeItems.BRONZE_SHEET,

            BronzeItems.BRONZE_SHOVEL, BronzeItems.BRONZE_PICKAXE, BronzeItems.BRONZE_AXE,
            BronzeItems.BRONZE_HOE, BronzeItems.BRONZE_SWORD,

            BronzeItems.BRONZE_HELMET, BronzeItems.BRONZE_CHESTPLATE, BronzeItems.BRONZE_LEGGINGS, BronzeItems.BRONZE_BOOTS,

            BronzeBlocks.BRONZE_COGWHEEL, BronzeBlocks.BRONZE_LARGE_COGWHEEL, BronzeBlocks.BRONZE_CASING


    );

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "bronze_age_tachibana" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BronzeAgeTachibana.MODID);

    public static RegistryObject<CreativeModeTab> BASE_BRONZE_TAB = CREATIVE_MODE_TABS.register(
            "bronze_age", () -> CreativeModeTab.builder()
                // Set name of tab to display
                .title(Component.translatable("item_group." + BronzeAgeTachibana.MODID + ".bronze_age"))
                // Set icon of creative tab
                .icon(() -> new ItemStack(BronzeItems.BRONZE_INGOT.get()))
                // Add default items to tab
                .displayItems((params, output) -> {
                    registerCreativeTabsItems.forEach(registryEntry -> output.accept((ItemLike) registryEntry.get()));
                })
                .build()
    );
}
