package com.github.mechalopa.jafohana.mixin;

import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.mechalopa.jafohana.util.ModTags;

import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.fml.ModList;

@Mixin(targets = "net.minecraft.world.entity.animal.Bee$BeePollinateGoal")
public abstract class BeePollinateGoalMixin
{
	@Shadow
	@Mutable
	@Final
	private Predicate<BlockState> VALID_POLLINATION_BLOCKS;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void jafohana_init(Bee bee, CallbackInfo info)
	{
		if (!ModList.get().isLoaded("productivebees")) //very ugly
		{
			VALID_POLLINATION_BLOCKS = VALID_POLLINATION_BLOCKS.and(state -> {
				return (state.is(ModTags.BlockTags.BEE_ATTRACTIVE_UPPER_ONLY) && state.hasProperty(DoublePlantBlock.HALF)) ? state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER : true;
			});
		}
	}
}