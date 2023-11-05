package com.github.mechalopa.jafohana.world.level.block;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.github.mechalopa.jafohana.util.ModTags;
import com.github.mechalopa.jafohana.world.level.block.state.properties.ModBlockStateProperties;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class CrimsonCloverBlock extends ModFlowerBlock
{
	public static final BooleanProperty ON_NYLIUM = ModBlockStateProperties.ON_NYLIUM;

	public CrimsonCloverBlock(Supplier<MobEffect> effectSupplier, int effectDuration)
	{
		super(effectSupplier, effectDuration);
		this.registerDefaultState(this.stateDefinition.any().setValue(ON_NYLIUM, false));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccerssor, BlockPos pos, BlockPos pos1)
	{
		return !state.canSurvive(levelAccerssor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state.setValue(ON_NYLIUM, levelAccerssor.getBlockState(pos.below()).is(BlockTags.NYLIUM)), direction, state1, levelAccerssor, pos, pos1);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
	{
		return state.is(ModTags.BlockTags.CRIMSON_CLOVER_PLANTABLE_ON);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		return this.defaultBlockState().setValue(ON_NYLIUM, context.getLevel().getBlockState(context.getClickedPos().below()).is(BlockTags.NYLIUM));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(ON_NYLIUM);
	}

	@Override
	public boolean isReallyFlammable(BlockState state, BlockGetter getter, BlockPos pos, Direction direction)
	{
		return !state.getValue(ON_NYLIUM);
	}
}