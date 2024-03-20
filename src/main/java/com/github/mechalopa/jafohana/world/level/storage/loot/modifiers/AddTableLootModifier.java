package com.github.mechalopa.jafohana.world.level.storage.loot.modifiers;

import javax.annotation.Nonnull;

import com.github.mechalopa.jafohana.registry.ModLootModifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class AddTableLootModifier extends LootModifier
{
    public static final Codec<AddTableLootModifier> CODEC = RecordCodecBuilder.create(inst -> inst.group(IGlobalLootModifier.LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(glm -> glm.conditions), ResourceLocation.CODEC.fieldOf("table").forGetter(AddTableLootModifier::table)).apply(inst, AddTableLootModifier::new));
	private final ResourceLocation lootTable;

	public AddTableLootModifier(LootItemCondition[] conditions, ResourceLocation lootTable)
	{
		super(conditions);
		this.lootTable = lootTable;
	}

	public ResourceLocation table()
	{
		return this.lootTable;
	}

	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
	{
		LootTable table = context.getResolver().getLootTable(this.lootTable);
		table.getRandomItemsRaw(context, LootTable.createStackSplitter(context.getLevel(), generatedLoot::add));
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec()
	{
		return ModLootModifiers.ADD_TABLE.get();
	}
}