package org.syju.bronze_age_tachibana.registry;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;

public class BronzePatialModels {
    public static final PartialModel
            BRONZE_COGWHEEL = block("bronze_cogwheel"),
            SHAFTLESS_BRONZE_LARGE_COGWHEEL = block("large_bronze_cogwheel_shaftless");

    private static PartialModel block(String path) {
        return PartialModel.of(BronzeAgeTachibana.asResource("block/" + path));
    }
}
