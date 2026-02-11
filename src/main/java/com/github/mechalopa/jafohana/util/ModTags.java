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
		public static final TagKey<Block> BEE_ATTRACTIVE_UPPER_ONLY = createBlockTag("bee_attractive_upper_only");
		public static final TagKey<Block> CONVERTIBLE_TO_CREEPANSY = createBlockTag("convertible_to_creepansy");
		public static final TagKey<Block> CONVERTIBLE_TO_FASCIATED_DANDELION = createBlockTag("convertible_to_fasciated_dandelion");
		public static final TagKey<Block> CONVERTIBLE_TO_FASCIATED_OXEYE_DAISY = createBlockTag("convertible_to_fasciated_oxeye_daisy");
		public static final TagKey<Block> CREEPANSY_PLANTABLE_ON = createBlockTag("creepansy_plantable_on");
		public static final TagKey<Block> CRIMSON_CLOVER_PLANTABLE_ON = createBlockTag("crimson_clover_plantable_on");
		public static final TagKey<Block> CRIMSON_NYLIUMS = createBlockTag("crimson_nyliums");
		public static final TagKey<Block> JADE_VINE_PLANTABLE_ON = createBlockTag("jade_vine_plantable_on");
		public static final TagKey<Block> RED_SPIDER_LILY_PLANTABLE_ON = createBlockTag("red_spider_lily_plantable_on");
		public static final TagKey<Block> WITHERED_BLOCKS = createBlockTag("withered_blocks");
	}

	public static final class EntityTypeTags
	{
		public static final TagKey<EntityType<?>> CREEPERS = createEntityTypeTag("creepers");
	}

	@SuppressWarnings("removal")
	private static TagKey<Block> createBlockTag(String name)
	{
		return TagKey.create(Registries.BLOCK, new ResourceLocation(JAFOhana.MODID, name));
	}

	@SuppressWarnings("removal")
	private static TagKey<EntityType<?>> createEntityTypeTag(String name)
	{
		return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(JAFOhana.MODID, name));
	}
}