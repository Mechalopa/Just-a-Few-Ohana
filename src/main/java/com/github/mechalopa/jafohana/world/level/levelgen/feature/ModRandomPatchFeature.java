package com.github.mechalopa.jafohana.world.level.levelgen.feature;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

public class ModRandomPatchFeature extends Feature<RandomPatchConfiguration>
{
	public ModRandomPatchFeature(Codec<RandomPatchConfiguration> config)
	{
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context)
	{
		RandomPatchConfiguration randompatchconfiguration = context.config();
		RandomSource randomsource = context.random();
		BlockPos blockpos = context.origin();
		WorldGenLevel worldgenlevel = context.level();
		int i = 0;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int j = randompatchconfiguration.xzSpread() + 1;
		int k = randompatchconfiguration.ySpread() + 1;

		for (int l = 0; l < randompatchconfiguration.tries(); ++l)
		{
			blockpos$mutableblockpos.setWithOffset(blockpos, randomsource.nextInt(j) - randomsource.nextInt(j), randomsource.nextInt(k) - randomsource.nextInt(k), randomsource.nextInt(j) - randomsource.nextInt(j));

			if (randompatchconfiguration.feature().value().place(worldgenlevel, context.chunkGenerator(), randomsource, blockpos$mutableblockpos))
			{
				++i;
			}
		}

		return i > 0;
	}
}