package com.github.mechalopa.jafohana;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents
{
//	@SubscribeEvent
//	public static void onBiomeLoad(BiomeLoadingEvent event)
//	{
//		if (event.getName() != null && ModConfigs.cachedServer.ENABLE_WORLD_GENERATION)
//		{
//			ResourceKey<Biome> biomeKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, event.getName());
//
//			if (biomeKey != null)
//			{
//				if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.OVERWORLD))
//				{
//					if (checkList(event.getName(), ModConfigs.cachedServer.FLOWER_FOREST_BIOMES))
//					{
//						event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_FLOWER_FOREST.getHolder().orElseThrow());
//					}
//					else if (checkList(event.getName(), ModConfigs.cachedServer.MEADOW_BIOMES))
//					{
//						event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_MEADOW.getHolder().orElseThrow());
//					}
//					else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SAVANNA))
//					{
//						event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.AFRICAN_DAISY.getHolder().orElseThrow());
//					}
//					else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS))
//					{
//						if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
//							event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_PLAIN.getHolder().orElseThrow());
//					}
//					else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST))
//					{
//						if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
//						{
//							if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
//								event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_SPOOKY_FOREST.getHolder().orElseThrow());
//							else
//								event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_FOREST.getHolder().orElseThrow());
//						}
//					}
//					else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HILLS))
//					{
//						if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SNOWY))
//							event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_HILL.getHolder().orElseThrow());
//					}
//					else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SWAMP))
//					{
//						event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_SWAMP.getHolder().orElseThrow());
//					}
//
//					if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT) && !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SNOWY))
//					{
//						if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS) || BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST))
//							event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.RED_SPIDER_LILY.getHolder().orElseThrow());
//						else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SWAMP))
//							event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.RED_SPIDER_LILY_SWAMP.getHolder().orElseThrow());
//						if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.RIVER))
//							event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.RED_SPIDER_LILY_RIVER.getHolder().orElseThrow());
//						else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST) && BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
//							event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.RED_SPIDER_LILY_SPOOKY_FOREST.getHolder().orElseThrow());
//					}
//				}
//			}
//		}
//	}
//
//	public static boolean checkList(ResourceLocation r, List<? extends String> list)
//	{
//		if (r != null && list != null && !list.isEmpty() && list.contains(r.toString()))
//			return true;
//		return false;
//	}
//
//	@SubscribeEvent
//	public static void onBonemeal(BonemealEvent event)
//	{
//		if (event.isCanceled())
//		{
//			return;
//		}
//
//		if (!event.getWorld().isClientSide() && ModConfigs.cachedServer.APPEAR_BY_BONE_MEAL && event.getBlock() != null && event.getBlock().getBlock() != null && event.getBlock().getBlock() instanceof GrassBlock)
//		{
//			Level world = event.getWorld();
//			BlockPos pos = event.getPos().above();
//			Random r = world.getRandom();
//
//			if (!world.isEmptyBlock(pos))
//			{
//				return;
//			}
//
//			for (int i = 0; i < 64; ++i)
//			{
//				BlockPos pos1 = pos;
//				int j = 0;
//
//				while (true)
//				{
//					pos1 = pos1.offset(r.nextInt(3) - 1, (r.nextInt(3) - 1) * r.nextInt(3) / 2, r.nextInt(3) - 1);
//					BlockState blockstate = world.getBlockState(pos1.below());
//
//					if ((blockstate.getBlock() != null && !(blockstate.getBlock() instanceof GrassBlock)) || world.getBlockState(pos1).isCollisionShapeFullBlock(world, pos1))
//					{
//						break;
//					}
//
//					if (j >= i / 16)
//					{
//						if (world.isEmptyBlock(pos1) && !pos.equals(pos1))
//						{
//							if (r.nextInt(32) == 0 && world.getBiome(pos1) != null)
//							{
//								final ResourceLocation resourcelocation = world.getBiome(pos1).value().getRegistryName();
//								ResourceKey<Biome> biomeKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, resourcelocation);
//
//								if (biomeKey != null)
//								{
//									if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.OVERWORLD))
//									{
//										Block block = null;
//										int chance = 1;
//
//										if (checkList(resourcelocation, ModConfigs.cachedServer.FLOWER_FOREST_BIOMES))
//										{
//											final int k = r.nextInt(3);
//											block = k == 0 ? ModBlocks.DAYFLOWER.get() : (k == 1 ? ModBlocks.EVENING_PRIMROSE.get() : ModBlocks.FORGET_ME_NOT.get());
//										}
//										else if (checkList(resourcelocation, ModConfigs.cachedServer.MEADOW_BIOMES))
//										{
//											final int k = r.nextInt(3);
//											block = k == 0 ? ModBlocks.EVENING_PRIMROSE.get() : (k == 1 ? ModBlocks.MILK_VETCH.get() : ModBlocks.FORGET_ME_NOT.get());
//										}
//										else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SAVANNA))
//										{
//											final int k = r.nextInt(3);
//											block = k == 0 ? ModBlocks.YELLOW_AFRICAN_DAISY.get() : (k == 1 ? ModBlocks.PINK_AFRICAN_DAISY.get() : ModBlocks.WHITE_AFRICAN_DAISY.get());
//										}
//										else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS))
//										{
//											if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
//											{
//												final int k = r.nextInt(3);
//												block = k == 0 ? ModBlocks.DAYFLOWER.get() : (k == 1 ? ModBlocks.EVENING_PRIMROSE.get() : ModBlocks.MILK_VETCH.get());
//											}
//										}
//										else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST))
//										{
//											if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
//											{
//												if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
//												{
//													block = ModBlocks.DAYFLOWER.get();
//													chance = 3;
//												}
//												else
//												{
//													final int k = r.nextInt(3);
//													block = k == 0 ? ModBlocks.DAYFLOWER.get() : (k == 1 ? ModBlocks.EVENING_PRIMROSE.get() : ModBlocks.FORGET_ME_NOT.get());
//													chance = 2;
//												}
//											}
//										}
//										else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HILLS))
//										{
//											if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SNOWY))
//											{
//												block = r.nextBoolean() ? ModBlocks.EVENING_PRIMROSE.get() : ModBlocks.FORGET_ME_NOT.get();
//												chance = 2;
//											}
//										}
//										else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SWAMP))
//										{
//											block = ModBlocks.MILK_VETCH.get();
//											chance = 2;
//										}
//
//										if (block != null && r.nextFloat() < 1.0F / (float)chance)
//										{
//											BlockState blockstate1 = block.defaultBlockState();
//
//											if (blockstate1.canSurvive(world, pos1))
//											{
//												world.setBlock(pos1, blockstate1, 3);
//											}
//										}
//									}
//								}
//							}
//						}
//
//						break;
//					}
//
//					++j;
//				}
//			}
//		}
//	}
}