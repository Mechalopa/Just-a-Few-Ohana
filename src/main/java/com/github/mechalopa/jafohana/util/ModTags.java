package com.github.mechalopa.jafohana.util;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class ModTags
{
	public static final class BlockTags
	{
		public static final TagKey<Block> AFFECTS_FASCIATIONS = createBlockTag("affects_fasciations");
		public static final TagKey<Block> CONVERTABLE_TO_CREEPANSY = createBlockTag("convertable_to_creepansy");
		public static final TagKey<Block> CONVERTABLE_TO_FASCIATED_DANDELION = createBlockTag("convertable_to_fasciated_dandelion");
		public static final TagKey<Block> CONVERTABLE_TO_FASCIATED_OXEYE_DAISY = createBlockTag("convertable_to_fasciated_oxeye_daisy");
		public static final TagKey<Block> JADE_VINE_PLANTABLE_ON = createBlockTag("jade_vine_plantable_on");
		public static final TagKey<Block> RED_SPIDER_LILY_PLANTABLE_ON = createBlockTag("red_spider_lily_plantable_on");
	}

	public static final class EntityTypeTags
	{
		public static final TagKey<EntityType<?>> CAN_CONVERT_TO_CREEPANSY = createEntityTypeTag("can_convert_to_creepansy");
	}

	private static TagKey<Block> createBlockTag(String name)
	{
		return TagKey.create(Registries.BLOCK, new ResourceLocation(JAFOhana.MODID, name));
	}

	private static TagKey<EntityType<?>> createEntityTypeTag(String name)
	{
		return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(JAFOhana.MODID, name));
	}
}