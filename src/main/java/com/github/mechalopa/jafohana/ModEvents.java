package com.github.mechalopa.jafohana;

import java.util.List;

import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.registry.ModItems;
import com.github.mechalopa.jafohana.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents
{
	@SubscribeEvent
	public static void onBonemeal(BonemealEvent event)
	{
		if (!event.isCanceled() && event.getBlock() != null)
		{
			Level level = event.getLevel();
			BlockPos pos = event.getPos();
			RandomSource random = level.getRandom();

			if (fasciate(level, pos, event.getBlock(), ModTags.BlockTags.CONVERTIBLE_TO_FASCIATED_DANDELION, ModBlocks.FASCIATED_DANDELION.get().defaultBlockState(), random, ModConfigs.cachedServer.DANDELION_FASCIATION_CHANCE))
			{
				event.setResult(Result.ALLOW);
			}
			else if (fasciate(level, pos, event.getBlock(), ModTags.BlockTags.CONVERTIBLE_TO_FASCIATED_OXEYE_DAISY, ModBlocks.FASCIATED_OXEYE_DAISY.get().defaultBlockState(), random, ModConfigs.cachedServer.OXEYE_DAISY_FASCIATION_CHANCE))
			{
				event.setResult(Result.ALLOW);
			}
		}
	}

	private static boolean fasciate(Level level, BlockPos blockpos, BlockState baseFlowerState, TagKey<Block> blockTag, BlockState fasciatedFlowerState, RandomSource random, double chance)
	{
		if (chance > 0.0D && baseFlowerState.is(blockTag) && fasciatedFlowerState.canSurvive(level, blockpos) && level.isEmptyBlock(blockpos.above()))
		{
			for (Direction direction : Direction.values())
			{
				if (direction.getAxis().isHorizontal())
				{
					BlockState state = level.getBlockState(blockpos.relative(direction));

					if (state != null && state.is(ModTags.BlockTags.WITHERED_BLOCKS) && (!state.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF) || state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.UPPER))
					{
						if (!level.isClientSide() && level instanceof ServerLevel && random.nextDouble() < chance)
						{
							BlockPos blockpos1 = blockpos.above();
							level.setBlockAndUpdate(blockpos, DoublePlantBlock.copyWaterloggedFrom(level, blockpos, fasciatedFlowerState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)));
							level.setBlockAndUpdate(blockpos1, DoublePlantBlock.copyWaterloggedFrom(level, blockpos1, fasciatedFlowerState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)));
						}

						return true;
					}
				}
			}
		}

		return false;
	}

	@SubscribeEvent
	public static void onBlockExplode(ExplosionEvent.Detonate event)
	{
		if (!event.getLevel().isClientSide() && event.getExplosion().getExploder() != null && event.getExplosion().getExploder().getType().is(ModTags.EntityTypeTags.PRODUCES_CREEPANSY) && !event.getAffectedBlocks().isEmpty() && ForgeEventFactory.getMobGriefingEvent(event.getLevel(), event.getExplosion().getExploder()))
		{
			Level level = event.getLevel();

			for (BlockPos pos : event.getAffectedBlocks())
			{
				BlockState state = level.getBlockState(pos);

				if (state.is(ModTags.BlockTags.CONVERTIBLE_TO_CREEPANSY) && (double)level.getRandom().nextFloat() < ModConfigs.cachedServer.CREEPANSY_CONVERT_CHANCE)
				{
					ItemEntity itementity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), ModItems.CREEPANSY.get().getDefaultInstance());

					if (level.addFreshEntity(itementity))
					{
						if (state.is(BlockTags.FLOWER_POTS))
						{
							level.setBlockAndUpdate(pos, Blocks.FLOWER_POT.defaultBlockState());
						}
						else
						{
							level.removeBlock(pos, false);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onWandererTrades(WandererTradesEvent event)
	{
		if (ModConfigs.cachedServer.ADDITIONAL_WANDERER_TRADES)
		{
			List<ItemListing> genericTrades = event.getGenericTrades();
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.DAYFLOWER.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.EVENING_PRIMROSE.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.MILK_VETCH.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.FORGET_ME_NOT.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.YELLOW_AFRICAN_DAISY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.PINK_AFRICAN_DAISY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WHITE_AFRICAN_DAISY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.BLACK_TULIP.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.GREEN_TULIP.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.BLOOD_RED_IRIS.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.BLUEBELL.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.RED_SNAPDRAGON.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.YELLOW_SNAPDRAGON.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.PINK_SNAPDRAGON.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WHITE_EGRET_ORCHID.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.ORANGE_SPIDER_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.DANCING_LADIES_GINGER.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.FIREBALL_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.SPATHIPHYLLUM.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.PURPLE_PANSY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WHITE_PANSY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.YELLOW_PANSY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.SNOWDROP.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WINTER_HEATH.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.BLUE_DAISY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.CRIMSON_CLOVER.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.MAGENTA_SPOTTED_BELLFLOWER.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WHITE_SPOTTED_BELLFLOWER.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WHEEL_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.RED_SPIDER_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.BELLS_OF_IRELAND.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(3, new ItemStack(ModItems.JADE_VINE.get(), 2), 5, 1, 0.05F));
		}
	}
}