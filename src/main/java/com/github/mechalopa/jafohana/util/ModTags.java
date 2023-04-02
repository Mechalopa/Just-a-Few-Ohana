package com.github.mechalopa.jafohana.util;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags
{
	public static final TagKey<Block> AFFECTS_FASCIATIONS = createBlockTag("affects_fasciations");
	public static final TagKey<Block> CONVERTABLE_TO_FASCIATED_DANDELION = createBlockTag("convertable_to_fasciated_dandelion");
	public static final TagKey<Block> CONVERTABLE_TO_FASCIATED_OXEYE_DAISY = createBlockTag("convertable_to_fasciated_oxeye_daisy");
	public static final TagKey<Block> RED_SPIDER_LILY_PLANTABLE_ON = createBlockTag("red_spider_lily_plantable_on");

	private static TagKey<Block> createBlockTag(String name)
	{
		return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(JAFOhana.MODID, name));
	}
}