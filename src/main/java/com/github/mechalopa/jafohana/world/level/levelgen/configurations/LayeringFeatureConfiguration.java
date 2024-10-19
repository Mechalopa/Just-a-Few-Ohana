package com.github.mechalopa.jafohana.world.level.levelgen.configurations;

import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import net.minecraft.core.HolderSet;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class LayeringFeatureConfiguration implements FeatureConfiguration
{
	public static final Codec<LayeringFeatureConfiguration> CODEC = ExtraCodecs.nonEmptyHolderSet(PlacedFeature.LIST_CODEC).fieldOf("features").xmap(LayeringFeatureConfiguration::new, p -> p.features).codec();
	public final HolderSet<PlacedFeature> features;

	public LayeringFeatureConfiguration(HolderSet<PlacedFeature> features)
	{
		this.features = features;
	}

	@Override
	public Stream<ConfiguredFeature<?, ?>> getFeatures()
	{
		return this.features.stream().flatMap(p -> p.value().getFeatures());
	}
}