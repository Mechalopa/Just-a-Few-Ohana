package com.github.mechalopa.jafohana;

import com.github.mechalopa.jafohana.registry.ModItems;

import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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
		ComposterBlock.COMPOSTABLES.put(ModItems.DANCING_LADIES_GINGER.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.FIREBALL_LILY.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.SPATHIPHYLLUM.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.RED_SPIDER_LILY.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BELLS_OF_IRELAND.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.ORANGE_MARIGOLD.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.YELLOW_MARIGOLD.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.GLORIOSA.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BLACK_BAT_FLOWER.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BIRD_OF_PARADISE.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BRAZILIAN_PLUME.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.FASCIATED_DANDELION.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.FASCIATED_OXEYE_DAISY.get(), 0.65F);
	}
}