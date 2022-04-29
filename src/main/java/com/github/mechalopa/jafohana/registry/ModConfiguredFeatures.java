package com.github.mechalopa.jafohana.registry;

import java.util.List;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredFeatures
{
	private static final DeferredRegister<ConfiguredFeature<?, ?>> REGISTRY = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, JAFOhana.MODID);

	public static final RegistryObject<ConfiguredFeature<?, ?>> FLOWER_PLAIN = REGISTRY.register("flower_plain", () -> new ConfiguredFeature<>(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(createPlacedFeatureHolder(ModBlocks.DAYFLOWER.get().defaultBlockState()), createPlacedFeatureHolder(ModBlocks.EVENING_PRIMROSE.get().defaultBlockState()), createPlacedFeatureHolder(ModBlocks.MILK_VETCH.get().defaultBlockState())))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> FLOWER_FOREST = REGISTRY.register("flower_forest", () -> new ConfiguredFeature<>(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(createPlacedFeatureHolder(ModBlocks.DAYFLOWER.get().defaultBlockState()), createPlacedFeatureHolder(ModBlocks.EVENING_PRIMROSE.get().defaultBlockState()), createPlacedFeatureHolder(ModBlocks.FORGET_ME_NOT.get().defaultBlockState())))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> FLOWER_SPOOKY_FOREST = REGISTRY.register("flower_spooky_forest", () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, createSimpleBlockConfiguration(ModBlocks.DAYFLOWER.get().defaultBlockState())))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> FLOWER_HILL = REGISTRY.register("flower_hill", () -> new ConfiguredFeature<>(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(createPlacedFeatureHolder(ModBlocks.EVENING_PRIMROSE.get().defaultBlockState()), createPlacedFeatureHolder(ModBlocks.FORGET_ME_NOT.get().defaultBlockState())))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> FLOWER_MEADOW = REGISTRY.register("flower_meadow", () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new DualNoiseProvider(new InclusiveRange<Integer>(1, 3), new NormalNoise.NoiseParameters(-7, 1.0D), 1.5F, 2345L, new NormalNoise.NoiseParameters(-6, 1.0D), 1.0F, List.of(ModBlocks.EVENING_PRIMROSE.get().defaultBlockState(), ModBlocks.MILK_VETCH.get().defaultBlockState(), ModBlocks.FORGET_ME_NOT.get().defaultBlockState())))))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> FLOWER_SWAMP = REGISTRY.register("flower_swamp", () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, createSimpleBlockConfiguration(ModBlocks.MILK_VETCH.get().defaultBlockState())))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> AFRICAN_DAISY = REGISTRY.register("african_daisy", () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.YELLOW_AFRICAN_DAISY.get().defaultBlockState(), 1).add(ModBlocks.PINK_AFRICAN_DAISY.get().defaultBlockState(), 1).add(ModBlocks.WHITE_AFRICAN_DAISY.get().defaultBlockState(), 1)))))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> RED_SPIDER_LILY_RIVER = REGISTRY.register("red_spider_lily_river", () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 9, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, createSimpleBlockConfiguration(ModBlocks.RED_SPIDER_LILY.get().defaultBlockState())))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> RED_SPIDER_LILY_SPOOKY_FOREST = REGISTRY.register("red_spider_lily_spooky_forest", () -> new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, createSimpleBlockConfiguration(ModBlocks.RED_SPIDER_LILY.get().defaultBlockState())))));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}

	private static SimpleBlockConfiguration createSimpleBlockConfiguration(BlockState blockstate)
	{
		return new SimpleBlockConfiguration(BlockStateProvider.simple(blockstate));
	}

	private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<PlacedFeature> createPlacedFeatureHolder(BlockState blockstate)
	{
		return PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, createSimpleBlockConfiguration(blockstate)));
	}
}