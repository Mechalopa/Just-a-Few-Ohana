package com.github.mechalopa.jafohana.util;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags
{
	public static final TagKey<Block> RED_SPIDER_LILY_PLANTABLE_ON = createBlockTag("red_spider_lily_plantable_on");

//	public static final TagKey<Biome> IS_FLOWER_FOREST = createBiomeTag("is_flower_forest");
//	public static final TagKey<Biome> IS_MEADOW = createBiomeTag("is_meadow");

	private static TagKey<Block> createBlockTag(String name)
	{
		return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(JAFOhana.MODID, name));
	}

//	private static TagKey<Biome> createBiomeTag(String name)
//	{
//		return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(JAFOhana.MODID, name));
//	}
}