package com.github.mechalopa.jafohana.world.level.block;

import java.util.Optional;

import javax.annotation.Nullable;

import com.github.mechalopa.jafohana.ModConfigs;
import com.github.mechalopa.jafohana.util.ModTags;
import com.github.mechalopa.jafohana.world.level.block.state.properties.JadeVinePart;
import com.github.mechalopa.jafohana.world.level.block.state.properties.ModBlockStateProperties;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
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

public class JadeVineBlock extends Block implements BonemealableBlock
{
	public static final EnumProperty<JadeVinePart> PART = ModBlockStateProperties.JADE_VINE_PART;

	public JadeVineBlock()
	{
		super(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.CAVE_VINES).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY));
		this.registerDefaultState(this.stateDefinition.any().setValue(PART, JadeVinePart.SINGLE));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccerssor, BlockPos pos, BlockPos pos1)
	{
		if (direction == Direction.UP && !this.canSurvive(state, levelAccerssor, pos))
		{
			return Blocks.AIR.defaultBlockState();
		}
		else if (direction.getAxis() == Direction.Axis.Y)
		{
			if (levelAccerssor.getBlockState(pos.above()).is(this))
			{
				if (levelAccerssor.getBlockState(pos.below()).is(this))
				{
					return state.setValue(PART, JadeVinePart.LONG_BODY);
				}
				else if (levelAccerssor.getBlockState(pos.above(2)).is(this))
				{
					return state.setValue(PART, JadeVinePart.LONG_HEAD);
				}
				else
				{
					return state.setValue(PART, JadeVinePart.SHORT_HEAD);
				}
			}
			else if (levelAccerssor.getBlockState(pos.below()).is(this))
			{
				if (levelAccerssor.getBlockState(pos.below(2)).is(this))
				{
					return state.setValue(PART, JadeVinePart.LONG_STEM);
				}
				else
				{
					return state.setValue(PART, JadeVinePart.SHORT_STEM);
				}
			}
			else
			{
				return state.setValue(PART, JadeVinePart.SINGLE);
			}
		}
		else
		{
			return super.updateShape(state, direction, state1, levelAccerssor, pos, pos1);
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos)
	{
		BlockState state1 = levelReader.getBlockState(pos.above());
		return state1.is(this) || state1.is(ModTags.BlockTags.JADE_VINE_PLANTABLE_ON);
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
				return state.setValue(PART, JadeVinePart.LONG_HEAD);
			}
			else
			{
				return state.setValue(PART, JadeVinePart.SHORT_HEAD);
			}
		}
		else
		{
			return state.setValue(PART, JadeVinePart.SINGLE);
		}
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos pos, BlockState state)
	{
		if (state.getValue(PART).isHead())
		{
			return this.canGrowInto(levelReader.getBlockState(pos.relative(Direction.DOWN)));
		}
		else
		{
			Optional<BlockPos> optional = this.getHeadPos(levelReader, pos, state.getBlock());
			return optional.isPresent() && this.canGrowInto(levelReader.getBlockState(optional.get().relative(Direction.DOWN)));
		}
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state)
	{
		return (double)level.getRandom().nextFloat() < ModConfigs.cachedServer.JADE_VINE_GROW_CHANCE;
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource random, BlockPos pos, BlockState state)
	{
		if (state.getValue(PART).isHead())
		{
			BlockPos pos1 = pos.relative(Direction.DOWN);
			JadeVinePart jadevinepart = state.getValue(PART);
			serverLevel.setBlockAndUpdate(pos1, state.setValue(PART, jadevinepart == JadeVinePart.SINGLE ? JadeVinePart.SHORT_HEAD : JadeVinePart.LONG_HEAD));
		}
		else
		{
			Optional<BlockPos> optional = this.getHeadPos(serverLevel, pos, state.getBlock());

			if (optional.isPresent())
			{
				BlockState blockstate = serverLevel.getBlockState(optional.get());
				((JadeVineBlock)blockstate.getBlock()).performBonemeal(serverLevel, random, optional.get(), blockstate);
			}
		}
	}

	protected boolean canGrowInto(BlockState state)
	{
		return state.isAir();
	}

	private Optional<BlockPos> getHeadPos(BlockGetter getter, BlockPos pos, Block p_153325_)
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
		BlockState blockstate;

		do
		{
			blockpos$mutableblockpos.move(Direction.DOWN);
			blockstate = getter.getBlockState(blockpos$mutableblockpos);
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