package org.syju.bronze_age_tachibana.content.kinetics;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.syju.bronze_age_tachibana.registry.BronzePatialModels;

public class EncasedBronzeCogRenderer extends KineticBlockEntityRenderer<SimpleKineticBlockEntity> {
    private final boolean large;

    public static EncasedCogRenderer small(BlockEntityRendererProvider.Context context) {
        return new EncasedCogRenderer(context, false);
    }

    public static EncasedCogRenderer large(BlockEntityRendererProvider.Context context) {
        return new EncasedCogRenderer(context, true);
    }

    public EncasedBronzeCogRenderer(BlockEntityRendererProvider.Context context, boolean large) {
        super(context);
        this.large = large;
    }

    @Override
    protected void renderSafe(SimpleKineticBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;

        BlockState blockState = be.getBlockState();
        Block block = blockState.getBlock();
        if (!(block instanceof IRotate def))
            return;

        Direction.Axis axis = getRotationAxisOf(be);
        BlockPos pos = be.getBlockPos();
        float angle = large ? BracketedKineticBlockEntityRenderer.getAngleForLargeCogShaft(be, axis)
                : getAngleForBe(be, pos, axis);

        for (Direction d : Iterate.directionsInAxis(getRotationAxisOf(be))) {
            if (!def.hasShaftTowards(be.getLevel(), be.getBlockPos(), blockState, d))
                continue;
            SuperByteBuffer shaft = CachedBuffers.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), d);
            kineticRotationTransform(shaft, be, axis, angle, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
        }
    }

    @Override
    protected SuperByteBuffer getRotatedModel(SimpleKineticBlockEntity be, BlockState state) {
        return CachedBuffers.partialFacingVertical(
                large ? BronzePatialModels.SHAFTLESS_BRONZE_LARGE_COGWHEEL : BronzePatialModels.SHAFTLESS_BRONZE_COGWHEEL, state,
                Direction.fromAxisAndDirection(state.getValue(EncasedCogwheelBlock.AXIS), Direction.AxisDirection.POSITIVE));
    }

}
