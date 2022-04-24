package com.github.mechalopa.jafohana.block;

import com.github.mechalopa.jafohana.util.ModTags;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

public class RedSpiderLilyBlock extends ModFlowerBlock
{
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D);

	public RedSpiderLilyBlock(Effect effect, int effectDuration, AbstractBlock.Properties properties)
	{
		super(effect, effectDuration, properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos)
	{
		return super.mayPlaceOn(state, world, pos) || ModTags.checkTagContains(ModTags.RED_SPIDER_LILY_PLANTABLE_ON, state.getBlock());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		Vector3d vector3d = state.getOffset(world, pos);
		return SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	}
}