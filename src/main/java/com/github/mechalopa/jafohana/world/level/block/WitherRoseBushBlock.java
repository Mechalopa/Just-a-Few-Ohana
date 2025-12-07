package com.github.mechalopa.jafohana.world.level.block;

import javax.annotation.Nullable;

import com.github.mechalopa.jafohana.ModConfigs;
import com.github.mechalopa.jafohana.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WitherRoseBushBlock extends ModTallFlowerBlock
{
	public WitherRoseBushBlock()
	{
		super();
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
	{
		return state.is(ModTags.BlockTags.WITHER_ROSE_BUSH_PLANTABLE_ON);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random)
	{
		VoxelShape voxelshape = this.getShape(state, level, pos, CollisionContext.empty());
		Vec3 vec3 = voxelshape.bounds().getCenter();
		double d0 = (double)pos.getX() + vec3.x();
		double d1 = (double)pos.getZ() + vec3.z();

		for (int i = 0; i < 3; i++)
		{
			if (random.nextBoolean())
			{
				level.addParticle(ParticleTypes.SMOKE, d0 + (random.nextDouble() - 0.5D) / 2.0D, (double)pos.getY() + (0.5D - random.nextDouble()), d1 + (random.nextDouble() - 0.5D) / 2.0D, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
	{
		if (!level.isClientSide() && level.getDifficulty() != Difficulty.PEACEFUL)
		{
			if (entity instanceof LivingEntity livingentity && !livingentity.isInvulnerableTo(level.damageSources().wither()))
			{
				livingentity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40));
			}
		}
	}

	@Override
	public PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob)
	{
		return PathType.DAMAGE_CAUTIOUS;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state)
	{
		return ModConfigs.cachedServer.WITHER_ROSE_BUSH_DUPLICATE_CHANCE_WHEN_USING_BONE_MEAL > 0.0D && super.isValidBonemealTarget(level, pos, state);
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state)
	{
		return level.getRandom().nextFloat() < ModConfigs.cachedServer.WITHER_ROSE_BUSH_DUPLICATE_CHANCE_WHEN_USING_BONE_MEAL && super.isBonemealSuccess(level, random, pos, state);
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource rand, BlockPos pos, BlockState blockState)
	{
		if (ModConfigs.cachedServer.WITHER_ROSE_BUSH_DROPS_NORMAL_WITHER_ROSE_WHEN_USING_BONE_MEAL)
		{
			popResource(serverLevel, pos, new ItemStack(Items.WITHER_ROSE, 1));
		}
		else
		{
			super.performBonemeal(serverLevel, rand, pos, blockState);
		}
	}
}