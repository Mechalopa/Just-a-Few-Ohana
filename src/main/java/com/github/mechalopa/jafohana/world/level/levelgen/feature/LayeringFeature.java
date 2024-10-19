package com.github.mechalopa.jafohana.world.level.levelgen.feature;

import com.github.mechalopa.jafohana.world.level.levelgen.feature.configurations.LayeringFeatureConfiguration;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class LayeringFeature extends Feature<LayeringFeatureConfiguration>
{
	public LayeringFeature(Codec<LayeringFeatureConfiguration> config)
	{
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<LayeringFeatureConfiguration> context)
	{
		RandomSource randomsource = context.random();
		LayeringFeatureConfiguration config = context.config();
		WorldGenLevel worldgenlevel = context.level();
		BlockPos blockpos = context.origin();
		ChunkGenerator chunkgenerator = context.chunkGenerator();
		int i = config.features.size();
		boolean flag = false;

		if (i > 0)
		{
			for (int j = 0; j < i; ++j)
			{
				PlacedFeature placedfeature = config.features.get(j).value();

				if (placedfeature != null)
				{
					flag = placedfeature.place(worldgenlevel, chunkgenerator, randomsource, blockpos) || flag;
				}
			}
		}

		return flag;
	}
}