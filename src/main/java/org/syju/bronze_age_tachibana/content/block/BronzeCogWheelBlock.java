package org.syju.bronze_age_tachibana.content.block;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.syju.bronze_age_tachibana.BronzeBlockEntityTypes;

public class BronzeCogWheelBlock extends CogWheelBlock {
    protected BronzeCogWheelBlock(boolean large, Properties properties) {
        super(large, properties);
    }
    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return BronzeBlockEntityTypes.BRONZE_BRACKETED_KINETIC.get();
    }

}
