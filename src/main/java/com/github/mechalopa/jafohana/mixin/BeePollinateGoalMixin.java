package com.github.mechalopa.jafohana.mixin;

import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.mechalopa.jafohana.registry.ModBlocks;

import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

@Mixin(targets = "net.minecraft.world.entity.animal.Bee$BeePollinateGoal")
public class BeePollinateGoalMixin
{
	@Shadow
	@Mutable
	@Final
	private Predicate<BlockState> VALID_POLLINATION_BLOCKS;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void jafohana_init(Bee bee, CallbackInfo info)
	{
		VALID_POLLINATION_BLOCKS = VALID_POLLINATION_BLOCKS.and(state -> {
			return (state.is(ModBlocks.BLACK_BAT_FLOWER.get()) || state.is(ModBlocks.BIRD_OF_PARADISE.get()) || state.is(ModBlocks.WORSLEYA.get()) || state.is(ModBlocks.FIREWEED.get()) || state.is(ModBlocks.ALPINE_RAGWORT.get()) || state.is(ModBlocks.TATARIAN_ASTER.get()) || state.is(ModBlocks.FASCIATED_DANDELION.get())) ? state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER : true;
		});
	}
}