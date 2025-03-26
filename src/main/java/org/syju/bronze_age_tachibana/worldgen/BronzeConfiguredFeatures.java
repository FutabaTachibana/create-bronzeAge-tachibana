package org.syju.bronze_age_tachibana.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;
import org.syju.bronze_age_tachibana.registry.BronzeBlocks;

import java.util.List;

public class BronzeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>>
            TIN_ORE = key("tin_ore");

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, BronzeAgeTachibana.asResource(name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> ctx) {
        RuleTest stoneOreReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> tinTargetStates = List.of(
                OreConfiguration.target(stoneOreReplaceables, BronzeBlocks.TIN_ORE.getDefaultState()),
                OreConfiguration.target(deepslateOreReplaceables, BronzeBlocks.DEEPSLATE_TIN_ORE.getDefaultState())
        );
//        FeatureUtils.register(ctx, TIN_ORE, Feature.ORE, new OreConfiguration(tinTargetStates, 12));
        ctx.register(TIN_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(tinTargetStates, 12)));
    }
}
