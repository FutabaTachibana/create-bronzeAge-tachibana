package org.syju.bronze_age_tachibana.worldgen;

import com.simibubi.create.infrastructure.worldgen.ConfigPlacementFilter;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;

import java.util.List;

public class BronzePlacedFeatures {
    public static final ResourceKey<PlacedFeature>
            TIN_ORE = key("tin_ore");

    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, BronzeAgeTachibana.asResource(name));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> ctx){
        HolderGetter<ConfiguredFeature<?, ?>> configured = ctx.lookup(Registries.CONFIGURED_FEATURE);
        ctx.register(TIN_ORE, new PlacedFeature(
                configured.getOrThrow(BronzeConfiguredFeatures.TIN_ORE),
                placement(CountPlacement.of(8), -63, 70)
        ));
    }

    private static List<PlacementModifier> placement(PlacementModifier frequency, int minHeight, int maxHeight) {
        return List.of(
                frequency,
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)),
                ConfigPlacementFilter.INSTANCE
        );
    }
}
