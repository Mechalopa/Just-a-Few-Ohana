package com.github.mechalopa.jafohana;

import java.util.List;

import com.github.mechalopa.jafohana.registry.ModDataMaps;
import com.github.mechalopa.jafohana.registry.ModItems;
import com.github.mechalopa.jafohana.util.ModUtils;
import com.github.mechalopa.jafohana.util.datamaps.ExplosionConvertible;
import com.github.mechalopa.jafohana.util.datamaps.MutableFlower;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
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
			RandomSource random = level.getRandom();

			if (mutate(level, pos, event.getState(), random))
			{
				event.setSuccessful(true);
				ModUtils.shrink(event.getStack(), level, event.getPlayer());
			}
		}
	}

	private static boolean mutate(Level level, BlockPos blockpos, BlockState baseFlowerState, RandomSource random)
	{
		if (baseFlowerState.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF))
		{
			if (baseFlowerState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.LOWER)
			{
				BlockPos blockpos1 = blockpos.below();
				BlockState state = level.getBlockState(blockpos1);
				return mutate(level, blockpos1, state, random, state.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF));
			}
			else
			{
				return mutate(level, blockpos, baseFlowerState, random, true);
			}
		}
		else
		{
			return mutate(level, blockpos, baseFlowerState, random, false);
		}
	}

	private static boolean mutate(Level level, BlockPos blockpos, BlockState baseFlowerState, RandomSource random, boolean flag)
	{
		Holder<Block> holder = baseFlowerState.getBlockHolder();
		MutableFlower data = holder.getData(ModDataMaps.MUTABLE_FLOWERS);

		if (data != null && data.catalystTag() != null && data.chance() > 0.0F)
		{
			BlockState mutatedFlowerState = data.mutatedFlower().defaultBlockState();
			boolean flag1 = mutatedFlowerState.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF);

			if (flag1)
			{
				mutatedFlowerState = mutatedFlowerState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER);
			}

			if (mutatedFlowerState.canSurvive(level, blockpos) && (flag || !flag1 || level.isEmptyBlock(blockpos.above())))
			{
				for (Direction direction : Direction.values())
				{
					if (direction.getAxis().isHorizontal())
					{
						BlockState state = level.getBlockState(blockpos.relative(direction));

						if (state != null && state.is(data.catalystTag()) && (!state.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF) || state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.UPPER))
						{
							if (!level.isClientSide() && level instanceof ServerLevel && random.nextFloat() < data.chance())
							{
								BlockPos blockpos1 = blockpos.above();

								if (flag1)
								{
									level.setBlockAndUpdate(blockpos, DoublePlantBlock.copyWaterloggedFrom(level, blockpos, mutatedFlowerState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)));
									level.setBlockAndUpdate(blockpos1, DoublePlantBlock.copyWaterloggedFrom(level, blockpos1, mutatedFlowerState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)));
								}
								else
								{
									level.setBlockAndUpdate(blockpos, ModUtils.tryWaterlogged(level, blockpos, mutatedFlowerState));

									if (flag)
									{
										level.removeBlock(blockpos1, false);
									}
								}
							}

							return true;
						}
					}
				}
			}
		}

		return false;
	}

	@SubscribeEvent
	public static void onBlockExplode(ExplosionEvent.Detonate event)
	{
		if (!event.getLevel().isClientSide() && event.getLevel() instanceof ServerLevel level && event.getExplosion().getDirectSourceEntity() != null && !event.getAffectedBlocks().isEmpty() && EventHooks.canEntityGrief(event.getLevel(), event.getExplosion().getDirectSourceEntity()))
		{
			for (BlockPos pos : event.getAffectedBlocks())
			{
				Holder<Block> holder = level.getBlockState(pos).getBlockHolder();
				ExplosionConvertible data = holder.getData(ModDataMaps.EXPLOSION_CONVERTIBLES);

				if (data != null && data.chance() > 0.0F && data.sourceEntityTypeTag() != null && event.getExplosion().getDirectSourceEntity().getType().is(data.sourceEntityTypeTag()) && level.getRandom().nextFloat() < data.chance())
				{
					LootTable loottable  = level.getServer().reloadableRegistries().getLootTable(data.lootTable());

					if (loottable != null)
					{
						LootParams lootparams = new LootParams.Builder(level).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos)).withParameter(LootContextParams.THIS_ENTITY, event.getExplosion().getDirectSourceEntity()).create(LootContextParamSets.GIFT);

						for (ItemStack stack : loottable.getRandomItems(lootparams))
						{
							ItemEntity itementity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack);
							level.addFreshEntity(itementity);
						}
					}

					if (!data.remainingBlock().defaultBlockState().isAir())
					{
						level.setBlockAndUpdate(pos, ModUtils.tryWaterlogged(level, pos, data.remainingBlock().defaultBlockState()));
					}
					else
					{
						level.removeBlock(pos, false);
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
			genericTrades.add(new BasicItemListing(1, new ItemStack(ModItems.DESERT_PEA.get()), 12, 1, 0.05F));
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
			genericTrades.add(new BasicItemListing(3, new ItemStack(ModItems.MYSORE_TRUMPETVINE.get(), 2), 5, 1, 0.05F));
		}
	}
}