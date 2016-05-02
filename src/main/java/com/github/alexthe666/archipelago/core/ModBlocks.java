package com.github.alexthe666.archipelago.core;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.github.alexthe666.archipelago.block.BlockShortPlant;
import com.github.alexthe666.archipelago.block.BlockTallPlant;
import com.google.common.collect.Lists;

public class ModBlocks {

	public List<String> list = Lists.newArrayList();
	public static Block anthurium_andraeanum;
	public static Block bottle_palm;
	public static Block ceratozamia_mexicana;
	public static Block jambu;
	public static Block canary_island_foxglove;

	public static void init(){
		anthurium_andraeanum = new BlockShortPlant("anthurium_andraeanum");
		bottle_palm = new BlockTallPlant("bottle_palm");
		ceratozamia_mexicana = new BlockTallPlant("ceratozamia_mexicana");
		jambu = new BlockShortPlant("jambu");
		canary_island_foxglove = new BlockTallPlant("canary_island_foxglove");
	}
}
