package com.github.mechalopa.jafohana.world.level.block;

import com.github.mechalopa.jafohana.ModConfigs;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public class FasciatedOxeyeDaisyBlock extends ModTallFlowerBlock
{
	public FasciatedOxeyeDaisyBlock()
	{
		super();
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource rand, BlockPos pos, BlockState blockState)
	{
		if (ModConfigs.cachedServer.FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL)
		{
			popResource(serverLevel, pos, new ItemStack(Items.OXEYE_DAISY, 1));
		}
		else
		{
			super.performBonemeal(serverLevel, rand, pos, blockState);
		}
	}
}