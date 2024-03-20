package com.github.mechalopa.jafohana.registry;

import java.util.function.Supplier;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs
{
	private static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JAFOhana.MODID);

	public static final Supplier<CreativeModeTab> TAB = REGISTRY.register("tab", () -> CreativeModeTab.builder().title(Component.translatable("item_group." + JAFOhana.MODID + ".tab")).icon(() -> new ItemStack(ModItems.DAYFLOWER.get())).displayItems((features, output) -> {
		for (DeferredHolder<Item, ? extends Item> item : ModItems.getItemRegistry().getEntries())
		{
			output.accept(item.get());
		}
	}).build());

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}