package com.github.mechalopa.jafohana.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.mechalopa.jafohana.ModConfigs;
import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity
{
	public LivingEntityMixin(EntityType<?> entityType, Level level)
	{
		super(entityType, level);
	}

	@Inject(method = "Lnet/minecraft/world/entity/LivingEntity;createWitherRose(Lnet/minecraft/world/entity/LivingEntity;)V", at = @At(value = "INVOKE", target="Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;", shift = At.Shift.BEFORE), cancellable = true)
	public void jafohana_createWitherRose(LivingEntity entitySource, CallbackInfo info)
	{
		if (convertNearestFlower(this.level(), this.blockPosition(), this.getRandom(), ModConfigs.cachedServer.ROSE_BUSH_WITHERING_DISTANCE))
		{
			info.cancel();
		}
	}

	private static boolean convertNearestFlower(Level level, BlockPos blockpos, RandomSource random, int distance)
	{
		BlockState witherRoseBushState = ModBlocks.WITHER_ROSE_BUSH.get().defaultBlockState();
		BlockPos.MutableBlockPos mutableblockpos = new BlockPos.MutableBlockPos();

		for (int i = 0; i <= distance; i = i > 0 ? -i : 1 - i)
		{
			for (int j = 0; j < distance; j++)
			{
				for (int k = 0; k <= j; k = k > 0 ? -k : 1 - k)
				{
					for (int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l)
					{
						mutableblockpos.setWithOffset(blockpos, k, i - 1, l);

						if (blockpos.closerThan(mutableblockpos, (double)distance))
						{
							BlockPos blockpos1 = mutableblockpos.immutable();
							BlockState state = level.getBlockState(blockpos1);

							if (state != null && state.is(ModTags.BlockTags.CONVERTABLE_TO_WITHER_ROSE_BUSH) && ((state.hasProperty(DoublePlantBlock.HALF) && state.getValue(DoublePlantBlock.HALF) != DoubleBlockHalf.UPPER) || level.isEmptyBlock(blockpos1.above())) && witherRoseBushState.canSurvive(level, blockpos1))
							{
								BlockPos blockpos2 = blockpos1.above();
								level.setBlock(blockpos1, DoublePlantBlock.copyWaterloggedFrom(level, blockpos1, witherRoseBushState.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)), 19);
								level.setBlock(blockpos2, DoublePlantBlock.copyWaterloggedFrom(level, blockpos2, witherRoseBushState.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)), 3);

								if (level instanceof ServerLevel)
								{
									addSmokeParticles((ServerLevel)level, blockpos1, random);
									addSmokeParticles((ServerLevel)level, blockpos2, random);
								}

								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}

	private static void addSmokeParticles(ServerLevel level, BlockPos blockpos, RandomSource random)
	{
		Vec3 vec3 = Vec3.atCenterOf(blockpos);

		for (int i = 0; i < 20; i++)
		{
			double d0 = vec3.x() + (random.nextDouble() - 0.5D) * 1.25D;
			double d1 = vec3.y() + (random.nextDouble() - 0.5D) * 1.25D;
			double d2 = vec3.z() + (random.nextDouble() - 0.5D) * 1.25D;
			level.sendParticles(ParticleTypes.SMOKE, d0, d1, d2, 0, 0.0D, 0.0D, 0.0D, 0.0D);
		}
	}
}