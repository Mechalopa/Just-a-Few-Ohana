package com.github.mechalopa.jafohana.registry;

import com.github.mechalopa.jafohana.JAFOhana;
import com.github.mechalopa.jafohana.world.level.modifiers.ModAddFeaturesBiomeModifier;
import com.mojang.serialization.Codec;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers
{
	private static final DeferredRegister<Codec<? extends BiomeModifier>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JAFOhana.MODID);

	public static final DeferredHolder<Codec<? extends BiomeModifier>, Codec<ModAddFeaturesBiomeModifier>> ADD_FEATURES = REGISTRY.register("add_features", () -> ModAddFeaturesBiomeModifier.CODEC);

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}