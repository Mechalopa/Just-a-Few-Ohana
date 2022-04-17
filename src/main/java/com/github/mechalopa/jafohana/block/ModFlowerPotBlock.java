package com.github.mechalopa.jafohana.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.material.Material;

public class ModFlowerPotBlock extends FlowerPotBlock
{
	@SuppressWarnings("deprecation")
	public ModFlowerPotBlock(Block flowerBlock)
	{
		super(flowerBlock, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion());
	}
}