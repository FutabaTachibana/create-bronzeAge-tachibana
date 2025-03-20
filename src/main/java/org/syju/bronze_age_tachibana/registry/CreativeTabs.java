package org.syju.bronze_age_tachibana.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;

import java.util.List;

public class CreativeTabs {
    private static final List<RegistryEntry> registerCreativeTabsItems = List.of(
            // Items and blocks...
            AllItems.RAW_TIN,
            AllItems.TIN_INGOT,
            AllItems.TIN_NUGGET,
            AllItems.TIN_SHEET,
            AllItems.BRONZE_INGOT,
            AllItems.BRONZE_NUGGET,
            AllItems.BRONZE_SHEET,
            AllItems.BRONZE_SWORD,
            AllItems.BRONZE_PICKAXE,
            AllItems.BRONZE_AXE,
            AllItems.BRONZE_SHOVEL,
            AllItems.BRONZE_HOE,
            AllItems.BRONZE_HELMET,
            AllItems.BRONZE_CHESTPLATE,
            AllItems.BRONZE_LEGGINGS,
            AllItems.BRONZE_BOOTS
    );

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "bronze_age_tachibana" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BronzeAgeTachibana.MODID);

    public static RegistryObject<CreativeModeTab> BASE_BRONZE_TAB = CREATIVE_MODE_TABS.register(
            "bronze_age", () -> CreativeModeTab.builder()
                // Set name of tab to display
                .title(Component.translatable("item_group." + BronzeAgeTachibana.MODID + ".bronze_age"))
                // Set icon of creative tab
                .icon(() -> new ItemStack(AllItems.BRONZE_INGOT.get()))
                // Add default items to tab
                .displayItems((params, output) -> {
                    registerCreativeTabsItems.forEach(registryEntry -> output.accept((ItemLike) registryEntry.get()));
                })
                .build()
    );
}
