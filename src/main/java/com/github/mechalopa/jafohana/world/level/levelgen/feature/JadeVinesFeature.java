package com.github.mechalopa.jafohana.world.level.levelgen.feature;

import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.world.level.block.JadeVineBlock;
import com.github.mechalopa.jafohana.world.level.block.state.properties.JadeVinePart;
import com.github.mechalopa.jafohana.world.level.levelgen.feature.JadeVinesFeature.JadeVinesConfiguration;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class JadeVinesFeature extends Feature<JadeVinesConfiguration>
{
	public JadeVinesFeature(Codec<JadeVinesConfiguration> config)
	{
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<JadeVinesConfiguration> context)
	{
		JadeVinesConfiguration jadevinesconfiguration = context.config();
		BlockState state = ModBlocks.JADE_VINE.get().defaultBlockState();
		int i = jadevinesconfiguration.height().sample(context.random());

		if (i == 0)
		{
			return false;
		}
		else
		{
			WorldGenLevel level = context.level();
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
				level.setBlock(pos, state.setValue(JadeVineBlock.PART, JadeVinePart.SINGLE), 2);
			}
			else if (i == 2)
			{
				level.setBlock(pos, state.setValue(JadeVineBlock.PART, JadeVinePart.SHORT_STEM), 2);
				level.setBlock(pos.below(), state.setValue(JadeVineBlock.PART, JadeVinePart.SHORT_HEAD), 2);
			}
			else
			{
				BlockPos.MutableBlockPos mutableblockpos1 = pos.mutable();

				for (int h = 0; h < i; ++h)
				{
					if (h == i - 1)
					{
						level.setBlock(mutableblockpos1, state.setValue(JadeVineBlock.PART, JadeVinePart.LONG_HEAD), 2);
						break;
					}
					else
					{
						level.setBlock(mutableblockpos1, state.setValue(JadeVineBlock.PART, h == 0 ? JadeVinePart.LONG_STEM : JadeVinePart.LONG_BODY), 2);
					}

					mutableblockpos1.move(Direction.DOWN);
				}
			}

			return true;
		}
	}

	public record JadeVinesConfiguration(IntProvider height) implements FeatureConfiguration
	{
		public static final Codec<JadeVinesConfiguration> CODEC = RecordCodecBuilder.create((p_191222_) -> {
			return p_191222_.group(IntProvider.NON_NEGATIVE_CODEC.fieldOf("height").forGetter(JadeVinesConfiguration::height)).apply(p_191222_, JadeVinesConfiguration::new);
		});
	}
}