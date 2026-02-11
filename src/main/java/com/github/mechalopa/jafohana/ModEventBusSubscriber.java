package com.github.mechalopa.jafohana;

import com.github.mechalopa.jafohana.registry.ModDataMaps;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = JAFOhana.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusSubscriber
{
	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event){}

	@SubscribeEvent
	public static void registerDataMaps(final RegisterDataMapTypesEvent event)
	{
		event.register(ModDataMaps.CREEPER_EXPLOSION_CONVERTIBLES);
		event.register(ModDataMaps.MUTABLE_FLOWERS);
	}
}