package com.github.mechalopa.jafohana.world.level.block.state.properties;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModBlockStateProperties
{
	public static final BooleanProperty ON_NYLIUM = BooleanProperty.create("on_nylium");
	public static final EnumProperty<VineFlowerPart> VINE_FLOWER_PART = EnumProperty.create("vine_flower", VineFlowerPart.class);
}