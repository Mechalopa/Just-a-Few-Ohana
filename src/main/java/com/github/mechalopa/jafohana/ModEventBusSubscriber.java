package com.github.mechalopa.jafohana;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusSubscriber
{
	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event){}
}