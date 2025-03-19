package org.syju.bronze_age_tachibana.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;

public class CreativeTabs {
    public static void registerCreativeTabs(final DeferredRegister<CreativeModeTab> deferredRegister) {
        deferredRegister.register("bronze_age", () -> CreativeModeTab.builder()
                // Set name of tab to display
                .title(Component.translatable("item_group." + BronzeAgeTachibana.MODID + ".bronze_age"))
                // Set icon of creative tab
                .icon(() -> new ItemStack(AllItems.BRONZE_INGOT.get()))
                // Add default items to tab
                .displayItems((params, output) -> {
                    output.accept(AllItems.BRONZE_INGOT.get());
                    output.accept(AllItems.BRONZE_AXE.get());
                })
                .build()
        );
    }
}
