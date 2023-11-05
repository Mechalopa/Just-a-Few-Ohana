package com.github.mechalopa.jafohana.registry;

import com.github.mechalopa.jafohana.JAFOhana;
import com.github.mechalopa.jafohana.world.level.levelgen.feature.JadeVinesFeature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeFeatures
{
	private static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, JAFOhana.MODID);

	public static final RegistryObject<Feature<JadeVinesFeature.JadeVinesConfiguration>> JADE_VINES = REGISTRY.register("jade_vines", () -> new JadeVinesFeature(JadeVinesFeature.JadeVinesConfiguration.CODEC));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}