package com.github.mechalopa.jafohana;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class ModConfigs
{
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec SERVER_CONFIG;

	public static ForgeConfigSpec.BooleanValue FASCIATED_DANDELION_DROPS_NORMAL_DANDELION_WHEN_USING_BONE_MEAL;
	public static ForgeConfigSpec.BooleanValue FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL;
	public static ForgeConfigSpec.DoubleValue DANDELION_FASCIATION_CHANCE;
	public static ForgeConfigSpec.DoubleValue OXEYE_DAISY_FASCIATION_CHANCE;
	public static ForgeConfigSpec.DoubleValue CREEPANSY_CONVERT_CHANCE;
	public static ForgeConfigSpec.DoubleValue JADE_VINE_GROW_CHANCE;
	public static ForgeConfigSpec.BooleanValue ADDITIONAL_CHEST_LOOTS;
	public static ForgeConfigSpec.BooleanValue ADDITIONAL_WANDERER_TRADES;

	static
	{
		SERVER_BUILDER.push("general");

		FASCIATED_DANDELION_DROPS_NORMAL_DANDELION_WHEN_USING_BONE_MEAL = SERVER_BUILDER.define("fasciatedDandelionDropsNormalDandelionWhenUsingBoneMeal", true);
		FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL = SERVER_BUILDER.define("fasciatedOxeyeDaisyDropsNormalOxeyeDaisyWhenUsingBoneMeal", true);
		DANDELION_FASCIATION_CHANCE = SERVER_BUILDER.defineInRange("dandelionFasciationChance", 0.4D, 0.0D, 1.0D);
		OXEYE_DAISY_FASCIATION_CHANCE = SERVER_BUILDER.defineInRange("oxeyeDaisyFasciationChance", 0.4D, 0.0D, 1.0D);
		CREEPANSY_CONVERT_CHANCE = SERVER_BUILDER.defineInRange("creepansyConvertChance", 1.0D, 0.0D, 1.0D);
		JADE_VINE_GROW_CHANCE = SERVER_BUILDER.defineInRange("jadeVineGrowChance", 0.45D, 0.0D, 1.0D);
		ADDITIONAL_CHEST_LOOTS = SERVER_BUILDER.define("additionalChestLoots", true);
		ADDITIONAL_WANDERER_TRADES = SERVER_BUILDER.define("additionalWandererTrades", true);

		SERVER_BUILDER.pop();

		SERVER_CONFIG = SERVER_BUILDER.build();
	}

	public static class cachedServer
	{
		public static boolean FASCIATED_DANDELION_DROPS_NORMAL_DANDELION_WHEN_USING_BONE_MEAL;
		public static boolean FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL;
		public static double DANDELION_FASCIATION_CHANCE;
		public static double OXEYE_DAISY_FASCIATION_CHANCE;
		public static double CREEPANSY_CONVERT_CHANCE;
		public static double JADE_VINE_GROW_CHANCE;
		public static boolean ADDITIONAL_CHEST_LOOTS;
		public static boolean ADDITIONAL_WANDERER_TRADES;

		public static void refresh()
		{
			FASCIATED_DANDELION_DROPS_NORMAL_DANDELION_WHEN_USING_BONE_MEAL = ModConfigs.FASCIATED_DANDELION_DROPS_NORMAL_DANDELION_WHEN_USING_BONE_MEAL.get();
			FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL = ModConfigs.FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL.get();
			DANDELION_FASCIATION_CHANCE = ModConfigs.DANDELION_FASCIATION_CHANCE.get();
			OXEYE_DAISY_FASCIATION_CHANCE = ModConfigs.OXEYE_DAISY_FASCIATION_CHANCE.get();
			CREEPANSY_CONVERT_CHANCE = ModConfigs.CREEPANSY_CONVERT_CHANCE.get();
			JADE_VINE_GROW_CHANCE = ModConfigs.JADE_VINE_GROW_CHANCE.get();
			ADDITIONAL_CHEST_LOOTS = ModConfigs.ADDITIONAL_CHEST_LOOTS.get();
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