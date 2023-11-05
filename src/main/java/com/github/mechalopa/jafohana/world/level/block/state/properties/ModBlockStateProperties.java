package com.github.mechalopa.jafohana.world.level.block.state.properties;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModBlockStateProperties
{
	public static final BooleanProperty ON_NYLIUM = BooleanProperty.create("on_nylium");
	public static final EnumProperty<JadeVinePart> JADE_VINE_PART = EnumProperty.create("jade_vine", JadeVinePart.class);
}