package com.github.mechalopa.jafohana.world.level.levelgen.feature.configurations;

import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.world.level.block.VineFlowerBlock;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class VineFlowersConfiguration implements FeatureConfiguration
{
	public static final Codec<VineFlowersConfiguration> CODEC = RecordCodecBuilder.create(p -> p.group(
            BlockPredicate.CODEC.fieldOf("allowed_placement").forGetter(p1 -> p1.allowedPlacement),
			IntProvider.NON_NEGATIVE_CODEC.fieldOf("height").forGetter(p1 -> p1.height),
			BuiltInRegistries.BLOCK.byNameCodec().fieldOf("place_block").flatXmap(VineFlowersConfiguration::apply, DataResult::success).orElse((VineFlowerBlock)ModBlocks.JADE_VINE.get()).forGetter(p1 -> p1.placeBlock)
			).apply(p, VineFlowersConfiguration::new));

	private final BlockPredicate allowedPlacement;
	private final IntProvider height;
	private final VineFlowerBlock placeBlock;

	public VineFlowersConfiguration(BlockPredicate allowedPlacement, IntProvider height, VineFlowerBlock placeBlock)
	{
		this.allowedPlacement = allowedPlacement;
		this.height = height;
		this.placeBlock = placeBlock;
	}

	public BlockPredicate allowedPlacement()
	{
		return this.allowedPlacement;
	}

	public IntProvider height()
	{
		return this.height;
	}

	public VineFlowerBlock placeBlock()
	{
		return this.placeBlock;
	}

	private static DataResult<VineFlowerBlock> apply(Block block)
	{
		return block instanceof VineFlowerBlock vineflowerblock ? DataResult.success(vineflowerblock) : DataResult.error(() -> "Growth block should be a vineflower block");
	}
}