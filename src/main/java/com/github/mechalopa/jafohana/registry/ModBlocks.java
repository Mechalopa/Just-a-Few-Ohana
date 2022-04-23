package com.github.mechalopa.jafohana.registry;

import com.github.mechalopa.jafohana.JAFOhana;
import com.github.mechalopa.jafohana.world.level.block.ModFlowerBlock;
import com.github.mechalopa.jafohana.world.level.block.ModFlowerPotBlock;
import com.github.mechalopa.jafohana.world.level.block.RedSpiderLilyBlock;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
	private static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, JAFOhana.MODID);

	public static final RegistryObject<Block> DAYFLOWER = REGISTRY.register("dayflower", () -> new ModFlowerBlock(MobEffects.WATER_BREATHING, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> EVENING_PRIMROSE = REGISTRY.register("evening_primrose", () -> new ModFlowerBlock(MobEffects.NIGHT_VISION, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> MILK_VETCH = REGISTRY.register("milk_vetch", () -> new ModFlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> FORGET_ME_NOT = REGISTRY.register("forget_me_not", () -> new ModFlowerBlock(MobEffects.INVISIBILITY, 8, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> YELLOW_AFRICAN_DAISY = REGISTRY.register("yellow_african_daisy", () -> new ModFlowerBlock(MobEffects.DAMAGE_RESISTANCE, 4, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> PINK_AFRICAN_DAISY = REGISTRY.register("pink_african_daisy", () -> new ModFlowerBlock(MobEffects.DAMAGE_RESISTANCE, 4, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> WHITE_AFRICAN_DAISY = REGISTRY.register("white_african_daisy", () -> new ModFlowerBlock(MobEffects.DAMAGE_RESISTANCE, 4, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> RED_SPIDER_LILY = REGISTRY.register("red_spider_lily", () -> new RedSpiderLilyBlock(MobEffects.POISON, 12, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> POTTED_DAYFLOWER = REGISTRY.register("potted_dayflower", () -> new ModFlowerPotBlock(DAYFLOWER.get()));
	public static final RegistryObject<Block> POTTED_EVENING_PRIMROSE = REGISTRY.register("potted_evening_primrose", () -> new ModFlowerPotBlock(EVENING_PRIMROSE.get()));
	public static final RegistryObject<Block> POTTED_MILK_VETCH = REGISTRY.register("potted_milk_vetch", () -> new ModFlowerPotBlock(MILK_VETCH.get()));
	public static final RegistryObject<Block> POTTED_FORGET_ME_NOT = REGISTRY.register("potted_forget_me_not", () -> new ModFlowerPotBlock(FORGET_ME_NOT.get()));
	public static final RegistryObject<Block> POTTED_YELLOW_AFRICAN_DAISY = REGISTRY.register("potted_yellow_african_daisy", () -> new ModFlowerPotBlock(YELLOW_AFRICAN_DAISY.get()));
	public static final RegistryObject<Block> POTTED_PINK_AFRICAN_DAISY = REGISTRY.register("potted_pink_african_daisy", () -> new ModFlowerPotBlock(PINK_AFRICAN_DAISY.get()));
	public static final RegistryObject<Block> POTTED_WHITE_AFRICAN_DAISY = REGISTRY.register("potted_white_african_daisy", () -> new ModFlowerPotBlock(WHITE_AFRICAN_DAISY.get()));
	public static final RegistryObject<Block> POTTED_RED_SPIDER_LILY = REGISTRY.register("potted_red_spider_lily", () -> new ModFlowerPotBlock(RED_SPIDER_LILY.get()));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}