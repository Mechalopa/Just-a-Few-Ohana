package com.github.mechalopa.jafohana.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RedSpiderLilyBlock extends ModFlowerBlock
{
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D);

	public RedSpiderLilyBlock(MobEffect effect, int effectDuration, BlockBehaviour.Properties properties)
	{
		super(effect, effectDuration, properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
	{
		return super.mayPlaceOn(state, world, pos) || state.is(BlockTags.SOUL_SPEED_BLOCKS);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		Vec3 vector3d = state.getOffset(world, pos);
		return SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	}
}