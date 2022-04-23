package com.github.mechalopa.jafohana.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ModFlowerPotBlock extends FlowerPotBlock
{
	@SuppressWarnings("deprecation")
	public ModFlowerPotBlock(Block flowerBlock)
	{
		super(flowerBlock, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion());
	}
}