package org.syju.bronze_age_tachibana.content.kinetics;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import org.syju.bronze_age_tachibana.registry.BronzeBlocks;
import org.syju.bronze_age_tachibana.registry.BronzePatialModels;

public class BronzeBracketedKineticBlockEntityRenderer extends KineticBlockEntityRenderer<BracketedKineticBlockEntity> {

    public BronzeBracketedKineticBlockEntityRenderer(Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(BracketedKineticBlockEntity be, float partialTicks, PoseStack ms,
                              MultiBufferSource buffer, int light, int overlay) {

        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;

        if (!BronzeBlocks.BRONZE_LARGE_COGWHEEL.has(be.getBlockState())) {
            super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
            return;
        }

        // Large cogs sometimes have to offset their teeth by 11.25 degrees in order to
        // mesh properly

        VertexConsumer vc = buffer.getBuffer(RenderType.solid());
        Axis axis = getRotationAxisOf(be);
        Direction facing = Direction.fromAxisAndDirection(axis, AxisDirection.POSITIVE);
        renderRotatingBuffer(be,
                CachedBuffers.partialFacingVertical(BronzePatialModels.SHAFTLESS_BRONZE_LARGE_COGWHEEL, be.getBlockState(), facing),
                ms, vc, light);

        float angle = getAngleForLargeCogShaft(be, axis);
        SuperByteBuffer shaft =
                CachedBuffers.partialFacingVertical(AllPartialModels.COGWHEEL_SHAFT, be.getBlockState(), facing);
        kineticRotationTransform(shaft, be, axis, angle, light);
        shaft.renderInto(ms, vc);

    }

    public static float getAngleForLargeCogShaft(SimpleKineticBlockEntity be, Axis axis) {
        BlockPos pos = be.getBlockPos();
        float offset = getShaftAngleOffset(axis, pos);
        float time = AnimationTickHolder.getRenderTime(be.getLevel());
        float angle = ((time * be.getSpeed() * 3f / 10 + offset) % 360) / 180 * (float) Math.PI;
        return angle;
    }

    public static float getShaftAngleOffset(Axis axis, BlockPos pos) {
        if (KineticBlockEntityVisual.shouldOffset(axis, pos)) {
            return 22.5f;
        } else {
            return 0;
        }
    }

}
