package com.github.mechalopa.jafohana;

import java.util.List;

import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.registry.ModItems;
import com.github.mechalopa.jafohana.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.bus.api.Event.Result;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents
{
	@SubscribeEvent
	public static void onBonemeal(BonemealEvent event)
	{
		if (!event.isCanceled() && !event.getLevel().isClientSide() && event.getLevel() instanceof ServerLevel && event.getBlock() != null)
		{
			ServerLevel serverLevel = (ServerLevel)event.getLevel();
			BlockPos pos = event.getPos();
			RandomSource r = serverLevel.getRandom();

			if (event.getBlock().is(ModTags.BlockTags.CONVERTABLE_TO_FASCIATED_DANDELION) && fasciate(serverLevel, pos, ModBlocks.FASCIATED_DANDELION.get().defaultBlockState(), r, ModConfigs.cachedServer.DANDELION_FASCIATION_CHANCE))
			{
				event.setResult(Result.ALLOW);
			}
			else if (event.getBlock().is(ModTags.BlockTags.CONVERTABLE_TO_FASCIATED_OXEYE_DAISY) && fasciate(serverLevel, pos, ModBlocks.FASCIATED_OXEYE_DAISY.get().defaultBlockState(), r, ModConfigs.cachedServer.OXEYE_DAISY_FASCIATION_CHANCE))
			{
				event.setResult(Result.ALLOW);
			}
		}
	}

	private static boolean fasciate(ServerLevel serverLevel, BlockPos blockpos, BlockState fasciatedFlowerState, RandomSource random, double chance)
	{
		if (fasciatedFlowerState.canSurvive(serverLevel, blockpos) && serverLevel.isEmptyBlock(blockpos.above()))
		{
			for (Direction direction : Direction.values())
			{
				if (direction.getAxis().isHorizontal())
				{
					BlockState state = serverLevel.getBlockState(blockpos.relative(direction));

					if (state != null && state.is(ModTags.BlockTags.AFFECTS_FASCIATIONS))
					{
						if (random.nextDouble() < chance)
						{
							BlockPos blockpos1 = blockpos.above();
							serverLevel.setBlockAndUpdate(blockpos, DoublePlantBlock.copyWaterloggedFrom(serverLevel, blockpos, fasciatedFlowerState.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)));
							serverLevel.setBlockAndUpdate(blockpos1, DoublePlantBlock.copyWaterloggedFrom(serverLevel, blockpos1, fasciatedFlowerState.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)));
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
		if (!event.getLevel().isClientSide() && event.getExplosion().getDirectSourceEntity() != null && event.getExplosion().getDirectSourceEntity().getType().is(ModTags.EntityTypeTags.CAN_CONVERT_TO_CREEPANSY) && !event.getAffectedBlocks().isEmpty() && EventHooks.getMobGriefingEvent(event.getLevel(), event.getExplosion().getDirectSourceEntity()))
		{
			Level level = event.getLevel();

			for (BlockPos pos : event.getAffectedBlocks())
			{
				BlockState state = level.getBlockState(pos);

				if (state.is(ModTags.BlockTags.CONVERTABLE_TO_CREEPANSY) && (double)level.getRandom().nextFloat() < ModConfigs.cachedServer.CREEPANSY_CONVERT_CHANCE)
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
	public static void onWandererTrades(WandererTradesEvent event)
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
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.BLUE_DAISY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WINTER_HEATH.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.CRIMSON_CLOVER.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WHEEL_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.MAGENTA_SPOTTED_BELLFLOWER.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WHITE_SPOTTED_BELLFLOWER.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(3, new ItemStack(ModItems.RED_SPIDER_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(3, new ItemStack(ModItems.BELLS_OF_IRELAND.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(3, new ItemStack(ModItems.JADE_VINE.get(), 2), 5, 1, 0.05F));
		}
	}
}