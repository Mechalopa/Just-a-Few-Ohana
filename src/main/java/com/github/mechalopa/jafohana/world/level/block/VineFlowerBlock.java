package com.github.mechalopa.jafohana.world.level.block;

import java.util.Optional;

import javax.annotation.Nullable;

import com.github.mechalopa.jafohana.ModConfigs;
import com.github.mechalopa.jafohana.util.ModTags;
import com.github.mechalopa.jafohana.world.level.block.state.properties.ModBlockStateProperties;
import com.github.mechalopa.jafohana.world.level.block.state.properties.VineFlowerPart;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class VineFlowerBlock extends Block implements BonemealableBlock
{
	public static final EnumProperty<VineFlowerPart> PART = ModBlockStateProperties.VINE_FLOWER_PART;

	public VineFlowerBlock()
	{
		super(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.CAVE_VINES).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY));
		this.registerDefaultState(this.stateDefinition.any().setValue(PART, VineFlowerPart.SINGLE));
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
	{
		if (direction == Direction.UP && !this.canSurvive(state, level, currentPos))
		{
			return Blocks.AIR.defaultBlockState();
		}
		else if (direction.getAxis() == Direction.Axis.Y)
		{
			if (level.getBlockState(currentPos.above()).is(this))
			{
				if (level.getBlockState(currentPos.below()).is(this))
				{
					return state.setValue(PART, VineFlowerPart.LONG_BODY);
				}
				else if (level.getBlockState(currentPos.above(2)).is(this))
				{
					return state.setValue(PART, VineFlowerPart.LONG_HEAD);
				}
				else
				{
					return state.setValue(PART, VineFlowerPart.SHORT_HEAD);
				}
			}
			else if (level.getBlockState(currentPos.below()).is(this))
			{
				if (level.getBlockState(currentPos.below(2)).is(this))
				{
					return state.setValue(PART, VineFlowerPart.LONG_STEM);
				}
				else
				{
					return state.setValue(PART, VineFlowerPart.SHORT_STEM);
				}
			}
			else
			{
				return state.setValue(PART, VineFlowerPart.SINGLE);
			}
		}
		else
		{
			return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		BlockPos pos1 = pos.above();
		BlockState state1 = level.getBlockState(pos1);
		return state1.is(this) || (state1.is(ModTags.BlockTags.VINE_FLOWER_PLANTABLE_ON) && (state1.is(BlockTags.LEAVES) || state1.isFaceSturdy(level, pos1, Direction.DOWN)));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		BlockState state = this.defaultBlockState();
		BlockPos pos = context.getClickedPos();
		LevelReader levelReader = context.getLevel();

		if (levelReader.getBlockState(pos.above()).is(this))
		{
			if (levelReader.getBlockState(pos.above(2)).is(this))
			{
				return state.setValue(PART, VineFlowerPart.LONG_HEAD);
			}
			else
			{
				return state.setValue(PART, VineFlowerPart.SHORT_HEAD);
			}
		}
		else
		{
			return state.setValue(PART, VineFlowerPart.SINGLE);
		}
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state)
	{
		if (this.getGrowChance() <= 0.0D)
		{
			return false;
		}
		else if (state.getValue(PART).isHead())
		{
			return this.canGrowInto(level.getBlockState(pos.relative(Direction.DOWN)));
		}
		else
		{
			Optional<BlockPos> optional = this.getHeadPos(level, pos);
			return optional.isPresent() && this.canGrowInto(level.getBlockState(optional.get().relative(Direction.DOWN)));
		}
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state)
	{
		return (double)level.getRandom().nextFloat() < this.getGrowChance();
	}

	public double getGrowChance()
	{
		return ModConfigs.cachedServer.VINE_FLOWER_GROW_CHANCE;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
	{
		if (state.getValue(PART).isHead())
		{
			BlockPos pos1 = pos.relative(Direction.DOWN);
			VineFlowerPart vineflowerpart = state.getValue(PART);
			level.setBlockAndUpdate(pos1, state.setValue(PART, vineflowerpart == VineFlowerPart.SINGLE ? VineFlowerPart.SHORT_HEAD : VineFlowerPart.LONG_HEAD));
		}
		else
		{
			Optional<BlockPos> optional = this.getHeadPos(level, pos);

			if (optional.isPresent())
			{
				BlockState blockstate = level.getBlockState(optional.get());
				((VineFlowerBlock)blockstate.getBlock()).performBonemeal(level, random, optional.get(), blockstate);
			}
		}
	}

	protected boolean canGrowInto(BlockState state)
	{
		return state.isAir();
	}

	private Optional<BlockPos> getHeadPos(BlockGetter level, BlockPos pos)
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
		BlockState blockstate;

		do
		{
			blockpos$mutableblockpos.move(Direction.DOWN);
			blockstate = level.getBlockState(blockpos$mutableblockpos);
		}
		while (blockstate.is(this) && !blockstate.getValue(PART).isHead());

		return (blockstate.is(this) && blockstate.getValue(PART).isHead()) ? Optional.of(blockpos$mutableblockpos) : Optional.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(PART);
	}

	@Override
	public boolean isFlammable(BlockState state, BlockGetter getter, BlockPos pos, Direction direction)
	{
		return true;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter getter, BlockPos pos, Direction direction)
	{
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter getter, BlockPos pos, Direction direction)
	{
		return 15;
	}
}