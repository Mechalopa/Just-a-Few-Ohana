package com.github.mechalopa.jafohana.registry;

import java.util.List;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacedFeatures
{
	private static final DeferredRegister<PlacedFeature> REGISTRY = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, JAFOhana.MODID);

	public static final RegistryObject<PlacedFeature> FLOWER_PLAIN = createPlacedFeatureRegistryObject("flower_plain", ModConfiguredFeatures.FLOWER_PLAIN.getHolder().orElseThrow(), 32);
	public static final RegistryObject<PlacedFeature> FLOWER_FOREST = createPlacedFeatureRegistryObject("flower_forest", ModConfiguredFeatures.FLOWER_FOREST.getHolder().orElseThrow(), 40);
	public static final RegistryObject<PlacedFeature> FLOWER_FLOWER_FOREST = createPlacedFeatureRegistryObject("flower_flower_forest", ModConfiguredFeatures.FLOWER_FOREST.getHolder().orElseThrow(), 12);
	public static final RegistryObject<PlacedFeature> FLOWER_SPOOKY_FOREST = createPlacedFeatureRegistryObject("flower_spooky_forest", ModConfiguredFeatures.FLOWER_SPOOKY_FOREST.getHolder().orElseThrow(), 40);
	public static final RegistryObject<PlacedFeature> FLOWER_HILL = createPlacedFeatureRegistryObject("flower_hill", ModConfiguredFeatures.FLOWER_HILL.getHolder().orElseThrow(), 48);
	public static final RegistryObject<PlacedFeature> FLOWER_MEADOW = createPlacedFeatureRegistryObject("flower_meadow", ModConfiguredFeatures.FLOWER_MEADOW.getHolder().orElseThrow(), 3);
	public static final RegistryObject<PlacedFeature> FLOWER_SWAMP = createPlacedFeatureRegistryObject("flower_swamp", ModConfiguredFeatures.FLOWER_SWAMP.getHolder().orElseThrow(), 64);
	public static final RegistryObject<PlacedFeature> AFRICAN_DAISY = createPlacedFeatureRegistryObject("african_daisy", ModConfiguredFeatures.AFRICAN_DAISY.getHolder().orElseThrow(), 32);
	public static final RegistryObject<PlacedFeature> RED_SPIDER_LILY = createPlacedFeatureRegistryObject("red_spider_lily", ModConfiguredFeatures.RED_SPIDER_LILY.getHolder().orElseThrow(), 8);
	public static final RegistryObject<PlacedFeature> RED_SPIDER_LILY_SWAMP = createPlacedFeatureRegistryObject("red_spider_lily_swamp", ModConfiguredFeatures.RED_SPIDER_LILY.getHolder().orElseThrow(), 16);
	public static final RegistryObject<PlacedFeature> RED_SPIDER_LILY_RIVER = createPlacedFeatureRegistryObject("red_spider_lily_river", ModConfiguredFeatures.RED_SPIDER_LILY_RIVER.getHolder().orElseThrow(), 24);
	public static final RegistryObject<PlacedFeature> RED_SPIDER_LILY_SPOOKY_FOREST = createPlacedFeatureRegistryObject("red_spider_lily_spooky_forest", ModConfiguredFeatures.RED_SPIDER_LILY_SPOOKY_FOREST.getHolder().orElseThrow(), 48);
	public static final RegistryObject<PlacedFeature> MARIGOLD = createPlacedFeatureRegistryObject("marigold", ModConfiguredFeatures.MARIGOLD.getHolder().orElseThrow(), 48);

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}

	private static RegistryObject<PlacedFeature> createPlacedFeatureRegistryObject(String name, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placement)
	{
		return REGISTRY.register(name, () -> new PlacedFeature(feature, placement));
	}

	private static RegistryObject<PlacedFeature> createPlacedFeatureRegistryObject(String name, Holder<ConfiguredFeature<?, ?>> feature, int chance)
	{
		return createPlacedFeatureRegistryObject(name, feature, List.of(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
	}
}