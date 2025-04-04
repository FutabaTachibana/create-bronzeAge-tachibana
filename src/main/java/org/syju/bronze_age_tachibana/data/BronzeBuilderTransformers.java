package org.syju.bronze_age_tachibana.data;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;
import org.syju.bronze_age_tachibana.config.BronzeCStress;
import org.syju.bronze_age_tachibana.registry.BronzeBlocks;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;

public class BronzeBuilderTransformers {
    public static <B extends EncasedShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedShaft(String casing,
                                                                                                         Supplier<CTSpriteShiftEntry> casingShift) {
        return builder -> encasedBase(builder, AllBlocks.SHAFT::get)
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() != s.getValue(EncasedShaftBlock.AXIS))))
                .blockstate((c, p) -> axisBlock(c, p, blockState -> p.models()
                        .getExistingFile(p.modLoc("block/encased_shaft/block_" + casing)), true))
                .item()
                .model(AssetLookup.customBlockItemModel("encased_shaft", "item_" + casing))
                .build();
    }

    public static <B extends EncasedCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedCogwheel(
            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        return b -> encasedCogwheelBase(b, casing, casingShift, () -> BronzeBlocks.BRONZE_COGWHEEL, false);
    }

    public static <B extends EncasedCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedLargeCogwheel(
            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        return b -> encasedCogwheelBase(b, casing, casingShift, () -> BronzeBlocks.BRONZE_LARGE_COGWHEEL, true)
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(casingShift.get())));
    }

    private static <B extends EncasedCogwheelBlock, P> BlockBuilder<B, P> encasedCogwheelBase(BlockBuilder<B, P> b,
                                                                                              String casing, Supplier<CTSpriteShiftEntry> casingShift, Supplier<ItemLike> drop, boolean large) {
        String encasedSuffix = "_encased_cogwheel_side" + (large ? "_connected" : "");
        String blockFolder = large ? "encased_bronze_large_cogwheel" : "encased_bronze_cogwheel";
        String wood = casing.equals("brass") ? "dark_oak" : "spruce";
        String gearbox = casing.equals("brass") ? "brass_gearbox" : "bronze_gearbox";
        // Necessary resources: ?_casing.png | ?_encased_cogwheel_side.png | ?_encased_cogwheel_side_connected.png | ?_gearbox.png
        return encasedBase(b, drop).addLayer(() -> RenderType::cutoutMipped)
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() == s.getValue(EncasedCogwheelBlock.AXIS)
                                && !s.getValue(f.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT
                                : EncasedCogwheelBlock.BOTTOM_SHAFT))))
                .blockstate((c, p) -> axisBlock(c, p, blockState -> {
                    String suffix = (blockState.getValue(EncasedCogwheelBlock.TOP_SHAFT) ? "_top" : "")
                            + (blockState.getValue(EncasedCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : "");
                    String modelName = c.getName() + suffix;
                    return p.models()
                            .withExistingParent(modelName, p.modLoc("block/" + blockFolder + "/block" + suffix))
                            .texture("casing", BronzeAgeTachibana.asResource("block/" + casing + "_casing"))
                            .texture("particle", BronzeAgeTachibana.asResource("block/" + casing + "_casing"))
                            .texture("4", BronzeAgeTachibana.asResource("block/" + gearbox))
                            .texture("1", ResourceLocation.withDefaultNamespace("block/stripped_" + wood + "_log_top"))
                            .texture("side", BronzeAgeTachibana.asResource("block/" + casing + encasedSuffix));
                }, false))
                .item()
                .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/" + blockFolder + "/item"))
                        .texture("casing", BronzeAgeTachibana.asResource("block/" + casing + "_casing"))
                        .texture("particle", BronzeAgeTachibana.asResource("block/" + casing + "_casing"))
                        .texture("1", ResourceLocation.withDefaultNamespace("block/stripped_" + wood + "_log_top"))
                        .texture("side", BronzeAgeTachibana.asResource("block/" + casing + encasedSuffix)))
                .build();
    }

    private static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> encasedBase(BlockBuilder<B, P> b,
                                                                                           Supplier<ItemLike> drop) {
        return b.initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(BronzeCStress.setNoImpact())
                .loot((p, lb) -> p.dropOther(lb, drop.get()));
    }
}
