package com.github.mechalopa.jafohana.world.gen.feature;

public class MixedFlowersFeature// extends DefaultFlowersFeature
{
//	public MixedFlowersFeature(Codec<BlockClusterFeatureConfig> codec)
//	{
//		super(codec);
//	}
//
//	@Override
//	public boolean place(ISeedReader seedReader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, BlockClusterFeatureConfig config)
//	{
//		BlockState blockstate = this.getRandomFlower(rand, pos, config);
//		int i = 0;
//
//		for (int j = 0; j < this.getCount(config); ++j)
//		{
//			BlockPos blockpos = this.getPos(rand, pos, config);
//
//			if (seedReader.isEmptyBlock(blockpos) && blockpos.getY() < 255 && blockstate.canSurvive(seedReader, blockpos) && this.isValid(seedReader, blockpos, config))
//			{
//				seedReader.setBlock(blockpos, blockstate, 2);
//				blockstate = this.getRandomFlower(rand, pos, config);
//				++i;
//			}
//		}
//
//		return i > 0;
//	}
}