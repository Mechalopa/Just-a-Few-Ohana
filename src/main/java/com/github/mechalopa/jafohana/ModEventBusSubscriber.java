package com.github.mechalopa.jafohana;

import com.github.mechalopa.jafohana.registry.ModItems;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusSubscriber
{
	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event)
	{
		event.enqueueWork(() -> {
			registerCompostables();
		});
	}

	public static void registerCompostables()
	{
		ComposterBlock.COMPOSTABLES.put(ModItems.DAYFLOWER.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.EVENING_PRIMROSE.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.MILK_VETCH.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.FORGET_ME_NOT.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.YELLOW_AFRICAN_DAISY.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.PINK_AFRICAN_DAISY.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.WHITE_AFRICAN_DAISY.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BLACK_TULIP.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BLOOD_RED_IRIS.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BLUEBELL.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.RED_SNAPDRAGON.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.YELLOW_SNAPDRAGON.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.PINK_SNAPDRAGON.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.WHITE_EGRET_ORCHID.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.ORANGE_SPIDER_LILY.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.RED_SPIDER_LILY.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BELLS_OF_IRELAND.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.ORANGE_MARIGOLD.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.YELLOW_MARIGOLD.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.GLORIOSA.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BLACK_BAT_FLOWER.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.FASCIATED_DANDELION.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.FASCIATED_OXEYE_DAISY.get(), 0.65F);
	}

	@SubscribeEvent
	public static void registerCreativeModeTab(final CreativeModeTabEvent.Register event)
	{
		event.registerCreativeModeTab(new ResourceLocation(JAFOhana.MODID, "tab"), builder -> builder.title(Component.translatable("item_group." + JAFOhana.MODID + ".tab")).icon(() -> new ItemStack(ModItems.DAYFLOWER.get())).displayItems((features, output) -> {
			for (RegistryObject<Item> item : ModItems.getItemRegistry().getEntries())
			{
				output.accept(item.get());
			}
		}));
	}
}