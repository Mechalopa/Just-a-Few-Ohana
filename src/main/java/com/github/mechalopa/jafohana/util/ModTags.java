package com.github.mechalopa.jafohana.util;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ModTags
{
	public static final TagKey<Block> RED_SPIDER_LILY_PLANTABLE_ON = createBlockTag("red_spider_lily_plantable_on");

	public static final TagKey<Biome> IS_FLOWER_FOREST = createBiomeTag("is_flower_forest");
	public static final TagKey<Biome> IS_MEADOW = createBiomeTag("is_meadow");

	private static TagKey<Block> createBlockTag(String name)
	{
		return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(JAFOhana.MODID, name));
	}

	private static TagKey<Biome> createBiomeTag(String name)
	{
		return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(JAFOhana.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> boolean checkTagContains(IForgeRegistry<T> registry, ResourceKey<T> value, TagKey<T> tag)
	{
		if (registry != null && tag != null && value != null)
		{
			try
			{
				if (registry.getHolder(value).orElseThrow().containsTag(tag))
				{
					return true;
				}
			}
			catch (Exception e)
			{
				JAFOhana.LOGGER.warn("Broken tags -> " + tag.toString(), (Throwable)e);
				return false;
			}
		}

		return false;
	}
}