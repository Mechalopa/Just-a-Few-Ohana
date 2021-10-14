package jafohana.registry;

import jafohana.JAFOhana;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
	private static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, JAFOhana.MODID);

	public static final RegistryObject<Item> DAYFLOWER = REGISTRY.register("dayflower", () -> new BlockItem(ModBlocks.DAYFLOWER.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	public static final RegistryObject<Item> EVENING_PRIMROSE = REGISTRY.register("evening_primrose", () -> new BlockItem(ModBlocks.EVENING_PRIMROSE.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	public static final RegistryObject<Item> MILK_VETCH = REGISTRY.register("milk_vetch", () -> new BlockItem(ModBlocks.MILK_VETCH.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}