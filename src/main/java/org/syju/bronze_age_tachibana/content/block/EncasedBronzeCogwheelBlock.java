package org.syju.bronze_age_tachibana.content.block;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.syju.bronze_age_tachibana.registry.BronzeBlockEntityTypes;
import org.syju.bronze_age_tachibana.registry.BronzeBlocks;

import java.util.function.Supplier;

public class EncasedBronzeCogwheelBlock extends EncasedCogwheelBlock {

    public EncasedBronzeCogwheelBlock(Properties properties, boolean large, Supplier<Block> casing) {
        super(properties, large, casing);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        if (target instanceof BlockHitResult)
            return ((BlockHitResult) target).getDirection()
                    .getAxis() != getRotationAxis(state)
                    ? isLarge ? BronzeBlocks.BRONZE_LARGE_COGWHEEL.asStack() : BronzeBlocks.BRONZE_COGWHEEL.asStack()
                    : getCasing().asItem().getDefaultInstance();
        return super.getCloneItemStack(state, target, world, pos, player);
    }
    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        if (context.getLevel().isClientSide)
            return InteractionResult.SUCCESS;
        context.getLevel()
                .levelEvent(2001, context.getClickedPos(), Block.getId(state));
        KineticBlockEntity.switchToBlockState(context.getLevel(), context.getClickedPos(),
                (isLarge ? BronzeBlocks.BRONZE_LARGE_COGWHEEL : BronzeBlocks.BRONZE_COGWHEEL).getDefaultState()
                        .setValue(AXIS, state.getValue(AXIS)));
        return InteractionResult.SUCCESS;
    }
    @Override
    public ItemRequirement getRequiredItems(BlockState state, BlockEntity be) {
        return ItemRequirement
                .of(isLarge ? BronzeBlocks.BRONZE_LARGE_COGWHEEL.getDefaultState() : BronzeBlocks.BRONZE_COGWHEEL.getDefaultState(), be);
    }
    @Override
    public BlockEntityType<? extends SimpleKineticBlockEntity> getBlockEntityType() {
        // Need to return the proper block entity
        return isLarge ? BronzeBlockEntityTypes.ENCASED_BRONZE_LARGE_COGWHEEL.get() : BronzeBlockEntityTypes.ENCASED_BRONZE_COGWHEEL.get();
    }
}
