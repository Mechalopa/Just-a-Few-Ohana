package com.github.mechalopa.jafohana.registry;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
	private static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, JAFOhana.MODID);

	public static final RegistryObject<Item> DAYFLOWER = REGISTRY.register("dayflower", () -> new BlockItem(ModBlocks.DAYFLOWER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> EVENING_PRIMROSE = REGISTRY.register("evening_primrose", () -> new BlockItem(ModBlocks.EVENING_PRIMROSE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> MILK_VETCH = REGISTRY.register("milk_vetch", () -> new BlockItem(ModBlocks.MILK_VETCH.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> FORGET_ME_NOT = REGISTRY.register("forget_me_not", () -> new BlockItem(ModBlocks.FORGET_ME_NOT.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> YELLOW_AFRICAN_DAISY = REGISTRY.register("yellow_african_daisy", () -> new BlockItem(ModBlocks.YELLOW_AFRICAN_DAISY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> PINK_AFRICAN_DAISY = REGISTRY.register("pink_african_daisy", () -> new BlockItem(ModBlocks.PINK_AFRICAN_DAISY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> WHITE_AFRICAN_DAISY = REGISTRY.register("white_african_daisy", () -> new BlockItem(ModBlocks.WHITE_AFRICAN_DAISY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> BLACK_TULIP = REGISTRY.register("black_tulip", () -> new BlockItem(ModBlocks.BLACK_TULIP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> BLOOD_RED_IRIS = REGISTRY.register("blood_red_iris", () -> new BlockItem(ModBlocks.BLOOD_RED_IRIS.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> RED_SPIDER_LILY = REGISTRY.register("red_spider_lily", () -> new BlockItem(ModBlocks.RED_SPIDER_LILY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> ORANGE_MARIGOLD = REGISTRY.register("orange_marigold", () -> new DoubleHighBlockItem(ModBlocks.ORANGE_MARIGOLD.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> YELLOW_MARIGOLD = REGISTRY.register("yellow_marigold", () -> new DoubleHighBlockItem(ModBlocks.YELLOW_MARIGOLD.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS)));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}