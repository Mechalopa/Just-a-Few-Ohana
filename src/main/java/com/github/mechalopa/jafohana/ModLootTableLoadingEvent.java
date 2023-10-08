package com.github.mechalopa.jafohana;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModLootTableLoadingEvent
{
	private static final List<String> CHEST_TABLES = ImmutableList.of(
			"ancient_city",
			"jungle_temple",
			"stronghold_corridor",
			"stronghold_crossing",
			"woodland_mansion",
			"village/village_plains_house",
			"village/village_savanna_house",
			"village/village_taiga_house");

	@SubscribeEvent
	public static void onLootTableLoad(LootTableLoadEvent event)
	{
		if (event.getName() != null && event.getTable() != null)
		{
			if (ModConfigs.cachedServer.ADDITIONAL_CHEST_LOOTS)
			{
				String prefix = "minecraft:chests/";
				String name = event.getName().toString();

				if (name.startsWith(prefix) && CHEST_TABLES.contains(name.substring(prefix.length())))
				{
					String file = name.substring(("minecraft:").length());
					event.getTable().addPool(getInjectPool(file));
				}
			}
		}
	}

	private static LootPool getInjectPool(String name)
	{
		return LootPool.lootPool().add(getInjectEntry(name)).name("jafohana_inject").build();
	}

	private static LootPoolEntryContainer.Builder<?> getInjectEntry(String name)
	{
		ResourceLocation r = new ResourceLocation(JAFOhana.MODID, "inject/" + name);
		return LootTableReference.lootTableReference(r).setWeight(1);
	}
}