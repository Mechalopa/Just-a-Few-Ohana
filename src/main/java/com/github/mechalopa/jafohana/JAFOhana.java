package com.github.mechalopa.jafohana;

import org.slf4j.Logger;

import com.github.mechalopa.jafohana.registry.ModBiomeFeatures;
import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.registry.ModCreativeModeTabs;
import com.github.mechalopa.jafohana.registry.ModItems;
import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(JAFOhana.MODID)
public class JAFOhana
{
	public static final String MODID = "jafohana";

	public static final Logger LOGGER = LogUtils.getLogger();

	public JAFOhana(IEventBus modEventBus, ModContainer modContainer, Dist dist)
	{
		modEventBus.addListener(ModConfigs::loadConfig);
		modContainer.registerConfig(ModConfig.Type.COMMON, ModConfigs.SERVER_CONFIG);

		if (dist == Dist.CLIENT)
		{
			modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}

		ModBlocks.register(modEventBus);
		ModBiomeFeatures.register(modEventBus);
		ModCreativeModeTabs.register(modEventBus);
		ModItems.register(modEventBus);
	}
}