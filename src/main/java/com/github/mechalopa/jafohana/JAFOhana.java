package com.github.mechalopa.jafohana;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.mechalopa.jafohana.registry.ModBiomeModifiers;
import com.github.mechalopa.jafohana.registry.ModBlocks;
import com.github.mechalopa.jafohana.registry.ModItems;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JAFOhana.MODID)
@Mod.EventBusSubscriber(modid = JAFOhana.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JAFOhana
{
	public static final String MODID = "jafohana";

	public static final Logger LOGGER = LogManager.getLogger();

	public static final CreativeModeTab MODTAB = new CreativeModeTab(JAFOhana.MODID + ".tab")
	{
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(ModItems.DAYFLOWER.get());
		}
	};

	public JAFOhana()
	{
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.SERVER_CONFIG);

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(ModConfigs::loadConfig);

		ModBlocks.register(modEventBus);
		ModItems.register(modEventBus);
		ModBiomeModifiers.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(new ModEvents());
	}
}