package com.github.mechalopa.jafohana;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class ModConfigs
{
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec SERVER_CONFIG;

	public static ForgeConfigSpec.BooleanValue ADDITIONAL_WANDERER_TRADES;

	static
	{
		SERVER_BUILDER.push("general");

		ADDITIONAL_WANDERER_TRADES = SERVER_BUILDER.define("additionalWandererTrades", true);

		SERVER_BUILDER.pop();

		SERVER_CONFIG = SERVER_BUILDER.build();
	}

	public static class cachedServer
	{
		public static boolean ADDITIONAL_WANDERER_TRADES;

		public static void refresh()
		{
			ADDITIONAL_WANDERER_TRADES = ModConfigs.ADDITIONAL_WANDERER_TRADES.get();
		}
	}

	@SubscribeEvent
	public static void loadConfig(final ModConfigEvent event)
	{
		if (event.getConfig().getSpec() == SERVER_CONFIG)
		{
			cachedServer.refresh();
		}
	}
}