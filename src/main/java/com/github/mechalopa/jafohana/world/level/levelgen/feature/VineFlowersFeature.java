package com.github.mechalopa.jafohana.world.level.levelgen.feature;

import com.github.mechalopa.jafohana.world.level.block.VineFlowerBlock;
import com.github.mechalopa.jafohana.world.level.block.state.properties.VineFlowerPart;
import com.github.mechalopa.jafohana.world.level.levelgen.feature.configurations.VineFlowersConfiguration;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class VineFlowersFeature extends Feature<VineFlowersConfiguration>
{
	public VineFlowersFeature(Codec<VineFlowersConfiguration> config)
	{
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<VineFlowersConfiguration> context)
	{
		VineFlowersConfiguration config = context.config();
		int i = config.height().sample(context.random());

		if (i == 0)
		{
			return false;
		}
		else
		{
			WorldGenLevel level = context.level();
			BlockState state = config.placeBlock().defaultBlockState();
			BlockPos pos = context.origin();
			BlockPos.MutableBlockPos mutableblockpos = pos.mutable();

			for (int j = 0; j < i; ++j)
			{
				if (!level.isEmptyBlock(mutableblockpos))
				{
					if (j > 1)
					{
						i = j;
						break;
					}
					else
					{
						return false;
					}
				}

				mutableblockpos.move(Direction.DOWN);
			}

			if (i == 1)
			{
				level.setBlock(pos, state.setValue(VineFlowerBlock.PART, VineFlowerPart.SINGLE), 2);
			}
			else if (i == 2)
			{
				level.setBlock(pos, state.setValue(VineFlowerBlock.PART, VineFlowerPart.SHORT_STEM), 2);
				level.setBlock(pos.below(), state.setValue(VineFlowerBlock.PART, VineFlowerPart.SHORT_HEAD), 2);
			}
			else
			{
				BlockPos.MutableBlockPos mutableblockpos1 = pos.mutable();

				for (int h = 0; h < i; ++h)
				{
					if (h == i - 1)
					{
						level.setBlock(mutableblockpos1, state.setValue(VineFlowerBlock.PART, VineFlowerPart.LONG_HEAD), 2);
						break;
					}
					else
					{
						level.setBlock(mutableblockpos1, state.setValue(VineFlowerBlock.PART, h == 0 ? VineFlowerPart.LONG_STEM : VineFlowerPart.LONG_BODY), 2);
					}

					mutableblockpos1.move(Direction.DOWN);
				}
			}

			return true;
		}
	}
}