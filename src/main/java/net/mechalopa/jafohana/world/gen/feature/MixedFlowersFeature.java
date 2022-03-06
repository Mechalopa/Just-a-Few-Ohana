package net.mechalopa.jafohana.world.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFlowersFeature;

public class MixedFlowersFeature extends DefaultFlowersFeature
{
	public MixedFlowersFeature(Codec<BlockClusterFeatureConfig> codec)
	{
		super(codec);
	}

	@Override
	public boolean place(ISeedReader seedReader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, BlockClusterFeatureConfig config)
	{
		BlockState blockstate = this.getRandomFlower(rand, pos, config);
		int i = 0;

		for (int j = 0; j < this.getCount(config); ++j)
		{
			BlockPos blockpos = this.getPos(rand, pos, config);

			if (seedReader.isEmptyBlock(blockpos) && blockpos.getY() < 255 && blockstate.canSurvive(seedReader, blockpos) && this.isValid(seedReader, blockpos, config))
			{
				if (seedReader.setBlock(blockpos, blockstate, 2))
				{
					blockstate = this.getRandomFlower(rand, pos, config);
				}

				++i;
			}
		}

		return i > 0;
	}
}