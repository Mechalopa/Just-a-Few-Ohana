package com.github.mechalopa.jafohana;

import java.util.List;

import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.registry.ModItems;
import com.github.mechalopa.jafohana.util.ModTags;
import com.github.mechalopa.jafohana.util.ModUtils;

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
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

@EventBusSubscriber(modid = JAFOhana.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents
{
	@SubscribeEvent
	public static void onBonemeal(BonemealEvent event)
	{
		if (!event.isCanceled() && event.getState() != null)
		{
			Level level = event.getLevel();
			BlockPos pos = event.getPos();
			RandomSource r = level.getRandom();

			if (fasciate(level, pos, event.getState(), ModTags.BlockTags.CONVERTABLE_TO_FASCIATED_DANDELION, ModBlocks.FASCIATED_DANDELION.get().defaultBlockState(), r, ModConfigs.cachedServer.DANDELION_FASCIATION_CHANCE))
			{
				event.setSuccessful(true);
				ModUtils.shrink(event.getStack(), level, event.getPlayer());
			}
			else if (fasciate(level, pos, event.getState(), ModTags.BlockTags.CONVERTABLE_TO_FASCIATED_OXEYE_DAISY, ModBlocks.FASCIATED_OXEYE_DAISY.get().defaultBlockState(), r, ModConfigs.cachedServer.OXEYE_DAISY_FASCIATION_CHANCE))
			{
				event.setSuccessful(true);
				ModUtils.shrink(event.getStack(), level, event.getPlayer());
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

					if (state != null && state.is(ModTags.BlockTags.AFFECTS_FASCIATIONS))
					{
						if (!level.isClientSide() && level instanceof ServerLevel && random.nextDouble() < chance)
						{
							BlockPos blockpos1 = blockpos.above();
							level.setBlockAndUpdate(blockpos, DoublePlantBlock.copyWaterloggedFrom(level, blockpos, fasciatedFlowerState.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)));
							level.setBlockAndUpdate(blockpos1, DoublePlantBlock.copyWaterloggedFrom(level, blockpos1, fasciatedFlowerState.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)));
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
		if (!event.getLevel().isClientSide() && event.getExplosion().getDirectSourceEntity() != null && event.getExplosion().getDirectSourceEntity().getType().is(ModTags.EntityTypeTags.CAN_CONVERT_TO_CREEPANSY) && !event.getAffectedBlocks().isEmpty() && EventHooks.canEntityGrief(event.getLevel(), event.getExplosion().getDirectSourceEntity()))
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
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.RED_SNAPDRAGON.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.YELLOW_SNAPDRAGON.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.PINK_SNAPDRAGON.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WHITE_EGRET_ORCHID.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.ORANGE_SPIDER_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.PEPPERMINT_SPIDER_LILY.get()), 12, 1, 0.05F));
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
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.FOX_AND_CUBS.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.PINK_HAWKS_BEARD.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.LADIES_TRESSES.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.APRICOT_MALLOW.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.BLUESTEM_PENSTEMON.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.FIRECRACKER_PENSTEMON.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.SEGO_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.SEA_HOLLY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.SEA_THRIFT.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.HAREBELL.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.MECONOPSIS.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.WHEEL_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.JACK_IN_THE_PULPIT.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.RED_SPIDER_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.BELLS_OF_IRELAND.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.BEACH_SPIDER_LILY.get()), 12, 1, 0.05F));
			genericTrades.add(new BasicItemListing(3, new ItemStack(ModItems.JADE_VINE.get(), 2), 5, 1, 0.05F));
		}
	}
}