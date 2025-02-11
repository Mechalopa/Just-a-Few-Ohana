package com.github.mechalopa.jafohana.world.level.block;

import com.github.mechalopa.jafohana.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class BadlandsMediumFlowerBlock extends MediumFlowerBlock
{
	public BadlandsMediumFlowerBlock(Holder<MobEffect> effect, float seconds)
	{
		super(effect, seconds);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
	{
		return state.is(ModTags.BlockTags.BADLANDS_FLOWER_PLANTABLE_ON);
	}
}