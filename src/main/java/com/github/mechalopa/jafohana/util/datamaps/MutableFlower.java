package com.github.mechalopa.jafohana.util.datamaps;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public record MutableFlower(TagKey<Block> catalystTag, float chance, Block mutatedFlower)
{
	public static final Codec<MutableFlower> CODEC = RecordCodecBuilder.create(p -> p.group(
			TagKey.hashedCodec(Registries.BLOCK).fieldOf("catalyst_tag").forGetter(MutableFlower::catalystTag),
			Codec.floatRange(0.0F, 1.0F).fieldOf("chance").forGetter(MutableFlower::chance),
			BuiltInRegistries.BLOCK.byNameCodec().fieldOf("mutated_flower").forGetter(MutableFlower::mutatedFlower)
			).apply(p, MutableFlower::new));
}