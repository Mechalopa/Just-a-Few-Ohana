package com.github.mechalopa.jafohana.world.level.block;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModFlowerBlock extends FlowerBlock
{
	public ModFlowerBlock(Supplier<MobEffect> effect, int effectDuration)
	{
		super(effect, effectDuration, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
	}

	public boolean isReallyFlammable(BlockState state, BlockGetter getter, BlockPos pos, Direction direction)
	{
		return true;
	}

	@Override
	public boolean isFlammable(BlockState state, BlockGetter getter, BlockPos pos, Direction direction)
	{
		return this.isReallyFlammable(state, getter, pos, direction) || super.isFlammable(state, getter, pos, direction);
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter getter, BlockPos pos, Direction direction)
	{
		return this.isReallyFlammable(state, getter, pos, direction) ? 100 : super.getFlammability(state, getter, pos, direction);
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter getter, BlockPos pos, Direction direction)
	{
		return this.isReallyFlammable(state, getter, pos, direction) ? 60 : super.getFireSpreadSpeed(state, getter, pos, direction);
	}
}