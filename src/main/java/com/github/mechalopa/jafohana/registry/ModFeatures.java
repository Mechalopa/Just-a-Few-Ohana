package com.github.mechalopa.jafohana.registry;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFeatures
{
	private static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, JAFOhana.MODID);

//	public static final net.minecraftforge.registries.RegistryObject<FlowersFeature<BlockClusterFeatureConfig>> MIXED_FLOWER = REGISTRY.register("mixed_flower", () -> new MixedFlowersFeature(BlockClusterFeatureConfig.CODEC));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}