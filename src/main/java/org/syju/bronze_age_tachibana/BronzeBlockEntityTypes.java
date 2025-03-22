package org.syju.bronze_age_tachibana;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityVisual;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogVisual;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import org.syju.bronze_age_tachibana.registry.AllBlocks;

import static org.syju.bronze_age_tachibana.registry.Registrate.REGISTRATE;

public class BronzeBlockEntityTypes {
//    public static final BlockEntityEntry<SimpleKineticBlockEntity> BRONZE_COGWHEEL = REGISTRATE
//            .blockEntity("bronze_cogwheel", SimpleKineticBlockEntity::new)
//            .visual(() -> EncasedCogVisual::small, false)
//            .validBlocks(AllBlocks.LARGE_BRONZE_COGWHEEL)
//            .renderer(() -> EncasedCogRenderer::large)
//            .register();
//    public static final BlockEntityEntry<SimpleKineticBlockEntity> LARGE_BRONZE_COGWHEEL = REGISTRATE
//            .blockEntity("bronze_cogwheel_large", SimpleKineticBlockEntity::new)
//            .visual(() -> EncasedCogVisual::large, false)
//            .validBlocks(AllBlocks.LARGE_BRONZE_COGWHEEL)
//            .renderer(() -> EncasedCogRenderer::large)
//            .register();
public static final BlockEntityEntry<BracketedKineticBlockEntity> BRONZE_BRACKETED_KINETIC = REGISTRATE
        .blockEntity("simple_kinetic", BracketedKineticBlockEntity::new)
        .visual(() -> BracketedKineticBlockEntityVisual::create, false)
        .validBlocks(AllBlocks.BRONZE_COGWHEEL, AllBlocks.LARGE_BRONZE_COGWHEEL)
        .renderer(() -> BracketedKineticBlockEntityRenderer::new)
        .register();
}
