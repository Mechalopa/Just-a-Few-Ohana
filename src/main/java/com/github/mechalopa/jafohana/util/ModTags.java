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
		public static final TagKey<Block> BADLANDS_FLOWER_PLANTABLE_ON = createBlockTag("badlands_flower_plantable_on");
		public static final TagKey<Block> BEACH_FLOWER_PLANTABLE_ON = createBlockTag("beach_flower_plantable_on");
		public static final TagKey<Block> BEE_ATTRACTIVE_UPPER_ONLY = createBlockTag("bee_attractive_upper_only");
		public static final TagKey<Block> CREEPANSY_PLANTABLE_ON = createBlockTag("creepansy_plantable_on");
		public static final TagKey<Block> CRIMSON_CLOVER_PLANTABLE_ON = createBlockTag("crimson_clover_plantable_on");
		public static final TagKey<Block> CRIMSON_NYLIUMS = createBlockTag("crimson_nyliums");
		public static final TagKey<Block> DESERT_FLOWER_PLANTABLE_ON = createBlockTag("desert_flower_plantable_on");
		public static final TagKey<Block> RED_SPIDER_LILY_PLANTABLE_ON = createBlockTag("red_spider_lily_plantable_on");
		public static final TagKey<Block> VINE_FLOWER_PLANTABLE_ON = createBlockTag("vine_flower_plantable_on");
	}

	public static final class EntityTypeTags
	{
		public static final TagKey<EntityType<?>> CREEPERS = createEntityTypeTag("creepers");
	}

	private static TagKey<Block> createBlockTag(String name)
	{
		return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(JAFOhana.MODID, name));
	}

	private static TagKey<EntityType<?>> createEntityTypeTag(String name)
	{
		return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(JAFOhana.MODID, name));
	}
}