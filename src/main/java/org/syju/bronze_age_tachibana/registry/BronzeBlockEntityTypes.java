package org.syju.bronze_age_tachibana.registry;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityVisual;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import org.syju.bronze_age_tachibana.content.kinetics.BronzeBracketedKineticBlockEntityRenderer;
import org.syju.bronze_age_tachibana.content.kinetics.BronzeBracketedKineticBlockEntityVisual;

import static org.syju.bronze_age_tachibana.registry.Registrate.REGISTRATE;

public class BronzeBlockEntityTypes {
    public static final BlockEntityEntry<BracketedKineticBlockEntity> BRONZE_BRACKETED_KINETIC = REGISTRATE
            .blockEntity("bronze_cogwheels", BracketedKineticBlockEntity::new)
            .visual(() -> BronzeBracketedKineticBlockEntityVisual::create, false)
            .validBlocks(BronzeBlocks.BRONZE_COGWHEEL, BronzeBlocks.LARGE_BRONZE_COGWHEEL)
            .renderer(() -> BronzeBracketedKineticBlockEntityRenderer::new)
            .register();

    public static void initialize() { }
}
