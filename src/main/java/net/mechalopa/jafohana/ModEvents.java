package net.mechalopa.jafohana;

import java.util.Random;

import net.mechalopa.jafohana.registry.ModBlocks;
import net.mechalopa.jafohana.world.gen.ModConfiguredFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents
{
	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event)
	{
		if (event.getName() != null && ModConfigs.cachedServer.ENABLE_WORLD_GENERATION)
		{
			RegistryKey<Biome> biomeKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES, event.getName());

			if (biomeKey != null)
			{
				if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SAVANNA))
				{
					event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.AFRICAN_DAISY);
				}
				else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS))
				{
					if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
						event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.FLOWER_PLAIN);
				}
				else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST))
				{
					if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
					{
						if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
							event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.FLOWER_SPOOKY_FOREST);
						else
							event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.FLOWER_FOREST);
					}
				}
				else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MOUNTAIN))
				{
					if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
						event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.FLOWER_MOUNTAIN);
				}
				else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SWAMP))
				{
					event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.FLOWER_SWAMP);
				}

				if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT) && !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD))
				{
					if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.RIVER))
						event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.RED_SPIDER_LILY_RIVER);
					else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST) && BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
						event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.RED_SPIDER_LILY_SPOOKY_FOREST);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBonemeal(BonemealEvent event)
	{
		if (event.isCanceled())
		{
			return;
		}

		if (!event.getWorld().isClientSide() && ModConfigs.cachedServer.APPEAR_BY_BONE_MEAL && event.getBlock() != null && event.getBlock().getBlock() != null && event.getBlock().getBlock() instanceof GrassBlock)
		{
			World world = event.getWorld();
			BlockPos pos = event.getPos().above();
			Random r = world.getRandom();

			for (int i = 0; i < 64; ++i)
			{
				BlockPos pos1 = pos;
				int j = 0;

				while (true)
				{
					pos1 = pos1.offset(r.nextInt(3) - 1, (r.nextInt(3) - 1) * r.nextInt(3) / 2, r.nextInt(3) - 1);
					BlockState blockstate = world.getBlockState(pos1.below());

					if ((blockstate.getBlock() != null && !(blockstate.getBlock() instanceof GrassBlock)) || world.getBlockState(pos1).isCollisionShapeFullBlock(world, pos1))
					{
						break;
					}

					if (j >= i / 16)
					{
						if (world.isEmptyBlock(pos1) && !pos.equals(pos1))
						{
							if (r.nextInt(32) == 0 && world.getBiome(pos1) != null)
							{
								RegistryKey<Biome> biomeKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES, world.getBiome(pos1).getRegistryName());

								if (biomeKey != null)
								{
									Block block = null;
									int chance = 1;

									if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SAVANNA))
									{
										final int k = r.nextInt(3);
										block = k == 0 ? ModBlocks.YELLOW_AFRICAN_DAISY.get() : (k == 1 ? ModBlocks.PINK_AFRICAN_DAISY.get() : ModBlocks.WHITE_AFRICAN_DAISY.get());
									}
									else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS))
									{
										if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
										{
											final int k = r.nextInt(3);
											block = k == 0 ? ModBlocks.DAYFLOWER.get() : (k == 1 ? ModBlocks.EVENING_PRIMROSE.get() : ModBlocks.MILK_VETCH.get());
										}
									}
									else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST))
									{
										if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
										{
											if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
											{
												block = ModBlocks.DAYFLOWER.get();
												chance = 6;
											}
											else
											{
												final int k = r.nextInt(4);
												block = k == 0 ? ModBlocks.EVENING_PRIMROSE.get() : (k == 1 ? ModBlocks.FORGET_ME_NOT.get() : ModBlocks.DAYFLOWER.get());
												chance = 2;
											}
										}
									}
									else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MOUNTAIN))
									{
										if (!BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
										{
											block = r.nextBoolean() ? ModBlocks.EVENING_PRIMROSE.get() : ModBlocks.FORGET_ME_NOT.get();
											chance = 4;
										}
									}
									else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SWAMP))
									{
										block = ModBlocks.MILK_VETCH.get();
										chance = 2;
									}

									if (block != null && r.nextFloat() < 1.0F / (float)chance)
									{
										BlockState blockstate1 = block.defaultBlockState();

										if (blockstate1.canSurvive(world, pos1))
										{
											world.setBlock(pos1, blockstate1, 3);
										}
									}
								}
							}
						}

						break;
					}

					++j;
				}
			}
		}
	}
}