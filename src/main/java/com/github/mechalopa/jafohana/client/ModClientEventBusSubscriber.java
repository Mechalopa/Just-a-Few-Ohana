package com.github.mechalopa.jafohana.client;

import com.github.mechalopa.jafohana.JAFOhana;
import com.github.mechalopa.jafohana.registry.ModBlocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ModClientEventBusSubscriber
{
	@SubscribeEvent
	public static void setupClient(final FMLClientSetupEvent event)
	{
		RenderTypeLookup.setRenderLayer(ModBlocks.DAYFLOWER.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.EVENING_PRIMROSE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.MILK_VETCH.get(), RenderType.cutout());;
		RenderTypeLookup.setRenderLayer(ModBlocks.FORGET_ME_NOT.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.YELLOW_AFRICAN_DAISY.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.PINK_AFRICAN_DAISY.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.WHITE_AFRICAN_DAISY.get(), RenderType.cutout());;
		RenderTypeLookup.setRenderLayer(ModBlocks.RED_SPIDER_LILY.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_DAYFLOWER.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_EVENING_PRIMROSE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_MILK_VETCH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_FORGET_ME_NOT.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_YELLOW_AFRICAN_DAISY.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_PINK_AFRICAN_DAISY.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_WHITE_AFRICAN_DAISY.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_RED_SPIDER_LILY.get(), RenderType.cutout());
//		MinecraftForge.EVENT_BUS.register(new ModClientEvents());
	}
}