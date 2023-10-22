package com.github.mechalopa.jafohana.world.level.block;

import net.minecraft.util.StringRepresentable;

public enum JadeVinePart implements StringRepresentable
{
	SINGLE("single"),
	SHORT_STEM("short_stem"),
	SHORT_HEAD("short_head"),
	LONG_STEM("long_stem"),
	LONG_BODY("long_body"),
	LONG_HEAD("long_head");

	private final String name;

	private JadeVinePart(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return this.name;
	}

	@Override
	public String getSerializedName()
	{
		return this.name;
	}

	public boolean isHead()
	{
		return this == SINGLE || this == SHORT_HEAD || this == LONG_HEAD;
	}
}