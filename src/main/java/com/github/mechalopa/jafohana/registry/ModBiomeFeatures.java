package com.github.mechalopa.jafohana.registry;

import java.util.function.Supplier;

import com.github.mechalopa.jafohana.JAFOhana;
import com.github.mechalopa.jafohana.world.level.levelgen.feature.LayeringFeature;
import com.github.mechalopa.jafohana.world.level.levelgen.feature.VineFlowersFeature;
import com.github.mechalopa.jafohana.world.level.levelgen.feature.configurations.LayeringFeatureConfiguration;
import com.github.mechalopa.jafohana.world.level.levelgen.feature.configurations.VineFlowersConfiguration;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBiomeFeatures
{
	private static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(Registries.FEATURE, JAFOhana.MODID);

	public static final Supplier<Feature<VineFlowersConfiguration>> JADE_VINES = REGISTRY.register("vine_flowers", () -> new VineFlowersFeature(VineFlowersConfiguration.CODEC));
	public static final Supplier<Feature<LayeringFeatureConfiguration>> LAYERING_FEATURE = REGISTRY.register("layering", () -> new LayeringFeature(LayeringFeatureConfiguration.CODEC));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}