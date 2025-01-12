package com.github.mechalopa.jafohana.world.level.block;

import com.github.mechalopa.jafohana.ModConfigs;

public class JadeVineBlock extends VineFlowerBlock
{
	public JadeVineBlock()
	{
		super();
	}

	@Override
	public double getGrowChance()
	{
		return ModConfigs.cachedServer.JADE_VINE_GROW_CHANCE;
	}
}