package com.github.mechalopa.jafohana.registry;

import com.github.mechalopa.jafohana.JAFOhana;
import com.github.mechalopa.jafohana.world.level.block.CrimsonCloverBlock;
import com.github.mechalopa.jafohana.world.level.block.FasciatedDandelionBlock;
import com.github.mechalopa.jafohana.world.level.block.FasciatedOxeyeDaisyBlock;
import com.github.mechalopa.jafohana.world.level.block.JadeVineBlock;
import com.github.mechalopa.jafohana.world.level.block.MediumFlowerBlock;
import com.github.mechalopa.jafohana.world.level.block.ModFlowerBlock;
import com.github.mechalopa.jafohana.world.level.block.ModFlowerPotBlock;
import com.github.mechalopa.jafohana.world.level.block.ModTallFlowerBlock;
import com.github.mechalopa.jafohana.world.level.block.RedSpiderLilyBlock;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
	private static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, JAFOhana.MODID);

	public static final RegistryObject<Block> DAYFLOWER = REGISTRY.register("dayflower", () -> new ModFlowerBlock(MobEffects.WATER_BREATHING, 5));
	public static final RegistryObject<Block> EVENING_PRIMROSE = REGISTRY.register("evening_primrose", () -> new ModFlowerBlock(MobEffects.NIGHT_VISION, 5));
	public static final RegistryObject<Block> MILK_VETCH = REGISTRY.register("milk_vetch", () -> new ModFlowerBlock(MobEffects.REGENERATION, 8));
	public static final RegistryObject<Block> FORGET_ME_NOT = REGISTRY.register("forget_me_not", () -> new ModFlowerBlock(MobEffects.INVISIBILITY, 8));
	public static final RegistryObject<Block> YELLOW_AFRICAN_DAISY = REGISTRY.register("yellow_african_daisy", () -> new ModFlowerBlock(MobEffects.DAMAGE_RESISTANCE, 4));
	public static final RegistryObject<Block> PINK_AFRICAN_DAISY = REGISTRY.register("pink_african_daisy", () -> new ModFlowerBlock(MobEffects.DAMAGE_RESISTANCE, 4));
	public static final RegistryObject<Block> WHITE_AFRICAN_DAISY = REGISTRY.register("white_african_daisy", () -> new ModFlowerBlock(MobEffects.DAMAGE_RESISTANCE, 4));
	public static final RegistryObject<Block> BLACK_TULIP = REGISTRY.register("black_tulip", () -> new ModFlowerBlock(MobEffects.WEAKNESS, 9));
	public static final RegistryObject<Block> GREEN_TULIP = REGISTRY.register("green_tulip", () -> new ModFlowerBlock(MobEffects.WEAKNESS, 9));
	public static final RegistryObject<Block> BLOOD_RED_IRIS = REGISTRY.register("blood_red_iris", () -> new ModFlowerBlock(MobEffects.POISON, 12));
	public static final RegistryObject<Block> BLUEBELL = REGISTRY.register("bluebell", () -> new ModFlowerBlock(MobEffects.REGENERATION, 8));
	public static final RegistryObject<Block> RED_SNAPDRAGON = REGISTRY.register("red_snapdragon", () -> new ModFlowerBlock(MobEffects.DAMAGE_BOOST, 4));
	public static final RegistryObject<Block> YELLOW_SNAPDRAGON = REGISTRY.register("yellow_snapdragon", () -> new ModFlowerBlock(MobEffects.DAMAGE_BOOST, 4));
	public static final RegistryObject<Block> PINK_SNAPDRAGON = REGISTRY.register("pink_snapdragon", () -> new ModFlowerBlock(MobEffects.DAMAGE_BOOST, 4));
	public static final RegistryObject<Block> WHITE_EGRET_ORCHID = REGISTRY.register("white_egret_orchid", () -> new ModFlowerBlock(MobEffects.SLOW_FALLING, 5));
	public static final RegistryObject<Block> ORANGE_SPIDER_LILY = REGISTRY.register("orange_spider_lily", () -> new ModFlowerBlock(MobEffects.POISON, 12));
	public static final RegistryObject<Block> DANCING_LADIES_GINGER = REGISTRY.register("dancing_ladies_ginger", () -> new ModFlowerBlock(MobEffects.MOVEMENT_SPEED, 5));
	public static final RegistryObject<Block> FIREBALL_LILY = REGISTRY.register("fireball_lily", () -> new ModFlowerBlock(MobEffects.FIRE_RESISTANCE, 4));
	public static final RegistryObject<Block> SPATHIPHYLLUM = REGISTRY.register("spathiphyllum", () -> new ModFlowerBlock(MobEffects.MOVEMENT_SPEED, 5));
	public static final RegistryObject<Block> PURPLE_PANSY = REGISTRY.register("purple_pansy", () -> new ModFlowerBlock(MobEffects.CONFUSION, 9));
	public static final RegistryObject<Block> WHITE_PANSY = REGISTRY.register("white_pansy", () -> new ModFlowerBlock(MobEffects.CONFUSION, 9));
	public static final RegistryObject<Block> YELLOW_PANSY = REGISTRY.register("yellow_pansy", () -> new ModFlowerBlock(MobEffects.CONFUSION, 9));
	public static final RegistryObject<Block> CREEPANSY = REGISTRY.register("creepansy", () -> new ModFlowerBlock(MobEffects.DAMAGE_BOOST, 4));
	public static final RegistryObject<Block> SNOWDROP = REGISTRY.register("snowdrop", () -> new ModFlowerBlock(MobEffects.POISON, 12));
	public static final RegistryObject<Block> WINTER_HEATH = REGISTRY.register("winter_heath", () -> new ModFlowerBlock(MobEffects.WEAKNESS, 9));
	public static final RegistryObject<Block> BLUE_DAISY = REGISTRY.register("blue_daisy", () -> new ModFlowerBlock(MobEffects.MOVEMENT_SPEED, 5));
	public static final RegistryObject<Block> CRIMSON_CLOVER = REGISTRY.register("crimson_clover", () -> new CrimsonCloverBlock(MobEffects.FIRE_RESISTANCE, 4));
	public static final RegistryObject<Block> WHEEL_LILY = REGISTRY.register("wheel_lily", () -> new ModFlowerBlock(MobEffects.SATURATION, 7));
	public static final RegistryObject<Block> MAGENTA_SPOTTED_BELLFLOWER = REGISTRY.register("magenta_spotted_bellflower", () -> new ModFlowerBlock(MobEffects.NIGHT_VISION, 5));
	public static final RegistryObject<Block> WHITE_SPOTTED_BELLFLOWER = REGISTRY.register("white_spotted_bellflower", () -> new ModFlowerBlock(MobEffects.NIGHT_VISION, 5));
	public static final RegistryObject<Block> RED_SPIDER_LILY = REGISTRY.register("red_spider_lily", () -> new RedSpiderLilyBlock(MobEffects.POISON, 12));
	public static final RegistryObject<Block> BELLS_OF_IRELAND = REGISTRY.register("bells_of_ireland", () -> new MediumFlowerBlock(MobEffects.SATURATION, 7));
	public static final RegistryObject<Block> ORANGE_MARIGOLD = REGISTRY.register("orange_marigold", () -> new ModTallFlowerBlock());
	public static final RegistryObject<Block> YELLOW_MARIGOLD = REGISTRY.register("yellow_marigold", () -> new ModTallFlowerBlock());
	public static final RegistryObject<Block> GLORIOSA = REGISTRY.register("gloriosa", () -> new ModTallFlowerBlock());
	public static final RegistryObject<Block> BLACK_BAT_FLOWER = REGISTRY.register("black_bat_flower", () -> new ModTallFlowerBlock());
	public static final RegistryObject<Block> BIRD_OF_PARADISE = REGISTRY.register("bird_of_paradise", () -> new ModTallFlowerBlock());
	public static final RegistryObject<Block> BRAZILIAN_PLUME = REGISTRY.register("brazilian_plume", () -> new ModTallFlowerBlock());
	public static final RegistryObject<Block> WORSLEYA = REGISTRY.register("worsleya", () -> new ModTallFlowerBlock());
	public static final RegistryObject<Block> FASCIATED_DANDELION = REGISTRY.register("fasciated_dandelion", () -> new FasciatedDandelionBlock());
	public static final RegistryObject<Block> FASCIATED_OXEYE_DAISY = REGISTRY.register("fasciated_oxeye_daisy", () -> new FasciatedOxeyeDaisyBlock());
	public static final RegistryObject<Block> JADE_VINE = REGISTRY.register("jade_vine", () -> new JadeVineBlock());
	public static final RegistryObject<Block> POTTED_DAYFLOWER = REGISTRY.register("potted_dayflower", () -> new ModFlowerPotBlock(DAYFLOWER.get()));
	public static final RegistryObject<Block> POTTED_EVENING_PRIMROSE = REGISTRY.register("potted_evening_primrose", () -> new ModFlowerPotBlock(EVENING_PRIMROSE.get()));
	public static final RegistryObject<Block> POTTED_MILK_VETCH = REGISTRY.register("potted_milk_vetch", () -> new ModFlowerPotBlock(MILK_VETCH.get()));
	public static final RegistryObject<Block> POTTED_FORGET_ME_NOT = REGISTRY.register("potted_forget_me_not", () -> new ModFlowerPotBlock(FORGET_ME_NOT.get()));
	public static final RegistryObject<Block> POTTED_YELLOW_AFRICAN_DAISY = REGISTRY.register("potted_yellow_african_daisy", () -> new ModFlowerPotBlock(YELLOW_AFRICAN_DAISY.get()));
	public static final RegistryObject<Block> POTTED_PINK_AFRICAN_DAISY = REGISTRY.register("potted_pink_african_daisy", () -> new ModFlowerPotBlock(PINK_AFRICAN_DAISY.get()));
	public static final RegistryObject<Block> POTTED_WHITE_AFRICAN_DAISY = REGISTRY.register("potted_white_african_daisy", () -> new ModFlowerPotBlock(WHITE_AFRICAN_DAISY.get()));
	public static final RegistryObject<Block> POTTED_BLACK_TULIP = REGISTRY.register("potted_black_tulip", () -> new ModFlowerPotBlock(BLACK_TULIP.get()));
	public static final RegistryObject<Block> POTTED_GREEN_TULIP = REGISTRY.register("potted_green_tulip", () -> new ModFlowerPotBlock(GREEN_TULIP.get()));
	public static final RegistryObject<Block> POTTED_BLOOD_RED_IRIS = REGISTRY.register("potted_blood_red_iris", () -> new ModFlowerPotBlock(BLOOD_RED_IRIS.get()));
	public static final RegistryObject<Block> POTTED_BLUEBELL = REGISTRY.register("potted_bluebell", () -> new ModFlowerPotBlock(BLUEBELL.get()));
	public static final RegistryObject<Block> POTTED_RED_SNAPDRAGON = REGISTRY.register("potted_red_snapdragon", () -> new ModFlowerPotBlock(RED_SNAPDRAGON.get()));
	public static final RegistryObject<Block> POTTED_YELLOW_SNAPDRAGON = REGISTRY.register("potted_yellow_snapdragon", () -> new ModFlowerPotBlock(YELLOW_SNAPDRAGON.get()));
	public static final RegistryObject<Block> POTTED_PINK_SNAPDRAGON = REGISTRY.register("potted_pink_snapdragon", () -> new ModFlowerPotBlock(PINK_SNAPDRAGON.get()));
	public static final RegistryObject<Block> POTTED_WHITE_EGRET_ORCHID = REGISTRY.register("potted_white_egret_orchid", () -> new ModFlowerPotBlock(WHITE_EGRET_ORCHID.get()));
	public static final RegistryObject<Block> POTTED_ORANGE_SPIDER_LILY = REGISTRY.register("potted_orange_spider_lily", () -> new ModFlowerPotBlock(ORANGE_SPIDER_LILY.get()));
	public static final RegistryObject<Block> POTTED_DANCING_LADIES_GINGER = REGISTRY.register("potted_dancing_ladies_ginger", () -> new ModFlowerPotBlock(DANCING_LADIES_GINGER.get()));
	public static final RegistryObject<Block> POTTED_FIREBALL_LILY = REGISTRY.register("potted_fireball_lily", () -> new ModFlowerPotBlock(FIREBALL_LILY.get()));
	public static final RegistryObject<Block> POTTED_SPATHIPHYLLUM = REGISTRY.register("potted_spathiphyllum", () -> new ModFlowerPotBlock(SPATHIPHYLLUM.get()));
	public static final RegistryObject<Block> POTTED_PURPLE_PANSY = REGISTRY.register("potted_purple_pansy", () -> new ModFlowerPotBlock(PURPLE_PANSY.get()));
	public static final RegistryObject<Block> POTTED_WHITE_PANSY = REGISTRY.register("potted_white_pansy", () -> new ModFlowerPotBlock(WHITE_PANSY.get()));
	public static final RegistryObject<Block> POTTED_YELLOW_PANSY = REGISTRY.register("potted_yellow_pansy", () -> new ModFlowerPotBlock(YELLOW_PANSY.get()));
	public static final RegistryObject<Block> POTTED_CREEPANSY = REGISTRY.register("potted_creepansy", () -> new ModFlowerPotBlock(CREEPANSY.get()));
	public static final RegistryObject<Block> POTTED_SNOWDROP = REGISTRY.register("potted_snowdrop", () -> new ModFlowerPotBlock(SNOWDROP.get()));
	public static final RegistryObject<Block> POTTED_WINTER_HEATH = REGISTRY.register("potted_winter_heath", () -> new ModFlowerPotBlock(WINTER_HEATH.get()));
	public static final RegistryObject<Block> POTTED_BLUE_DAISY = REGISTRY.register("potted_blue_daisy", () -> new ModFlowerPotBlock(BLUE_DAISY.get()));
	public static final RegistryObject<Block> POTTED_CRIMSON_CLOVER = REGISTRY.register("potted_crimson_clover", () -> new ModFlowerPotBlock(CRIMSON_CLOVER.get()));
	public static final RegistryObject<Block> POTTED_WHEEL_LILY = REGISTRY.register("potted_wheel_lily", () -> new ModFlowerPotBlock(WHEEL_LILY.get()));
	public static final RegistryObject<Block> POTTED_MAGENTA_SPOTTED_BELLFLOWER = REGISTRY.register("potted_magenta_spotted_bellflower", () -> new ModFlowerPotBlock(MAGENTA_SPOTTED_BELLFLOWER.get()));
	public static final RegistryObject<Block> POTTED_WHITE_SPOTTED_BELLFLOWER = REGISTRY.register("potted_white_spotted_bellflower", () -> new ModFlowerPotBlock(WHITE_SPOTTED_BELLFLOWER.get()));
	public static final RegistryObject<Block> POTTED_RED_SPIDER_LILY = REGISTRY.register("potted_red_spider_lily", () -> new ModFlowerPotBlock(RED_SPIDER_LILY.get()));
	public static final RegistryObject<Block> POTTED_BELLS_OF_IRELAND = REGISTRY.register("potted_bells_of_ireland", () -> new ModFlowerPotBlock(BELLS_OF_IRELAND.get()));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}