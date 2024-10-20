package com.github.mechalopa.jafohana.util;

import javax.annotation.Nullable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ModUtils
{
	public static void shrink(ItemStack stack, ServerLevel serverLevel, @Nullable LivingEntity livingEntity)
	{
		if (stack != null && !stack.isEmpty() && stack.getItem() != null)
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
				stack.hurtAndBreak(1, serverLevel, null, p -> {});
			}
		}
	}
}