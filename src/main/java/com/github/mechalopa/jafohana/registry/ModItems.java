package com.github.mechalopa.jafohana.registry;

import java.util.function.Supplier;

import com.github.mechalopa.jafohana.JAFOhana;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems
{
	private static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Registries.ITEM, JAFOhana.MODID);

	public static final Supplier<Item> DAYFLOWER = REGISTRY.register("dayflower", () -> createBlockItem(ModBlocks.DAYFLOWER.get()));
	public static final Supplier<Item> EVENING_PRIMROSE = REGISTRY.register("evening_primrose", () -> createBlockItem(ModBlocks.EVENING_PRIMROSE.get()));
	public static final Supplier<Item> MILK_VETCH = REGISTRY.register("milk_vetch", () -> createBlockItem(ModBlocks.MILK_VETCH.get()));
	public static final Supplier<Item> FORGET_ME_NOT = REGISTRY.register("forget_me_not", () -> createBlockItem(ModBlocks.FORGET_ME_NOT.get()));
	public static final Supplier<Item> YELLOW_AFRICAN_DAISY = REGISTRY.register("yellow_african_daisy", () -> createBlockItem(ModBlocks.YELLOW_AFRICAN_DAISY.get()));
	public static final Supplier<Item> PINK_AFRICAN_DAISY = REGISTRY.register("pink_african_daisy", () -> createBlockItem(ModBlocks.PINK_AFRICAN_DAISY.get()));
	public static final Supplier<Item> WHITE_AFRICAN_DAISY = REGISTRY.register("white_african_daisy", () -> createBlockItem(ModBlocks.WHITE_AFRICAN_DAISY.get()));
	public static final Supplier<Item> BLACK_TULIP = REGISTRY.register("black_tulip", () -> createBlockItem(ModBlocks.BLACK_TULIP.get()));
	public static final Supplier<Item> GREEN_TULIP = REGISTRY.register("green_tulip", () -> createBlockItem(ModBlocks.GREEN_TULIP.get()));
	public static final Supplier<Item> BLOOD_RED_IRIS = REGISTRY.register("blood_red_iris", () -> createBlockItem(ModBlocks.BLOOD_RED_IRIS.get()));
	public static final Supplier<Item> RED_SNAPDRAGON = REGISTRY.register("red_snapdragon", () -> createBlockItem(ModBlocks.RED_SNAPDRAGON.get()));
	public static final Supplier<Item> YELLOW_SNAPDRAGON = REGISTRY.register("yellow_snapdragon", () -> createBlockItem(ModBlocks.YELLOW_SNAPDRAGON.get()));
	public static final Supplier<Item> PINK_SNAPDRAGON = REGISTRY.register("pink_snapdragon", () -> createBlockItem(ModBlocks.PINK_SNAPDRAGON.get()));
	public static final Supplier<Item> WHITE_EGRET_ORCHID = REGISTRY.register("white_egret_orchid", () -> createBlockItem(ModBlocks.WHITE_EGRET_ORCHID.get()));
	public static final Supplier<Item> ORANGE_SPIDER_LILY = REGISTRY.register("orange_spider_lily", () -> createBlockItem(ModBlocks.ORANGE_SPIDER_LILY.get()));
	public static final Supplier<Item> PEPPERMINT_SPIDER_LILY = REGISTRY.register("peppermint_spider_lily", () -> createBlockItem(ModBlocks.PEPPERMINT_SPIDER_LILY.get()));
	public static final Supplier<Item> DANCING_LADIES_GINGER = REGISTRY.register("dancing_ladies_ginger", () -> createBlockItem(ModBlocks.DANCING_LADIES_GINGER.get()));
	public static final Supplier<Item> FIREBALL_LILY = REGISTRY.register("fireball_lily", () -> createBlockItem(ModBlocks.FIREBALL_LILY.get()));
	public static final Supplier<Item> SPATHIPHYLLUM = REGISTRY.register("spathiphyllum", () -> createBlockItem(ModBlocks.SPATHIPHYLLUM.get()));
	public static final Supplier<Item> PURPLE_PANSY = REGISTRY.register("purple_pansy", () -> createBlockItem(ModBlocks.PURPLE_PANSY.get()));
	public static final Supplier<Item> WHITE_PANSY = REGISTRY.register("white_pansy", () -> createBlockItem(ModBlocks.WHITE_PANSY.get()));
	public static final Supplier<Item> YELLOW_PANSY = REGISTRY.register("yellow_pansy", () -> createBlockItem(ModBlocks.YELLOW_PANSY.get()));
	public static final Supplier<Item> CREEPANSY = REGISTRY.register("creepansy", () -> createBlockItem(ModBlocks.CREEPANSY.get()));
	public static final Supplier<Item> SNOWDROP = REGISTRY.register("snowdrop", () -> createBlockItem(ModBlocks.SNOWDROP.get()));
	public static final Supplier<Item> WINTER_HEATH = REGISTRY.register("winter_heath", () -> createBlockItem(ModBlocks.WINTER_HEATH.get()));
	public static final Supplier<Item> BLUE_DAISY = REGISTRY.register("blue_daisy", () -> createBlockItem(ModBlocks.BLUE_DAISY.get()));
	public static final Supplier<Item> CRIMSON_CLOVER = REGISTRY.register("crimson_clover", () -> createBlockItem(ModBlocks.CRIMSON_CLOVER.get()));
	public static final Supplier<Item> FOX_AND_CUBS = REGISTRY.register("fox_and_cubs", () -> createBlockItem(ModBlocks.FOX_AND_CUBS.get()));
	public static final Supplier<Item> PINK_HAWKS_BEARD = REGISTRY.register("pink_hawks_beard", () -> createBlockItem(ModBlocks.PINK_HAWKS_BEARD.get()));
	public static final Supplier<Item> LADIES_TRESSES = REGISTRY.register("ladies_tresses", () -> createBlockItem(ModBlocks.LADIES_TRESSES.get()));
	public static final Supplier<Item> DESERT_PEA = REGISTRY.register("desert_pea", () -> createBlockItem(ModBlocks.DESERT_PEA.get()));
	public static final Supplier<Item> APRICOT_MALLOW = REGISTRY.register("apricot_mallow", () -> createBlockItem(ModBlocks.APRICOT_MALLOW.get()));
	public static final Supplier<Item> BLUESTEM_PENSTEMON = REGISTRY.register("bluestem_penstemon", () -> createBlockItem(ModBlocks.BLUESTEM_PENSTEMON.get()));
	public static final Supplier<Item> FIRECRACKER_PENSTEMON = REGISTRY.register("firecracker_penstemon", () -> createBlockItem(ModBlocks.FIRECRACKER_PENSTEMON.get()));
	public static final Supplier<Item> SEGO_LILY = REGISTRY.register("sego_lily", () -> createBlockItem(ModBlocks.SEGO_LILY.get()));
	public static final Supplier<Item> SEA_HOLLY = REGISTRY.register("sea_holly", () -> createBlockItem(ModBlocks.SEA_HOLLY.get()));
	public static final Supplier<Item> SEA_THRIFT = REGISTRY.register("sea_thrift", () -> createBlockItem(ModBlocks.SEA_THRIFT.get()));
	public static final Supplier<Item> HAREBELL = REGISTRY.register("harebell", () -> createBlockItem(ModBlocks.HAREBELL.get()));
	public static final Supplier<Item> MECONOPSIS = REGISTRY.register("meconopsis", () -> createBlockItem(ModBlocks.MECONOPSIS.get()));
	public static final Supplier<Item> WHEEL_LILY = REGISTRY.register("wheel_lily", () -> createBlockItem(ModBlocks.WHEEL_LILY.get()));
	public static final Supplier<Item> JACK_IN_THE_PULPIT = REGISTRY.register("jack_in_the_pulpit", () -> createBlockItem(ModBlocks.JACK_IN_THE_PULPIT.get()));
	public static final Supplier<Item> RED_SPIDER_LILY = REGISTRY.register("red_spider_lily", () -> createBlockItem(ModBlocks.RED_SPIDER_LILY.get()));
	public static final Supplier<Item> BELLS_OF_IRELAND = REGISTRY.register("bells_of_ireland", () -> createBlockItem(ModBlocks.BELLS_OF_IRELAND.get()));
	public static final Supplier<Item> BEACH_SPIDER_LILY = REGISTRY.register("beach_spider_lily", () -> createBlockItem(ModBlocks.BEACH_SPIDER_LILY.get()));
	public static final Supplier<Item> ORANGE_MARIGOLD = REGISTRY.register("orange_marigold", () -> createDoubleHighBlockItem(ModBlocks.ORANGE_MARIGOLD.get()));
	public static final Supplier<Item> YELLOW_MARIGOLD = REGISTRY.register("yellow_marigold", () -> createDoubleHighBlockItem(ModBlocks.YELLOW_MARIGOLD.get()));
	public static final Supplier<Item> GLORIOSA = REGISTRY.register("gloriosa", () -> createDoubleHighBlockItem(ModBlocks.GLORIOSA.get()));
	public static final Supplier<Item> BLACK_BAT_FLOWER = REGISTRY.register("black_bat_flower", () -> createDoubleHighBlockItem(ModBlocks.BLACK_BAT_FLOWER.get()));
	public static final Supplier<Item> BIRD_OF_PARADISE = REGISTRY.register("bird_of_paradise", () -> createDoubleHighBlockItem(ModBlocks.BIRD_OF_PARADISE.get()));
	public static final Supplier<Item> BRAZILIAN_PLUME = REGISTRY.register("brazilian_plume", () -> createDoubleHighBlockItem(ModBlocks.BRAZILIAN_PLUME.get()));
	public static final Supplier<Item> WORSLEYA = REGISTRY.register("worsleya", () -> createDoubleHighBlockItem(ModBlocks.WORSLEYA.get()));
	public static final Supplier<Item> OAKLEAF_HYDRANGEA = REGISTRY.register("oakleaf_hydrangea", () -> createDoubleHighBlockItem(ModBlocks.OAKLEAF_HYDRANGEA.get()));
	public static final Supplier<Item> BLUE_CEANOTHUS = REGISTRY.register("blue_ceanothus", () -> createDoubleHighBlockItem(ModBlocks.BLUE_CEANOTHUS.get()));
	public static final Supplier<Item> PURPLE_CEANOTHUS = REGISTRY.register("purple_ceanothus", () -> createDoubleHighBlockItem(ModBlocks.PURPLE_CEANOTHUS.get()));
	public static final Supplier<Item> ASPHODEL = REGISTRY.register("asphodel", () -> createDoubleHighBlockItem(ModBlocks.ASPHODEL.get()));
	public static final Supplier<Item> FIREWEED = REGISTRY.register("fireweed", () -> createDoubleHighBlockItem(ModBlocks.FIREWEED.get()));
	public static final Supplier<Item> ALPINE_RAGWORT = REGISTRY.register("alpine_ragwort", () -> createDoubleHighBlockItem(ModBlocks.ALPINE_RAGWORT.get()));
	public static final Supplier<Item> TATARIAN_ASTER = REGISTRY.register("tatarian_aster", () -> createDoubleHighBlockItem(ModBlocks.TATARIAN_ASTER.get()));
	public static final Supplier<Item> CARDINAL_FLOWER = REGISTRY.register("cardinal_flower", () -> createDoubleHighBlockItem(ModBlocks.CARDINAL_FLOWER.get()));
	public static final Supplier<Item> YELLOWFLAG_IRIS = REGISTRY.register("yellowflag_iris", () -> createDoubleHighBlockItem(ModBlocks.YELLOWFLAG_IRIS.get()));
	public static final Supplier<Item> PRINCES_PLUME = REGISTRY.register("princes_plume", () -> createDoubleHighBlockItem(ModBlocks.PRINCES_PLUME.get()));
	public static final Supplier<Item> FASCIATED_DANDELION = REGISTRY.register("fasciated_dandelion", () -> createDoubleHighBlockItem(ModBlocks.FASCIATED_DANDELION.get()));
	public static final Supplier<Item> FASCIATED_OXEYE_DAISY = REGISTRY.register("fasciated_oxeye_daisy", () -> createDoubleHighBlockItem(ModBlocks.FASCIATED_OXEYE_DAISY.get()));
	public static final Supplier<Item> WITHER_ROSE_BUSH = REGISTRY.register("wither_rose_bush", () -> createDoubleHighBlockItem(ModBlocks.WITHER_ROSE_BUSH.get(), Rarity.UNCOMMON));
	public static final Supplier<Item> JADE_VINE = REGISTRY.register("jade_vine", () -> createBlockItem(ModBlocks.JADE_VINE.get()));
	public static final Supplier<Item> MYSORE_TRUMPETVINE = REGISTRY.register("mysore_trumpetvine", () -> createBlockItem(ModBlocks.MYSORE_TRUMPETVINE.get()));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}

	private static BlockItem createBlockItem(Block block)
	{
		return new BlockItem(block, new Item.Properties());
	}

	private static DoubleHighBlockItem createDoubleHighBlockItem(Block block)
	{
		return new DoubleHighBlockItem(block, new Item.Properties());
	}

	private static DoubleHighBlockItem createDoubleHighBlockItem(Block block, Rarity rarity)
	{
		return new DoubleHighBlockItem(block, new Item.Properties().rarity(rarity));
	}

	public static DeferredRegister<Item> getItemRegistry()
	{
		return REGISTRY;
	}
}