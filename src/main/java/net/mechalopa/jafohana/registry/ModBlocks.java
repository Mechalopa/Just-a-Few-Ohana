package net.mechalopa.jafohana.registry;

import net.mechalopa.jafohana.JAFOhana;
import net.mechalopa.jafohana.block.ModFlowerBlock;
import net.mechalopa.jafohana.block.ModFlowerPotBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks
{
	private static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, JAFOhana.MODID);

	public static final RegistryObject<Block> DAYFLOWER = REGISTRY.register("dayflower", () -> new ModFlowerBlock(Effects.WATER_BREATHING, 5, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> EVENING_PRIMROSE = REGISTRY.register("evening_primrose", () -> new ModFlowerBlock(Effects.NIGHT_VISION, 5, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> MILK_VETCH = REGISTRY.register("milk_vetch", () -> new ModFlowerBlock(Effects.REGENERATION, 8, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> POTTED_DAYFLOWER = REGISTRY.register("potted_dayflower", () -> new ModFlowerPotBlock(DAYFLOWER.get()));
	public static final RegistryObject<Block> POTTED_EVENING_PRIMROSE = REGISTRY.register("potted_evening_primrose", () -> new ModFlowerPotBlock(EVENING_PRIMROSE.get()));
	public static final RegistryObject<Block> POTTED_MILK_VETCH = REGISTRY.register("potted_milk_vetch", () -> new ModFlowerPotBlock(MILK_VETCH.get()));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}