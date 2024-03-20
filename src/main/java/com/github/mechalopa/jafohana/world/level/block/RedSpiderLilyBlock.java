package com.github.mechalopa.jafohana.world.level.block;

import java.util.function.Supplier;

import com.github.mechalopa.jafohana.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class RedSpiderLilyBlock extends MediumFlowerBlock
{
	public RedSpiderLilyBlock(Supplier<MobEffect> effect, int effectDuration)
	{
		super(effect, effectDuration);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
	{
		return state.is(ModTags.BlockTags.RED_SPIDER_LILY_PLANTABLE_ON);
	}
}