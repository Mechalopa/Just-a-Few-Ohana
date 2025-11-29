package com.github.mechalopa.jafohana.world.level.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class ModFlowerPotBlock extends FlowerPotBlock
{
	@SuppressWarnings("deprecation")
	public ModFlowerPotBlock(Block flowerBlock, BlockBehaviour.Properties properties)
	{
		super(flowerBlock, properties);
	}

	public static Block create(Block flowerBlock)
	{
		return new ModFlowerPotBlock(flowerBlock, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
	}
}