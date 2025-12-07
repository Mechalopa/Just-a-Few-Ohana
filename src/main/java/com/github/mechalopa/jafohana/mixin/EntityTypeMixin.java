package com.github.mechalopa.jafohana.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.google.common.collect.ImmutableSet;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(EntityType.class)
public abstract class EntityTypeMixin
{
	@Shadow
	@Final
	private ImmutableSet<Block> immuneTo;

	@Inject(method = "Lnet/minecraft/world/entity/EntityType;isBlockDangerous(Lnet/minecraft/world/level/block/state/BlockState;)Z", at = @At("HEAD"), cancellable = true)
	public void jafohana_isBlockDangerous(BlockState state, CallbackInfoReturnable<Boolean> cir)
	{
		if (state.is(ModBlocks.WITHER_ROSE_BUSH.get()) && this.immuneTo.contains(Blocks.WITHER_ROSE))
		{
			cir.setReturnValue(false);
		}
	}
}