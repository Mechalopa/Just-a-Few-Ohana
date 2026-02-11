package com.github.mechalopa.jafohana.util.datamaps;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public record CreeperExplosionConvertible(Item convertedItem, float chance, Block remainingBlock)
{
	public static final Codec<CreeperExplosionConvertible> CODEC = RecordCodecBuilder.create(p -> p.group(
			BuiltInRegistries.ITEM.byNameCodec().fieldOf("converted_item").forGetter(CreeperExplosionConvertible::convertedItem),
			Codec.floatRange(0.0F, 1.0F).fieldOf("chance").orElse(1.0F).forGetter(CreeperExplosionConvertible::chance),
			BuiltInRegistries.BLOCK.byNameCodec().fieldOf("remaining_block").orElse(Blocks.AIR).forGetter(CreeperExplosionConvertible::remainingBlock)
			).apply(p, CreeperExplosionConvertible::new));
}