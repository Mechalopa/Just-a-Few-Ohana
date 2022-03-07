package net.mechalopa.jafohana.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.Effect;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

public class RedSpiderLilyBlock extends ModFlowerBlock
{
	public static final IntegerProperty VARIANT = IntegerProperty.create("variant", 0, 2);
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D);

	public RedSpiderLilyBlock(Effect effect, int effectDuration, AbstractBlock.Properties properties)
	{
		super(effect, effectDuration, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(VARIANT, 0));
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos)
	{
		return super.mayPlaceOn(state, world, pos) || state.is(BlockTags.SOUL_SPEED_BLOCKS);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return this.getStateForPlacement(context.getLevel(), context.getClickedPos());
	}

	protected BlockState getStateForPlacement(IBlockReader world, BlockPos pos)
	{
		final long i = MathHelper.getSeed(pos);
		return this.defaultBlockState().setValue(VARIANT, MathHelper.clamp(Math.abs((int)(i % 3L)), 0, 2));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		Vector3d vector3d = state.getOffset(world, pos);
		return SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(VARIANT);
	}
}