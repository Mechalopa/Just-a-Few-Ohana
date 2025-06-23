package com.github.mechalopa.jafohana.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.mechalopa.jafohana.ModConfigs;
import com.github.mechalopa.jafohana.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin
{
	@Inject(method = "Lnet/minecraft/world/item/BoneMealItem;addGrowthParticles(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;I)V", at = @At("HEAD"), cancellable = true)
	private static void jafohana_addGrowthParticles(LevelAccessor level, BlockPos pos, int count, CallbackInfo info)
	{
		BlockState blockstate = level.getBlockState(pos);

		if (!(blockstate.getBlock() instanceof BonemealableBlock) && ((ModConfigs.cachedServer.DANDELION_FASCIATION_CHANCE > 0.0D && blockstate.is(ModTags.BlockTags.CONVERTABLE_TO_FASCIATED_DANDELION)) || (ModConfigs.cachedServer.OXEYE_DAISY_FASCIATION_CHANCE > 0.0D && blockstate.is(ModTags.BlockTags.CONVERTABLE_TO_FASCIATED_OXEYE_DAISY))))
		{
			ParticleUtils.spawnParticleInBlock(level, pos, count, ParticleTypes.HAPPY_VILLAGER);
			info.cancel();
		}
	}
}