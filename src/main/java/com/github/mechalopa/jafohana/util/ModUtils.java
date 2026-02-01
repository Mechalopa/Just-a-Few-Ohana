package com.github.mechalopa.jafohana.util;

import javax.annotation.Nullable;

import com.github.mechalopa.jafohana.registry.ModDataMaps;
import com.github.mechalopa.jafohana.util.datamaps.MutableFlower;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;

public class ModUtils
{
	public static void shrink(ItemStack stack, Level level, @Nullable LivingEntity livingEntity)
	{
		if (stack != null && !stack.isEmpty() && stack.getItem() != null && level instanceof ServerLevel)
		{
			if (!stack.isDamageableItem())
			{
				stack.shrink(1);
			}
			else if (livingEntity != null)
			{
				EquipmentSlot equipmentslot = stack.equals(livingEntity.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
				stack.hurtAndBreak(1, livingEntity, equipmentslot);
			}
			else
			{
				stack.hurtAndBreak(1, (ServerLevel)level, null, p -> {});
			}
		}
	}

	public static BlockState tryWaterlogged(LevelReader level, BlockPos pos, BlockState state)
	{
		if (state.hasProperty(BlockStateProperties.WATERLOGGED))
		{
			boolean flag = level.getFluidState(pos).getType() == Fluids.WATER;

			if (state.getValue(BlockStateProperties.WATERLOGGED).booleanValue() ^ flag)
			{
				return state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(flag));
			}
		}

		return state;
	}

	public static boolean mutable(BlockState state)
	{
		Holder<Block> holder = state.getBlockHolder();
		MutableFlower data = holder.getData(ModDataMaps.MUTABLE_FLOWERS);
		return data != null && data.catalystTag() != null && data.chance() > 0.0F;
	}
}