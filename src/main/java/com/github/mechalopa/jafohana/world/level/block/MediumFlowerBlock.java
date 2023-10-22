package com.github.mechalopa.jafohana.world.level.block;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MediumFlowerBlock extends ModFlowerBlock
{
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D);

	public MediumFlowerBlock(Supplier<MobEffect> effectSupplier, int effectDuration)
	{
		super(effectSupplier, effectDuration);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		Vec3 vector3d = state.getOffset(world, pos);
		return SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	}
}