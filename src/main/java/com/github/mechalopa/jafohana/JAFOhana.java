package com.github.mechalopa.jafohana;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.registry.ModItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JAFOhana.MODID)
@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JAFOhana
{
	public static final String MODID = "jafohana";

	public static final Logger LOGGER = LogManager.getLogger();

	public JAFOhana()
	{
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.SERVER_CONFIG);

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(ModConfigs::loadConfig);

		ModBlocks.register(modEventBus);
//		ModFeatures.register(modEventBus);
		ModItems.register(modEventBus);
	}
}