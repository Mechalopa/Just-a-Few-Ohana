package com.github.mechalopa.jafohana.util.datamaps;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;

public record ExplosionConvertible(TagKey<EntityType<?>> sourceEntityTypeTag, float chance, ResourceKey<LootTable> lootTable, Block remainingBlock)
{
	public static final Codec<ExplosionConvertible> CODEC = RecordCodecBuilder.create(p -> p.group(
			TagKey.hashedCodec(Registries.ENTITY_TYPE).fieldOf("source_entity_type_tag").forGetter(ExplosionConvertible::sourceEntityTypeTag),
			Codec.floatRange(0.0F, 1.0F).fieldOf("chance").orElse(1.0F).forGetter(ExplosionConvertible::chance),
			ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("loot_table").forGetter(ExplosionConvertible::lootTable),
			BuiltInRegistries.BLOCK.byNameCodec().fieldOf("remaining_block").orElse(Blocks.AIR).forGetter(ExplosionConvertible::remainingBlock)
			).apply(p, ExplosionConvertible::new));
}