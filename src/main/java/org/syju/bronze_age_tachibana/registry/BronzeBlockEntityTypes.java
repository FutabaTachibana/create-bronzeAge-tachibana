package org.syju.bronze_age_tachibana.registry;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import org.syju.bronze_age_tachibana.content.kinetics.BronzeBracketedKineticBlockEntityRenderer;
import org.syju.bronze_age_tachibana.content.kinetics.BronzeBracketedKineticBlockEntityVisual;
import org.syju.bronze_age_tachibana.content.kinetics.EncasedBronzeCogRenderer;
import org.syju.bronze_age_tachibana.content.kinetics.EncasedBronzeCogVisual;

import static org.syju.bronze_age_tachibana.registry.Registrate.REGISTRATE;

public class BronzeBlockEntityTypes {
    // Bronze Cogwheel and Large Bronze Cogwheel
    public static final BlockEntityEntry<BracketedKineticBlockEntity> BRONZE_BRACKETED_KINETIC = REGISTRATE
            .blockEntity("bronze_cogwheels", BracketedKineticBlockEntity::new)
            .visual(() -> BronzeBracketedKineticBlockEntityVisual::create, false)
            .validBlocks(BronzeBlocks.BRONZE_COGWHEEL, BronzeBlocks.BRONZE_LARGE_COGWHEEL)
            .renderer(() -> BronzeBracketedKineticBlockEntityRenderer::new)
            .register();

    // Encased Bronze Cogwheel (& the large one)
    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_BRONZE_COGWHEEL = REGISTRATE
            .blockEntity("encased_bronze_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedBronzeCogVisual::small, false)
            .validBlocks(BronzeBlocks.BRASS_ENCASED_BRONZE_COGWHEEL)
            .renderer(() -> EncasedBronzeCogRenderer::small)
            .register();

    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_BRONZE_LARGE_COGWHEEL = REGISTRATE
            .blockEntity("encased_bronze_large_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedBronzeCogVisual::large, false)
            .validBlocks(BronzeBlocks.BRASS_ENCASED_BRONZE_LARGE_COGWHEEL)
            .renderer(() -> EncasedBronzeCogRenderer::large)
            .register();

    public static void initialize() { }
}
