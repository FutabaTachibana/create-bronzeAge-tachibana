package org.syju.bronze_age_tachibana;

import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;

public class BronzeSpriteShifts {
    public static final CTSpriteShiftEntry BRONZE_CASING = omni("bronze_casing");

    public static final CTSpriteShiftEntry BRONZE_ENCASED_COGWHEEL_SIDE = vertical("bronze_encased_cogwheel_side"),
            BRONZE_ENCASED_COGWHEEL_OTHERSIDE = horizontal("bronze_encased_cogwheel_side");

    private static CTSpriteShiftEntry omni(String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }

    private static CTSpriteShiftEntry horizontal(String name) {
        return getCT(AllCTTypes.HORIZONTAL, name);
    }

    private static CTSpriteShiftEntry vertical(String name) {
        return getCT(AllCTTypes.VERTICAL, name);
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, BronzeAgeTachibana.asResource("block/" + blockTextureName),
                BronzeAgeTachibana.asResource("block/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }
}
