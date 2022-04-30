package com.github.mechalopa.jafohana;

import java.util.Collections;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class ModConfigs
{
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec SERVER_CONFIG;

	public static ForgeConfigSpec.BooleanValue ENABLE_WORLD_GENERATION;
	public static ForgeConfigSpec.BooleanValue APPEAR_BY_BONE_MEAL;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> FLOWER_FOREST_BIOMES;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> MEADOW_BIOMES;

	static
	{
		SERVER_BUILDER.push("general");

		ENABLE_WORLD_GENERATION = SERVER_BUILDER.define("enableWorldGeneration", true);
		APPEAR_BY_BONE_MEAL = SERVER_BUILDER.define("appearByBoneMeal", true);
		FLOWER_FOREST_BIOMES = SERVER_BUILDER.defineList("flowerForestBiomes", Collections.singletonList("minecraft:flower_forest"), (s) -> true);
		MEADOW_BIOMES = SERVER_BUILDER.defineList("meadowBiomes", Collections.singletonList("minecraft:meadow"), (s) -> true);

		SERVER_BUILDER.pop();

		SERVER_CONFIG = SERVER_BUILDER.build();
	}

	public static class cachedServer
	{
		public static boolean ENABLE_WORLD_GENERATION;
		public static boolean APPEAR_BY_BONE_MEAL;
		public static List<? extends String> FLOWER_FOREST_BIOMES;
		public static List<? extends String> MEADOW_BIOMES;

		public static void refresh()
		{
			ENABLE_WORLD_GENERATION = ModConfigs.ENABLE_WORLD_GENERATION.get();
			APPEAR_BY_BONE_MEAL = ModConfigs.APPEAR_BY_BONE_MEAL.get();
			FLOWER_FOREST_BIOMES = ModConfigs.FLOWER_FOREST_BIOMES.get();
			MEADOW_BIOMES = ModConfigs.MEADOW_BIOMES.get();
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