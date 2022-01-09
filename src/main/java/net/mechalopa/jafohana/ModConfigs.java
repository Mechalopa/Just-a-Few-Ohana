package net.mechalopa.jafohana;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class ModConfigs
{
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec SERVER_CONFIG;

	public static ForgeConfigSpec.BooleanValue ENABLE_WORLD_GENERATION;
	public static ForgeConfigSpec.BooleanValue APPEAR_BY_BONE_MEAL;

	static
	{
		SERVER_BUILDER.push("general");

		ENABLE_WORLD_GENERATION = SERVER_BUILDER.define("enableWorldGeneration", true);
		APPEAR_BY_BONE_MEAL = SERVER_BUILDER.define("appearByBoneMeal", true);

		SERVER_BUILDER.pop();

		SERVER_CONFIG = SERVER_BUILDER.build();
	}

	public static class cachedServer
	{
		public static boolean ENABLE_WORLD_GENERATION;
		public static boolean APPEAR_BY_BONE_MEAL;

		public static void refresh()
		{
			ENABLE_WORLD_GENERATION= ModConfigs.ENABLE_WORLD_GENERATION.get();
			APPEAR_BY_BONE_MEAL= ModConfigs.APPEAR_BY_BONE_MEAL.get();
		}
	}

	@SubscribeEvent
	public static void loadConfig(final ModConfig.ModConfigEvent event)
	{
		if (event.getConfig().getSpec() == SERVER_CONFIG)
		{
			cachedServer.refresh();
		}
	}
}