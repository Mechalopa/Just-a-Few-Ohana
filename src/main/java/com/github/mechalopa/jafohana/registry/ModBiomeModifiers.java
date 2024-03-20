package com.github.mechalopa.jafohana.registry;

import com.github.mechalopa.jafohana.JAFOhana;
import com.github.mechalopa.jafohana.world.level.modifiers.ModAddFeaturesBiomeModifier;
import com.mojang.serialization.Codec;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers
{
	private static final DeferredRegister<Codec<? extends BiomeModifier>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JAFOhana.MODID);

	public static final RegistryObject<Codec<ModAddFeaturesBiomeModifier>> ADD_FEATURES = REGISTRY.register("add_features", ModAddFeaturesBiomeModifier.CODEC);

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}