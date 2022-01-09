package net.mechalopa.jafohana.client;

import net.mechalopa.jafohana.JAFOhana;
import net.mechalopa.jafohana.registry.ModBlocks;
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
		RenderTypeLookup.setRenderLayer(ModBlocks.MILK_VETCH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_DAYFLOWER.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_EVENING_PRIMROSE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_MILK_VETCH.get(), RenderType.cutout());
//		MinecraftForge.EVENT_BUS.register(new ModClientEvents());
	}
}