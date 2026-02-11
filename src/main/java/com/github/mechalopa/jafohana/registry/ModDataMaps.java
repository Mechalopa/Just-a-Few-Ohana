package com.github.mechalopa.jafohana.registry;

import com.github.mechalopa.jafohana.JAFOhana;
import com.github.mechalopa.jafohana.util.datamaps.CreeperExplosionConvertible;
import com.github.mechalopa.jafohana.util.datamaps.MutableFlower;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public class ModDataMaps
{
	public static final DataMapType<Block, CreeperExplosionConvertible> CREEPER_EXPLOSION_CONVERTIBLES = DataMapType.builder(ResourceLocation.fromNamespaceAndPath(JAFOhana.MODID, "creeper_explosion_convertibles"), Registries.BLOCK, CreeperExplosionConvertible.CODEC).synced(CreeperExplosionConvertible.CODEC, false).build();
	public static final DataMapType<Block, MutableFlower> MUTABLE_FLOWERS = DataMapType.builder(ResourceLocation.fromNamespaceAndPath(JAFOhana.MODID, "mutable_flowers"), Registries.BLOCK, MutableFlower.CODEC).synced(MutableFlower.CODEC, false).build();
}