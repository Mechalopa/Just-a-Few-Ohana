package com.github.mechalopa.jafohana;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfigs
{
	private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
	public static ModConfigSpec SERVER_CONFIG;

	public static ModConfigSpec.BooleanValue FASCIATED_DANDELION_DROPS_NORMAL_DANDELION_WHEN_USING_BONE_MEAL;
	public static ModConfigSpec.BooleanValue FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL;
	public static ModConfigSpec.DoubleValue DANDELION_FASCIATION_CHANCE;
	public static ModConfigSpec.DoubleValue OXEYE_DAISY_FASCIATION_CHANCE;
	public static ModConfigSpec.DoubleValue CREEPANSY_CONVERT_CHANCE;
	public static ModConfigSpec.DoubleValue VINE_FLOWER_GROW_CHANCE;
	public static ModConfigSpec.DoubleValue WITHER_ROSE_BUSH_DUPLICATE_CHANCE_WHEN_USING_BONE_MEAL;
	public static ModConfigSpec.BooleanValue WITHER_ROSE_BUSH_DROPS_NORMAL_WITHER_ROSE_WHEN_USING_BONE_MEAL;
	public static ModConfigSpec.BooleanValue ADDITIONAL_WANDERER_TRADES;

	static
	{
		SERVER_BUILDER.push("general");

		FASCIATED_DANDELION_DROPS_NORMAL_DANDELION_WHEN_USING_BONE_MEAL = SERVER_BUILDER.define("fasciatedDandelionDropsNormalDandelionWhenUsingBoneMeal", true);
		FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL = SERVER_BUILDER.define("fasciatedOxeyeDaisyDropsNormalOxeyeDaisyWhenUsingBoneMeal", true);
		DANDELION_FASCIATION_CHANCE = SERVER_BUILDER.defineInRange("dandelionFasciationChance", 0.4D, 0.0D, 1.0D);
		OXEYE_DAISY_FASCIATION_CHANCE = SERVER_BUILDER.defineInRange("oxeyeDaisyFasciationChance", 0.4D, 0.0D, 1.0D);
		CREEPANSY_CONVERT_CHANCE = SERVER_BUILDER.defineInRange("creepansyConvertChance", 1.0D, 0.0D, 1.0D);
		VINE_FLOWER_GROW_CHANCE = SERVER_BUILDER.defineInRange("vineFlowerGrowChance", 0.45D, 0.0D, 1.0D);
		WITHER_ROSE_BUSH_DUPLICATE_CHANCE_WHEN_USING_BONE_MEAL = SERVER_BUILDER.defineInRange("witherRoseBushDuplicateChanceWhenUsingBoneMeal", 0.0D, 0.0D, 1.0D);
		WITHER_ROSE_BUSH_DROPS_NORMAL_WITHER_ROSE_WHEN_USING_BONE_MEAL = SERVER_BUILDER.define("witherRoseBushDropsNormalWitherRoseWhenUsingBoneMeal", true);
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
		public static double VINE_FLOWER_GROW_CHANCE;
		public static double WITHER_ROSE_BUSH_DUPLICATE_CHANCE_WHEN_USING_BONE_MEAL;
		public static boolean WITHER_ROSE_BUSH_DROPS_NORMAL_WITHER_ROSE_WHEN_USING_BONE_MEAL;
		public static boolean ADDITIONAL_WANDERER_TRADES;

		public static void refresh()
		{
			FASCIATED_DANDELION_DROPS_NORMAL_DANDELION_WHEN_USING_BONE_MEAL = ModConfigs.FASCIATED_DANDELION_DROPS_NORMAL_DANDELION_WHEN_USING_BONE_MEAL.get();
			FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL = ModConfigs.FASCIATED_OXEYE_DAISY_DROPS_NORMAL_OXEYE_DAISY_WHEN_USING_BONE_MEAL.get();
			DANDELION_FASCIATION_CHANCE = ModConfigs.DANDELION_FASCIATION_CHANCE.get();
			OXEYE_DAISY_FASCIATION_CHANCE = ModConfigs.OXEYE_DAISY_FASCIATION_CHANCE.get();
			CREEPANSY_CONVERT_CHANCE = ModConfigs.CREEPANSY_CONVERT_CHANCE.get();
			VINE_FLOWER_GROW_CHANCE = ModConfigs.VINE_FLOWER_GROW_CHANCE.get();
			WITHER_ROSE_BUSH_DUPLICATE_CHANCE_WHEN_USING_BONE_MEAL = ModConfigs.WITHER_ROSE_BUSH_DUPLICATE_CHANCE_WHEN_USING_BONE_MEAL.get();
			WITHER_ROSE_BUSH_DROPS_NORMAL_WITHER_ROSE_WHEN_USING_BONE_MEAL = ModConfigs.WITHER_ROSE_BUSH_DROPS_NORMAL_WITHER_ROSE_WHEN_USING_BONE_MEAL.get();
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