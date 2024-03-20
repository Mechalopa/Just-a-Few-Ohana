package com.github.mechalopa.jafohana;

import org.slf4j.Logger;

import com.github.mechalopa.jafohana.registry.ModBiomeFeatures;
import com.github.mechalopa.jafohana.registry.ModBiomeModifiers;
import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.registry.ModCreativeModeTabs;
import com.github.mechalopa.jafohana.registry.ModItems;
import com.github.mechalopa.jafohana.registry.ModLootModifiers;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(JAFOhana.MODID)
public class JAFOhana
{
	public static final String MODID = "jafohana";

	public static final Logger LOGGER = LogUtils.getLogger();

	public JAFOhana(IEventBus modEventBus)
	{
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.SERVER_CONFIG);
		modEventBus.addListener(ModConfigs::loadConfig);

		ModBlocks.register(modEventBus);
		ModBiomeFeatures.register(modEventBus);
		ModCreativeModeTabs.register(modEventBus);
		ModItems.register(modEventBus);
		ModLootModifiers.register(modEventBus);
		ModBiomeModifiers.register(modEventBus);
	}
}